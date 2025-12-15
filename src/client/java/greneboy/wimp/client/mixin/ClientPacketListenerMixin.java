package greneboy.wimp.client.mixin;

import greneboy.wimp.client.util.WimpPacketTracker;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Connection.class)
public class ClientPacketListenerMixin {

    @Inject(method = "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/protocol/Packet;)V", at = @At("HEAD"))
    private void onChannelRead(ChannelHandlerContext ctx, Packet<?> packet, CallbackInfo ci) {
        long now = System.currentTimeMillis();

        WimpPacketTracker.lastPacketType = packet.getClass().getSimpleName();
        WimpPacketTracker.lastPacketTime = now;
    }
}