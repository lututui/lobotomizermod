package com.lututui.lobotomizer.eventhandler;

import com.lututui.lobotomizer.LobotomizerMod;
import com.lututui.lobotomizer.init.Items;
import com.lututui.lobotomizer.item.ItemBase;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = LobotomizerMod.MODID)
public class ModelRegistryEventHandler {
    @SubscribeEvent
    public static void onModelRegistryEvent(ModelRegistryEvent event) {
        registerAll(
                Items.LOBOTOMIZER, Items.RANGED_LOBOTOMIZER,
                Items.TWIN_EGG_ADDON, Items.SILENCE_ADDON, Items.GRASSLESS_WOOL_ADDON
        );
    }

    private static void registerItems(ItemBase item) {
        ModelLoader.setCustomModelResourceLocation(
                item,
                0,
                new ModelResourceLocation(item.getRegistryName().toString(), "inventory")
        );
    }

    private static void registerAll(ItemBase... itemList) {
        for (final ItemBase item : itemList) {
            ModelRegistryEventHandler.registerItems(item);
        }
    }
}
