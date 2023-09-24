package me.lutuk.utils;

import me.lutuk.ids.Boots.*;
import me.lutuk.ids.Bows.*;
import me.lutuk.ids.Chestplates.Discoverer;
import me.lutuk.ids.Daggers.*;
import me.lutuk.ids.Reliks.*;
import me.lutuk.ids.Spears.*;
import me.lutuk.ids.Wands.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.io.IOException;

import static me.lutuk.ExampleMod.toggle;

public class CodingUtils {
    public static void msg(String msg) {
        MinecraftClient.getInstance().player.sendMessage(Text.of(msg));
    }

    public static String color(int weight) {
        if (weight <= 10) {
            return " §4" + weight;
        } else if (weight > 10 && weight <= 25) {
            return " §c" + weight;
        } else if (weight > 25 && weight <= 50) {
            return " §e" + weight;
        } else if (weight > 50 && weight <= 80) {
            return " §a" + weight;
        } else if (weight > 80 && weight <= 99) {
            return " §b" + weight;
        } else if (weight == 100) {
            return " §6§l" + weight;
        } else {
            return null;
        }
    }

    public static ItemStack mythicCheck(ItemStack currentItem) throws IOException {
        if (toggle) {
            if (currentItem.getName().contains(Text.of("Unidentified")))
                return currentItem;
            switch (currentItem.getName().getString()) {
                //BOOTS
                case "§5Boreal":
                case "§5BorealÀ":
                    return Boreal.Boreal(currentItem,0);
                case "§5Crusade Sabatons":
                case "§5Crusade SabatonsÀ":
                    return Crusade_Sabatons.Crusade_Sabatons(currentItem,0);
                case "§5Dawnbreak":
                case "§5DawnbreakÀ":
                    return Dawnbreak.Dawnbreak(currentItem,0);
                case "§5Galleon":
                case "§5GalleonÀ":
                    return Galleon.Galleon(currentItem,0);
                case "§5Moontower":
                case "§5MoontowerÀ":
                    return Moontower.Moontower(currentItem,0);
                case "§5Resurgence":
                case "§5ResurgenceÀ":
                    return Resurgence.Resurgence(currentItem,0);
                case "§5Revenant":
                case "§5RevenantÀ":
                    return Revenant.Revenant(currentItem,0);
                case "§5Slayer":
                case "§5SlayerÀ":
                    return Slayer.Slayer(currentItem,0);
                case "§5Stardew":
                case "§5StardewÀ":
                    return Stardew.Stardew(currentItem,0);
                case "§5Warchief":
                case "§5WarchiefÀ":
                    return Warchief.Warchief(currentItem,0);
                //BOWS
                case "§5Az":
                case "§5AzÀ":
                    return Az.Az(currentItem,0);
                case "§5Divzer":
                case "§5DivzerÀ":
                    return Divzer.Divzer(currentItem,0);
                case "§5Epoch":
                case "§5EpochÀ":
                    return Epoch.Epoch(currentItem,0);
                case "§5Freedom":
                case "§5FreedomÀ":
                    return Freedom.Freedom(currentItem,0);
                case "§5Grandmother":
                case "§5GrandmotherÀ":
                    return Grandmother.Grandmother(currentItem,0);
                case "§5Ignis":
                case "§5IgnisÀ":
                    return Ignis.Ignis(currentItem,0);
                case "§5Spring":
                case "§5SpringÀ":
                    return Spring.Spring(currentItem,0);
                case "§5Stratiformis":
                case "§5StratiformisÀ":
                    return Stratiformis.Stratiformis(currentItem,0);
                //CHESTPLATES
                case "§5Discoverer":
                case "§5DiscovererÀ":
                    return Discoverer.Discoverer(currentItem,0);
                //DAGGERS
                case "§5Archangel":
                case "§5ArchangelÀ":
                    return Archangel.Archangel(currentItem,0);
                case "§5Cataclysm":
                case "§5CataclysmÀ":
                    return Cataclysm.Cataclysm(currentItem,0);
                case "§5Grimtrap":
                case "§5GrimtrapÀ":
                    return Grimtrap.Grimtrap(currentItem,0);
                case "§5Inferno":
                case "§5InfernoÀ":
                    return Inferno.Inferno(currentItem,0);
                case "§5Nirvana":
                case "§5NirvanaÀ":
                    return Nirvana.Nirvana(currentItem,0);
                case "§5Nullification":
                case "§5NullificationÀ":
                    return Nullification.Nullification(currentItem,0);
                case "§5Oblivion":
                case "§5OblivionÀ":
                    return Oblivion.Oblivion(currentItem,0);
                case "§5Weathered":
                case "§5WeatheredÀ":
                    return Weathered.Weathered(currentItem,0);
                //RELIKS
                case "§5Absolution":
                case "§5AbsolutionÀ":
                    return Absolution.Absolution(currentItem,0);
                case "§5Aftershock":
                case "§5AftershockÀ":
                    return Aftershock.AfterShock(currentItem,0);
                case "§5Fantasia":
                case "§5FantasiaÀ":
                    return Fantasia.Fantasia(currentItem,0);
                case "§5Hadal":
                case "§5HadalÀ":
                    return Hadal.Hadal(currentItem,0);
                case "§5Immolation":
                case "§5ImmolationÀ":
                    return Immolation.Immolation(currentItem,0);
                case "§5Olympic":
                case "§5OlympicÀ":
                    return Olympic.Olympic(currentItem,0);
                case "§5Sunstar":
                case "§5SunstarÀ":
                    return Sunstar.Sunstar(currentItem,0);
                case "§5Toxoplasmosis":
                case "§5ToxoplasmosisÀ":
                    return Toxoplasmosis.Toxoplasmosis(currentItem,0);
                //SPEAR
                case "§5Alkatraz":
                case "§5AlkatrazÀ":
                    return Alkatraz.Alkatraz(currentItem,0);
                case "§5Apocalypse":
                case "§5ApocalypseÀ":
                    return Apocalypse.Apocalypse(currentItem,0);
                case "§5Collapse":
                case "§5CollapseÀ":
                    return Collapse.Collapse(currentItem,0);
                case "§5Convergence":
                case "§5ConvergenceÀ":
                    return Convergence.Convergence(currentItem,0);
                case "§5Guardian":
                case "§5GuardianÀ":
                    return Guardian.Guardian(currentItem,0);
                case "§5HeroÀ":
                case "§5Hero":
                    return Hero.Hero(currentItem,0);
                case "§5Idol":
                case "§5IdolÀ":
                    return Idol.Idol(currentItem,0);
                case "§5Thrundacrack":
                case "§5ThrundacrackÀ":
                    return Thrundacrack.Thrundacrack(currentItem,0);
                //WANDS
                case "§5Fatal":
                case "§5FatalÀ":
                    return Fatal.Fatal(currentItem,0);
                case "§5Gaia":
                case "§5GaiaÀ":
                    return Gaia.Gaia(currentItem,0);
                case "§5Lament":
                case "§5LamentÀ":
                    return Lament.Lament(currentItem,0);
                case "§5Monster":
                case "§5MonsterÀ":
                    return Monster.Monster(currentItem,0);
                case "§5Pure":
                case "§5PureÀ":
                    return Pure.Pure(currentItem,0);
                case "§5Quetzalcoatl":
                case "§5QuetzalcoatlÀ":
                    return Quetzalcoatl.Quetzalcoatl(currentItem,0);
                case "§5Singularity":
                case "§5SingularityÀ":
                    return Singularity.Singularity(currentItem,0);
                case "§5Warp":
                case "§5WarpÀ":
                    return Warp.Warp(currentItem,0);
                //BOOTS
                case "§f⬡ §5Shiny Boreal":
                case "§f⬡ §5Shiny BorealÀ":
                    return Boreal.Boreal(currentItem,2);
                case "§f⬡ §5Shiny Crusade Sabatons":
                case "§f⬡ §5Shiny Crusade SabatonsÀ":
                    return Crusade_Sabatons.Crusade_Sabatons(currentItem,2);
                case "§f⬡ §5Shiny Dawnbreak":
                case "§f⬡ §5Shiny DawnbreakÀ":
                    return Dawnbreak.Dawnbreak(currentItem,2);
                case "§f⬡ §5Shiny Galleon":
                case "§f⬡ §5Shiny GalleonÀ":
                    return Galleon.Galleon(currentItem,2);
                case "§f⬡ §5Shiny Moontower":
                case "§f⬡ §5Shiny MoontowerÀ":
                    return Moontower.Moontower(currentItem,2);
                case "§f⬡ §5Shiny Resurgence":
                case "§f⬡ §5Shiny ResurgenceÀ":
                    return Resurgence.Resurgence(currentItem,2);
                case "§f⬡ §5Shiny Revenant":
                case "§f⬡ §5Shiny RevenantÀ":
                    return Revenant.Revenant(currentItem,2);
                case "§f⬡ §5Shiny Slayer":
                case "§f⬡ §5Shiny SlayerÀ":
                    return Slayer.Slayer(currentItem,2);
                case "§f⬡ §5Shiny Stardew":
                case "§f⬡ §5Shiny StardewÀ":
                    return Stardew.Stardew(currentItem,2);
                case "§f⬡ §5Shiny Warchief":
                case "§f⬡ §5Shiny WarchiefÀ":
                    return Warchief.Warchief(currentItem,2);
                //BOWS
                case "§f⬡ §5Shiny Az":
                case "§f⬡ §5Shiny AzÀ":
                    return Az.Az(currentItem,2);
                case "§f⬡ §5Shiny Divzer":
                case "§f⬡ §5Shiny DivzerÀ":
                    return Divzer.Divzer(currentItem,2);
                case "§f⬡ §5Shiny Epoch":
                case "§f⬡ §5Shiny EpochÀ":
                    return Epoch.Epoch(currentItem,2);
                case "§f⬡ §5Shiny Freedom":
                case "§f⬡ §5Shiny FreedomÀ":
                    return Freedom.Freedom(currentItem,2);
                case "§f⬡ §5Shiny Grandmother":
                case "§f⬡ §5Shiny GrandmotherÀ":
                    return Grandmother.Grandmother(currentItem,2);
                case "§f⬡ §5Shiny Ignis":
                case "§f⬡ §5Shiny IgnisÀ":
                    return Ignis.Ignis(currentItem,2);
                case "§f⬡ §5Shiny Spring":
                case "§f⬡ §5Shiny SpringÀ":
                    return Spring.Spring(currentItem,2);
                case "§f⬡ §5Shiny Stratiformis":
                case "§f⬡ §5Shiny StratiformisÀ":
                    return Stratiformis.Stratiformis(currentItem,2);
                //CHESTPLATES
                case "§f⬡ §5Shiny Discoverer":
                case "§f⬡ §5Shiny DiscovererÀ":
                    return Discoverer.Discoverer(currentItem,2);
                //DAGGERS
                case "§f⬡ §5Shiny Archangel":
                case "§f⬡ §5Shiny ArchangelÀ":
                    return Archangel.Archangel(currentItem,2);
                case "§f⬡ §5Shiny Cataclysm":
                case "§f⬡ §5Shiny CataclysmÀ":
                    return Cataclysm.Cataclysm(currentItem,2);
                case "§f⬡ §5Shiny Grimtrap":
                case "§f⬡ §5Shiny GrimtrapÀ":
                    return Grimtrap.Grimtrap(currentItem,2);
                case "§f⬡ §5Shiny Inferno":
                case "§f⬡ §5Shiny InfernoÀ":
                    return Inferno.Inferno(currentItem,2);
                case "§f⬡ §5Shiny Nirvana":
                case "§f⬡ §5Shiny NirvanaÀ":
                    return Nirvana.Nirvana(currentItem,2);
                case "§f⬡ §5Shiny Nullification":
                case "§f⬡ §5Shiny NullificationÀ":
                    return Nullification.Nullification(currentItem,2);
                case "§f⬡ §5Shiny Oblivion":
                case "§f⬡ §5Shiny OblivionÀ":
                    return Oblivion.Oblivion(currentItem,2);
                case "§f⬡ §5Shiny Weathered":
                case "§f⬡ §5Shiny WeatheredÀ":
                    return Weathered.Weathered(currentItem,2);
                //RELIKS
                case "§f⬡ §5Shiny Absolution":
                case "§f⬡ §5Shiny AbsolutionÀ":
                    return Absolution.Absolution(currentItem,2);
                case "§f⬡ §5Shiny Aftershock":
                case "§f⬡ §5Shiny AftershockÀ":
                    return Aftershock.AfterShock(currentItem,2);
                case "§f⬡ §5Shiny Fantasia":
                case "§f⬡ §5Shiny FantasiaÀ":
                    return Fantasia.Fantasia(currentItem,2);
                case "§f⬡ §5Shiny Hadal":
                case "§f⬡ §5Shiny HadalÀ":
                    return Hadal.Hadal(currentItem,2);
                case "§f⬡ §5Shiny Immolation":
                case "§f⬡ §5Shiny ImmolationÀ":
                    return Immolation.Immolation(currentItem,2);
                case "§f⬡ §5Shiny Olympic":
                case "§f⬡ §5Shiny OlympicÀ":
                    return Olympic.Olympic(currentItem,2);
                case "§f⬡ §5Shiny Sunstar":
                case "§f⬡ §5Shiny SunstarÀ":
                    return Sunstar.Sunstar(currentItem,2);
                case "§f⬡ §5Shiny Toxoplasmosis":
                case "§f⬡ §5Shiny ToxoplasmosisÀ":
                    return Toxoplasmosis.Toxoplasmosis(currentItem,2);
                //SPEAR
                case "§f⬡ §5Shiny Alkatraz":
                case "§f⬡ §5Shiny AlkatrazÀ":
                    return Alkatraz.Alkatraz(currentItem,2);
                case "§f⬡ §5Shiny Apocalypse":
                case "§f⬡ §5Shiny ApocalypseÀ":
                    return Apocalypse.Apocalypse(currentItem,2);
                case "§f⬡ §5Shiny Collapse":
                case "§f⬡ §5Shiny CollapseÀ":
                    return Collapse.Collapse(currentItem,2);
                case "§f⬡ §5Shiny Convergence":
                case "§f⬡ §5Shiny ConvergenceÀ":
                    return Convergence.Convergence(currentItem,2);
                case "§f⬡ §5Shiny Guardian":
                case "§f⬡ §5Shiny GuardianÀ":
                    return Guardian.Guardian(currentItem,2);
                case "§f⬡ §5Shiny HeroÀ":
                case "§f⬡ §5Shiny Hero":
                    return Hero.Hero(currentItem,2);
                case "§f⬡ §5Shiny Idol":
                case "§f⬡ §5Shiny IdolÀ":
                    return Idol.Idol(currentItem,2);
                case "§f⬡ §5Shiny Thrundacrack":
                case "§f⬡ §5Shiny ThrundacrackÀ":
                    return Thrundacrack.Thrundacrack(currentItem,2);
                //WANDS
                case "§f⬡ §5Shiny Fatal":
                case "§f⬡ §5Shiny FatalÀ":
                    return Fatal.Fatal(currentItem,2);
                case "§f⬡ §5Shiny Gaia":
                case "§f⬡ §5Shiny GaiaÀ":
                    return Gaia.Gaia(currentItem,2);
                case "§f⬡ §5Shiny Lament":
                case "§f⬡ §5Shiny LamentÀ":
                    return Lament.Lament(currentItem,2);
                case "§f⬡ §5Shiny Monster":
                case "§f⬡ §5Shiny MonsterÀ":
                    return Monster.Monster(currentItem,2);
                case "§f⬡ §5Shiny Pure":
                case "§f⬡ §5Shiny PureÀ":
                    return Pure.Pure(currentItem,2);
                case "§f⬡ §5Shiny Quetzalcoatl":
                case "§f⬡ §5Shiny QuetzalcoatlÀ":
                    return Quetzalcoatl.Quetzalcoatl(currentItem,2);
                case "§f⬡ §5Shiny Singularity":
                case "§f⬡ §5Shiny SingularityÀ":
                    return Singularity.Singularity(currentItem,2);
                case "§f⬡ §5Shiny Warp":
                case "§f⬡ §5Shiny WarpÀ":
                    return Warp.Warp(currentItem,2);
                default:
                    return currentItem;
            }
        }
        return currentItem;
    }
}
