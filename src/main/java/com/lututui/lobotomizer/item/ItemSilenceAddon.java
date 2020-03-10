package com.lututui.lobotomizer.item;

import com.lututui.lobotomizer.LobotomizerMod;
import com.lututui.lobotomizer.world.SavedData;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class ItemSilenceAddon extends ItemBase {
    public static final String REGISTRY_NAME = "silence_addon";

    public ItemSilenceAddon() {
        super(REGISTRY_NAME);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target instanceof EntityLiving) {
            final EntityLiving entityLiving = (EntityLiving) target;

            if (!target.world.isRemote) {
                final SavedData savedData = SavedData.get(target.world);

                assert savedData != null;
                if (savedData.isSilenced(entityLiving)) {
                    return false;
                }

                savedData.silence(entityLiving);
                entityLiving.setSilent(true);
                LobotomizerMod.logger.info("Silenced entity with UUID " + entityLiving.getUniqueID());
            }

            stack.shrink(1);

            return true;
        }

        return false;
    }
}
