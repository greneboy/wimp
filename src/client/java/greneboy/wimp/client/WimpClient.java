package greneboy.wimp.client;

import greneboy.wimp.client.config.WimpConfig;
import greneboy.wimp.client.util.WimpPacketTracker;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class WimpClient implements ClientModInitializer {

    public static final String MOD_ID = "wimp";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static WimpConfig config;

    @Override
    public void onInitializeClient() {

        // Attach HUD element before the chat
        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "wimp_delta"),
                (context, deltaTracker) -> renderHud(context)
        );

        AutoConfig.register(WimpConfig.class, GsonConfigSerializer::new);
        WimpClient.config = AutoConfig.getConfigHolder(WimpConfig.class).getConfig();
    }

    private static void renderHud(GuiGraphics context) {
        if (!WimpClient.config.show_hud) return;
        if (WimpPacketTracker.lastPacketTime == -1L) return;

        long delta = System.currentTimeMillis() - WimpPacketTracker.lastPacketTime;

        if (delta < WimpClient.config.threshold_ms) return;

        Minecraft minecraft = Minecraft.getInstance();

        long timeUntilDisconnect = Math.max(0, 30000 - delta);

        List<String> parts = new ArrayList<>();

        if (config.show_packet_loss) {
            parts.add(Component.translatable("wimp.hud.packet_loss", delta).getString());
        }
        if (config.show_timeout_countdown) {
            parts.add(Component.translatable("wimp.hud.timeout_countdown", timeUntilDisconnect).getString());
        }
        if (config.show_packet_type) {
            parts.add(WimpPacketTracker.lastPacketType);
        }

        String message = String.join(" | ", parts);

        int color;
        if (delta > WimpClient.config.threshold_ms_bad) color = WimpClient.config.text_bad_color;
        else color = WimpClient.config.text_default_color;

        context.drawString(
                minecraft.font,
                message,
                WimpClient.config.position_x,
                WimpClient.config.position_y,
                color,
                WimpClient.config.text_shadow
        );
    }

}
