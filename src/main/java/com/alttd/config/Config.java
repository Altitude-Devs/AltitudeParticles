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
    public static String RELOAD_MESSAGE = "<green>Reload configs: <gold>/apart reload</gold></green>";

    private static void loadHelp() {
        HELP_MESSAGE_WRAPPER = config.getString("help.help-wrapper", HELP_MESSAGE_WRAPPER);
        HELP_MESSAGE = config.getString("help.help", HELP_MESSAGE);
        RELOAD_MESSAGE = config.getString("help.reload", RELOAD_MESSAGE);
    }

    public static String NO_PERMISSION = "<red>You do not have permission to do that.</red>";
    public static String NO_CONSOLE = "<red>You cannot use this command from console.</red>";

    private static void loadGeneric() {
        NO_PERMISSION = config.getString("generic.no-permission", NO_PERMISSION);
        NO_CONSOLE = config.getString("generic.no-console", NO_CONSOLE);
    }

    private static void loadMessages() {
    }

    public static boolean DEBUG = false;

    private static void loadSettings() {
        DEBUG = config.getBoolean("settings.debug", DEBUG);
    }
}
