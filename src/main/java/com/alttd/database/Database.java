package com.alttd.database;

import com.alttd.AltitudeParticles;
import com.alttd.config.DatabaseConfig;
import com.alttd.util.Logger;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database instance = null;
    public static Connection connection = null;

    private Database() {

    }

    public static Database getDatabase(){
        if (instance == null)
            instance = new Database();
        return (instance);
    }

    public void init() {
        try {
            openConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Tables
        createActiveParticlesTable();
        createUserSettingsTable();
    }

    /**
     * Opens the connection if it's not already open.
     * @throws SQLException If it can't create the connection.
     */
    private void openConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        synchronized (this) {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + DatabaseConfig.IP + ":" + DatabaseConfig.PORT + "/" + DatabaseConfig.DATABASE_NAME +
                            "?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true",
                    DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD);
        }
    }

    private static void createActiveParticlesTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS active_particles(" +
                    "uuid VARCHAR(36) NOT NULL, " +
                    "particle_type VARCHAR(36) NOT NULL, " +
                    "particle_id VARCHAR(36) NOT NULL, " +
                    "PRIMARY KEY (uuid, particle_type)" +
                    ")";
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.severe("Error while trying to create user point table");
            Logger.severe("Shutting down AltitudeParticles");
            Bukkit.getPluginManager().disablePlugin(AltitudeParticles.getInstance());
        }
    }

    private static void createUserSettingsTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS user_settings(" +
                    "uuid VARCHAR(36) NOT NULL, " +
                    "particles_active BIT(1) NOT NULL DEFAULT b'1', " +
                    "seeing_particles BIT(1) NOT NULL DEFAULT b'1', " +
                    "PRIMARY KEY (uuid)" +
                    ")";
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.severe("Error while trying to create user point table");
            Logger.severe("Shutting down AltitudeParticles");
            Bukkit.getPluginManager().disablePlugin(AltitudeParticles.getInstance());
        }
    }

}
