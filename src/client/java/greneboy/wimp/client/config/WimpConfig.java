package greneboy.wimp.client.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "wimp")
public class WimpConfig implements ConfigData {
    public boolean show_hud = true;
    public int threshold_ms = 200;
    public int position_x = 4;
    public int position_y = 4;
    public boolean show_packet_loss = true;
    public boolean show_packet_type = false;
    public boolean show_timeout_countdown = false;
    public boolean text_shadow = true;
    public int threshold_ms_bad = 1000;
    public int text_default_color = 0xFFFFFF00;
    public int text_bad_color = 0xFFFF0000;
}
