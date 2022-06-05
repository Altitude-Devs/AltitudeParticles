package com.alttd.particles;

import com.alttd.objects.APartType;
import com.alttd.objects.AParticle;
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
//
//    private static final ItemStack itemStack;
//
//    static {
//        MiniMessage miniMessage = MiniMessage.miniMessage();
//        itemStack = new ItemStack(Material.BUCKET);
//        ItemMeta itemMeta = itemStack.getItemMeta();
//        itemMeta.displayName(miniMessage.deserialize("<gold>TestParticles</gold>"));
//        itemMeta.lore(List.of(
//                miniMessage.deserialize("<dark_aqua>A particle to test</dark_aqua>"),
//                miniMessage.deserialize("<dark_aqua>the functionality of this plugin</dark_aqua>")));
//        itemStack.setItemMeta(itemMeta);
//    }
//
//    public Test() {
//        APartType test = APartType.BREAK_PLACE_BLOCK;
//        List<Frame> frameList = new ArrayList<>();
//        //
//        frameList.add(new Frame(frameOne()));
//        //....
//        ParticleStorage.addParticleSet(test, new ParticleSet(frameList, 5, 5, 0, test, "UNIQUE_NAME_TEST", "apart.particle.test", itemStack));
//        ParticleStorage.addParticleSet(APartType.TRAIL, new ParticleSet(frameList, 5, -1, 40, APartType.TRAIL, "UNIQUE_NAME_TEST", "apart.particle.test", itemStack));
//        ParticleStorage.addParticleSet(APartType.DEATH, new ParticleSet(frameList, 5, 2, 10, APartType.DEATH, "UNIQUE_NAME_TEST", "apart.particle.test", itemStack));
//        ParticleStorage.addParticleSet(APartType.KILL, new ParticleSet(frameList, 5, -1, 10, APartType.KILL, "UNIQUE_NAME_TEST", "apart.particle.test", itemStack));
//        ParticleStorage.addParticleSet(APartType.TELEPORT_ARRIVE, new ParticleSet(frameList, 5, 5, 40, APartType.TELEPORT_ARRIVE, "UNIQUE_NAME_TEST", "apart.particle.test", itemStack));
//        ParticleStorage.addParticleSet(APartType.CLICK_BLOCK, new ParticleSet(frameList, 5, 5, 40, APartType.CLICK_BLOCK, "UNIQUE_NAME_TEST", "apart.particle.test", itemStack));
//    }
//
//
//    public List<AParticle> frameOne() {
//        List<AParticle> list = new ArrayList<>();
//        list.add(new AParticle(0, 2, 0, 0.3, new ParticleBuilder(Particle.TOTEM)));
//        list.add(new AParticle(0, 2, 0, 0.3, new ParticleBuilder(Particle.TOTEM)));
//        list.add(new AParticle(0, 2, 0, 0.3, new ParticleBuilder(Particle.TOTEM)));
//        list.add(new AParticle(0, 2, 0, 0.3, new ParticleBuilder(Particle.TOTEM)));
//        return list;
//    }
}
