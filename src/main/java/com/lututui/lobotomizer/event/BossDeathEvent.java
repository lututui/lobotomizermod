package com.lututui.lobotomizer.event;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class BossDeathEvent extends LivingDeathEvent {
    public BossDeathEvent(EntityLivingBase entity, DamageSource source) {
        super(entity, source);
    }
}
