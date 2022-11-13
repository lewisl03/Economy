package uk.lewisl.economy.commands.economy;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import uk.lewisl.economy.Economy;
import uk.lewisl.economy.data.PlayerBalance;
import uk.lewisl.economy.utils.Maths;
import uk.lewisl.economy.utils.PlayerUtils;
import uk.lewisl.economy.utils.Text;

/**
 * @author lewis
 * @since 12/11/2022
 */
public class BalTop implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Bukkit.getScheduler().runTaskLater(Economy.getInstance(), new Runnable() {
            public void run() {
        int playersAmount = Economy.mysql.dbCount();
        System.out.println("Amount "+playersAmount);

        //page 1
        if(strings.length == 0){
            String page = "1";
            int displayPerPage = Economy.configManager.getConfig().getInt("moneySettings.baltopShowAmount");
            int maxPages = playersAmount / displayPerPage;

            PlayerBalance[] topBalances = Economy.mysql.getTopPlayers(displayPerPage, 0);

            String message =  Text.convertString(Economy.configManager.getConfig().getString("messages.balTopHeader"))
                    .replaceAll("%minPage%", page)
                    .replaceAll("%maxPage%", maxPages+"");

            for (int i = 0; i < topBalances.length; i++){
                PlayerBalance pb = topBalances[i];
                if(pb == null)break;

                message += Text.convertString(Economy.configManager.getConfig().getString("messages.balTopMain")).replaceAll("%rank%", ""+i)
                        .replaceAll("%amount%",pb.getPlayerStringBalance()+"" )
                        .replaceAll("%name%", (PlayerUtils.getPlayerName(pb.getPlayerUUID()) == null ? "unknown" : PlayerUtils.getPlayerName(pb.getPlayerUUID())));

            }
            message += Text.convertString(Economy.configManager.getConfig().getString("messages.balTopBottom"));
            commandSender.sendMessage(message);

            return;
        }




        if(!Maths.isNumeric(strings[0])){
            commandSender.sendMessage("You have given letters for page, this can only be numbers");
            return;
        }

        int intPage = (Integer.parseInt(strings[0]) == 0 ? 1 : Integer.parseInt(strings[0]));


        int displayPerPage = Economy.configManager.getConfig().getInt("moneySettings.baltopShowAmount");

        double d_num = (double) playersAmount / (double) displayPerPage;
        int maxPages = d_num % 1 == 0 ? (playersAmount / displayPerPage) : (playersAmount / displayPerPage)+1;
        System.out.println("MaxPages "+maxPages );



        intPage = (intPage > maxPages ? maxPages : intPage);


        int offset = (intPage - 1) * displayPerPage;


        PlayerBalance[] topBalances = Economy.mysql.getTopPlayers(displayPerPage, offset);

        String message = Text.convertString(Economy.configManager.getConfig().getString("messages.balTopHeader"))
                .replaceAll("%minPage%", intPage+"")
                .replaceAll("%maxPage%", maxPages+"");

        for (int i = 0; i < topBalances.length; i++){
            PlayerBalance pb = topBalances[i];
            if(pb == null)break;

            message += Text.convertString(Economy.configManager.getConfig().getString("messages.balTopMain")).replaceAll("%rank%", ""+((((intPage - 1) * displayPerPage)+1)+i))
                    .replaceAll("%amount%",pb.getPlayerStringBalance()+"" )
                    .replaceAll("%name%", (PlayerUtils.getPlayerName(pb.getPlayerUUID()) == null ? "unknown" : PlayerUtils.getPlayerName(pb.getPlayerUUID())));



        }
        message += Text.convertString(Economy.configManager.getConfig().getString("messages.balTopBottom"));
        commandSender.sendMessage(message);

        return;

            }
        }, 0L);
        return true;
    }
}
