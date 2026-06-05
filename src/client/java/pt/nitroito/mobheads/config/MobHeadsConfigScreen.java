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
        toggles.addEntry(entry.startBooleanToggle(Component.literal("Colored Sheep"),config.spawnColoredSheeps)
            .setDefaultValue(false)
            .setTooltip(Component.literal("Allow colored sheep to spawn naturally"))
            .setSaveConsumer(v -> config.spawnColoredSheeps = v)
            .build());
        toggles.addEntry(entry.startBooleanToggle(Component.literal("Colored Slimes"),config.spawnColoredSlimes)
            .setDefaultValue(false)
            .setTooltip(Component.literal("Allow colored slime to spawn naturally"))
            .setSaveConsumer(v -> config.spawnColoredSlimes = v)
            .build());
        toggles.addEntry(entry.startBooleanToggle(Component.literal("Colored Shulkers"),config.spawnColoredShulkers)
            .setDefaultValue(false)
            .setTooltip(Component.literal("Allow colored shulker to spawn naturally"))
            .setSaveConsumer(v -> config.spawnColoredShulkers = v)
            .build());
        toggles.addEntry(entry.startBooleanToggle(Component.literal("Jeb Sheep"),config.spawnJebSheep)
            .setDefaultValue(false)
            .setTooltip(Component.literal("Allow \"jeb_\" sheep to spawn naturally"))
            .setSaveConsumer(v -> config.spawnJebSheep = v)
            .build());
        toggles.addEntry(entry.startBooleanToggle(Component.literal("Toast Rabbit"),config.spawnToastRabbit)
            .setDefaultValue(false)
            .setTooltip(Component.literal("Allow \"Toast\" rabbit to spawn naturally"))
            .setSaveConsumer(v -> config.spawnToastRabbit = v)
            .build());
        toggles.addEntry(entry.startBooleanToggle(Component.literal("Killer Rabbit"),config.spawnKillerRabbit)
            .setDefaultValue(false)
            .setTooltip(Component.literal("Allow \"The Killer Bunny\" to spawn naturally"))
            .setSaveConsumer(v -> config.spawnKillerRabbit = v)
            .build());

        //[ Spawn Chances ]*******************************************************************************************//
        ConfigCategory chances = builder.getOrCreateCategory(Component.literal("Spawn Chances"));
        chances.addEntry(entry.startFloatField(Component.literal("Colored Sheep Chance"),config.spawnColoredSheepChance)
            .setDefaultValue(0.5f).setMin(0.0f).setMax(1.0f)
            .setTooltip(Component.literal("Chance for colored Sheep spawn"))
            .setSaveConsumer(v -> config.spawnColoredSheepChance = v)
            .build());
        chances.addEntry(entry.startFloatField(Component.literal("Colored Slime Chance"),config.spawnColoredSlimeChance)
            .setDefaultValue(0.5f).setMin(0.0f).setMax(1.0f)
            .setTooltip(Component.literal("Chance for colored Slime spawn"))
            .setSaveConsumer(v -> config.spawnColoredSlimeChance = v)
            .build());
        chances.addEntry(entry.startFloatField(Component.literal("Colored Shulker Chance"),config.spawnColoredShulkerChance)
            .setDefaultValue(0.5f).setMin(0.0f).setMax(1.0f)
            .setTooltip(Component.literal("Chance for colored Shulker spawn"))
            .setSaveConsumer(v -> config.spawnColoredShulkerChance = v)
            .build());
        chances.addEntry(entry.startFloatField(Component.literal("\"Jeb_Sheep\" Chance"),config.spawnKillerRabbitChance)
            .setDefaultValue(0.01f).setMin(0.0f).setMax(1.0f)
            .setTooltip(Component.literal("Chance for Jeb_Sheep spawn"))
            .setSaveConsumer(v -> config.spawnKillerRabbitChance = v)
            .build());
        chances.addEntry(entry.startFloatField(Component.literal("\"The Killer Bunny\" Chance"),config.spawnKillerRabbitChance)
            .setDefaultValue(0.01f).setMin(0.0f).setMax(1.0f)
            .setTooltip(Component.literal("Chance for Killer Rabbit spawn"))
            .setSaveConsumer(v -> config.spawnKillerRabbitChance = v)
            .build());
        chances.addEntry(entry.startFloatField(Component.literal("\"Toast\" Rabbit Chance"),config.spawnToastRabbitChance)
            .setDefaultValue(0.01f).setMin(0.0f).setMax(1.0f)
            .setTooltip(Component.literal("Chance for Toast Rabbit spawn"))
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
