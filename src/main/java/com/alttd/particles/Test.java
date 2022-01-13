package com.alttd.particles;

import com.alttd.objects.APartType;
import com.alttd.objects.Frame;
import com.alttd.objects.ParticleSet;
import com.alttd.storage.ParticleStorage;
import com.destroystokyo.paper.ParticleBuilder;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public Test() {
        APartType test = APartType.DEATH;
        List<Frame> frameList = new ArrayList<>();
        //
        frameList.add(new Frame(frameOne()));
        //....
        ParticleSet particleSet = new ParticleSet(frameList, 5, 5, test, "apart.particle.test", new ItemStack(Material.CHEST));
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
