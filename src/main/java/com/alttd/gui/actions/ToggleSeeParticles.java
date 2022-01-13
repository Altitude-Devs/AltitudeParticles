package com.alttd.gui.actions;

import com.alttd.database.Queries;
import com.alttd.gui.GUIAction;
import com.alttd.gui.windows.OpenParticleGUI;
import com.alttd.storage.PlayerSettings;
import org.bukkit.entity.Player;

public class ToggleSeeParticles implements GUIAction {

    OpenParticleGUI openParticleGUI;
    PlayerSettings playerSettings;

    public ToggleSeeParticles(OpenParticleGUI openParticleGUI, PlayerSettings playerSettings) {
        this.openParticleGUI = openParticleGUI;
        this.playerSettings = playerSettings;
    }

    @Override
    public void click(Player player) {
        boolean result = playerSettings.toggleSeeingParticles();
        openParticleGUI.updateSettingSlots(playerSettings);
        Queries.setSeeingParticles(player.getUniqueId(), result);
    }
}
