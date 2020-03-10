package com.lututui.lobotomizer.item;

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
            ((EntityLiving) entity).setNoAI(true);

            return true;
        }

        return super.onLeftClickEntity(stack, player, entity);
    }
}
