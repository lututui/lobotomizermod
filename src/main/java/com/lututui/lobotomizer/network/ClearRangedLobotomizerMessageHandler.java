package com.lututui.lobotomizer.network;

import com.lututui.lobotomizer.init.Items;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClearRangedLobotomizerMessageHandler implements IMessageHandler<ClearRangedLobotomizerMessage, IMessage> {
    @Override
    public IMessage onMessage(ClearRangedLobotomizerMessage message, MessageContext ctx) {
        final EntityPlayerMP player = ctx.getServerHandler().player;

        player.getServerWorld().addScheduledTask(
                () -> {
                    final ItemStack itemMainHand = player.getHeldItemMainhand();

                    if (itemMainHand.getItem() == Items.RANGED_LOBOTOMIZER) {
                        itemMainHand.setTagCompound(new NBTTagCompound());
                        player.sendStatusMessage(
                                new TextComponentTranslation("other.lobotomizer.toolMessage.targetClear"),
                                true
                        );
                    }
                }
        );

        return null;
    }
}
