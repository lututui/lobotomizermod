package com.lututui.lobotomizer.item;

import com.lututui.lobotomizer.LobotomizerMod;
import com.lututui.lobotomizer.world.SavedData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class ItemGrasslessWoolAddon extends ItemBase {
    public static final String REGISTRY_NAME = "grassless_wool_addon";

    public ItemGrasslessWoolAddon() {
        super(REGISTRY_NAME);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target instanceof EntitySheep) {
            final EntitySheep sheep = (EntitySheep) target;

            if (!target.world.isRemote) {
                final SavedData savedData = SavedData.get(sheep.world);

                assert savedData != null;
                if (savedData.contains(sheep)) {
                    return false;
                }

                savedData.add(sheep);
                LobotomizerMod.logger.info("Added sheep with UUID " + sheep.getUniqueID() + " to upgraded set");
            }

            stack.shrink(1);

            return true;
        }

        return false;
    }
}
