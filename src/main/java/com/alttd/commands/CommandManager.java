package com.alttd.commands;

import com.alttd.AltitudeParticles;
import com.alttd.commands.subcommands.CommandGUI;
import com.alttd.commands.subcommands.CommandHelp;
import com.alttd.commands.subcommands.CommandReload;
import com.alttd.config.Config;
import com.alttd.util.Logger;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandManager implements CommandExecutor, TabExecutor {
    private final List<SubCommand> subCommands;

    public CommandManager() {
        AltitudeParticles aPart = AltitudeParticles.getInstance();

        PluginCommand command = aPart.getCommand("apart");
        if (command == null) {
            subCommands = null;
            Logger.severe("Unable to find AltitudeParticles command.");
            return;
        }
        command.setExecutor(this);
        command.setTabCompleter(this);

        subCommands = Arrays.asList(
                new CommandHelp(this),
                new CommandReload(),
                new CommandGUI());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String cmd, @NotNull String[] args) {
        SubCommand subCommand;
        if (args.length == 0)
            subCommand = getSubCommand("gui");
        else
            subCommand = getSubCommand(args[0]);

        if (!commandSender.hasPermission(subCommand.getPermission())) {
            commandSender.sendMiniMessage(Config.NO_PERMISSION, null);
            return true;
        }

        return subCommand.onCommand(commandSender, args);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String cmd, @NotNull String[] args) {
        List<String> res = new ArrayList<>();

        if (args.length <= 1) {
            res.addAll(subCommands.stream()
                    .filter(subCommand -> commandSender.hasPermission(subCommand.getPermission()))
                    .map(SubCommand::getName)
                    .filter(name -> args.length == 0 || name.startsWith(args[0]))
                    .collect(Collectors.toList())
            );
        } else {
            SubCommand subCommand = getSubCommand(args[0]);
            if (subCommand != null && commandSender.hasPermission(subCommand.getPermission()))
                res.addAll(subCommand.getTabComplete(commandSender, args));
        }
        return res;
    }

    public List<SubCommand> getSubCommands() {
        return subCommands;
    }

    private SubCommand getSubCommand(String cmdName) {
        return subCommands.stream()
                .filter(subCommand -> subCommand.getName().equals(cmdName))
                .findFirst()
                .orElse(null);
    }
}