package com.lututui.lobotomizer.event;

import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EntitySheepDeathEvent extends LivingDeathEvent {
    public EntitySheepDeathEvent(EntitySheep sheep, DamageSource source) {
        super(sheep, source);
    }

    @Override
    public EntitySheep getEntity() {
        return (EntitySheep) super.getEntity();
    }
}
