package uk.lewisl.economy;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import uk.lewisl.economy.commands.economy.Balance;
import uk.lewisl.economy.commands.economy.Pay;
import uk.lewisl.economy.commands.economy.Withdraw;
import uk.lewisl.economy.data.BalanceCache;
import uk.lewisl.economy.data.ConfigManager;
import uk.lewisl.economy.data.MySQL;
import uk.lewisl.economy.listeners.Listeners;


public final class Economy extends JavaPlugin {
    private static Economy instance;
    public static ConfigManager configManager = new ConfigManager();
    public static MySQL mysql;
    public static BalanceCache balanceCache;




    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        //load data
        configManager.setupFiles();
        mysql = new MySQL();
        mysql.setup();




        balanceCache = new BalanceCache();

        balanceCache.loadAll();

        getServer().getPluginManager().registerEvents(balanceCache, this);
        getServer().getPluginManager().registerEvents(new Listeners(), this);

        //register command
        getInstance().getCommand("pay").setExecutor(new Pay());
        getInstance().getCommand("bal").setExecutor(new Balance());
        getInstance().getCommand("withdraw").setExecutor(new Withdraw());















    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        balanceCache.saveAll();


    }


    public static Economy getInstance() {
        return instance;
    }
}
