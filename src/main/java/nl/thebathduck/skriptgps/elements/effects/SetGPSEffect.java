package nl.thebathduck.skriptgps.elements.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import javax.annotation.Nullable;

import nl.thebathduck.skriptgps.SkriptGPSPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class SetGPSEffect extends Effect {
    private Expression<Player> player;
    private Expression<String> gpsName;

    @Override
    public void execute(Event event) {
        if (this.player != null) {
            if (this.gpsName != null) {
                SkriptGPSPlugin.getInstance().getGps().startGPS(this.player.getSingle(event), this.gpsName.getSingle(event));
            }
        }
    }


    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "start gps for %player% to %string%";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        this.player = (Expression<Player>) expressions[0];
        this.gpsName = (Expression<String>) expressions[1];
        return true;
    }

}