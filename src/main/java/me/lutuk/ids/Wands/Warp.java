package me.lutuk.ids.Wands;

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

public class Warp {
    private static double currenthprPercent;
    private static double currentmanaRegen;
    private static double currentreflection;
    private static double currentexploding;
    private static double currentwalkSpeed;
    private static double currentrawHpr;
    private static double currentairDamage;
    private static double currentfirstSpellRaw;
    private static double currentsecondSpellRaw;
    public static ItemStack Warp(ItemStack mainHandItem, int i) throws IOException {
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

        double[] hprPercentList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Warp").getAsJsonObject().get("hprPercent"), double[].class);
        double hprPercentWeight1 = CalcUtils.negativeStats(hprPercentList[1],hprPercentList[0],currenthprPercent,hprPercentList[2]);
        double hprPercentWeight2 = CalcUtils.negativeStats(hprPercentList[1],hprPercentList[0],currenthprPercent,hprPercentList[3]);

        String manaRegen= getLore.get(i+5);
        manaRegen= manaRegen.toLowerCase().replaceAll("[^1234567890/]", "");
        manaRegen = manaRegen.substring(1, manaRegen.lastIndexOf("/"));
        currentmanaRegen = Double.parseDouble(manaRegen);

        double[] manaRegenList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Warp").getAsJsonObject().get("manaRegen"), double[].class);
        double manaRegenWeight1 = CalcUtils.negativeStats(manaRegenList[1],manaRegenList[0],currentmanaRegen,manaRegenList[2]);
        double manaRegenWeight2 = CalcUtils.negativeStats(manaRegenList[1],manaRegenList[0],currentmanaRegen,manaRegenList[3]);

        String reflection= getLore.get(i+6);
        reflection= reflection.toLowerCase().replaceAll("[^1234567890%]", "");
        reflection = reflection.substring(1, reflection.lastIndexOf("%"));
        currentreflection = Double.parseDouble(reflection);

        double[] reflectionList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Warp").getAsJsonObject().get("reflection"), double[].class);
        double reflectionWeight1 = CalcUtils.positveStats(reflectionList[1],reflectionList[0],currentreflection,reflectionList[2]);
        double reflectionWeight2 = CalcUtils.positveStats(reflectionList[1],reflectionList[0],currentreflection,reflectionList[3]);
        String exploding= getLore.get(i+7);
        exploding= exploding.toLowerCase().replaceAll("[^1234567890%]", "");
        exploding = exploding.substring(1, exploding.lastIndexOf("%"));
        currentexploding = Double.parseDouble(exploding);

        double[] explodingList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Warp").getAsJsonObject().get("exploding"), double[].class);
        double explodingWeight1 = CalcUtils.positveStats(explodingList[1],explodingList[0],currentexploding,explodingList[2]);
        double explodingWeight2 = CalcUtils.positveStats(explodingList[1],explodingList[0],currentexploding,explodingList[3]);
        String walkSpeed= getLore.get(i+8);
        walkSpeed= walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Warp").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight1 = CalcUtils.positveStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[2]);
        double walkSpeedWeight2 = CalcUtils.positveStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[3]);
        String rawHpr= getLore.get(i+9);
        if (rawHpr.contains("*")){
            rawHpr=rawHpr.toLowerCase().replaceAll("[^1234567890/*]", "");
            rawHpr=rawHpr.substring(1,rawHpr.indexOf("*")-1);
        }else {
            rawHpr=rawHpr.toLowerCase().replaceAll("[^1234567890/]", "");
            rawHpr=rawHpr.substring(1,rawHpr.lastIndexOf("7"));
        }
        currentrawHpr = Double.parseDouble(rawHpr);

        double[] rawHprList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Warp").getAsJsonObject().get("rawHpr"), double[].class);
        double rawHprWeight1 = CalcUtils.negativeStats(rawHprList[1],rawHprList[0],currentrawHpr,rawHprList[2]);
        double rawHprWeight2 = CalcUtils.negativeStats(rawHprList[1],rawHprList[0],currentrawHpr,rawHprList[3]);
        String airDamage= getLore.get(i+10);
        airDamage= airDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        airDamage = airDamage.substring(1, airDamage.lastIndexOf("%"));
        currentairDamage = Double.parseDouble(airDamage);

        double[] airDamageList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Warp").getAsJsonObject().get("airDamage"), double[].class);
        double airDamageWeight1 = CalcUtils.positveStats(airDamageList[1],airDamageList[0],currentairDamage,airDamageList[2]);
        double airDamageWeight2 = CalcUtils.positveStats(airDamageList[1],airDamageList[0],currentairDamage,airDamageList[3]);
        String firstSpellRaw= getLore.get(i+11);
        if (firstSpellRaw.contains("*")){
            firstSpellRaw=firstSpellRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            firstSpellRaw=firstSpellRaw.substring(1,firstSpellRaw.indexOf("*")-1);
        }else {
            firstSpellRaw=firstSpellRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            firstSpellRaw=firstSpellRaw.substring(1,firstSpellRaw.lastIndexOf("7"));
        }
        currentfirstSpellRaw = Double.parseDouble(firstSpellRaw);

        double[] firstSpellRawList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Warp").getAsJsonObject().get("firstSpellRaw"), double[].class);
        double firstSpellRawWeight1 = CalcUtils.negativeStats(firstSpellRawList[1],firstSpellRawList[0],currentfirstSpellRaw,firstSpellRawList[2]);
        double firstSpellRawWeight2 = CalcUtils.negativeStats(firstSpellRawList[1],firstSpellRawList[0],currentfirstSpellRaw,firstSpellRawList[3]);
        String secondSpellRaw= getLore.get(i+12);
        if (secondSpellRaw.contains("*")){
            secondSpellRaw=secondSpellRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            secondSpellRaw=secondSpellRaw.substring(1,secondSpellRaw.indexOf("*")-1);
        }else {
            secondSpellRaw=secondSpellRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            secondSpellRaw=secondSpellRaw.substring(1,secondSpellRaw.lastIndexOf("7"));
        }
        currentsecondSpellRaw = Double.parseDouble(secondSpellRaw);

        double[] secondSpellRawList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Warp").getAsJsonObject().get("secondSpellRaw"), double[].class);
        double secondSpellRawWeight1 = CalcUtils.positveStats(secondSpellRawList[1],secondSpellRawList[0],currentsecondSpellRaw,secondSpellRawList[2]);
        double secondSpellRawWeight2 = CalcUtils.positveStats(secondSpellRawList[1],secondSpellRawList[0],currentsecondSpellRaw,secondSpellRawList[3]);

        int finalWeight1 = (int) Math.round(reflectionWeight1+explodingWeight1+walkSpeedWeight1+rawHprWeight1+airDamageWeight1+firstSpellRawWeight1+secondSpellRawWeight1+manaRegenWeight1+hprPercentWeight1);
        int finalWeight2 = (int) Math.round(reflectionWeight2+explodingWeight2+walkSpeedWeight2+rawHprWeight2+airDamageWeight2+firstSpellRawWeight2+secondSpellRawWeight2+manaRegenWeight2+hprPercentWeight2);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight1)+" §f, " + CodingUtils.color(finalWeight2)));
        return mainHandItem;
    }
}
