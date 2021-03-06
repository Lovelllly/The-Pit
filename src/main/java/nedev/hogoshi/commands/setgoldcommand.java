package nedev.hogoshi.commands;

import lombok.val;
import nedev.hogoshi.ThePit;
import nedev.hogoshi.listener.ListenerHelper;
import nedev.hogoshi.mysql.LoadedUser;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setgoldcommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
        if (s.hasPermission("commands.gold.set") || s.hasPermission("commands.gold.*") || s.hasPermission("commands.*")) {
            if (args.length == 0) {
                if (ThePit.getInstance().getConfig().getInt("Language") == 1) {
                    s.sendMessage(ThePit.messages.getString("Prefix") + ThePit.messages.getString("Russian.Commands.SetGoldCommandUsage"));
                } else {
                    s.sendMessage(ThePit.messages.getString("Prefix") + ThePit.messages.getString("English.Commands.SetGoldCommandUsage"));
                }
                return true;
            }

            if (args.length == 1) {
                if (ThePit.getInstance().getConfig().getInt("Language") == 1) {
                    s.sendMessage(ThePit.messages.getString("Prefix") + ThePit.messages.getString("Russian.Commands.SetGoldCommandUsage"));
                } else {
                    s.sendMessage(ThePit.messages.getString("Prefix") + ThePit.messages.getString("English.Commands.SetGoldCommandUsage"));
                }
                return true;
            }

            if (args.length == 2) {
                String player = args[0];
                Player p = Bukkit.getPlayer(player);

                if (parse(args[1])) {
                    int i = Integer.parseInt(args[1]);

                    val loadedUser = LoadedUser.USER_CACHE.getUnchecked(p.getUniqueId());

                    loadedUser.setCoins(i);

                    ListenerHelper.setupscoreboard(p);

                    if (ThePit.getInstance().getConfig().getInt("Language") == 1) {
                        p.sendTitle(ThePit.messages.getString("Sender").replaceAll("<sender>", s.getName()), ThePit.messages.getString("Russian.Commands.SetedGold").replaceAll("<gold>", Integer.toString(i)));
                    } else {
                        p.sendTitle(ThePit.messages.getString("Sender").replaceAll("<sender>", s.getName()), ThePit.messages.getString("English.Commands.SetedGold").replaceAll("<gold>", Integer.toString(i)));
                    }

                } else {
                    if (ThePit.getInstance().getConfig().getInt("Language") == 1) {
                        s.sendMessage(ThePit.messages.getString("Prefix") + ThePit.messages.getString("Russian.Commands.UseNumber"));
                    } else {
                        s.sendMessage(ThePit.messages.getString("Prefix") + ThePit.messages.getString("English.Commands.UseNumber"));
                    }
                }
            }
        } else {
            if (ThePit.getInstance().getConfig().getInt("Language") == 1) {
                s.sendMessage(ThePit.messages.getString("Prefix") + ThePit.messages.getString("Russian.NoPerms"));
            } else {
                s.sendMessage(ThePit.messages.getString("Prefix") + ThePit.messages.getString("English.NoPerms"));
            }
            return true;
        }
        return true;
    }

    public boolean parse(String i) {
        try {
            int e = Integer.parseInt(i);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
