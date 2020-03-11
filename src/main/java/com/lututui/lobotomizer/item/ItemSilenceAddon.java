package com.lututui.lobotomizer.item;

import com.lututui.lobotomizer.LobotomizerMod;
import com.lututui.lobotomizer.world.SavedData;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;

public class ItemSilenceAddon extends ItemAddonBase {
    public static final String REGISTRY_NAME = "silence_addon";

    public ItemSilenceAddon() {
        super(REGISTRY_NAME, EntityLiving.class);
    }

    @Override
    public boolean apply(EntityLivingBase entityLivingBase) {
        final EntityLiving entityLiving = (EntityLiving) entityLivingBase;
        final SavedData savedData = SavedData.get(entityLiving.world);

        assert savedData != null;
        if (savedData.isSilenced(entityLiving)) {
            return false;
        }

        savedData.silence(entityLiving);
        entityLiving.setSilent(true);
        LobotomizerMod.logger.info("Silenced entity with UUID " + entityLiving.getUniqueID());

        return true;
    }
}
