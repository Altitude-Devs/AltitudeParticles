package com.alttd.gui.actions;

import com.alttd.gui.GUIAction;
import com.alttd.objects.ParticleSet;
import org.bukkit.entity.Player;

public class ActivateParticleSet implements GUIAction {

    ParticleSet particleSet;

    public ActivateParticleSet(ParticleSet particleSet) {
        this.particleSet = particleSet;
    }

    @Override
    public void click(Player player) {
        //TODO activate particle
    }
}
