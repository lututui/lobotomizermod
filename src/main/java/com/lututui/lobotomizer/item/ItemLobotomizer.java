package com.lututui.lobotomizer.item;

import com.lututui.lobotomizer.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

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
                Util.sendPlayerStatusMessage(player, "other.lobotomizer.toolMessage.alreadyLobotomized");
            } else {
                entityLiving.setNoAI(true);
                Util.playSuccessSound(entity.world, player, stack);
            }
        } else {
            Util.sendPlayerStatusMessage(player, "other.lobotomizer.toolMessage.invalidEntity");
        }

        return true;
    }
}
