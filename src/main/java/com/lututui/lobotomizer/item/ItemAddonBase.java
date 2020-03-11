package com.lututui.lobotomizer.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
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

    public final void alreadyUpgradedWithAddon(EntityPlayer player) {
        player.sendStatusMessage(
                new TextComponentTranslation("other.lobotomizer.addonMessage.alreadyUpgraded"),
                true
        );
    }

    public final void wrongEntityForAddon(EntityPlayer player) {
        player.sendStatusMessage(
                new TextComponentTranslation("other.lobotomizer.addonMessage.wrongEntity"),
                true
        );
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
