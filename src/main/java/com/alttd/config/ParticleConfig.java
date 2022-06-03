package com.alttd.config;

import com.alttd.objects.APartType;
import com.alttd.objects.AParticle;
import com.alttd.objects.Frame;
import com.alttd.objects.ParticleSet;
import com.alttd.storage.ParticleStorage;
import com.alttd.util.Logger;
import com.destroystokyo.paper.ParticleBuilder;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParticleConfig extends AbstractConfig {

    static ParticleConfig config;
    public ParticleConfig() {
        super(new File(System.getProperty("user.home") + File.separator + "share" + File.separator + "configs" + File.separator + "AltitudeParticles"), "particles.yml");
    }

    public static void reload() {
        config = new ParticleConfig();

        config.readConfig(config.getClass(), null);
    }

    private static void loadParticles() {
        ParticleStorage.clear();
        ConfigurationSection particles = config.getConfigurationSection("particles");
        if (particles == null) {
            Logger.warning("No particles in particles config");
            return;
        }
        for (String key : particles.getKeys(false)) {
            ConfigurationSection cs = particles.getConfigurationSection(key);
            if (cs == null)
                continue;
            APartType aPartType = APartType.valueOf(cs.getString("part-type"));
            ParticleSet particleSet = new ParticleSet(
                    getAParticle(cs),
                    cs.getInt("frame-delay"),
                    cs.getInt("repeat"),
                    cs.getInt("repeat-delay"),
                    aPartType,
                    cs.getString("unique-name"),
                    cs.getString("permission"),
                    new ItemStack(Material.valueOf(cs.getString("material"))));
            ParticleStorage.addParticleSet(aPartType, particleSet);
        }
    }

    private static List<Frame> getAParticle(ConfigurationSection configurationSection) {
        List<Frame> list = new ArrayList<>();
        ConfigurationSection frames = configurationSection.getConfigurationSection("frames");
        if (frames == null) {
            Logger.warning("Unable to find frames for something");
            return null;
        }
        for (String key : frames.getKeys(false)) {
            ConfigurationSection cs = frames.getConfigurationSection(key);
            if (cs == null) {
                Logger.warning("Unable to load particle %", key);
                return null;
            }
            int offset = frames.getInt("offset-range");
            List<AParticle> aParticleList = new ArrayList<>();
            List<Double> x = Arrays.stream(cs.getString("x").split(", ")).map(Double::valueOf).collect(Collectors.toList());
            List<Double> y = Arrays.stream(cs.getString("y").split(", ")).map(Double::valueOf).collect(Collectors.toList());
            List<Double> z = Arrays.stream(cs.getString("z").split(", ")).map(Double::valueOf).collect(Collectors.toList());
            if (x.size() != y.size() || y.size() != z.size()) {
                Logger.warning("Unable to load % xyz is not the same length", key);
            }
            ParticleBuilder particleBuilder = getParticleBuilder(cs);
            for (int i = 0; i < x.size(); i++) {
                aParticleList.add(new AParticle(x.get(i), y.get(i), z.get(i), offset, particleBuilder));
            }
            list.add(new Frame(aParticleList));
        }
        return list;
    }

    private static ParticleBuilder getParticleBuilder(ConfigurationSection cs) {
        cs.getString("particle");
        ConfigurationSection color = cs.getConfigurationSection("color");
        ParticleBuilder particle = new ParticleBuilder(Particle.valueOf(cs.getString("particle")));
        if (color != null) {
            particle = particle.color(color.getInt("r"), color.getInt("g"), color.getInt("b"));
        }
        return particle.count(cs.getInt("count", 1));
    }
}
