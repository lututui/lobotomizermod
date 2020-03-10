package com.lututui.lobotomizer.event;

import net.minecraft.entity.passive.EntitySheep;
import net.minecraftforge.event.entity.EntityEvent;

public class EntitySheepMayEatGrassEvent extends EntityEvent {
    public EntitySheepMayEatGrassEvent(EntitySheep sheep) {
        super(sheep);
    }

    @Override
    public EntitySheep getEntity() {
        return (EntitySheep) super.getEntity();
    }
}
