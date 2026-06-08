package pt.nitroito.mobheads.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;


@Config(name = "mobheads")
public class MobHeadsConfigFile implements ConfigData {
    // SHEEP
    public boolean spawnColoredSheep = false;
    public float spawnColoredSheepChance = 0.50f;
    public boolean spawnJebSheep = false;
    public float spawnJebSheepChance = 0.05f;
    // SLIMES
    public boolean spawnColoredSlimes = false;
    public float spawnColoredSlimesChance = 0.50f;
    // SHULKERS
    public boolean spawnColoredShulkers = false;
    public float spawnColoredShulkersChance = 0.50f;
    // RABBITS
    public boolean spawnToastRabbit = false;
    public float spawnToastRabbitChance = 0.05f;
    public boolean spawnKillerRabbit = false;
    public float spawnKillerRabbitChance = 0.05f;
}
