package com.alttd.config;

import java.io.File;

public final class Config extends AbstractConfig {

    static Config config;
    static int version;
    public Config() {
        super(new File(System.getProperty("user.home") + File.separator + "share" + File.separator + "configs" + File.separator + "AltitudeParticles"), "config.yml");
    }

    public static void reload() {
        config = new Config();

        version = config.getInt("config-version", 1);
        config.set("config-version", 1);

        config.readConfig(Config.class, null);
    }

    public static String HELP_MESSAGE_WRAPPER = "<gold>AltitudeParticles help:\n<commands></gold>";
    public static String HELP_MESSAGE = "<green>Show this menu: <gold>/apart help</gold></green>";
    public static String RELOAD_HELP_MESSAGE = "<green>Reload configs: <gold>/apart reload</gold></green>";
    public static String GUI_HELP_MESSAGE = "<green>Open GUI: <gold>/apart</gold> or <gold>/apart gui</gold>";

    private static void loadHelp() {
        HELP_MESSAGE_WRAPPER = config.getString("help.help-wrapper", HELP_MESSAGE_WRAPPER);
        HELP_MESSAGE = config.getString("help.help", HELP_MESSAGE);
        RELOAD_HELP_MESSAGE = config.getString("help.reload", RELOAD_HELP_MESSAGE);
        GUI_HELP_MESSAGE = config.getString("help.gui", GUI_HELP_MESSAGE);
    }

    public static String NO_PERMISSION = "<red>You do not have permission to do that.</red>";
    public static String NO_CONSOLE = "<red>You cannot use this command from console.</red>";

    private static void loadGeneric() {
        NO_PERMISSION = config.getString("generic.no-permission", NO_PERMISSION);
        NO_CONSOLE = config.getString("generic.no-console", NO_CONSOLE);
    }

    private static void loadMessages() {
    }

    public static String PARTICLE_TYPE_BUTTON_NAME = "<gold><name></gold>";
    public static String PARTICLE_TYPE_GUI_NAME = "<red>AltitudeParticles</red>";
    private static void loadGUIText() {
        PARTICLE_TYPE_BUTTON_NAME = config.getString("gui-text.particle-type-button-name", PARTICLE_TYPE_BUTTON_NAME);
        PARTICLE_TYPE_GUI_NAME = config.getString("gui-text.particles-type-gui-name", PARTICLE_TYPE_GUI_NAME);
    }

    public static String SEE_OTHERS_ON = "<green>Particles visible</green>";
    public static String SEE_OTHERS_ON_DESC = "<dark_aqua>Click to hide particles</dark_aqua>";
    public static String SEE_OTHERS_OFF = "<red>Particles hidden</red>";
    public static String SEE_OTHERS_OFF_DESC = "<dark_aqua>Click to show particles</dark_aqua>";
    public static String PARTICLES_ON = "<green>Particles on</green>";
    public static String PARTICLES_ON_DESC = "<dark_aqua>Click to disable particles</dark_aqua>";
    public static String PARTICLES_OFF = "<red>Particles off</red>";
    public static String PARTICLES_OFF_DESC = "<dark_aqua>Click to enable particles</dark_aqua>";
    public static String PARTICLES_CLEAR = "<yellow>Clear</yellow>";
    public static String PARTICLES_CLEAR_DESC = "<dark_aqua>Clears all active particles</dark_aqua>";
    public static String BACK_BUTTON = "<yellow>Back</yellow>";

    private static void loadGUIButtons() {
        SEE_OTHERS_ON = config.getString("gui-buttons.see-others-on", SEE_OTHERS_ON);
        SEE_OTHERS_ON_DESC = config.getString("gui-buttons.see-others-on-desc", SEE_OTHERS_ON_DESC);
        SEE_OTHERS_OFF = config.getString("gui-buttons.see-others-off", SEE_OTHERS_OFF);
        SEE_OTHERS_OFF_DESC = config.getString("gui-buttons.see-others-off-desc", SEE_OTHERS_OFF_DESC);
        PARTICLES_ON = config.getString("gui-buttons.particles-on", PARTICLES_ON);
        PARTICLES_ON_DESC = config.getString("gui-buttons.particles-on-desc", PARTICLES_ON_DESC);
        PARTICLES_OFF = config.getString("gui-buttons.particles-off", PARTICLES_OFF);
        PARTICLES_OFF_DESC = config.getString("gui-buttons.particles-off-desc", PARTICLES_OFF_DESC);
        PARTICLES_CLEAR = config.getString("gui-buttons.particles-clear", PARTICLES_CLEAR);
        PARTICLES_CLEAR_DESC = config.getString("gui-buttons.particles-clear-desc", PARTICLES_CLEAR_DESC);
        BACK_BUTTON = config.getString("gui-buttons.back-button", BACK_BUTTON);
    }

    public static boolean DEBUG = false;

    private static void loadSettings() {
        DEBUG = config.getBoolean("settings.debug", DEBUG);
    }
}
