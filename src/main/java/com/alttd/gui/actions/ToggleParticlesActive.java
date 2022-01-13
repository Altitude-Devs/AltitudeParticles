package com.alttd.gui.actions;

import com.alttd.database.Queries;
import com.alttd.gui.GUIAction;
import com.alttd.gui.windows.OpenParticleGUI;
import com.alttd.storage.PlayerSettings;
import org.bukkit.entity.Player;

public class ToggleParticlesActive implements GUIAction {

    OpenParticleGUI openParticleGUI;
    PlayerSettings playerSettings;

    public ToggleParticlesActive(OpenParticleGUI openParticleGUI, PlayerSettings playerSettings) {
        this.openParticleGUI = openParticleGUI;
        this.playerSettings = playerSettings;
    }

    @Override
    public void click(Player player) {
        boolean result = playerSettings.toggleParticlesActive();
        openParticleGUI.updateSettingSlots(playerSettings);
        Queries.setParticlesActive(player.getUniqueId(), result);
    }
}
