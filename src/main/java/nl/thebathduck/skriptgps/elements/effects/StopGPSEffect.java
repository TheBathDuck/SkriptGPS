package nl.thebathduck.skriptgps.elements.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.live.bemmamin.gps.api.GPSAPI;
import javax.annotation.Nullable;
import nl.thebathduck.skriptgps.SkriptGPSPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class StopGPSEffect extends Effect {
    private Expression<Player> player;

    @Override
    public void execute(Event event) {
        if (this.player != null) {
            GPSAPI gps = SkriptGPSPlugin.getInstance().getGps();
            if (gps.gpsIsActive(this.player.getSingle(event))) {
                gps.stopGPS(this.player.getSingle(event));
            }

        }
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "stop gps for %player%.";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        this.player = (Expression<Player>) expressions[0];
        return true;
    }

}