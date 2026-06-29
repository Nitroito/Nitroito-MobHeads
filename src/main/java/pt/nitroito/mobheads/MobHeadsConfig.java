package pt.nitroito.mobheads;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import pt.nitroito.mobheads.config.MobHeadsConfigEvents;
import pt.nitroito.mobheads.config.MobHeadsConfigFile;
import pt.nitroito.mobheads.utils.MobHeadsRandom;


public class MobHeadsConfig {
    public static MobHeadsConfigFile CONFIG_FILE;

    public static boolean spawnColoredSheep;
    public static float spawnColoredSheepChance;
    public static boolean spawnColoredSlimes;
    public static float spawnColoredSlimeChance;
    public static boolean spawnColoredShulkers;
    public static float spawnColoredShulkerChance;
    public static boolean spawnJebSheep;
    public static float spawnJebSheepChance;
    public static boolean spawnKillerRabbit;
    public static float spawnKillerRabbitChance;
    public static boolean spawnToastRabbit;
    public static float spawnToastRabbitChance;

    public static boolean canSpawnColoredSheep(){return (spawnColoredSheep && MobHeadsRandom.nextFloat()<=spawnColoredSheepChance);}
    public static boolean canSpawnColoredSlime(){return (spawnColoredSlimes && MobHeadsRandom.nextFloat()<=spawnColoredSlimeChance);}
    public static boolean canSpawnColoredShulker(){return (spawnColoredShulkers && MobHeadsRandom.nextFloat()<=spawnColoredShulkerChance);}
    public static boolean canSpawnJebSheep(){return (spawnJebSheep && MobHeadsRandom.nextFloat()<=spawnJebSheepChance);}
    public static boolean canSpawnKillerRabbit(){return (spawnKillerRabbit && MobHeadsRandom.nextFloat()<=spawnKillerRabbitChance);}
    public static boolean canSpawnToastRabbit(){return (spawnToastRabbit && MobHeadsRandom.nextFloat()<=spawnToastRabbitChance);}

    public static void loadConfigFile(MobHeadsConfigFile config) {
        spawnColoredSheep = config.spawnColoredSheep;
        spawnColoredSheepChance = config.spawnColoredSheepChance;
        spawnColoredSlimes = config.spawnColoredSlimes;
        spawnColoredSlimeChance = config.spawnColoredSlimesChance;
        spawnColoredShulkers = config.spawnColoredShulkers;
        spawnColoredShulkerChance = config.spawnColoredShulkersChance;
        spawnJebSheep = config.spawnJebSheep;
        spawnJebSheepChance = config.spawnJebSheepChance;
        spawnKillerRabbit = config.spawnKillerRabbit;
        spawnKillerRabbitChance = config.spawnKillerRabbitChance;
        spawnToastRabbit = config.spawnToastRabbit;
        spawnToastRabbitChance = config.spawnToastRabbitChance;
    }

    public static void register() {
        AutoConfig.register(MobHeadsConfigFile.class, Toml4jConfigSerializer::new);
        CONFIG_FILE = AutoConfig.getConfigHolder(MobHeadsConfigFile.class).getConfig();
        MobHeadsConfig.loadConfigFile(CONFIG_FILE);
        MobHeadsConfigEvents.fire();
    }
}
