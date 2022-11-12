package uk.lewisl.economy.data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import uk.lewisl.economy.Economy;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * @author lewis
 * @since 12/11/2022
 */
public class ConfigManager {

    private FileConfiguration config;

    private File configfile;
    public void setupFiles() {
        configfile = new File(Economy.getInstance().getDataFolder(), "config.yml");

        if (!this.configfile.exists()) {
            this.configfile.getParentFile().mkdirs();
            Economy.getInstance().saveResource("config.yml", false);
            Bukkit.getLogger().log(Level.FINE, "config.yml file created");
        }

        this.config = new YamlConfiguration();

        try {
            this.config.load(this.configfile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    public void saveConfig() {
        try {
            this.config.save(this.configfile);
            Bukkit.getLogger().log(Level.FINE, "Config.yml has been saved");
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Config.yml could not be saved");
        }
    }



    public FileConfiguration getConfig() {
        return config;
    }




}
