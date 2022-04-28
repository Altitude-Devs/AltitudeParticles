package com.alttd.objects;

import com.alttd.config.Config;
import com.alttd.storage.PlayerSettings;
import com.alttd.util.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Frame {
    List<AParticle> AParticles;

    public Frame(List<AParticle> AParticles) {
        this.AParticles = AParticles;
    }

    /**
     * Spawns all particles in a frame (CALL ASYNC)
     *
     * @param   location  Location to spawn particles at
     */
    public void spawn(Location location, float rotation) {
        Location tmpLocation = location.clone();
        AParticles.forEach(AParticle -> {
            ThreadLocalRandom current = ThreadLocalRandom.current();
            double offsetX = ((AParticle.offset_range() == 0) ? 0 : current.nextDouble(-AParticle.offset_range(), AParticle.offset_range()));
            double offsetZ = ((AParticle.offset_range() == 0) ? 0 : current.nextDouble(-AParticle.offset_range(), AParticle.offset_range()));
            double offsetY = ((AParticle.offset_range() == 0) ? 0 : current.nextDouble(-AParticle.offset_range(), AParticle.offset_range()));
            XZ xz = new XZ(location.getX(), location.getX() + AParticle.x() + offsetX,
                    location.getZ(), location.getZ() +  AParticle.z() + offsetZ,
                    rotation);
            AParticle.particleBuilder()
                    .location(tmpLocation.set(
                            xz.getRotatedX(),
                            location.getY() + AParticle.y() + offsetY,
                            xz.getRotatedZ()))
                    .receivers(Bukkit.getOnlinePlayers().stream()
                            .filter(player -> {
                                PlayerSettings playerSettings = PlayerSettings.getPlayer(player.getUniqueId());
                                if (playerSettings == null)
                                    return false;
                                if (!playerSettings.isSeeingParticles())
                                    return false;
                                Location playerLocation = player.getLocation();
                                return location.getWorld().getUID().equals(playerLocation.getWorld().getUID()) && player.getLocation().distance(location) < 100;
                            }).collect(Collectors.toList()))
                    .spawn();
        });
    }

    private class XZ {
        private final double cx, cz; //Coordinates to rotate around
        private double x, z; //Coordinated to rotate

        public XZ(double cx, double x, double cz, double z, float rotation) {
            this.cx = cx; //X to rotate around
            this.x = x; //Current X pos to be rotated
            this.cz = cz; //Z to rotate around
            this.z = z; //Current Z pos to be rotated
            if (rotation < 0) //Make sure rotation is positive
                rotation = (rotation - 180) * -1;
            rotateCoords(Math.toRadians(rotation)); //Rotate
        }

        private void rotateCoords(double angle) {
            double cos = Math.cos(angle);
            double sin = Math.sin(angle);
            double temp;
            temp = ((x - cx) * cos - (z - cz) * sin) + cx;
            z = ((x - cx) * sin + (z - cz) * cos) + cz;
            x = temp;
        }

        public double getRotatedX() {
            return x;
        }

        public double getRotatedZ() {
            return z;
        }
    }
}
