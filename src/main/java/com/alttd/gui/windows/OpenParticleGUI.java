package com.alttd.gui.windows;

import com.alttd.config.Config;
import com.alttd.gui.DefaultGUI;
import com.alttd.gui.actions.EnterParticleMenu;
import com.alttd.gui.actions.ToggleParticlesActive;
import com.alttd.gui.actions.ToggleSeeParticles;
import com.alttd.objects.APartType;
import com.alttd.storage.PlayerSettings;
import com.alttd.util.Logger;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.Template;
import net.kyori.adventure.text.minimessage.template.TemplateResolver;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class OpenParticleGUI extends DefaultGUI {

    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    private static final Component GUIName;
    private static final ItemStack seeOthersOn, seeOthersOff, particlesOn, particlesOff;

    public OpenParticleGUI(Player player) {
        super(GUIName);
        int i = 10;
        for (APartType particlesType : APartType.values()) {
            if (i > 25) {
                Logger.warning("Unable to add the rest of the particles to the GUI (ran out of space)");
                break;
            }
            if (i == 17)
                i = 19;
            setItem(i++, particlesType.getItemStack(), new EnterParticleMenu(particlesType));
        }
        PlayerSettings playerSettings = PlayerSettings.getPlayer(player.getUniqueId());
        setItem(25, playerSettings.isSeeingParticles() ? seeOthersOn : seeOthersOff, new ToggleSeeParticles(this, playerSettings));
        setItem(26, playerSettings.hasActiveParticles() ? particlesOn : particlesOff, new ToggleParticlesActive(this, playerSettings));
    }

    public void updateSettingSlots(PlayerSettings playerSettings) {
        setItem(25, playerSettings.isSeeingParticles() ? seeOthersOn : seeOthersOff, new ToggleSeeParticles(this, playerSettings));
        setItem(26, playerSettings.hasActiveParticles() ? particlesOn : particlesOff, new ToggleParticlesActive(this, playerSettings));
    }

    static { //Init gui name
        GUIName = miniMessage.deserialize(Config.PARTICLE_TYPE_GUI_NAME);
    }

    static { //init APart items
        Arrays.stream(APartType.values()).forEach(particlesType -> {
            ItemStack itemStack = new ItemStack(particlesType.getMaterial());
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.displayName(miniMessage.deserialize(Config.PARTICLE_TYPE_BUTTON_NAME,
                    TemplateResolver.resolving(Template.template("name", particlesType.getDisplayName()))));
            itemStack.setItemMeta(itemMeta);
            particlesType.setItemStack(itemStack);
        });
    }

    static { //init setting buttons
        ItemMeta itemMeta;

        seeOthersOn = new ItemStack(Material.PLAYER_HEAD);
        itemMeta = seeOthersOn.getItemMeta();
        itemMeta.displayName(miniMessage.deserialize(Config.SEE_OTHERS_ON));
        itemMeta.displayName(miniMessage.deserialize(Config.SEE_OTHERS_ON_DESC));
        seeOthersOn.setItemMeta(itemMeta);

        seeOthersOff = new ItemStack(Material.SKELETON_SKULL);
        itemMeta = seeOthersOff.getItemMeta();
        itemMeta.displayName(miniMessage.deserialize(Config.SEE_OTHERS_OFF));
        itemMeta.displayName(miniMessage.deserialize(Config.SEE_OTHERS_OFF_DESC));
        seeOthersOff.setItemMeta(itemMeta);

        particlesOn = new ItemStack(Material.LIGHT);
        itemMeta = particlesOn.getItemMeta();
        itemMeta.displayName(miniMessage.deserialize(Config.PARTICLES_ON));
        itemMeta.displayName(miniMessage.deserialize(Config.PARTICLES_ON_DESC));
        particlesOn.setItemMeta(itemMeta);

        particlesOff = new ItemStack(Material.BARRIER);
        itemMeta = particlesOff.getItemMeta();
        itemMeta.displayName(miniMessage.deserialize(Config.PARTICLES_OFF));
        itemMeta.displayName(miniMessage.deserialize(Config.PARTICLES_OFF_DESC));
        particlesOff.setItemMeta(itemMeta);
    }
}
