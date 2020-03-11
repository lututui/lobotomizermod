package com.lututui.lobotomizer.init;

import com.lututui.lobotomizer.LobotomizerMod;
import com.lututui.lobotomizer.network.ClearRangedLobotomizerMessage;
import com.lututui.lobotomizer.network.ClearRangedLobotomizerMessageHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class Packets {
    public static final SimpleNetworkWrapper NETWORK_CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(LobotomizerMod.MODID);
    private static int MESSAGE_ID = 0;

    public static void register() {
        NETWORK_CHANNEL.registerMessage(
                ClearRangedLobotomizerMessageHandler.class,
                ClearRangedLobotomizerMessage.class,
                MESSAGE_ID++,
                Side.SERVER
        );
    }
}
