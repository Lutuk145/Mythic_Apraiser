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

public class Guardian {
    private static double currentthorns;
    private static double currenthealth;
    private static double currentrawHpr;
    private static double currentfireDefense;
    private static double currentwaterDefense;
    private static double currentearthDefense;

    public static ItemStack Guardian(ItemStack mainHandItem) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String thorns = getLore.get(5);
        thorns = thorns.toLowerCase().replaceAll("[^1234567890%]", "");
        thorns = thorns.substring(1, thorns.lastIndexOf("%"));
        currentthorns = Double.parseDouble(thorns);

        double[] thornsList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Guardian").getAsJsonObject().get("thorns"), double[].class);
        double thornsWeight = CalcUtils.positveStats(thornsList[1], thornsList[0], currentthorns, thornsList[2]);
        String health = getLore.get(6);
        if (health.contains("*")) {
            health = health.toLowerCase().replaceAll("[^1234567890/*]", "");
            health = health.substring(1, health.indexOf("*") - 1);
        } else {
            health = health.toLowerCase().replaceAll("[^1234567890/]", "");
            health = health.substring(1, health.lastIndexOf("7"));
        }
        currenthealth = Double.parseDouble(health);

        double[] healthList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Guardian").getAsJsonObject().get("health"), double[].class);
        double healthWeight = CalcUtils.positveStats(healthList[1], healthList[0], currenthealth, healthList[2]);
        String rawHpr = getLore.get(7);
        if (rawHpr.contains("*")) {
            rawHpr = rawHpr.toLowerCase().replaceAll("[^1234567890/*]", "");
            rawHpr = rawHpr.substring(1, rawHpr.indexOf("*") - 1);
        } else {
            rawHpr = rawHpr.toLowerCase().replaceAll("[^1234567890/]", "");
            rawHpr = rawHpr.substring(1, rawHpr.lastIndexOf("7"));
        }
        currentrawHpr = Double.parseDouble(rawHpr);

        double[] rawHprList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Guardian").getAsJsonObject().get("rawHpr"), double[].class);
        double rawHprWeight = CalcUtils.positveStats(rawHprList[1], rawHprList[0], currentrawHpr, rawHprList[2]);
        String fireDefense = getLore.get(8);
        fireDefense = fireDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        fireDefense = fireDefense.substring(1, fireDefense.lastIndexOf("%"));
        currentfireDefense = Double.parseDouble(fireDefense);

        double[] fireDefenseList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Guardian").getAsJsonObject().get("fireDefense"), double[].class);
        double fireDefenseWeight = CalcUtils.positveStats(fireDefenseList[1], fireDefenseList[0], currentfireDefense, fireDefenseList[2]);
        String waterDefense = getLore.get(9);
        waterDefense = waterDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        waterDefense = waterDefense.substring(1, waterDefense.lastIndexOf("%"));
        currentwaterDefense = Double.parseDouble(waterDefense);

        double[] waterDefenseList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Guardian").getAsJsonObject().get("waterDefense"), double[].class);
        double waterDefenseWeight = CalcUtils.positveStats(waterDefenseList[1], waterDefenseList[0], currentwaterDefense, waterDefenseList[2]);
        String earthDefense = getLore.get(10);
        earthDefense = earthDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        earthDefense = earthDefense.substring(1, earthDefense.lastIndexOf("%"));
        currentearthDefense = Double.parseDouble(earthDefense);

        double[] earthDefenseList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Guardian").getAsJsonObject().get("earthDefense"), double[].class);
        double earthDefenseWeight = CalcUtils.positveStats(earthDefenseList[1], earthDefenseList[0], currentearthDefense, earthDefenseList[2]);

        int finalWeight = (int) (thornsWeight + healthWeight + rawHprWeight + fireDefenseWeight + waterDefenseWeight + earthDefenseWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
