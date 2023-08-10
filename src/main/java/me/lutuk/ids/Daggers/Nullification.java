package me.lutuk.ids.Daggers;

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

public class Nullification {
    private static double currentlifeSteal;
    private static double currentmanaSteal;
    private static double currentreflection;
    private static double currentpoison;
    private static double currentfireDefense;
    private static double currentwaterDefense;
    private static double currentairDefense;
    private static double currentthunderDefense;
    private static double currentearthDefense;
    public static ItemStack Nullification(ItemStack mainHandItem) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String lifeSteal= getLore.get(4);
        lifeSteal= lifeSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        lifeSteal = lifeSteal.substring(1, lifeSteal.lastIndexOf("/"));
        currentlifeSteal = Double.parseDouble(lifeSteal);

        double[] lifeStealList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Nullification").getAsJsonObject().get("lifeSteal"), double[].class);
        double lifeStealWeight = CalcUtils.positveStats(lifeStealList[1],lifeStealList[0],currentlifeSteal,lifeStealList[2]);


        String manaSteal= getLore.get(5);
        manaSteal= manaSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        manaSteal = manaSteal.substring(1, manaSteal.lastIndexOf("/"));
        currentmanaSteal = Double.parseDouble(manaSteal);

        double[] manaStealList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Nullification").getAsJsonObject().get("manaSteal"), double[].class);
        double manaStealWeight = CalcUtils.positveStats(manaStealList[1],manaStealList[0],currentmanaSteal,manaStealList[2]);

        String reflection= getLore.get(6);
        reflection= reflection.toLowerCase().replaceAll("[^1234567890%]", "");
        reflection = reflection.substring(1, reflection.lastIndexOf("%"));
        currentreflection = Double.parseDouble(reflection);

        double[] reflectionList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Nullification").getAsJsonObject().get("reflection"), double[].class);
        double reflectionWeight = CalcUtils.positveStats(reflectionList[1],reflectionList[0],currentreflection,reflectionList[2]);

        String poison= getLore.get(7);
        poison= poison.toLowerCase().replaceAll("[^1234567890/]", "");
        poison = poison.substring(1, poison.lastIndexOf("/"));
        currentpoison = Double.parseDouble(poison);

        double[] poisonList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Nullification").getAsJsonObject().get("poison"), double[].class);
        double poisonWeight = CalcUtils.negativeStats(poisonList[1],poisonList[0],currentpoison,poisonList[2]);

        String fireDefense= getLore.get(8);
        fireDefense= fireDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        fireDefense = fireDefense.substring(1, fireDefense.lastIndexOf("%"));
        currentfireDefense = Double.parseDouble(fireDefense);

        double[] fireDefenseList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Nullification").getAsJsonObject().get("fireDefense"), double[].class);
        double fireDefenseWeight = CalcUtils.positveStats(fireDefenseList[1],fireDefenseList[0],currentfireDefense,fireDefenseList[2]);
        String waterDefense= getLore.get(9);
        waterDefense= waterDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        waterDefense = waterDefense.substring(1, waterDefense.lastIndexOf("%"));
        currentwaterDefense = Double.parseDouble(waterDefense);

        double[] waterDefenseList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Nullification").getAsJsonObject().get("waterDefense"), double[].class);
        double waterDefenseWeight = CalcUtils.positveStats(waterDefenseList[1],waterDefenseList[0],currentwaterDefense,waterDefenseList[2]);
        String airDefense= getLore.get(10);
        airDefense= airDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        airDefense = airDefense.substring(1, airDefense.lastIndexOf("%"));
        currentairDefense = Double.parseDouble(airDefense);

        double[] airDefenseList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Nullification").getAsJsonObject().get("airDefense"), double[].class);
        double airDefenseWeight = CalcUtils.positveStats(airDefenseList[1],airDefenseList[0],currentairDefense,airDefenseList[2]);
        String thunderDefense= getLore.get(11);
        thunderDefense= thunderDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        thunderDefense = thunderDefense.substring(1, thunderDefense.lastIndexOf("%"));
        currentthunderDefense = Double.parseDouble(thunderDefense);

        double[] thunderDefenseList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Nullification").getAsJsonObject().get("thunderDefense"), double[].class);
        double thunderDefenseWeight = CalcUtils.positveStats(thunderDefenseList[1],thunderDefenseList[0],currentthunderDefense,thunderDefenseList[2]);
        String earthDefense= getLore.get(12);
        earthDefense= earthDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        earthDefense = earthDefense.substring(1, earthDefense.lastIndexOf("%"));
        currentearthDefense = Double.parseDouble(earthDefense);

        double[] earthDefenseList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Nullification").getAsJsonObject().get("earthDefense"), double[].class);
        double earthDefenseWeight = CalcUtils.positveStats(earthDefenseList[1],earthDefenseList[0],currentearthDefense,earthDefenseList[2]);

        int finalWeight = (int) (lifeStealWeight + manaStealWeight + reflectionWeight + poisonWeight + fireDefenseWeight + waterDefenseWeight + airDefenseWeight + thunderDefenseWeight + earthDefenseWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }

}
