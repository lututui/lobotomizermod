package com.lututui.lobotomizer.event;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EntityLivingDeathEvent extends LivingDeathEvent {
    public EntityLivingDeathEvent(EntityLiving entity, DamageSource source) {
        super(entity, source);
    }

    @Override
    public EntityLiving getEntity() {
        return (EntityLiving) super.getEntity();
    }
}
