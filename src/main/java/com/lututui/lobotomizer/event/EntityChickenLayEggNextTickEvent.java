package com.lututui.lobotomizer.event;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraftforge.event.entity.EntityEvent;

public class EntityChickenLayEggNextTickEvent extends EntityEvent {
    public EntityChickenLayEggNextTickEvent(EntityChicken chicken) {
        super(chicken);
    }

    @Override
    public EntityChicken getEntity() {
        return (EntityChicken) super.getEntity();
    }
}
