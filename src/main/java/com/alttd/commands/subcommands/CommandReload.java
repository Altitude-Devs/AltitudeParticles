package com.alttd.commands.subcommands;

import com.alttd.AltitudeParticles;
import com.alttd.commands.SubCommand;
import com.alttd.config.Config;
import com.alttd.config.DatabaseConfig;
import com.alttd.config.ParticleConfig;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CommandReload extends SubCommand {

    @Override
    public boolean onCommand(CommandSender commandSender, String[] args) {
        AltitudeParticles.getInstance().reload();
        commandSender.sendMiniMessage("<green>Reloaded AltitudeParticles config.</green>", null);
        return true;
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public List<String> getTabComplete(CommandSender commandSender, String[] args) {
        return new ArrayList<>();
    }

    @Override
    public String getHelpMessage() {
        return Config.RELOAD_HELP_MESSAGE;
    }
}
