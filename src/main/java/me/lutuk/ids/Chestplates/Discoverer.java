package me.lutuk.ids.Chestplates;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.lutuk.utils.CalcUtils;
import me.lutuk.utils.CodingUtils;
import me.lutuk.utils.JsonUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Discoverer {
    private static double currentlootBonus;
    private static double currentxpBonus;
    public static ItemStack Discoverer(ItemStack mainHandItem) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String lootBonus= getLore.get(3);
        lootBonus= lootBonus.toLowerCase().replaceAll("[^1234567890%]", "");
        lootBonus = lootBonus.substring(1, lootBonus.lastIndexOf("%"));
        currentlootBonus = Double.parseDouble(lootBonus);

        double[] lootBonusList = gson.fromJson(jsonObject.get("chestplates").getAsJsonObject().get("Discoverer").getAsJsonObject().get("lootBonus"), double[].class);
        double lootBonusWeight = CalcUtils.positveStats(lootBonusList[1],lootBonusList[0],currentlootBonus,lootBonusList[2]);
        String xpBonus= getLore.get(2);
        xpBonus= xpBonus.toLowerCase().replaceAll("[^1234567890%]", "");
        xpBonus = xpBonus.substring(1, xpBonus.lastIndexOf("%"));
        currentxpBonus = Double.parseDouble(xpBonus);

        double[] xpBonusList = gson.fromJson(jsonObject.get("chestplates").getAsJsonObject().get("Discoverer").getAsJsonObject().get("xpBonus"), double[].class);
        double xpBonusWeight = CalcUtils.positveStats(xpBonusList[1],xpBonusList[0],currentxpBonus,xpBonusList[2]);

        int finalWeight = (int) Math.round (lootBonusWeight+xpBonusWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
