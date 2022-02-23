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
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.List;

public class Cupid {
    private static final ItemStack itemStack;

    static {
        MiniMessage miniMessage = MiniMessage.miniMessage();
        itemStack = new ItemStack(Material.PINK_TULIP);
        /*PotionMeta meta = (PotionMeta) itemStack.getItemMeta();
        meta.setBasePotionData(new PotionData(PotionType.REGEN));
        itemStack.setItemMeta(meta);*/
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(miniMessage.deserialize("<gold>Cupid Particles</gold>"));
        itemMeta.lore(List.of(
                miniMessage.deserialize("<dark_aqua>No one is immune</dark_aqua>"),
                miniMessage.deserialize("<dark_aqua>to Cupid's arrow...</dark_aqua>")));
        itemStack.setItemMeta(itemMeta);
    }

    public Cupid() {
        List<Frame> frameList = new ArrayList<>();

        frameList.add(new Frame(frameOne()));

        ParticleStorage.addParticleSet(APartType.HEAD, new ParticleSet(frameList, 10, 5, 40, APartType.HEAD, "CUPID_HEAD", "apart.particle.test", itemStack));
        ParticleStorage.addParticleSet(APartType.TELEPORT_ARRIVE, new ParticleSet(frameList, 10, 5, 40, APartType.TELEPORT_ARRIVE, "CUPID_TELEPORT", "apart.particle.test", itemStack));
        ParticleStorage.addParticleSet(APartType.CLICK_BLOCK, new ParticleSet(frameList, 10, 5, 40, APartType.CLICK_BLOCK, "CUPID_CLICK", "apart.particle.test", itemStack));
    }


    public List<AParticle> frameOne() {
        List<AParticle> list = new ArrayList<>();

        list.add(new AParticle((Math.random() * 2) - 1, (Math.random() * 2) - 1, (Math.random() * 2) - 1, 0.5, new ParticleBuilder(Particle.HEART).count(1)));
        list.add(new AParticle((Math.random() * 2) - 1, (Math.random() * 2) - 1, (Math.random() * 2) - 1, 0.5, new ParticleBuilder(Particle.HEART).count(1)));
        list.add(new AParticle((Math.random() * 2) - 1, (Math.random() * 2) - 1, (Math.random() * 2) - 1, 0.5,new ParticleBuilder(Particle.HEART).count(1)));
        return list;
    }
}