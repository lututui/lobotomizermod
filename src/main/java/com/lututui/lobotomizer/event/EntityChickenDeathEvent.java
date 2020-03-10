package com.lututui.lobotomizer.event;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EntityChickenDeathEvent extends LivingDeathEvent {
    public EntityChickenDeathEvent(EntityChicken chicken, DamageSource source) {
        super(chicken, source);
    }

    @Override
    public EntityChicken getEntity() {
        return (EntityChicken) super.getEntity();
    }
}
