package com.lututui.lobotomizer.eventhandler;

import com.lututui.lobotomizer.LobotomizerMod;
import com.lututui.lobotomizer.item.ItemBase;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.lututui.lobotomizer.init.Items.CRAFTING_COMPONENT;
import static com.lututui.lobotomizer.init.Items.GRASSLESS_WOOL_ADDON;
import static com.lututui.lobotomizer.init.Items.LOBOTOMIZER;
import static com.lututui.lobotomizer.init.Items.RANGED_LOBOTOMIZER;
import static com.lututui.lobotomizer.init.Items.SILENCE_ADDON;
import static com.lututui.lobotomizer.init.Items.TWIN_EGG_ADDON;

@Mod.EventBusSubscriber(modid = LobotomizerMod.MODID)
public class ModelRegistryEventHandler {
    @SubscribeEvent
    public static void onModelRegistryEvent(ModelRegistryEvent event) {
        registerAll(
                LOBOTOMIZER, RANGED_LOBOTOMIZER,
                TWIN_EGG_ADDON, SILENCE_ADDON, GRASSLESS_WOOL_ADDON,
                CRAFTING_COMPONENT
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
