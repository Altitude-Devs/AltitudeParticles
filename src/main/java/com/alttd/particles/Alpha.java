package com.alttd.particles;

import com.alttd.objects.APartType;
import com.alttd.objects.AParticle;
import com.alttd.objects.Frame;
import com.alttd.objects.ParticleSet;
import com.alttd.storage.ParticleStorage;
import com.destroystokyo.paper.ParticleBuilder;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Alpha {
    private static final ItemStack itemStack;

    static {
        MiniMessage miniMessage = MiniMessage.miniMessage();
        itemStack = new ItemStack(Material.ANVIL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(miniMessage.deserialize("<gold>Alpha Particles</gold>"));
        itemMeta.lore(List.of(
                miniMessage.deserialize("<dark_aqua>An exclusive particle</dark_aqua>"),
                miniMessage.deserialize("<dark_aqua>set for Alpha testers!</dark_aqua>")));
        itemStack.setItemMeta(itemMeta);
    }

    public Alpha() {
        List<Frame> frameList = new ArrayList<>();

        frameList.add(new Frame(frameOne()));

        ParticleStorage.addParticleSet(APartType.KILL, new ParticleSet(frameList, 5, 10, 10, APartType.KILL, "ALPHA_KILL", "apart.particle.alpha", itemStack));
        ParticleStorage.addParticleSet(APartType.TELEPORT_ARRIVE, new ParticleSet(frameList, 5, 10, 10, APartType.TELEPORT_ARRIVE, "ALPHA_TELEPORT", "apart.particle.alpha", itemStack));
        ParticleStorage.addParticleSet(APartType.CLICK_BLOCK, new ParticleSet(frameList, 5, 10, 10, APartType.CLICK_BLOCK, "ALPHA_CLICK", "apart.particle.alpha", itemStack));
    }


    public List<AParticle> frameOne() {
        List<AParticle> list = new ArrayList<>();
        double[] xPts = {0.23, 0.21, 0.19, 0.17, 0.15, 0.13, 0.11, 0.09, 0.06, 0.02, -0.03, -0.08, -0.14, -0.19, -0.24, -0.26, -0.28, -0.28, -0.27, -0.25, -0.22, -0.18, -0.13, -0.08, -0.03, 0.00, 0.03, 0.06, 0.09, 0.11, 0.14, 0.15, 0.16, 0.18, 0.22, 0.26, 0.30, 0.32};
        double[] zPts = {0.91, 0.86, 0.81, 0.76, 0.70, 0.63, 0.58, 0.52, 0.47, 0.42, 0.39, 0.38, 0.39, 0.42, 0.47, 0.52, 0.58, 0.63, 0.69, 0.75, 0.81, 0.86, 0.89, 0.90, 0.89, 0.87, 0.83, 0.79, 0.74, 0.69, 0.57, 0.51, 0.46, 0.42, 0.40, 0.40, 0.42, 0.47};

        for(int i = 0; i < xPts.length; i++) {
            list.add(new AParticle(xPts[i] * 5, 2, zPts[i] * 5, 0, new ParticleBuilder(Particle.REDSTONE).color(Color.GRAY).count(1)));
        }
        return list;
    }
}
