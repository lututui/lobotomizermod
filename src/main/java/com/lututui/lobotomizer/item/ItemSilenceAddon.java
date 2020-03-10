package com.lututui.lobotomizer.item;

import com.lututui.lobotomizer.LobotomizerMod;
import com.lututui.lobotomizer.world.SavedData;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class ItemSilenceAddon extends Item {
    public ItemSilenceAddon() {
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setRegistryName(LobotomizerMod.MODID, "silence_addon");
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target.world.isRemote) {
            return false;
        }

        if (!(target instanceof EntityLiving)) {
            return false;
        }

        final EntityLiving entityLiving = (EntityLiving) target;
        final SavedData savedData = SavedData.get(target.world);

        assert savedData != null;
        if (savedData.isSilenced(entityLiving)) {
            return false;
        }

        savedData.silence(entityLiving);
        entityLiving.setSilent(true);
        LobotomizerMod.logger.info("Silenced entity with UUID " + entityLiving.getUniqueID());
        stack.shrink(1);

        return true;
    }
}
