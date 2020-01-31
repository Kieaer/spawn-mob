import arc.util.CommandHandler;
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
        handler.<Player>register("spawn", "<mob_name> <count> <x> <y> <team>", "Spawn mob in player position", (arg, player) -> {
            UnitType targetunit;
            switch (arg[0]) {
                case "draug":
                    targetunit = UnitTypes.draug;
                    break;
                case "spirit":
                    targetunit = UnitTypes.spirit;
                    break;
                case "phantom":
                    targetunit = UnitTypes.phantom;
                    break;
                case "wraith":
                    targetunit = UnitTypes.wraith;
                    break;
                case "ghoul":
                    targetunit = UnitTypes.ghoul;
                    break;
                case "revenant":
                    targetunit = UnitTypes.revenant;
                    break;
                case "lich":
                    targetunit = UnitTypes.lich;
                    break;
                case "reaper":
                    targetunit = UnitTypes.reaper;
                    break;
                case "dagger":
                    targetunit = UnitTypes.dagger;
                    break;
                case "crawler":
                    targetunit = UnitTypes.crawler;
                    break;
                case "titan":
                    targetunit = UnitTypes.titan;
                    break;
                case "fortress":
                    targetunit = UnitTypes.fortress;
                    break;
                case "eruptor":
                    targetunit = UnitTypes.eruptor;
                    break;
                case "chaosArray":
                    targetunit = UnitTypes.chaosArray;
                    break;
                case "eradicator":
                    targetunit = UnitTypes.eradicator;
                    break;
                default:
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
            Team targetteam;
            switch (arg[4]) {
                case "sharded":
                    targetteam = Team.sharded;
                    break;
                case "blue":
                    targetteam = Team.blue;
                    break;
                case "crux":
                    targetteam = Team.crux;
                    break;
                case "derelict":
                    targetteam = Team.derelict;
                    break;
                case "green":
                    targetteam = Team.green;
                    break;
                case "purple":
                    targetteam = Team.purple;
                    break;
                default:
                    player.sendMessage("Avaliable team: sharded, blue, crux, derelict, green, purple");
                    return;
            }
            
            float x;
            float y;
            if (arg[2] == "~" && arg[3] == "~") {
                arg[2] = String.valueOf(player.x);
                arg[3] = String.valueOf(player.y);
            }
            if (arg[2].equals("~") && arg[3].equals("~"){
                x = player.getX();
                y = player.getY();
            } else {
                try{
                    x = Float.parseFloat(arg[2]);
                    y = Float.parseFloat(arg[3]);
                } catch (NumberFormatException error){
                    player.sendMessage("x, y value must be number!");
                    return;
                }
            }
            for (int i = 0; count > i; i++) {
                BaseUnit baseUnit = targetunit.create(targetteam);
                baseUnit.set(x, y);
                baseUnit.add();
            }
        });
    }
}
