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

public class Boreal {
    private static double currenthprPercent;
    private static double currentmanaRegen;
    private static double currentreflection;
    private static double currentwalkSpeed;
    private static double currentrawHpr;
    private static double currentthunderDamage;
    private static double currentearthDamage;
    private static double currentfireDefense;
    private static double currentairDefense;
    public static ItemStack Boreal(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String hprPercent= getLore.get(i+2);
        hprPercent= hprPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        hprPercent = hprPercent.substring(1, hprPercent.lastIndexOf("%"));
        currenthprPercent = Double.parseDouble(hprPercent);

        double[] hprPercentList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Boreal").getAsJsonObject().get("hprPercent"), double[].class);
        double hprPercentWeight = CalcUtils.positveStats(hprPercentList[1],hprPercentList[0],currenthprPercent,hprPercentList[2]);

        String manaRegen= getLore.get(i+3);
        manaRegen= manaRegen.toLowerCase().replaceAll("[^1234567890/]", "");
        manaRegen = manaRegen.substring(1, manaRegen.lastIndexOf("/"));
        currentmanaRegen = Double.parseDouble(manaRegen);

        double[] manaRegenList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Boreal").getAsJsonObject().get("manaRegen"), double[].class);
        double manaRegenWeight = CalcUtils.positveStats(manaRegenList[1],manaRegenList[0],currentmanaRegen,manaRegenList[2]);

        String reflection= getLore.get(i+4);
        reflection= reflection.toLowerCase().replaceAll("[^1234567890%]", "");
        reflection = reflection.substring(1, reflection.lastIndexOf("%"));
        currentreflection = Double.parseDouble(reflection);

        double[] reflectionList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Boreal").getAsJsonObject().get("reflection"), double[].class);
        double reflectionWeight = CalcUtils.positveStats(reflectionList[1],reflectionList[0],currentreflection,reflectionList[2]);
        String walkSpeed= getLore.get(i+5);
        walkSpeed= walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Boreal").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight = CalcUtils.positveStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[2]);
        String rawHpr= getLore.get(i+6);
        if (rawHpr.contains("*")){
            rawHpr=rawHpr.toLowerCase().replaceAll("[^1234567890/*]", "");
            rawHpr=rawHpr.substring(1,rawHpr.indexOf("*")-1);
        }else {
            rawHpr=rawHpr.toLowerCase().replaceAll("[^1234567890/]", "");
            rawHpr=rawHpr.substring(1,rawHpr.lastIndexOf("7"));
        }
        currentrawHpr = Double.parseDouble(rawHpr);

        double[] rawHprList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Boreal").getAsJsonObject().get("rawHpr"), double[].class);
        double rawHprWeight = CalcUtils.positveStats(rawHprList[1],rawHprList[0],currentrawHpr,rawHprList[2]);
        String thunderDamage= getLore.get(i+7);
        thunderDamage= thunderDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        thunderDamage = thunderDamage.substring(1, thunderDamage.lastIndexOf("%"));
        currentthunderDamage = Double.parseDouble(thunderDamage);

        double[] thunderDamageList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Boreal").getAsJsonObject().get("thunderDamage"), double[].class);
        double thunderDamageWeight = CalcUtils.negativeStats(thunderDamageList[1],thunderDamageList[0],currentthunderDamage,thunderDamageList[2]);
        String earthDamage= getLore.get(i+8);
        earthDamage= earthDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        earthDamage = earthDamage.substring(1, earthDamage.lastIndexOf("%"));
        currentearthDamage = Double.parseDouble(earthDamage);

        double[] earthDamageList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Boreal").getAsJsonObject().get("earthDamage"), double[].class);
        double earthDamageWeight = CalcUtils.negativeStats(earthDamageList[1],earthDamageList[0],currentearthDamage,earthDamageList[2]);
        String fireDefense= getLore.get(i+9);
        fireDefense= fireDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        fireDefense = fireDefense.substring(1, fireDefense.lastIndexOf("%"));
        currentfireDefense = Double.parseDouble(fireDefense);

        double[] fireDefenseList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Boreal").getAsJsonObject().get("fireDefense"), double[].class);
        double fireDefenseWeight = CalcUtils.positveStats(fireDefenseList[1],fireDefenseList[0],currentfireDefense,fireDefenseList[2]);
        String airDefense= getLore.get(i+10);
        airDefense= airDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        airDefense = airDefense.substring(1, airDefense.lastIndexOf("%"));
        currentairDefense = Double.parseDouble(airDefense);

        double[] airDefenseList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Boreal").getAsJsonObject().get("airDefense"), double[].class);
        double airDefenseWeight = CalcUtils.positveStats(airDefenseList[1],airDefenseList[0],currentairDefense,airDefenseList[2]);

        int finalWeight = (int) (hprPercentWeight + manaRegenWeight + reflectionWeight + walkSpeedWeight + rawHprWeight + thunderDamageWeight + earthDamageWeight + fireDefenseWeight + airDefenseWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString()+ CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
