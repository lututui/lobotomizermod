package com.lututui.lobotomizer.item;

import com.lututui.lobotomizer.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class ItemAddonBase extends ItemBase {
    private Class<?> targetEntityClass;

    public ItemAddonBase(String registryName, Class<?> targetEntityClass) {
        super(registryName);
        this.targetEntityClass = targetEntityClass;
    }

    private void onSuccessfulApply(World world, Entity target, ItemStack stack) {
        Util.playSuccessSound(world, target, stack);
        stack.shrink(1);
    }

    public final void alreadyUpgradedWithAddon(EntityPlayer player) {
        Util.sendPlayerStatusMessage(player, "other.lobotomizer.addonMessage.alreadyUpgraded");
    }

    public final void wrongEntityForAddon(EntityPlayer player) {
        Util.sendPlayerStatusMessage(player, "other.lobotomizer.addonMessage.wrongEntity");
    }

    public abstract boolean apply(EntityLivingBase entityLivingBase, EntityPlayer player);

    @Override
    public final boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (this.targetEntityClass.isInstance(target)) {
            if (!target.world.isRemote && this.apply(target, playerIn)) {
                this.onSuccessfulApply(playerIn.world, target, stack);
                return true;
            }
        } else {
            this.wrongEntityForAddon(playerIn);
        }

        return false;
    }
}
