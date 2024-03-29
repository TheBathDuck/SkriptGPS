package nl.thebathduck.skriptgps;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.live.bemmamin.gps.api.GPSAPI;
import lombok.Getter;
import nl.thebathduck.skriptgps.elements.conditions.HasActiveGPS;
import nl.thebathduck.skriptgps.elements.effects.SetGPSEffect;
import nl.thebathduck.skriptgps.elements.effects.StopGPSEffect;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

@Getter
public class SkriptGPSPlugin extends JavaPlugin {

    @Getter
    private static SkriptGPSPlugin instance;

    private SkriptAddon addon;
    private GPSAPI gps;

    @Override
    public void onEnable() {
        instance = this;

        this.gps = new GPSAPI(this);
        this.addon = Skript.registerAddon(this);

        try {
            this.addon.loadClasses("nl.thebathduck.skriptgps", "elements");
        } catch (IOException e) {
            this.getLogger().severe("Could not register Skript classes.");
        }

        Skript.registerCondition(HasActiveGPS.class, "%player% has [active] gps");
        Skript.registerEffect(SetGPSEffect.class, "start gps for %player% to %string%");
        Skript.registerEffect(StopGPSEffect.class, "stop gps for %player%");
    }

}