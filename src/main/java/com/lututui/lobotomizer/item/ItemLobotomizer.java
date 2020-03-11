package com.lututui.lobotomizer.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;

public class ItemLobotomizer extends ItemBase {
    public static final String REGISTRY_NAME = "lobotomizer";

    public ItemLobotomizer() {
        super(REGISTRY_NAME, 1);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (entity instanceof EntityLiving) {
            final EntityLiving entityLiving = ((EntityLiving) entity);

            if (entityLiving.isAIDisabled()) {
                player.sendStatusMessage(
                        new TextComponentTranslation("other.lobotomizer.toolMessage.alreadyLobotomized"),
                        true
                );
            } else {
                entityLiving.setNoAI(true);
                entity.world.playSound(
                        null,
                        entity.getPosition(),
                        SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
                        SoundCategory.MASTER,
                        0.8F, 0.9F
                );
            }
        } else {
            player.sendStatusMessage(
                    new TextComponentTranslation("other.lobotomizer.toolMessage.invalidEntity"),
                    true
            );
        }

        return true;
    }
}
