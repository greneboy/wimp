package greneboy.wimp.client.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class WimpSettings {

    public static Screen getConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("wimp.config.title"));

        ConfigCategory general = builder.getOrCreateCategory(Component.translatable("wimp.config.general"));
        ConfigCategory advanced = builder.getOrCreateCategory(Component.translatable("wimp.config.advanced"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        WimpConfig config = AutoConfig.getConfigHolder(WimpConfig.class).getConfig();

        general.addEntry(entryBuilder
                .startBooleanToggle(Component.translatable("wimp.config.show_hud"), config.show_hud)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> config.show_hud = newValue)
                .build()
        );

        general.addEntry(entryBuilder
                .startBooleanToggle(Component.translatable("wimp.config.text_shadow"), config.text_shadow)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> config.text_shadow = newValue)
                .build()
        );

        general.addEntry(entryBuilder
                .startIntField(Component.translatable("wimp.config.threshold"), config.threshold_ms)
                .setDefaultValue(200)
                .setSaveConsumer(newValue -> config.threshold_ms = newValue)
                .build()
        );

        general.addEntry(entryBuilder
                .startIntField(Component.translatable("wimp.config.pos_x"), config.position_x)
                .setDefaultValue(4)
                .setSaveConsumer(newValue -> config.position_x = newValue)
                .build()
        );

        general.addEntry(entryBuilder
                .startIntField(Component.translatable("wimp.config.pos_y"), config.position_y)
                .setDefaultValue(4)
                .setSaveConsumer(newValue -> config.position_y = newValue)
                .build()
        );

        advanced.addEntry(entryBuilder
                .startBooleanToggle(Component.translatable("wimp.config.show_packet_loss"), config.show_packet_loss)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> config.show_packet_loss = newValue)
                .build()
        );

        advanced.addEntry(entryBuilder
                .startBooleanToggle(Component.translatable("wimp.config.show_timeout_countdown"), config.show_timeout_countdown)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> config.show_timeout_countdown = newValue)
                .build()
        );

        advanced.addEntry(entryBuilder
                .startBooleanToggle(Component.translatable("wimp.config.show_packet_type"), config.show_packet_type)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> config.show_packet_type = newValue)
                .build()
        );

        advanced.addEntry(entryBuilder
                .startAlphaColorField(Component.translatable("wimp.config.default_color"), config.text_default_color)
                .setDefaultValue(0xFFFFFF00)
                .setSaveConsumer(newValue -> config.text_default_color = newValue)
                .build()
        );

        advanced.addEntry(entryBuilder
                .startIntField(Component.translatable("wimp.config.threshold_bad"), config.threshold_ms_bad)
                .setDefaultValue(1000)
                .setSaveConsumer(newValue -> config.threshold_ms_bad = newValue)
                .build()
        );

        advanced.addEntry(entryBuilder
                .startAlphaColorField(Component.translatable("wimp.config.threshold_bad_color"), config.text_bad_color)
                .setDefaultValue(0xFFFF0000)
                .setSaveConsumer(newValue -> config.text_bad_color = newValue)
                .build()
        );

        builder.setSavingRunnable(() -> {
            AutoConfig.getConfigHolder(WimpConfig.class).save();
        });

        return builder.build();
    }
}
