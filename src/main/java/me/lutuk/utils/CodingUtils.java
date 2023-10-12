package me.lutuk.utils;

import me.lutuk.ids.Boots.*;
import me.lutuk.ids.Bows.*;
import me.lutuk.ids.Chestplates.Discoverer;
import me.lutuk.ids.Daggers.*;
import me.lutuk.ids.Reliks.*;
import me.lutuk.ids.Spears.*;
import me.lutuk.ids.Wands.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.io.IOException;

import static me.lutuk.ExampleMod.toggle;

public class CodingUtils {
    public static final String RED = "§4";
    public static final String LIGHT_RED = "§c";
    public static final String GOLD = "§6";
    public static final String YELLOW = "§e";
    public static final String GREEN = "§2";
    public static final String LIGHT_GREEN = "§a";
    public static final String AQUA = "§b";
    public static final String DARK_AQUA = "§3";
    public static final String BLUE = "§1";
    public static final String LIGHT_BLUE = "§9";
    public static final String PINK = "§d";
    public static final String PURPLE = "§5";
    public static final String WHITE = "§f";
    public static final String LIGHT_GRAY = "§7";
    public static final String GRAY = "§8";
    public static final String BLACK = "§0";
    public static final String MAGIC = "§k";
    public static final String BOLD = "§l";
    public static final String STRIKE_THROUGH = "§m";
    public static final String UNDERLINE = "§n";
    public static final String ITALIC = "§o";
    public static final String RESET = "§r";
    public static int milestoneOne = 10;
    public static int milestoneTwo = 25;
    public static int milestoneThree = 50;
    public static int milestoneFour = 80;
    public static int milestoneFive = 99;
    public static int milestoneSix = 100;
    public static String colorOne = RED;
    public static String colorTwo = LIGHT_RED;
    public static String colorThree = YELLOW;
    public static String colorFour = LIGHT_GREEN;
    public static String colorFive = AQUA;
    public static String colorSix = GOLD+BOLD;

    public static void msg(String msg) {
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(Text.of(msg));
    }

    public static String color(int weight) {
        if (weight <= milestoneOne) {
            return " "+colorOne + weight;
        } else if (weight <= milestoneTwo) {
            return " "+colorTwo + weight;
        } else if (weight <= milestoneThree) {
            return " "+colorThree + weight;
        } else if (weight <= milestoneFour) {
            return " "+colorFour + weight;
        } else if (weight <= milestoneFive) {
            return " "+colorFive + weight;
        } else if (weight == milestoneSix) {
            return " "+colorSix + weight;
        } else {
            return null;
        }
    }

    public static ItemStack mythicCheck(ItemStack currentItem) throws IOException {
        if (toggle) {
            if (currentItem.getName().contains(Text.of("Unidentified")))
                return currentItem;
            return switch (currentItem.getName().getString()) {
                //BOOTS
                case "§5Boreal", "§5BorealÀ" -> Boreal.Boreal(currentItem, 0);
                case "§5Crusade Sabatons", "§5Crusade SabatonsÀ" -> Crusade_Sabatons.Crusade_Sabatons(currentItem, 0);
                case "§5Dawnbreak", "§5DawnbreakÀ" -> Dawnbreak.Dawnbreak(currentItem, 0);
                case "§5Galleon", "§5GalleonÀ" -> Galleon.Galleon(currentItem, 0);
                case "§5Moontower", "§5MoontowerÀ" -> Moontower.Moontower(currentItem, 0);
                case "§5Resurgence", "§5ResurgenceÀ" -> Resurgence.Resurgence(currentItem, 0);
                case "§5Revenant", "§5RevenantÀ" -> Revenant.Revenant(currentItem, 0);
                case "§5Slayer", "§5SlayerÀ" -> Slayer.Slayer(currentItem, 0);
                case "§5Stardew", "§5StardewÀ" -> Stardew.Stardew(currentItem, 0);
                case "§5Warchief", "§5WarchiefÀ" -> Warchief.Warchief(currentItem, 0);
                //BOWS
                case "§5Az", "§5AzÀ" -> Az.Az(currentItem, 0);
                case "§5Divzer", "§5DivzerÀ" -> Divzer.Divzer(currentItem, 0);
                case "§5Epoch", "§5EpochÀ" -> Epoch.Epoch(currentItem, 0);
                case "§5Freedom", "§5FreedomÀ" -> Freedom.Freedom(currentItem, 0);
                case "§5Grandmother", "§5GrandmotherÀ" -> Grandmother.Grandmother(currentItem, 0);
                case "§5Ignis", "§5IgnisÀ" -> Ignis.Ignis(currentItem, 0);
                case "§5Spring", "§5SpringÀ" -> Spring.Spring(currentItem, 0);
                case "§5Stratiformis", "§5StratiformisÀ" -> Stratiformis.Stratiformis(currentItem, 0);
                //CHESTPLATES
                case "§5Discoverer", "§5DiscovererÀ" -> Discoverer.Discoverer(currentItem, 0);
                //DAGGERS
                case "§5Archangel", "§5ArchangelÀ" -> Archangel.Archangel(currentItem, 0);
                case "§5Cataclysm", "§5CataclysmÀ" -> Cataclysm.Cataclysm(currentItem, 0);
                case "§5Grimtrap", "§5GrimtrapÀ" -> Grimtrap.Grimtrap(currentItem, 0);
                case "§5Inferno", "§5InfernoÀ" -> Inferno.Inferno(currentItem, 0);
                case "§5Nirvana", "§5NirvanaÀ" -> Nirvana.Nirvana(currentItem, 0);
                case "§5Nullification", "§5NullificationÀ" -> Nullification.Nullification(currentItem, 0);
                case "§5Oblivion", "§5OblivionÀ" -> Oblivion.Oblivion(currentItem, 0);
                case "§5Weathered", "§5WeatheredÀ" -> Weathered.Weathered(currentItem, 0);
                //RELIKS
                case "§5Absolution", "§5AbsolutionÀ" -> Absolution.Absolution(currentItem, 0);
                case "§5Aftershock", "§5AftershockÀ" -> Aftershock.AfterShock(currentItem, 0);
                case "§5Fantasia", "§5FantasiaÀ" -> Fantasia.Fantasia(currentItem, 0);
                case "§5Hadal", "§5HadalÀ" -> Hadal.Hadal(currentItem, 0);
                case "§5Immolation", "§5ImmolationÀ" -> Immolation.Immolation(currentItem, 0);
                case "§5Olympic", "§5OlympicÀ" -> Olympic.Olympic(currentItem, 0);
                case "§5Sunstar", "§5SunstarÀ" -> Sunstar.Sunstar(currentItem, 0);
                case "§5Toxoplasmosis", "§5ToxoplasmosisÀ" -> Toxoplasmosis.Toxoplasmosis(currentItem, 0);
                //SPEAR
                case "§5Alkatraz", "§5AlkatrazÀ" -> Alkatraz.Alkatraz(currentItem, 0);
                case "§5Apocalypse", "§5ApocalypseÀ" -> Apocalypse.Apocalypse(currentItem, 0);
                case "§5Collapse", "§5CollapseÀ" -> Collapse.Collapse(currentItem, 0);
                case "§5Convergence", "§5ConvergenceÀ" -> Convergence.Convergence(currentItem, 0);
                case "§5Guardian", "§5GuardianÀ" -> Guardian.Guardian(currentItem, 0);
                case "§5HeroÀ", "§5Hero" -> Hero.Hero(currentItem, 0);
                case "§5Idol", "§5IdolÀ" -> Idol.Idol(currentItem, 0);
                case "§5Thrundacrack", "§5ThrundacrackÀ" -> Thrundacrack.Thrundacrack(currentItem, 0);
                //WANDS
                case "§5Fatal", "§5FatalÀ" -> Fatal.Fatal(currentItem, 0);
                case "§5Gaia", "§5GaiaÀ" -> Gaia.Gaia(currentItem, 0);
                case "§5Lament", "§5LamentÀ" -> Lament.Lament(currentItem, 0);
                case "§5Monster", "§5MonsterÀ" -> Monster.Monster(currentItem, 0);
                case "§5Pure", "§5PureÀ" -> Pure.Pure(currentItem, 0);
                case "§5Quetzalcoatl", "§5QuetzalcoatlÀ" -> Quetzalcoatl.Quetzalcoatl(currentItem, 0);
                case "§5Singularity", "§5SingularityÀ" -> Singularity.Singularity(currentItem, 0);
                case "§5Warp", "§5WarpÀ" -> Warp.Warp(currentItem, 0);
                //BOOTS
                case "§f⬡ §5Shiny Boreal", "§f⬡ §5Shiny BorealÀ" -> Boreal.Boreal(currentItem, 2);
                case "§f⬡ §5Shiny Crusade Sabatons", "§f⬡ §5Shiny Crusade SabatonsÀ" ->
                        Crusade_Sabatons.Crusade_Sabatons(currentItem, 2);
                case "§f⬡ §5Shiny Dawnbreak", "§f⬡ §5Shiny DawnbreakÀ" -> Dawnbreak.Dawnbreak(currentItem, 2);
                case "§f⬡ §5Shiny Galleon", "§f⬡ §5Shiny GalleonÀ" -> Galleon.Galleon(currentItem, 2);
                case "§f⬡ §5Shiny Moontower", "§f⬡ §5Shiny MoontowerÀ" -> Moontower.Moontower(currentItem, 2);
                case "§f⬡ §5Shiny Resurgence", "§f⬡ §5Shiny ResurgenceÀ" -> Resurgence.Resurgence(currentItem, 2);
                case "§f⬡ §5Shiny Revenant", "§f⬡ §5Shiny RevenantÀ" -> Revenant.Revenant(currentItem, 2);
                case "§f⬡ §5Shiny Slayer", "§f⬡ §5Shiny SlayerÀ" -> Slayer.Slayer(currentItem, 2);
                case "§f⬡ §5Shiny Stardew", "§f⬡ §5Shiny StardewÀ" -> Stardew.Stardew(currentItem, 2);
                case "§f⬡ §5Shiny Warchief", "§f⬡ §5Shiny WarchiefÀ" -> Warchief.Warchief(currentItem, 2);
                //BOWS
                case "§f⬡ §5Shiny Az", "§f⬡ §5Shiny AzÀ" -> Az.Az(currentItem, 2);
                case "§f⬡ §5Shiny Divzer", "§f⬡ §5Shiny DivzerÀ" -> Divzer.Divzer(currentItem, 2);
                case "§f⬡ §5Shiny Epoch", "§f⬡ §5Shiny EpochÀ" -> Epoch.Epoch(currentItem, 2);
                case "§f⬡ §5Shiny Freedom", "§f⬡ §5Shiny FreedomÀ" -> Freedom.Freedom(currentItem, 2);
                case "§f⬡ §5Shiny Grandmother", "§f⬡ §5Shiny GrandmotherÀ" -> Grandmother.Grandmother(currentItem, 2);
                case "§f⬡ §5Shiny Ignis", "§f⬡ §5Shiny IgnisÀ" -> Ignis.Ignis(currentItem, 2);
                case "§f⬡ §5Shiny Spring", "§f⬡ §5Shiny SpringÀ" -> Spring.Spring(currentItem, 2);
                case "§f⬡ §5Shiny Stratiformis", "§f⬡ §5Shiny StratiformisÀ" ->
                        Stratiformis.Stratiformis(currentItem, 2);
                //CHESTPLATES
                case "§f⬡ §5Shiny Discoverer", "§f⬡ §5Shiny DiscovererÀ" -> Discoverer.Discoverer(currentItem, 2);
                //DAGGERS
                case "§f⬡ §5Shiny Archangel", "§f⬡ §5Shiny ArchangelÀ" -> Archangel.Archangel(currentItem, 2);
                case "§f⬡ §5Shiny Cataclysm", "§f⬡ §5Shiny CataclysmÀ" -> Cataclysm.Cataclysm(currentItem, 2);
                case "§f⬡ §5Shiny Grimtrap", "§f⬡ §5Shiny GrimtrapÀ" -> Grimtrap.Grimtrap(currentItem, 2);
                case "§f⬡ §5Shiny Inferno", "§f⬡ §5Shiny InfernoÀ" -> Inferno.Inferno(currentItem, 2);
                case "§f⬡ §5Shiny Nirvana", "§f⬡ §5Shiny NirvanaÀ" -> Nirvana.Nirvana(currentItem, 2);
                case "§f⬡ §5Shiny Nullification", "§f⬡ §5Shiny NullificationÀ" ->
                        Nullification.Nullification(currentItem, 2);
                case "§f⬡ §5Shiny Oblivion", "§f⬡ §5Shiny OblivionÀ" -> Oblivion.Oblivion(currentItem, 2);
                case "§f⬡ §5Shiny Weathered", "§f⬡ §5Shiny WeatheredÀ" -> Weathered.Weathered(currentItem, 2);
                //RELIKS
                case "§f⬡ §5Shiny Absolution", "§f⬡ §5Shiny AbsolutionÀ" -> Absolution.Absolution(currentItem, 2);
                case "§f⬡ §5Shiny Aftershock", "§f⬡ §5Shiny AftershockÀ" -> Aftershock.AfterShock(currentItem, 2);
                case "§f⬡ §5Shiny Fantasia", "§f⬡ §5Shiny FantasiaÀ" -> Fantasia.Fantasia(currentItem, 2);
                case "§f⬡ §5Shiny Hadal", "§f⬡ §5Shiny HadalÀ" -> Hadal.Hadal(currentItem, 2);
                case "§f⬡ §5Shiny Immolation", "§f⬡ §5Shiny ImmolationÀ" -> Immolation.Immolation(currentItem, 2);
                case "§f⬡ §5Shiny Olympic", "§f⬡ §5Shiny OlympicÀ" -> Olympic.Olympic(currentItem, 2);
                case "§f⬡ §5Shiny Sunstar", "§f⬡ §5Shiny SunstarÀ" -> Sunstar.Sunstar(currentItem, 2);
                case "§f⬡ §5Shiny Toxoplasmosis", "§f⬡ §5Shiny ToxoplasmosisÀ" ->
                        Toxoplasmosis.Toxoplasmosis(currentItem, 2);
                //SPEAR
                case "§f⬡ §5Shiny Alkatraz", "§f⬡ §5Shiny AlkatrazÀ" -> Alkatraz.Alkatraz(currentItem, 2);
                case "§f⬡ §5Shiny Apocalypse", "§f⬡ §5Shiny ApocalypseÀ" -> Apocalypse.Apocalypse(currentItem, 2);
                case "§f⬡ §5Shiny Collapse", "§f⬡ §5Shiny CollapseÀ" -> Collapse.Collapse(currentItem, 2);
                case "§f⬡ §5Shiny Convergence", "§f⬡ §5Shiny ConvergenceÀ" -> Convergence.Convergence(currentItem, 2);
                case "§f⬡ §5Shiny Guardian", "§f⬡ §5Shiny GuardianÀ" -> Guardian.Guardian(currentItem, 2);
                case "§f⬡ §5Shiny HeroÀ", "§f⬡ §5Shiny Hero" -> Hero.Hero(currentItem, 2);
                case "§f⬡ §5Shiny Idol", "§f⬡ §5Shiny IdolÀ" -> Idol.Idol(currentItem, 2);
                case "§f⬡ §5Shiny Thrundacrack", "§f⬡ §5Shiny ThrundacrackÀ" ->
                        Thrundacrack.Thrundacrack(currentItem, 2);
                //WANDS
                case "§f⬡ §5Shiny Fatal", "§f⬡ §5Shiny FatalÀ" -> Fatal.Fatal(currentItem, 2);
                case "§f⬡ §5Shiny Gaia", "§f⬡ §5Shiny GaiaÀ" -> Gaia.Gaia(currentItem, 2);
                case "§f⬡ §5Shiny Lament", "§f⬡ §5Shiny LamentÀ" -> Lament.Lament(currentItem, 2);
                case "§f⬡ §5Shiny Monster", "§f⬡ §5Shiny MonsterÀ" -> Monster.Monster(currentItem, 2);
                case "§f⬡ §5Shiny Pure", "§f⬡ §5Shiny PureÀ" -> Pure.Pure(currentItem, 2);
                case "§f⬡ §5Shiny Quetzalcoatl", "§f⬡ §5Shiny QuetzalcoatlÀ" ->
                        Quetzalcoatl.Quetzalcoatl(currentItem, 2);
                case "§f⬡ §5Shiny Singularity", "§f⬡ §5Shiny SingularityÀ" -> Singularity.Singularity(currentItem, 2);
                case "§f⬡ §5Shiny Warp", "§f⬡ §5Shiny WarpÀ" -> Warp.Warp(currentItem, 2);
                default -> currentItem;
            };
        }
        return currentItem;
    }
}
