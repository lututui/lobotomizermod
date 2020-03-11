package com.lututui.lobotomizer.item;

import java.util.List;

import com.lututui.lobotomizer.LobotomizerMod;
import com.lututui.lobotomizer.Util;
import com.lututui.lobotomizer.init.Items;
import com.lututui.lobotomizer.init.Packets;
import com.lututui.lobotomizer.network.ClearRangedLobotomizerMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = LobotomizerMod.MODID)
public class ItemRangedLobotomizer extends ItemBase {
    public static final String REGISTRY_NAME = "ranged_lobotomizer";

    public ItemRangedLobotomizer() {
        super(REGISTRY_NAME, 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        final ItemStack stack = playerIn.getHeldItem(handIn);

        if (playerIn.isSneaking()) {
            stack.setTagCompound(null);

            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }

        if (stack.getTagCompound() == null || !stack.getTagCompound().hasKey("target")) {
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        if (!worldIn.isRemote) {
            final String targetEntity = Util.getTagCompoundSafe(stack).getString("target");

            final List<EntityLiving> entitiesInRange = worldIn.getEntitiesWithinAABB(
                    EntityLiving.class,
                    playerIn.getEntityBoundingBox().grow(5),
                    entityLiving -> !entityLiving.isAIDisabled() && entityLiving.getClass().getName().equals(targetEntity)
            );

            for (final EntityLiving e : entitiesInRange) {
                LobotomizerMod.logger.info("Entity in range: " + e.getClass().getName());

                e.setNoAI(true);
                e.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 100));
            }
        }

        return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (entity instanceof EntityLiving) {
            final String newTarget = entity.getClass().getName();
            final String oldTarget = Util.getTagCompoundSafe(stack).getString("target");

            if (oldTarget.equals(newTarget)) {
                player.sendStatusMessage(
                        new TextComponentTranslation("other.lobotomizer.toolMessage.targetAlreadySet"),
                        true
                );
            } else {
                stack.setTagInfo("target", new NBTTagString(newTarget));
                player.sendStatusMessage(
                        new TextComponentTranslation("other.lobotomizer.toolMessage.setTarget"),
                        true
                );
            }
        }

        return true;
    }

    @SubscribeEvent
    public static void onLeftClickEmptyAir(PlayerInteractEvent.LeftClickEmpty event) {
        if (
                event.getEntityPlayer().isSneaking() &&
                event.getItemStack().getItem().equals(Items.RANGED_LOBOTOMIZER)
        ) {
            Packets.NETWORK_CHANNEL.sendToServer(new ClearRangedLobotomizerMessage());
        }
    }
}
