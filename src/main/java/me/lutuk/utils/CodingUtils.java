package me.lutuk.utils;

import me.lutuk.ids.Chestplates.Discoverer;
import me.lutuk.ids.Reliks.*;
import me.lutuk.ids.Spears.*;
import me.lutuk.ids.Boots.Slayer;
import me.lutuk.ids.Bows.Stratiformis;
import me.lutuk.ids.Wands.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.io.IOException;

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
        }
        else {
            return null;
        }
    }

    public static ItemStack mythicCheck(ItemStack currentItem) throws IOException {
        if (currentItem.getName().contains(Text.of("Unidentified")))
            return currentItem;
        switch (currentItem.getName().getString()) {
            //BOOTS
            case "§5Boreal":
            case "§5BorealÀ":
                return currentItem;
            case "§5Crusade Sabatons":
            case "§5Crusade SabatonsÀ":
                return currentItem;
            case "§5Dawnbreak":
            case "§5DawnbreakÀ":
                return currentItem;
            case "§5Galleon":
            case "§5GalleonÀ":
                return currentItem;
            case "§5Moontower":
            case "§5MoontowerÀ":
                return currentItem;
            case "§5Resurgence":
            case "§5ResurgenceÀ":
                return currentItem;
            case "§5Revenant":
            case "§5RevenantÀ":
                return currentItem;
            case "§5Slayer":
            case "§5SlayerÀ":
                return Slayer.Slayer(currentItem);
            case "§5Stardew":
            case "§5StardewÀ":
                return currentItem;
            case "§5Warchief":
            case "§5WarchiefÀ":
                return currentItem;
            //BOWS
            case "§5Az":
            case "§5AzÀ":
                return currentItem;
            case "§5Divzer":
            case "§5DivzerÀ":
                return currentItem;
            case "§5Epoch":
            case "§5EpochÀ":
                return currentItem;
            case "§5Freedom":
            case "§5FreedomÀ":
                return currentItem;
            case "§5Grandmother":
            case "§5GrandmotherÀ":
                return currentItem;
            case "§5Ignis":
            case "§5IgnisÀ":
                return currentItem;
            case "§5Spring":
            case "§5SpringÀ":
                return currentItem;
            case "§5Stratiformis":
            case "§5StratiformisÀ":
                return Stratiformis.Stratiformis(currentItem);
            //CHESTPLATES
            case "§5Discoverer":
            case "§5DiscovererÀ":
                return Discoverer.Discoverer(currentItem);
            //DAGGERS
            case "§5Archangel":
            case "§5ArchangelÀ":
                return currentItem;
            case "§5Cataclysm":
            case "§5CataclysmÀ":
                return currentItem;
            case "§5Grimtrap":
            case "§5GrimtrapÀ":
                return currentItem;
            case "§5Imfero":
            case "§5ImferoÀ":
                 return currentItem;
            case "§5Nirvana":
            case "§5NirvanaÀ":
                return currentItem;
            case "§5Nullification":
            case "§5NullificationÀ":
                return currentItem;
            case "§5Oblivion":
            case "§5OblivionÀ":
                return currentItem;
            case "§5Weathered":
            case "§5WeatheredÀ":
                return currentItem;
            //RELIKS
            case "§5Absolution":
            case "§5AbsolutionÀ":
                return Absolution.Absolution(currentItem);
            case "§5Aftershock":
            case "§5AftershockÀ":
                return Aftershock.AfterShock(currentItem);
            case "§5Fantasia":
            case "§5FantasiaÀ":
                return Fantasia.Fantasia(currentItem);
            case "§5Hadal":
            case "§5HadalÀ":
                return Hadal.Hadal(currentItem);
            case "§5Immolation":
            case "§5ImmolationÀ":
                return Immolation.Immolation(currentItem);
            case "§5Olympic":
            case "§5OlympicÀ":
                return Olympic.Olympic(currentItem);
            case "§5Sunstar":
            case "§5SunstarÀ":
                return Sunstar.Sunstar(currentItem);
            case "§5Toxoplasmosis":
            case "§5ToxoplasmosisÀ":
                return Toxoplasmosis.Toxoplasmosis(currentItem);
            //SPEAR
            case "§5Alkatraz":
            case "§5AlkatrazÀ":
                return Alkatraz.Alkatraz(currentItem);
            case "§5Apocalypse":
            case "§5ApocalypseÀ":
                return Apocalypse.Apocalypse(currentItem);
            case "§5Collapse":
            case "§5CollapseÀ":
                return Collapse.Collapse(currentItem);
            case "§5Convergence":
            case "§5ConvergenceÀ":
                return Convergence.Convergence(currentItem);
            case "§5Guardian":
            case "§5GuardianÀ":
                return Guardian.Guardian(currentItem);
            case "§5HeroÀ":
            case "§5Hero":
                return Hero.Hero(currentItem);
            case "§5Idol":
            case "§5IdolÀ":
                return Idol.Idol(currentItem);
            case "§5Thrundacrack":
            case "§5ThrundacrackÀ":
                return Thrundacrack.Thrundacrack(currentItem);
            //WANDS
            case "§5Fatal":
            case "§5FatalÀ":
                return Fatal.Fatal(currentItem);
            case "§5Gaia":
            case "§5GaiaÀ":
                return Gaia.Gaia(currentItem);
            case "§5Lament":
            case "§5LamentÀ":
                return Lament.Lament(currentItem);
            case "§5Monster":
            case "§5MonsterÀ":
                return Monster.Monster(currentItem);
            case "§5Pure":
            case "§5PureÀ":
                return Pure.Pure(currentItem);
            case "§5Quetzalcoatl":
            case "§5QuetzalcoatlÀ":
                return Quetzalcoatl.Quetzalcoatl(currentItem);
            case "§5Singularity":
            case "§5SingularityÀ":
                return Singularity.Singularity(currentItem);
            case "§5Warp":
            case "§5WarpÀ":
                return Warp.Warp(currentItem);
            default:
                return currentItem;
        }
    }
}
