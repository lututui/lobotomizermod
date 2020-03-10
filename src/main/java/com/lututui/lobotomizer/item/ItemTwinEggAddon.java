package com.lututui.lobotomizer.item;

import com.lututui.lobotomizer.LobotomizerMod;
import com.lututui.lobotomizer.world.SavedData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class ItemTwinEggAddon extends ItemBase {
    public static final String REGISTRY_NAME = "twin_egg_addon";

    public ItemTwinEggAddon() {
        super(REGISTRY_NAME);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (!target.world.isRemote && target instanceof EntityChicken) {
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

        return false;
    }
}
