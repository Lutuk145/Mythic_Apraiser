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

public class Collapse {
    private static double currentmanaSteal;
    private static double currentexploding;
    private static double currentmainDamage;
    private static double currentfireDefense;
    private static double currentwaterDefense;
    private static double currentairDefense;
    private static double currentthunderDefense;
    private static double currentearthDefense;

    public static ItemStack Collapse(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String manaSteal = getLore.get(i+4);
        manaSteal = manaSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        manaSteal = manaSteal.substring(1, manaSteal.lastIndexOf("/"));
        currentmanaSteal = Double.parseDouble(manaSteal);

        double[] manaStealList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Collapse").getAsJsonObject().get("manaSteal"), double[].class);
        double manaStealWeight1 = CalcUtils.positveStats(manaStealList[1], manaStealList[0], currentmanaSteal, manaStealList[2]);
        double manaStealWeight2 = CalcUtils.positveStats(manaStealList[1], manaStealList[0], currentmanaSteal, manaStealList[3]);

        String exploding = getLore.get(i+5);
        exploding = exploding.toLowerCase().replaceAll("[^1234567890%]", "");
        exploding = exploding.substring(1, exploding.lastIndexOf("%"));
        currentexploding = Double.parseDouble(exploding);

        double[] explodingList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Collapse").getAsJsonObject().get("exploding"), double[].class);
        double explodingWeight1 = CalcUtils.positveStats(explodingList[1], explodingList[0], currentexploding, explodingList[2]);
        double explodingWeight2 = CalcUtils.positveStats(explodingList[1], explodingList[0], currentexploding, explodingList[3]);

        String mainDamage = getLore.get(i+6);
        mainDamage = mainDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        mainDamage = mainDamage.substring(1, mainDamage.lastIndexOf("%"));
        currentmainDamage = Double.parseDouble(mainDamage);

        double[] mainDamageList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Collapse").getAsJsonObject().get("meleeDamagePercent"), double[].class);
        double mainDamageWeight1 = CalcUtils.positveStats(mainDamageList[1], mainDamageList[0], currentmainDamage, mainDamageList[2]);
        double mainDamageWeight2 = CalcUtils.positveStats(mainDamageList[1], mainDamageList[0], currentmainDamage, mainDamageList[3]);

        String fireDefense = getLore.get(i+7);
        fireDefense = fireDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        fireDefense = fireDefense.substring(1, fireDefense.lastIndexOf("%"));
        currentfireDefense = Double.parseDouble(fireDefense);

        double[] fireDefenseList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Collapse").getAsJsonObject().get("fireDefense"), double[].class);
        double fireDefenseWeight1 = CalcUtils.negativeStats(fireDefenseList[1], fireDefenseList[0], currentfireDefense, fireDefenseList[2]);
        double fireDefenseWeight2 = CalcUtils.negativeStats(fireDefenseList[1], fireDefenseList[0], currentfireDefense, fireDefenseList[3]);

        String waterDefense = getLore.get(i+8);
        waterDefense = waterDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        waterDefense = waterDefense.substring(1, waterDefense.lastIndexOf("%"));
        currentwaterDefense = Double.parseDouble(waterDefense);

        double[] waterDefenseList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Collapse").getAsJsonObject().get("waterDefense"), double[].class);
        double waterDefenseWeight1 = CalcUtils.negativeStats(waterDefenseList[1], waterDefenseList[0], currentwaterDefense, waterDefenseList[2]);
        double waterDefenseWeight2 = CalcUtils.negativeStats(waterDefenseList[1], waterDefenseList[0], currentwaterDefense, waterDefenseList[3]);

        String airDefense = getLore.get(i+9);
        airDefense = airDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        airDefense = airDefense.substring(1, airDefense.lastIndexOf("%"));
        currentairDefense = Double.parseDouble(airDefense);

        double[] airDefenseList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Collapse").getAsJsonObject().get("airDefense"), double[].class);
        double airDefenseWeight1 = CalcUtils.negativeStats(airDefenseList[1], airDefenseList[0], currentairDefense, airDefenseList[2]);
        double airDefenseWeight2 = CalcUtils.negativeStats(airDefenseList[1],airDefenseList[0], currentairDefense, airDefenseList[3]);

        String thunderDefense = getLore.get(i+10);
        thunderDefense = thunderDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        thunderDefense = thunderDefense.substring(1, thunderDefense.lastIndexOf("%"));
        currentthunderDefense = Double.parseDouble(thunderDefense);

        double[] thunderDefenseList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Collapse").getAsJsonObject().get("thunderDefense"), double[].class);
        double thunderDefenseWeight1 = CalcUtils.negativeStats(thunderDefenseList[1], thunderDefenseList[0], currentthunderDefense, thunderDefenseList[2]);
        double thunderDefenseWeight2 = CalcUtils.negativeStats(thunderDefenseList[1], thunderDefenseList[0], currentthunderDefense, thunderDefenseList[3]);

        String earthDefense = getLore.get(i+11);
        earthDefense = earthDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        earthDefense = earthDefense.substring(1, earthDefense.lastIndexOf("%"));
        currentearthDefense = Double.parseDouble(earthDefense);

        double[] earthDefenseList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Collapse").getAsJsonObject().get("earthDefense"), double[].class);
        double earthDefenseWeight1 = CalcUtils.negativeStats(earthDefenseList[1], earthDefenseList[0], currentearthDefense, earthDefenseList[2]);
        double earthDefenseWeight2 = CalcUtils.negativeStats(earthDefenseList[1], earthDefenseList[0], currentearthDefense, earthDefenseList[3]);

        int finalWeight1 = (int) Math.round(manaStealWeight1 + explodingWeight1 + mainDamageWeight1+fireDefenseWeight1+waterDefenseWeight1+airDefenseWeight1+thunderDefenseWeight1+earthDefenseWeight1);
        int finalWeight2 = (int) Math.round(manaStealWeight2 + explodingWeight2 + mainDamageWeight2+fireDefenseWeight2+waterDefenseWeight2+airDefenseWeight2+thunderDefenseWeight2+earthDefenseWeight2);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight1)+" §f, "+CodingUtils.color(finalWeight2)+" §f"));

        return mainHandItem;
    }
}
