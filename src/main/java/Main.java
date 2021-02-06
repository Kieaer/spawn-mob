import arc.util.CommandHandler;
import mindustry.Vars;
import mindustry.game.Team;
import mindustry.gen.Playerc;
import mindustry.gen.Unit;
import mindustry.mod.Plugin;
import mindustry.type.UnitType;

public class Main extends Plugin {
    @Override
    public void registerClientCommands(CommandHandler handler){
        handler.<Playerc>register("spawn", "<mob_name> <count> <team>", "Spawn mob in player position", (arg, player) -> {
            if(!player.admin()){
                player.sendMessage("You're not admin!");
                return;
            }

            UnitType unit = Vars.content.units().find(unitType -> unitType.name.equals(arg[0]));
            if(unit == null) {
                player.sendMessage("Mob name not found! avaliable mob name: draug, spirit, phantom, wraith, ghoul, revenant, lich, reaper, dagger, crawler, titan, fortress, eruptor, chaosArray, eradicator");
                return;
            }

            int count;
            try {
                count = Integer.parseInt(arg[1]);
            } catch (NumberFormatException e) {
                player.sendMessage("The number of mobs must be a number!");
                return;
            }
            Team team;
            switch (arg[4]) {
                case "sharded" -> team = Team.sharded;
                case "blue" -> team = Team.blue;
                case "crux" -> team = Team.crux;
                case "derelict" -> team = Team.derelict;
                case "green" -> team = Team.green;
                case "purple" -> team = Team.purple;
                default -> {
                    player.sendMessage("Avaliable team: sharded, blue, crux, derelict, green, purple");
                    return;
                }
            }

            for (int i = 0; count > i; i++) {
                Unit baseUnit = unit.create(team);
                baseUnit.set(player.getX(), player.getY());
                baseUnit.add();
            }
        });
    }
}
