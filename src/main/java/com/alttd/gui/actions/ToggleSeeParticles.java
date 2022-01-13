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
        Queries.setSeeingParticles(player.getUniqueId(), result);

        if (result || !playerSettings.hasActiveParticles()) {
            openParticleGUI.updateSettingSlots(playerSettings);
            return;
        }

        boolean result2 = playerSettings.toggleParticlesActive();
        openParticleGUI.updateSettingSlots(playerSettings);
        Queries.setParticlesActive(player.getUniqueId(), result2);
    }
}
