package com.alttd.particles;

import com.alttd.objects.APartType;
import com.alttd.objects.Frame;
import com.alttd.objects.ParticleSet;
import com.alttd.storage.ParticleStorage;
import com.destroystokyo.paper.ParticleBuilder;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Test {

    private static final ItemStack itemStack;

    static {
        MiniMessage miniMessage = MiniMessage.miniMessage();
        itemStack = new ItemStack(Material.CHEST);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(miniMessage.deserialize("<gold>TestParticles</gold>"));
        itemMeta.lore(List.of(miniMessage.deserialize("<dark_aqua>A particle to test\nthe functionality of this plugin</dark_aqua>")));
        itemStack.setItemMeta(itemMeta);
    }

    public Test() {
        APartType test = APartType.BREAK_PLACE_BLOCK;
        List<Frame> frameList = new ArrayList<>();
        //
        frameList.add(new Frame(frameOne()));
        //....
        ParticleSet particleSet = new ParticleSet(frameList, 5, 5, test, "UNIQUE_NAME_TEST", "apart.particle.test", itemStack);
        ParticleStorage.addParticleSet(test, particleSet);
    }


    public List<ParticleBuilder> frameOne() {
        List<ParticleBuilder> list = new ArrayList<>();
        list.add(new ParticleBuilder(Particle.TOTEM).offset(0, 2, 0));
        list.add(new ParticleBuilder(Particle.TOTEM).offset(0, 2, 1));
        list.add(new ParticleBuilder(Particle.TOTEM).offset(0, 2, 2));
        list.add(new ParticleBuilder(Particle.TOTEM).offset(0, 2, 3));
        return list;
    }
}
