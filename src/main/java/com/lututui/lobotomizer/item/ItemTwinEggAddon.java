package com.lututui.lobotomizer.item;

import com.lututui.lobotomizer.LobotomizerMod;
import com.lututui.lobotomizer.world.SavedData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;

public class ItemTwinEggAddon extends ItemAddonBase {
    public static final String REGISTRY_NAME = "twin_egg_addon";

    public ItemTwinEggAddon() {
        super(REGISTRY_NAME, EntityChicken.class);
    }

    @Override
    public boolean apply(EntityLivingBase entityLivingBase) {
        final EntityChicken chicken = (EntityChicken) entityLivingBase;
        final SavedData savedData = SavedData.get(chicken.world);

        assert savedData != null;
        if (savedData.contains(chicken)) {
            return false;
        }

        savedData.add(chicken);
        LobotomizerMod.logger.info("Added chicken with UUID " + chicken.getUniqueID() + " to upgraded set");

        return true;
    }
}
