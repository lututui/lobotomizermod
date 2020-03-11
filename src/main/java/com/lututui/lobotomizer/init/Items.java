package com.lututui.lobotomizer.init;

import com.lututui.lobotomizer.LobotomizerMod;
import com.lututui.lobotomizer.item.ItemGrasslessWoolAddon;
import com.lututui.lobotomizer.item.ItemLobotomizer;
import com.lututui.lobotomizer.item.ItemRangedLobotomizer;
import com.lututui.lobotomizer.item.ItemSilenceAddon;
import com.lututui.lobotomizer.item.ItemTwinEggAddon;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = LobotomizerMod.MODID)
public class Items {
    public static final ItemLobotomizer LOBOTOMIZER = new ItemLobotomizer();
    public static final ItemTwinEggAddon TWIN_EGG_ADDON = new ItemTwinEggAddon();
    public static final ItemGrasslessWoolAddon GRASSLESS_WOOL_ADDON = new ItemGrasslessWoolAddon();
    public static final ItemRangedLobotomizer RANGED_LOBOTOMIZER = new ItemRangedLobotomizer();
    public static final ItemSilenceAddon SILENCE_ADDON = new ItemSilenceAddon();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                LOBOTOMIZER, RANGED_LOBOTOMIZER, TWIN_EGG_ADDON, GRASSLESS_WOOL_ADDON, SILENCE_ADDON
        );
    }
}
