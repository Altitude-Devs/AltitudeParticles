package com.alttd.config;


import com.alttd.objects.APartType;
import com.alttd.objects.AParticle;
import com.alttd.objects.Frame;
import com.alttd.objects.ParticleSet;
import com.alttd.storage.ParticleStorage;
import com.alttd.util.Logger;
import com.destroystokyo.paper.ParticleBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;

public class ParticleConfig {

    private static final File particlesDir = new File(System.getProperty("user.home") + File.separator + "share" + File.separator + "configs"
            + File.separator + "AltitudeParticles" + File.separator + "particles");
    private static ParticleConfig instance = null;

    private static ParticleConfig getInstance() {
        if (instance == null)
            instance = new ParticleConfig();
        return instance;
    }

    /**
     * Finds all files in particles directory that are valid .json files
     * @return all files found
     */
    private List<File> getJsonFiles() {
        List<File> files = new ArrayList<>();
        if (!particlesDir.exists()) {
            if (!particlesDir.mkdir())
                Logger.warning("Unable to create particles directory");
            return files;
        }
        if (!particlesDir.isDirectory()) {
            Logger.warning("Particles directory doesn't exist (it's a file??)");
            return files;
        }
        File[] validFiles = particlesDir.listFiles(file -> file.isFile() && file.canRead() && file.getName().endsWith(".json"));
        if (validFiles == null)
            return files;
        files.addAll(List.of(validFiles));
        return files;
    }

    public ParticleSet loadParticle(JSONObject jsonObject) throws Exception {
        String particleName = (String) jsonObject.get("particle_name");
        String displayName = (String) jsonObject.get("display_name");
        APartType aPartType = APartType.valueOf((String) jsonObject.get("particle_type"));
        String lore = (String) jsonObject.get("lore");
        ItemStack displayItem = new ItemStack(Material.valueOf((String) jsonObject.get("display_item")));
        String permission = (String) jsonObject.get("permission");
        String packagePermission = (String) jsonObject.get("package_permission");
        int frameDelay = (int) (long) jsonObject.get("frame_delay");
        int repeat = (int) (long)  jsonObject.get("repeat");
        int repeatDelay = (int) (long) jsonObject.get("repeat_delay");
        double randomOffset = (double) jsonObject.get("random_offset");
        JSONObject frames = (JSONObject) jsonObject.get("frames");
        List<Frame> loadedFrames = new ArrayList<>();
        for (Object key : frames.keySet()) {
            Object o = frames.get(key);
            List<AParticle> aParticleList = new ArrayList<>();
            for (Object o1 : ((JSONArray) o)) {
                JSONObject pData = (JSONObject) o1;
                Particle particleType = Particle.valueOf((String) pData.get("particle_type"));

                double x = (double) pData.get("x");
                double y = (double) pData.get("y");
                double z = (double) pData.get("z");
                ParticleBuilder particleBuilder = new ParticleBuilder(particleType);
                if (particleType.getDataType().equals(Particle.DustOptions.class)) {
                    int rgb = HexFormat.fromHexDigits((String) pData.get("color"));
                    particleBuilder.data(new Particle.DustOptions(Color.fromBGR(rgb), 1));
                } else if (particleType.getDataType().equals(MaterialData.class)) {
                    //TODO implement
                } else if (particleType.getDataType().equals(BlockData.class)) {
                    //TODO implement
                } else if (particleType.getDataType().equals(Integer.class)) {
                    //TODO implement
                } else if (particleType.getDataType().equals(Float.class)) {
                    //TODO implement
                } else if (particleType.getDataType().equals(Particle.DustTransition.class)) {
                    //TODO implement
                } else if (particleType.getDataType().equals(ItemStack.class)) {
                    //TODO implement
                }
                aParticleList.add(new AParticle(x, y, z, randomOffset, particleBuilder));
            }
            loadedFrames.add(new Frame(aParticleList));
        }
        return new ParticleSet(loadedFrames, displayName, List.of(lore.split("\n")), frameDelay, repeat, repeatDelay, aPartType, particleName, permission, packagePermission, displayItem);
    }

    public static void reload() {
        ParticleStorage.clear();
        ParticleConfig instance = getInstance();
        for (File file : instance.getJsonFiles()) {
            JSONParser parser = new JSONParser();
            try {
                Object obj = parser.parse(new FileReader(file));
                ParticleSet particleSet = instance.loadParticle((JSONObject) obj);
                ParticleStorage.addParticleSet(particleSet.getAPartType(), particleSet);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        //TODO implement
    }
}

