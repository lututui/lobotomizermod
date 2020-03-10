package com.lututui.lobotomizer.event;

import com.lututui.lobotomizer.LobotomizerMod;
import com.lututui.lobotomizer.init.Items;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = LobotomizerMod.MODID)
public class ModelRegistryEventHandler {
    @SubscribeEvent
    public static void onModelRegistryEvent(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(Items.LOBOTOMIZER, 0, new ModelResourceLocation(Items.LOBOTOMIZER.getRegistryName().toString(), "inventory"));
    }
}
