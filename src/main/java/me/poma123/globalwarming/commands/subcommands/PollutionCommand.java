package me.poma123.globalwarming.commands.subcommands;

import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.CommonPatterns;
import me.poma123.globalwarming.GlobalWarmingPlugin;
import me.poma123.globalwarming.TemperatureManager;
import me.poma123.globalwarming.api.PollutionManager;
import me.poma123.globalwarming.commands.GlobalWarmingCommand;
import me.poma123.globalwarming.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

class PollutionCommand extends SubCommand {

    PollutionCommand(GlobalWarmingPlugin plugin, GlobalWarmingCommand cmd) {
        super(plugin, cmd, "pollution", "允许你手动修改污染值", false);
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        if (sender.hasPermission("globalwarming.command.pollution") || !(sender instanceof Player)) {
            if (args.length > 2) {
                World world = Bukkit.getWorld(args[2]);

                if (world != null && GlobalWarmingPlugin.getRegistry().isWorldEnabled(world.getName())) {
                    if (args[1].equalsIgnoreCase("get")) {
                        double pollution = TemperatureManager.fixDouble(PollutionManager.getPollutionInWorld(world), 2);

                        sender.sendMessage(ChatColors.color("&b世界 &a" + world.getName() + " &b的污染值为: &a" + pollution));
                    } else if (args[1].equalsIgnoreCase("set")) {
                        if (args.length > 3) {
                            setPollution(sender, world, args);
                        } else {
                            sender.sendMessage(ChatColors.color("&4用法: &c/globalwarming pollution <set> <world> <amount>"));
                        }
                    }
                } else {
                    sender.sendMessage(ChatColors.color("&4该世界无法使用该指令"));
                }
            } else {
                sender.sendMessage(ChatColors.color("&用法: &c/globalwarming pollution <set|get> <world>"));
            }
        } else {
            sender.sendMessage(ChatColors.color("&4你没有足够的权限执行此命令"));
        }
    }

    private void setPollution(CommandSender sender, World world, String[] args) {
        int amount = parseAmount(args);

        if (amount > -1) {
            if (PollutionManager.setPollutionInWorld(world, amount)) {
                sender.sendMessage(ChatColors.color("&b已设置世界 '&a%world%&b' 的污染值为 '&a%newValue%&b'").replace("%newValue%", amount + "").replace("%world%", world.getName()));
            } else {
                // This is nearly impossible, but let us check
                sender.sendMessage(ChatColors.color("&4该世界无法使用该指令"));
            }
        } else {
            sender.sendMessage(ChatColors.color("&4%amount% &c不是一个有效的值").replace("%amount%", amount + ""));
        }
    }

    private int parseAmount(String[] args) {
        int amount = -1;

        if (args.length == 4 && CommonPatterns.NUMERIC.matcher(args[3]).matches()) {
            amount = Integer.parseInt(args[3]);
        }

        return amount;
    }
}
