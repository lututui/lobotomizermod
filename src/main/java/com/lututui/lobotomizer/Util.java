package com.lututui.lobotomizer;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class Util {
    private static Random rand = new Random();

    public static NBTTagCompound getTagCompoundSafe(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        return stack.getTagCompound();
    }

    public static Integer nextInt(int bound) {
        return rand.nextInt(bound);
    }

    public static Float nextFloat() {
        return rand.nextFloat();
    }

    public static void sendPlayerStatusMessage(EntityPlayer player, String translationKey) {
        player.sendStatusMessage(
                new TextComponentTranslation(translationKey),
                true
        );
    }

    public static void playSuccessSound(World world, Entity target, ItemStack stack) {
        world.playSound(
                null,
                target.getPosition(),
                SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
                SoundCategory.MASTER,
                0.8F, 0.9F
        );
    }
}
