package com.alttd.database;

import com.alttd.objects.APartType;
import com.alttd.objects.ParticleSet;
import com.alttd.storage.ParticleStorage;
import com.alttd.storage.PlayerSettings;
import com.alttd.util.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class Queries {

    public static void setSeeingParticles(UUID uuid, boolean seeingParticles) {
        String sql = "UPDATE user_settings " +
                "SET seeing_particles = ?" +
                "WHERE uuid = ?";

        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, seeingParticles ? 1 : 0);
            preparedStatement.setString(2, uuid.toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setParticlesActive(UUID uuid, boolean particlesActive) {
        String sql = "UPDATE user_settings " +
                "SET particles_active = ?" +
                "WHERE uuid = ?";

        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, particlesActive ? 1 : 0);
            preparedStatement.setString(2, uuid.toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addParticle(UUID uuid, APartType aPartType, String particleId) {
        String sql = "INSERT INTO active_particles (uuid, particle_type, particle_id) " +
                "VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE particle_id = ?";

        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, aPartType.getName());
            preparedStatement.setString(3, particleId);
            preparedStatement.setString(4, particleId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeParticle(UUID uuid, APartType aPartType) {
        String sql = "DELETE FROM active_particles " +
                "WHERE uuid = ? " +
                "AND particle_type = ?";

        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, aPartType.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static PlayerSettings getPlayerSettings(UUID uuid) {
        String sql = "SELECT * FROM user_settings WHERE uuid = ?";
        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, uuid.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                boolean particlesActive = resultSet.getInt("particles_active") == 1;
                boolean seeingParticles = resultSet.getInt("seeing_particles") == 1;
                HashMap<APartType, ParticleSet> activeParticles = getActiveParticles(uuid);

                return new PlayerSettings(particlesActive, seeingParticles, uuid, activeParticles);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return createNewPlayerSettings(uuid);
    }

    private static HashMap<APartType, ParticleSet> getActiveParticles(UUID uuid) {
        HashMap<APartType, ParticleSet> activeParticles = new HashMap<>();
        String sql = "SELECT * FROM active_particles " +
                "WHERE uuid = ?";

        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, uuid.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String particleType = resultSet.getString("particle_type");
                String particleId = resultSet.getString("particle_id");
                APartType aPartType;

                try {
                    aPartType = APartType.valueOf(particleType);
                } catch (IllegalArgumentException exception) {
                        Logger.warning("Found unknown APartType % in database for user %", particleType, uuid.toString());
                        continue;
                }
                Optional<ParticleSet> first = ParticleStorage.getParticleSets(aPartType).stream()
                        .filter(particleSet -> particleSet.getParticleId().equalsIgnoreCase(particleId))
                        .findFirst();
                if (first.isEmpty()) {
                    Logger.warning("Found unknown particle id % in database for user % and particle type %", particleId, uuid.toString(), particleType);
                    continue;
                }
                activeParticles.put(aPartType, first.get());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activeParticles;
    }

    private static PlayerSettings createNewPlayerSettings(UUID uuid) {
        String sql = "INSERT INTO user_settings (uuid, particles_active, seeing_particles)" +
                "VALUES (?, ?, ?)" +
                "ON DUPLICATE KEY UPDATE particles_active = ?, seeing_particles = ?";

        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setInt(2, 1);
            preparedStatement.setInt(3, 1);
            preparedStatement.setInt(4, 1);
            preparedStatement.setInt(5, 1);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new PlayerSettings(true, true, uuid);
    }
}
