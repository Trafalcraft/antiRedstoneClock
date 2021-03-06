package com.trafalcraft.antiRedstoneClock.util;

import com.trafalcraft.antiRedstoneClock.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public enum Msg {

        Prefix("&bAntiRedstoneClock &9&l> &r&b "),
        ERROR("&4AntiRedstoneClock &l> &r&c "),
        NO_PERMISSIONS("&4ERROR &9&l> &r&bYou don't have permission to do that!"),
        Command_Use("&4AntiRedstoneClock &l> &r&cCommand usage: &6/arc $command"),

        //Msg
        MsgToAdmin("Redstone clock disable in x:$X y:$Y Z:$Z. In the world $World"),
        reloadSuccess("Reload Success!"),
        unknownCmd("Unknown command. Type \"/help\" for help."),
        newValueInConfig("The new value of $setting is $value"),
        RedStoneClockListHeader("RedstoneClockList: $page"),
        RedStoneClockListFooter(""),
        //Exception
        duplicate_object("This list already contains this redstone");

        static final JavaPlugin plugin = Main.getInstance();

        public static void getHelp(CommandSender sender) {
                sender.sendMessage("");
                sender.sendMessage("§3§l-------------AntiRedstoneClock-------------");
                sender.sendMessage("§3/arc checkList <Page number> §b- display the active redstoneclock.");
                sender.sendMessage("§3/arc setMaxPulses <number> §b- Change the \"MaxPulses\" setting.");
                sender.sendMessage("§3/arc SetDelay <number> §b- Change the \"Delay\" setting.");
                sender.sendMessage("§3/arc NotifyAdmin <true/false> §b- change the \"NotifyAdmin\" setting.");
                sender.sendMessage(
                        "§3/arc AutoRemoveDetectedClock <true/false> §b- change the \"AutoRemoveDetectedClock\" setting.");
                sender.sendMessage(
                        "§3/arc CreateSignWhenClockIsBreak <true/false> §b- change the \"CreateSignWhenClockIsBreak\" setting.");
                sender.sendMessage("§3/arc Reload §b- To Reload the config file.");
                sender.sendMessage("                       §3Version: §6" + plugin.getDescription().getVersion());
                sender.sendMessage("§3------------------------------------------------");
                sender.sendMessage("");
        }

        private String value;

        Msg(String value) {
                this.value = value;
        }

        public String toString() {
                return value;
        }

        private void replaceBy(String value) {
                this.value = value;
        }

        public static void load() {
                FileConfiguration config = plugin.getConfig();
                Prefix.replaceBy(config.getString("Msg.default.prefix").replace("&", "§"));
                ERROR.replaceBy(config.getString("Msg.default.error").replace("&", "§"));
                NO_PERMISSIONS.replaceBy(config.getString("Msg.default.no_permission").replace("&", "§"));
                Command_Use.replaceBy(config.getString("Msg.default.command_use").replace("&", "§"));

                MsgToAdmin.replaceBy(config.getString("Msg.message.MsgToAdmin").replace("&", "§"));
                reloadSuccess.replaceBy(config.getString("Msg.message.reloadSuccess").replace("&", "§"));
                unknownCmd.replaceBy(config.getString("Msg.message.unknownCmd").replace("&", "§"));
                newValueInConfig.replaceBy(config.getString("Msg.message.newValueInConfig").replace("&", "§"));
                RedStoneClockListHeader.replaceBy(
                        config.getString("Msg.message.RedStoneClockListHeader").replace("&", "§"));
                RedStoneClockListFooter.replaceBy(
                        config.getString("Msg.message.RedStoneClockListFooter").replace("&", "§"));

                duplicate_object.replaceBy(config.getString("Msg.Exception.duplicate_object").replace("&", "§"));

                String sIgnoreWorld = config.getString("IgnoreWorlds");
                Main.getIgnoredWorlds().addAll(Arrays.asList(sIgnoreWorld.split("/")));
                String sIgnoreRegion = config.getString("IgnoreRegions");
                Main.getIgnoredRegions().addAll(Arrays.asList(sIgnoreRegion.split("/")));
        }

}
