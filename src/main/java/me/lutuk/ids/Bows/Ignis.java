package me.lutuk.ids.Bows;

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

public class Ignis {
    private static double currenthprPercent;
    private static double currenthealth;
    private static double currentrawHpr;
    private static double currentfireDamage;
    private static double currentfireDefense;
    private static double currentairDefense;
    private static double currentfourthSpellPercent;

    public static ItemStack Ignis(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String hprPercent= getLore.get(i+4);
        hprPercent= hprPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        hprPercent = hprPercent.substring(1, hprPercent.lastIndexOf("%"));
        currenthprPercent = Double.parseDouble(hprPercent);

        double[] hprPercentList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Ignis").getAsJsonObject().get("hprPercent"), double[].class);
        double hprPercentWeight = CalcUtils.positveStats(hprPercentList[1],hprPercentList[0],currenthprPercent,hprPercentList[2]);
        String health= getLore.get(i+5);
        if (health.contains("*")){
            health=health.toLowerCase().replaceAll("[^1234567890/*]", "");
            health=health.substring(1,health.indexOf("*")-1);
        }else {
            health=health.toLowerCase().replaceAll("[^1234567890/]", "");
            health=health.substring(1,health.lastIndexOf("7"));
        }
        currenthealth = Double.parseDouble(health);

        double[] healthList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Ignis").getAsJsonObject().get("health"), double[].class);
        double healthWeight = CalcUtils.positveStats(healthList[1],healthList[0],currenthealth,healthList[2]);
        String rawHpr= getLore.get(i+6);
        if (rawHpr.contains("*")){
            rawHpr=rawHpr.toLowerCase().replaceAll("[^1234567890/*]", "");
            rawHpr=rawHpr.substring(1,rawHpr.indexOf("*")-1);
        }else {
            rawHpr=rawHpr.toLowerCase().replaceAll("[^1234567890/]", "");
            rawHpr=rawHpr.substring(1,rawHpr.lastIndexOf("7"));
        }
        currentrawHpr = Double.parseDouble(rawHpr);

        double[] rawHprList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Ignis").getAsJsonObject().get("rawHpr"), double[].class);
        double rawHprWeight = CalcUtils.positveStats(rawHprList[1],rawHprList[0],currentrawHpr,rawHprList[2]);
        String fireDamage= getLore.get(i+7);
        fireDamage= fireDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        fireDamage = fireDamage.substring(1, fireDamage.lastIndexOf("%"));
        currentfireDamage = Double.parseDouble(fireDamage);

        double[] fireDamageList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Ignis").getAsJsonObject().get("fireDamage"), double[].class);
        double fireDamageWeight = CalcUtils.positveStats(fireDamageList[1],fireDamageList[0],currentfireDamage,fireDamageList[2]);
        String fireDefense= getLore.get(i+8);
        fireDefense= fireDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        fireDefense = fireDefense.substring(1, fireDefense.lastIndexOf("%"));
        currentfireDefense = Double.parseDouble(fireDefense);

        double[] fireDefenseList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Ignis").getAsJsonObject().get("fireDefense"), double[].class);
        double fireDefenseWeight = CalcUtils.positveStats(fireDefenseList[1],fireDefenseList[0],currentfireDefense,fireDefenseList[2]);
        String airDefense= getLore.get(i+9);
        airDefense= airDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        airDefense = airDefense.substring(1, airDefense.lastIndexOf("%"));
        currentairDefense = Double.parseDouble(airDefense);

        double[] airDefenseList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Ignis").getAsJsonObject().get("airDefense"), double[].class);
        double airDefenseWeight = CalcUtils.positveStats(airDefenseList[1],airDefenseList[0],currentairDefense,airDefenseList[2]);
        String fourthSpellPercent= getLore.get(i+10);
        fourthSpellPercent= fourthSpellPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        fourthSpellPercent = fourthSpellPercent.substring(1, fourthSpellPercent.lastIndexOf("%"));
        currentfourthSpellPercent = Double.parseDouble(fourthSpellPercent);

        double[] fourthSpellPercentList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Ignis").getAsJsonObject().get("forthSpellPercent"), double[].class);
        double fourthSpellPercentWeight = CalcUtils.positveStats(fourthSpellPercentList[1],fourthSpellPercentList[0],currentfourthSpellPercent,fourthSpellPercentList[2]);

        int finalWeight1 = (int) (hprPercentWeight + healthWeight + rawHprWeight + fireDamageWeight + fireDefenseWeight + airDefenseWeight + fourthSpellPercentWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight1)));
        return mainHandItem;
    }
}
