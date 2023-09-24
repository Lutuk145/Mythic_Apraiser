package me.lutuk.ids.Boots;

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

public class Galleon {
    private static double currentmanaSteal;
    private static double currentlootBonus;
    private static double currentpoison;
    private static double currentstealing;
    private static double currentwaterDamage;
    private static double currentearthDamage;
    private static double currentmeleeDamage;
    public static ItemStack Galleon(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String manaSteal= getLore.get(i+2);
        manaSteal= manaSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        manaSteal = manaSteal.substring(1, manaSteal.lastIndexOf("/"));
        currentmanaSteal = Double.parseDouble(manaSteal);

        double[] manaStealList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Galleon").getAsJsonObject().get("manaSteal"), double[].class);
        double manaStealWeight = CalcUtils.positveStats(manaStealList[1],manaStealList[0],currentmanaSteal,manaStealList[2]);

        String lootBonus= getLore.get(i+3);
        lootBonus= lootBonus.toLowerCase().replaceAll("[^1234567890%]", "");
        lootBonus = lootBonus.substring(1, lootBonus.lastIndexOf("%"));
        currentlootBonus = Double.parseDouble(lootBonus);

        double[] lootBonusList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Galleon").getAsJsonObject().get("lootBonus"), double[].class);
        double lootBonusWeight = CalcUtils.positveStats(lootBonusList[1],lootBonusList[0],currentlootBonus,lootBonusList[2]);

        String poison= getLore.get(i+5);
        poison= poison.toLowerCase().replaceAll("[^1234567890/]", "");
        poison = poison.substring(1, poison.lastIndexOf("/"));
        currentpoison = Double.parseDouble(poison);

        double[] poisonList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Galleon").getAsJsonObject().get("poison"), double[].class);
        double poisonWeight = CalcUtils.positveStats(poisonList[1],poisonList[0],currentpoison,poisonList[2]);

        String stealing= getLore.get(i+6);
        stealing= stealing.toLowerCase().replaceAll("[^1234567890%]", "");
        stealing = stealing.substring(1, stealing.lastIndexOf("%"));
        currentstealing = Double.parseDouble(stealing);

        double[] stealingList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Galleon").getAsJsonObject().get("stealing"), double[].class);
        double stealingWeight = CalcUtils.positveStats(stealingList[1],stealingList[0],currentstealing,stealingList[2]);
        String waterDamage= getLore.get(i+7);
        waterDamage= waterDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        waterDamage = waterDamage.substring(1, waterDamage.lastIndexOf("%"));
        currentwaterDamage = Double.parseDouble(waterDamage);

        double[] waterDamageList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Galleon").getAsJsonObject().get("waterDamage"), double[].class);
        double waterDamageWeight = CalcUtils.positveStats(waterDamageList[1],waterDamageList[0],currentwaterDamage,waterDamageList[2]);
        String earthDamage= getLore.get(i+8);
        earthDamage= earthDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        earthDamage = earthDamage.substring(1, earthDamage.lastIndexOf("%"));
        currentearthDamage = Double.parseDouble(earthDamage);

        double[] earthDamageList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Galleon").getAsJsonObject().get("earthDamage"), double[].class);
        double earthDamageWeight = CalcUtils.positveStats(earthDamageList[1],earthDamageList[0],currentearthDamage,earthDamageList[2]);
        String meleeDamage= getLore.get(i+9);
        meleeDamage= meleeDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        meleeDamage = meleeDamage.substring(1, meleeDamage.lastIndexOf("%"));
        currentmeleeDamage = Double.parseDouble(meleeDamage);

        double[] meleeDamageList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Galleon").getAsJsonObject().get("meleeDamage"), double[].class);
        double meleeDamageWeight = CalcUtils.positveStats(meleeDamageList[1],meleeDamageList[0],currentmeleeDamage,meleeDamageList[2]);

        int finalWeight = (int) (manaStealWeight + lootBonusWeight + poisonWeight + stealingWeight + waterDamageWeight + earthDamageWeight + meleeDamageWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString()+ CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
