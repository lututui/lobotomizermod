package com.lututui.lobotomizer.item;

import com.lututui.lobotomizer.LobotomizerMod;
import com.lututui.lobotomizer.world.SavedData;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class ItemGrasslessWoolAddon extends Item {
    public ItemGrasslessWoolAddon() {
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setRegistryName(LobotomizerMod.MODID, "grassless_wool_addon");
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target.world.isRemote) {
            return false;
        }

        if (!(target instanceof EntitySheep)) {
            return false;
        }

        final EntitySheep sheep = (EntitySheep) target;
        final SavedData savedData = SavedData.get(sheep.world);

        assert savedData != null;
        if (savedData.contains(sheep)) {
            return false;
        }

        savedData.add(sheep);
        LobotomizerMod.logger.info("Added sheep with UUID " + sheep.getUniqueID() + " to upgraded set");
        stack.shrink(1);

        return true;
    }
}
