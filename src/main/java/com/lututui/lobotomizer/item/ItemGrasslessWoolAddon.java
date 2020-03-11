package com.lututui.lobotomizer.item;

import com.lututui.lobotomizer.LobotomizerMod;
import com.lututui.lobotomizer.world.SavedData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;

public class ItemGrasslessWoolAddon extends ItemAddonBase {
    public static final String REGISTRY_NAME = "grassless_wool_addon";

    public ItemGrasslessWoolAddon() {
        super(REGISTRY_NAME, EntitySheep.class);
    }

    @Override
    public boolean apply(EntityLivingBase entityLivingBase) {
        final EntitySheep sheep = (EntitySheep) entityLivingBase;
        final SavedData savedData = SavedData.get(sheep.world);

        assert savedData != null;
        if (savedData.contains(sheep)) {
            return false;
        }

        savedData.add(sheep);
        LobotomizerMod.logger.info("Added sheep with UUID " + sheep.getUniqueID() + " to upgraded set");

        return true;
    }
}
