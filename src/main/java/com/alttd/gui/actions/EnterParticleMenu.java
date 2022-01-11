package com.alttd.gui.actions;

import com.alttd.gui.GUIAction;
import com.alttd.gui.windows.ChooseParticleGUI;
import com.alttd.objects.APartType;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public class EnterParticleMenu implements GUIAction {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    private final APartType aPartType;

    public EnterParticleMenu(APartType aPartType) {
        this.aPartType = aPartType;
    }

    @Override
    public void click(Player player) {
        ChooseParticleGUI chooseParticleGUI = new ChooseParticleGUI(aPartType, miniMessage.deserialize(aPartType.getName()));
        chooseParticleGUI.open(player);
    }
}