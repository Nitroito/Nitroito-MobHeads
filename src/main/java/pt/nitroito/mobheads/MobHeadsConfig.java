package pt.nitroito.mobheads;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import pt.nitroito.mobheads.config.MobHeadsConfigEvents;
import pt.nitroito.mobheads.config.MobHeadsConfigFile;
import pt.nitroito.mobheads.utils.MobHeadsRandom;

public class MobHeadsConfig {
    public static MobHeadsConfigFile CONFIG_FILE;

    public static boolean spawnColoredSheeps;
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

    public static boolean canSpawnColoredSheep(){return (MobHeadsConfig.spawnColoredSheeps && MobHeadsRandom.nextFloat()<=MobHeadsConfig.spawnColoredSheepChance);}
    public static boolean canSpawnColoredSlime(){return (MobHeadsConfig.spawnColoredSlimes && MobHeadsRandom.nextFloat()<=MobHeadsConfig.spawnColoredSlimeChance);}
    public static boolean canSpawnColoredShulker(){return (MobHeadsConfig.spawnColoredShulkers && MobHeadsRandom.nextFloat()<=MobHeadsConfig.spawnColoredShulkerChance);}
    public static boolean canSpawnJebSheep(){return (MobHeadsConfig.spawnJebSheep && MobHeadsRandom.nextFloat()<=MobHeadsConfig.spawnJebSheepChance);}
    public static boolean canSpawnKillerRabbit(){return (MobHeadsConfig.spawnKillerRabbit && MobHeadsRandom.nextFloat()<=MobHeadsConfig.spawnKillerRabbitChance);}
    public static boolean canSpawnToastRabbit(){return (MobHeadsConfig.spawnToastRabbit && MobHeadsRandom.nextFloat()<=MobHeadsConfig.spawnToastRabbitChance);}

    public static void loadConfigFile(MobHeadsConfigFile config) {
        spawnColoredSheeps = config.spawnColoredSheeps;
        spawnColoredSheepChance = config.spawnColoredSheepChance;
        spawnColoredSlimes = config.spawnColoredSlimes;
        spawnColoredSlimeChance = config.spawnColoredSlimeChance;
        spawnColoredShulkers = config.spawnColoredShulkers;
        spawnColoredShulkerChance = config.spawnColoredShulkerChance;
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

