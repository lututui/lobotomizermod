package com.lututui.lobotomizer;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

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
    public static Float nextFloat() { return rand.nextFloat(); }
}
