package com.lututui.lobotomizer.eventhandler;

import com.lututui.lobotomizer.LobotomizerMod;
import com.lututui.lobotomizer.Util;
import com.lututui.lobotomizer.event.EntityChickenDeathEvent;
import com.lututui.lobotomizer.event.EntityChickenLayEggNextTickEvent;
import com.lututui.lobotomizer.event.EntityLivingDeathEvent;
import com.lututui.lobotomizer.event.EntitySheepDeathEvent;
import com.lututui.lobotomizer.event.EntitySheepMayEatGrassEvent;
import com.lututui.lobotomizer.world.SavedData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = LobotomizerMod.MODID)
public class LivingUpdateEventHandler {
    @SubscribeEvent
    public static void onLivingEntityDeath(LivingDeathEvent event) {
        if (event instanceof EntitySheepDeathEvent ||
                event instanceof EntityChickenDeathEvent ||
                event instanceof EntityLivingDeathEvent) {
            return;
        }

        final Entity entity = event.getEntity();

        if (entity instanceof EntityChicken) {
            MinecraftForge.EVENT_BUS.post(new EntityChickenDeathEvent((EntityChicken) entity, event.getSource()));
        } else if (entity instanceof EntitySheep) {
            MinecraftForge.EVENT_BUS.post(new EntitySheepDeathEvent((EntitySheep) entity, event.getSource()));
        }

        if (entity instanceof EntityLiving) {
            MinecraftForge.EVENT_BUS.post(new EntityLivingDeathEvent((EntityLiving) entity, event.getSource()));
        }

    }

    @SubscribeEvent
    public static void onEntityLivingDeath(EntityLivingDeathEvent event) {
        final EntityLiving entityLiving = event.getEntity();

        if (entityLiving.world.isRemote) {
            return;
        }

        final SavedData savedData = SavedData.get(entityLiving.world);
        assert savedData != null;

        LobotomizerMod.logger.debug("Living entity died " + entityLiving.getUniqueID());

        if (savedData.isSilenced(entityLiving)) {
            savedData.unsilence(entityLiving);
        }
    }

    @SubscribeEvent
    public static void onChickenDeath(EntityChickenDeathEvent event) {
        final EntityChicken chicken = event.getEntity();

        if (chicken.world.isRemote) {
            return;
        }

        final SavedData savedData = SavedData.get(chicken.world);
        assert savedData != null;

        LobotomizerMod.logger.debug("Chicken died " + chicken.getUniqueID());

        if (savedData.contains(chicken)) {
            savedData.remove(chicken);
        }
    }

    @SubscribeEvent
    public static void onSheepDeath(EntitySheepDeathEvent event) {
        final EntitySheep sheep = event.getEntity();

        if (sheep.world.isRemote) {
            return;
        }

        final SavedData savedData = SavedData.get(sheep.world);
        assert savedData != null;

        LobotomizerMod.logger.debug("Sheep died " + sheep.getUniqueID());

        if (savedData.contains(sheep)) {
            savedData.remove(sheep);
        }
    }

    @SubscribeEvent
    public static void onLivingEntityUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        final Entity entity = event.getEntity();

        if (entity.world.isRemote) {
            return;
        }

        if (entity instanceof EntityChicken) {
            final EntityChicken chicken = (EntityChicken) entity;

            if (!chicken.isChild() && !chicken.isChickenJockey() && chicken.timeUntilNextEgg - 1 <= 0) {
                final SavedData savedData = SavedData.get(entity.world);
                assert savedData != null;

                if (savedData.contains(chicken)) {
                    MinecraftForge.EVENT_BUS.post(new EntityChickenLayEggNextTickEvent(chicken));
                }
            }
        } else if (entity instanceof EntitySheep) {
            final EntitySheep sheep = (EntitySheep) entity;
            final SavedData savedData = SavedData.get(entity.world);
            assert savedData != null;

            if (!savedData.contains(sheep)) {
                return;
            }

            if (sheep.getGrowingAge() < 0 && Util.nextInt(50) == 0 ||
                    sheep.getGrowingAge() >= 0 && Util.nextInt(1000) == 0) {
                MinecraftForge.EVENT_BUS.post(new EntitySheepMayEatGrassEvent(sheep));
            }
        }
    }

    @SubscribeEvent
    public static void onChickenLayEggEvent(EntityChickenLayEggNextTickEvent event) {
        final EntityChicken chicken = event.getEntity();

        if (chicken.world.isRemote) {
            return;
        }

        chicken.playSound(
                SoundEvents.ENTITY_CHICKEN_EGG,
                1.0F,
                (Util.nextFloat() - Util.nextFloat()) * 0.2F + 1.0F
        );
        chicken.dropItem(Items.EGG, 1);
    }

    @SubscribeEvent
    public static void onSheepMayEatGrassEvent(EntitySheepMayEatGrassEvent event) {
        final EntitySheep sheep = event.getEntity();

        if (sheep.world.isRemote) {
            return;
        }

        sheep.eatGrassBonus();
    }
}
