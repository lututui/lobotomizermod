package com.lututui.lobotomizer;

import com.lututui.lobotomizer.init.Packets;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = LobotomizerMod.MODID, name = LobotomizerMod.NAME, version = LobotomizerMod.VERSION)
public class LobotomizerMod {
    public static final String MODID = "lobotomizer";
    public static final String NAME = "Lobotomizer";
    public static final String VERSION = "1.7.2-0.1";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("Hello world!");

        Packets.register();
    }


}
