package nl.thebathduck.skriptgps.elements.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import nl.thebathduck.skriptgps.SkriptGPSPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("GPS")
@Description({"If the player has gps enabled."})
public class HasActiveGPS extends Condition {
    Expression<Player> exprPlayer;

    @Override
    public boolean check(Event event) {
        Player p = this.exprPlayer.getSingle(event);
        if (p == null) {
            return this.isNegated();
        } else {
            return SkriptGPSPlugin.getInstance().getGps().gpsIsActive(p) ? !this.isNegated() : this.isNegated();
        }
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "Has gps active " + SkriptGPSPlugin.getInstance().getGps().gpsIsActive(this.exprPlayer.getSingle(event));
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.exprPlayer = (Expression<Player>) expressions[0];
        return true;
    }

}