package com.lututui.lobotomizer.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public abstract class ItemAddonBase extends ItemBase {
    private Class<?> targetEntityClass;

    public ItemAddonBase(String registryName, Class<?> targetEntityClass) {
        super(registryName);
        this.targetEntityClass = targetEntityClass;
    }

    private void onSuccessfulApply(World world, Entity target, ItemStack stack) {
        world.playSound(
                null,
                target.getPosition(),
                SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
                SoundCategory.MASTER,
                0.8F, 0.9F
        );
        stack.shrink(1);
    }

    public abstract boolean apply(EntityLivingBase entityLivingBase);

    @Override
    public final boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (this.targetEntityClass.isInstance(target) && !target.world.isRemote && this.apply(target)) {
            this.onSuccessfulApply(playerIn.world, target, stack);
            return true;
        }

        return false;
    }
}
