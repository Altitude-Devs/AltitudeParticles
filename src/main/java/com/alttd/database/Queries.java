package com.alttd.database;

import com.alttd.storage.PlayerSettings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Queries {
    public static PlayerSettings getPlayerSettings(UUID uuid) {
        String sql = "SELECT * FROM user_settings WHERE uuid = ?";
        try {
            PreparedStatement preparedStatement = Database.connection.prepareStatement(sql);
            preparedStatement.setString(1, uuid.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                boolean particlesActive = resultSet.getInt("particles_active") == 1;
                boolean seeingParticles = resultSet.getInt("seeing_particles") == 1;
                return new PlayerSettings(particlesActive, seeingParticles, uuid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
