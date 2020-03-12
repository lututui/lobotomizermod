package com.lututui.lobotomizer.eventhandler;

import com.lututui.lobotomizer.LobotomizerMod;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.lututui.lobotomizer.init.Items.CRAFTING_COMPONENT;
import static com.lututui.lobotomizer.init.Items.GRASSLESS_WOOL_ADDON;
import static com.lututui.lobotomizer.init.Items.LOBOTOMIZER;
import static com.lututui.lobotomizer.init.Items.RANGED_LOBOTOMIZER;
import static com.lututui.lobotomizer.init.Items.SILENCE_ADDON;
import static com.lututui.lobotomizer.init.Items.TWIN_EGG_ADDON;

@Mod.EventBusSubscriber(modid = LobotomizerMod.MODID)
public class ItemRegistryEvent {
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                LOBOTOMIZER, RANGED_LOBOTOMIZER,
                TWIN_EGG_ADDON, GRASSLESS_WOOL_ADDON, SILENCE_ADDON, CRAFTING_COMPONENT
        );
    }
}
