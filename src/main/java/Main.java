import arc.ApplicationListener;
import arc.Core;
import arc.util.CommandHandler;
import mindustry.Vars;
import mindustry.content.UnitTypes;
import mindustry.entities.type.BaseUnit;
import mindustry.entities.type.Player;
import mindustry.game.Team;
import mindustry.plugin.Plugin;
import mindustry.type.UnitType;

public class Main extends Plugin {
    public Main(){}

    @Override
    public void registerClientCommands(CommandHandler handler){
        handler.<Player>register("spawn", "<mob_name> <count> <team>", "Spawn mob in player position", (arg, player) -> {
            if(!player.isAdmin){
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
                case "sharded":
                    team = Team.sharded;
                    break;
                case "blue":
                    team = Team.blue;
                    break;
                case "crux":
                    team = Team.crux;
                    break;
                case "derelict":
                    team = Team.derelict;
                    break;
                case "green":
                    team = Team.green;
                    break;
                case "purple":
                    team = Team.purple;
                    break;
                default:
                    player.sendMessage("Avaliable team: sharded, blue, crux, derelict, green, purple");
                    return;
            }

            for (int i = 0; count > i; i++) {
                BaseUnit baseUnit = unit.create(team);
                baseUnit.set(player.x, player.y);
                baseUnit.add();
            }

            new Thread(() -> {
                ApplicationListener listener = new ApplicationListener() {
                    @Override
                    public void update() {
                        player.heal();
                    }
                };
                Core.app.addListener(listener);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ignored) {}
                Core.app.removeListener(listener);
            }).start();
        });
    }
}
