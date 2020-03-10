package com.lututui.lobotomizer.item;

import com.lututui.lobotomizer.LobotomizerMod;
import com.lututui.lobotomizer.world.SavedData;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class ItemTwinEggAddon extends Item {
    public ItemTwinEggAddon() {
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setRegistryName(LobotomizerMod.MODID, "twin_egg_addon");
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target.world.isRemote) {
            return false;
        }

        if (!(target instanceof EntityChicken)) {
            return false;
        }

        final EntityChicken chicken = (EntityChicken) target;
        final SavedData savedData = SavedData.get(target.world);

        assert savedData != null;
        if (savedData.contains(chicken)) {
            return false;
        }

        savedData.add(chicken);
        LobotomizerMod.logger.info("Added chicken with UUID " + chicken.getUniqueID() + " to upgraded set");
        stack.shrink(1);

        return true;
    }
}
