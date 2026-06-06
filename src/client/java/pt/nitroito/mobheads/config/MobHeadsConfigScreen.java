package pt.nitroito.mobheads.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import pt.nitroito.mobheads.MobHeadsConfig;

public class MobHeadsConfigScreen {

    public static Screen create(Screen parent) {
        MobHeadsConfigFile config =AutoConfig.getConfigHolder(MobHeadsConfigFile.class).getConfig();
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(Component.literal("MobHeads Settings"));
        ConfigEntryBuilder entry = builder.entryBuilder();

        //[ General Settings ]****************************************************************************************//
        ConfigCategory toggles = builder.getOrCreateCategory(Component.literal("General"));
        toggles.addEntry(entry.startBooleanToggle(Component.literal("Spawn Colored Sheep"),config.spawnColoredSheeps)
            .setDefaultValue(false)
            .setTooltip(Component.literal("Allow colored sheep to spawn naturally"))
            .setSaveConsumer(v -> config.spawnColoredSheeps = v)
            .build());
        toggles.addEntry(entry.startBooleanToggle(Component.literal("Spawn Colored Slimes"),config.spawnColoredSlimes)
            .setDefaultValue(false)
            .setTooltip(Component.literal("Allow colored slime to spawn naturally"))
            .setSaveConsumer(v -> config.spawnColoredSlimes = v)
            .build());
        toggles.addEntry(entry.startBooleanToggle(Component.literal("Spawn Colored Shulkers"),config.spawnColoredShulkers)
            .setDefaultValue(false)
            .setTooltip(Component.literal("Allow colored shulker to spawn naturally"))
            .setSaveConsumer(v -> config.spawnColoredShulkers = v)
            .build());
        toggles.addEntry(entry.startBooleanToggle(Component.literal("Spawn \"Jeb Sheep\""),config.spawnJebSheep)
            .setDefaultValue(false)
            .setTooltip(Component.literal("Allow \"jeb_\" sheep to spawn naturally"))
            .setSaveConsumer(v -> config.spawnJebSheep = v)
            .build());
        toggles.addEntry(entry.startBooleanToggle(Component.literal("Spawn \"Toast\" Bunny"),config.spawnToastRabbit)
            .setDefaultValue(false)
            .setTooltip(Component.literal("Allow \"Toast\" bunny to spawn naturally"))
            .setSaveConsumer(v -> config.spawnToastRabbit = v)
            .build());
        toggles.addEntry(entry.startBooleanToggle(Component.literal("Spawn \"Killer Bunny"),config.spawnKillerRabbit)
            .setDefaultValue(false)
            .setTooltip(Component.literal("Allow \"The Killer Bunny\" to spawn naturally"))
            .setSaveConsumer(v -> config.spawnKillerRabbit = v)
            .build());

        //[ Spawn Chances ]*******************************************************************************************//
        ConfigCategory chances = builder.getOrCreateCategory(Component.literal("Spawn Chances"));
        chances.addEntry(entry.startFloatField(Component.literal("Colored Sheep"),config.spawnColoredSheepChance)
            .setDefaultValue(0.5f).setMin(0.0f).setMax(1.0f)
            .setTooltip(Component.literal("Chance that a colored Sheep spawns naturally"))
            .setSaveConsumer(v -> config.spawnColoredSheepChance = v)
            .build());
        chances.addEntry(entry.startFloatField(Component.literal("Colored Slime"),config.spawnColoredSlimeChance)
            .setDefaultValue(0.5f).setMin(0.0f).setMax(1.0f)
            .setTooltip(Component.literal("Chance that a colored Slime spawns naturally"))
            .setSaveConsumer(v -> config.spawnColoredSlimeChance = v)
            .build());
        chances.addEntry(entry.startFloatField(Component.literal("Colored Shulker"),config.spawnColoredShulkerChance)
            .setDefaultValue(0.5f).setMin(0.0f).setMax(1.0f)
            .setTooltip(Component.literal("Chance that a colored Shulker spawns naturally"))
            .setSaveConsumer(v -> config.spawnColoredShulkerChance = v)
            .build());
        chances.addEntry(entry.startFloatField(Component.literal("\"Jeb Sheep\""),config.spawnKillerRabbitChance)
            .setDefaultValue(0.01f).setMin(0.0f).setMax(1.0f)
            .setTooltip(Component.literal("Chance the a Jeb_Sheep spawns naturally"))
            .setSaveConsumer(v -> config.spawnKillerRabbitChance = v)
            .build());
        chances.addEntry(entry.startFloatField(Component.literal("\"The Killer Bunny\""),config.spawnKillerRabbitChance)
            .setDefaultValue(0.01f).setMin(0.0f).setMax(1.0f)
            .setTooltip(Component.literal("Chance that a \"Killer Rabbit\" spawns naturally"))
            .setSaveConsumer(v -> config.spawnKillerRabbitChance = v)
            .build());
        chances.addEntry(entry.startFloatField(Component.literal("\"Toast\" Bunny"),config.spawnToastRabbitChance)
            .setDefaultValue(0.01f).setMin(0.0f).setMax(1.0f)
            .setTooltip(Component.literal("Chance that a \"Toast\" Rabbit spawn"))
            .setSaveConsumer(v -> config.spawnToastRabbitChance = v)
            .build());

        // SAVE CONFIG
        builder.setSavingRunnable(() -> {
            AutoConfig.getConfigHolder(MobHeadsConfigFile.class).save();
            MobHeadsConfigFile cfg = AutoConfig.getConfigHolder(MobHeadsConfigFile.class).getConfig();
            MobHeadsConfig.loadConfigFile(cfg);
            MobHeadsConfigEvents.fire();
        });

        return builder.build();
    }
}
