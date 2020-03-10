package com.lututui.lobotomizer.init;

import com.lututui.lobotomizer.LobotomizerMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Tabs {
    public static final CreativeTabs MAIN_TAB = new CreativeTabs(LobotomizerMod.NAME) {
        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.LOBOTOMIZER);
        }
    };
}
