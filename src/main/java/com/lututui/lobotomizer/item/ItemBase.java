package com.lututui.lobotomizer.item;

import com.lututui.lobotomizer.LobotomizerMod;
import com.lututui.lobotomizer.init.Tabs;
import net.minecraft.item.Item;

public abstract class ItemBase extends Item {
    public ItemBase(final String registryName) {
        this.setCreativeTab(Tabs.MAIN_TAB);
        this.setRegistryName(LobotomizerMod.MODID, registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    public ItemBase(final String registryName, final int maxStack) {
        this(registryName);
        this.setMaxStackSize(maxStack);
    }
}
