package uk.lewisl.economy.data;

import org.bukkit.Bukkit;
import uk.lewisl.economy.Economy;

import javax.swing.text.Style;
import java.sql.*;
import java.util.UUID;
import java.util.logging.Level;

public class MySQL {
    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    private String sql;

    private Connection createConn(){

        try {

            conn = DriverManager.getConnection("jdbc:mysql://"+
                            Economy.configManager.getConfig().getString("mysql.host") +":"+
                            Economy.configManager.getConfig().getInt("mysql.port")+"/"+
                            Economy.configManager.getConfig().getString("mysql.database")+
                            "?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",

                    Economy.configManager.getConfig().getString("mysql.username"),
                    Economy.configManager.getConfig().getString("mysql.password"));


            if(conn == null && conn.isClosed()){
                Economy.getInstance().getLogger().log(Level.SEVERE, "Unable to connect to MySQL, Shutting plugin down");
                Bukkit.getPluginManager().disablePlugin(Economy.getInstance());
            }else{

            }

            return conn;
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        System.out.println("Unable to connect to database...");
        System.exit(0);
        return null;
    }

    public Connection getConn(){

        try {
            conn = (conn == null ? createConn() : conn.isClosed() ? createConn() : conn);
            if(conn == null && conn.isClosed()){
                Economy.getInstance().getLogger().log(Level.SEVERE, "Unable to connect to MySQL, Shutting plugin down");
                Bukkit.getPluginManager().disablePlugin(Economy.getInstance());

            }
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void setup(){


        sql = "CREATE TABLE IF NOT EXISTS `PlayerBalance` (\n" +
                "`UUID` varchar(254) NOT NULL,\n" +
                "`Balance` int(254) NOT NULL" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
        execute(sql);

    }


    private void execute(String sql){
        try {
            preparedStatement = getConn().prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet executeQuery(String sql){
        try {
            preparedStatement = getConn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    //shit

    public void addPlayer(String playerUUID, long balance){
        sql = "INSERT INTO PlayerBalance (`UUID`, `Balance`) VALUES ('"+
                playerUUID+"','"+balance+"')";
        execute(sql);
    }

    public void removePlayer(String playerUUID){
        sql = "DELETE FROM `PlayerBalance` WHERE `UUID`='"+playerUUID+"' ";
        execute(sql);
    }

    public PlayerBalance loadPlayer(UUID playerUUID){
        sql = "SELECT * FROM PlayerBalance WHERE UUID='"+playerUUID+"'";
        ResultSet rs = executeQuery(sql);

        String uUID = null;
        long balance = 0;

        try {

            if(!rs.last()){
                return null;
            }

            uUID = rs.getString("UUID");
            balance = rs.getLong("Balance");
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return uUID == null ? null : new PlayerBalance(UUID.fromString(uUID), balance);

    }

    public void savePlayer(PlayerBalance p){
        sql = "DELETE FROM `PlayerBalance` WHERE UUID='"+p.getPlayerUUID()+"';";
        execute(sql);
        sql = "INSERT INTO `PlayerBalance`(`UUID`, `Balance`) VALUES ('"+p.playerUUID+"', '"+p.playerBalance+"')";
        execute(sql);
    }



























}
