package me.lutuk.ids.Spears;

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

public class Apocalypse {
    private static double currenthealthRegenPercent;
    private static double currentexploding;
    private static double currentwaterDamage;
    private static double currentsoulPointregen;
    private static double currentfireDefense;
    private static double currentwaterDefense;
    private static double currentlifeSteal;

    public static ItemStack Apocalypse(ItemStack mainHandItem) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();

        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String healthRegenPercent = getLore.get(5);
        healthRegenPercent = healthRegenPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        healthRegenPercent = healthRegenPercent.substring(1, healthRegenPercent.lastIndexOf("%"));
        currenthealthRegenPercent = Double.parseDouble(healthRegenPercent);

        double[] healthRegenPercentList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Apocalypse").getAsJsonObject().get("healthRegenPercent"), double[].class);
        double healthRegenPercentWeight = CalcUtils.negativeStats(healthRegenPercentList[1], healthRegenPercentList[0], currenthealthRegenPercent, healthRegenPercentList[2]);

        String exploding = getLore.get(7);
        exploding = exploding.toLowerCase().replaceAll("[^1234567890%]", "");
        exploding = exploding.substring(1, exploding.lastIndexOf("%"));
        currentexploding = Double.parseDouble(exploding);

        double[] explodingList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Apocalypse").getAsJsonObject().get("exploding"), double[].class);
        double explodingWeight = CalcUtils.positveStats(explodingList[1], explodingList[0], currentexploding, explodingList[2]);

        String waterDamage = getLore.get(9);
        waterDamage = waterDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        waterDamage = waterDamage.substring(1, waterDamage.lastIndexOf("%"));
        currentwaterDamage = Double.parseDouble(waterDamage);

        double[] waterDamageList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Apocalypse").getAsJsonObject().get("waterDamage"), double[].class);
        double waterDamageWeight = CalcUtils.negativeStats(waterDamageList[1], waterDamageList[0], currentwaterDamage, waterDamageList[2]);
        String soulPointregen = getLore.get(8);
        soulPointregen = soulPointregen.toLowerCase().replaceAll("[^1234567890%]", "");
        soulPointregen = soulPointregen.substring(1, soulPointregen.lastIndexOf("%"));
        currentsoulPointregen = Double.parseDouble(soulPointregen);

        double[] soulPointregenList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Apocalypse").getAsJsonObject().get("soulPointregen"), double[].class);
        double soulPointregenWeight = CalcUtils.negativeStats(soulPointregenList[1], soulPointregenList[0], currentsoulPointregen, soulPointregenList[2]);
        String fireDefense = getLore.get(10);
        fireDefense = fireDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        fireDefense = fireDefense.substring(1, fireDefense.lastIndexOf("%"));
        currentfireDefense = Double.parseDouble(fireDefense);

        double[] fireDefenseList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Apocalypse").getAsJsonObject().get("fireDefense"), double[].class);
        double fireDefenseWeight = CalcUtils.positveStats(fireDefenseList[1], fireDefenseList[0], currentfireDefense, fireDefenseList[2]);
        String waterDefense = getLore.get(11);
        waterDefense = waterDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        waterDefense = waterDefense.substring(1, waterDefense.lastIndexOf("%"));
        currentwaterDefense = Double.parseDouble(waterDefense);

        double[] waterDefenseList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Apocalypse").getAsJsonObject().get("waterDefense"), double[].class);
        double waterDefenseWeight = CalcUtils.negativeStats(waterDefenseList[1], waterDefenseList[0], currentwaterDefense, waterDefenseList[2]);

        String lifeSteal = getLore.get(6);
        lifeSteal = lifeSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        lifeSteal = lifeSteal.substring(1, lifeSteal.lastIndexOf("/"));
        currentlifeSteal = Double.parseDouble(lifeSteal);

        double[] lifeStealList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Apocalypse").getAsJsonObject().get("lifeSteal"), double[].class);
        double lifeStealWeight = CalcUtils.positveStats(lifeStealList[1], lifeStealList[0], currentlifeSteal, lifeStealList[2]);

        int finalWeight = (int) Math.round(healthRegenPercentWeight + explodingWeight + waterDamageWeight + soulPointregenWeight + fireDefenseWeight + waterDefenseWeight + lifeStealWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
