package me.lutuk.utils;

import me.lutuk.ids.Spears.Hero;
import me.lutuk.ids.Boots.Slayer;
import me.lutuk.ids.Bows.Stratiformis;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.io.IOException;
import java.util.Objects;

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
        }else if (Objects.equals(weight, null)) {
            return " §4Broken /msg Lutuk145";
        }
        else {
            return null;
        }
    }

    public static ItemStack mythicCheck(ItemStack currentItem) throws IOException {
        if (currentItem.getName().contains(Text.of("Unidentified")))
            return currentItem;
        if (currentItem.getName().equals(Text.of("§5HeroÀ"))||currentItem.getName().equals(Text.of("§5Hero"))) {
            return Hero.Hero(currentItem);
        } else if (currentItem.getName().equals(Text.of("§5Slayer"))||currentItem.getName().equals(Text.of("§5SlayerÀ"))) {
            return Slayer.Slayer(currentItem);

        } else if (currentItem.getName().equals(Text.of("§5Stratiformis"))||currentItem.getName().equals(Text.of("§5StratiformisÀ"))) {

            System.out.println("Found Strati");return Stratiformis.Stratiformis(currentItem);
        }
        else {
            return currentItem;
        }
    }
}
