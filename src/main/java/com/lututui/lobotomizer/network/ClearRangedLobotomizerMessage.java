package com.lututui.lobotomizer.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ClearRangedLobotomizerMessage implements IMessage {
    public ClearRangedLobotomizerMessage() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {}

    @Override
    public void toBytes(ByteBuf buf) {}
}
