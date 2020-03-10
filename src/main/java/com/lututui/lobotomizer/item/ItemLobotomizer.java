package com.lututui.lobotomizer.item;

import com.lututui.lobotomizer.LobotomizerMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemLobotomizer extends Item {
    public ItemLobotomizer() {
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setRegistryName(LobotomizerMod.MODID, "lobotomizer");
        this.setUnlocalizedName(this.getRegistryName().toString());
        this.setMaxStackSize(1);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!(entity instanceof EntityLiving)) {
            return super.onLeftClickEntity(stack, player, entity);
        }

        EntityLiving livingEntity = (EntityLiving) entity;

        livingEntity.setNoAI(!livingEntity.isAIDisabled());

        return true;
    }
}
