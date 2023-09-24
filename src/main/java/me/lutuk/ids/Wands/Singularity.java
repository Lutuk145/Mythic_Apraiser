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

public class Singularity {
    private static double currentwalkSpeed;
    private static double currentrawHpr;
    private static double currentspellDamage;
    private static double currentspelLRaw;
    private static double currentmeleePercent;
    private static double currentmeleeRaw;

    public static ItemStack Singularity(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String walkSpeed = getLore.get(i+4);
        walkSpeed = walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Singularity").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight1 = CalcUtils.negativeStats(walkSpeedList[1], walkSpeedList[0], currentwalkSpeed, walkSpeedList[2]);
        double walkSpeedWeight2 = CalcUtils.negativeStats(walkSpeedList[1], walkSpeedList[0], currentwalkSpeed, walkSpeedList[3]);
        String rawHpr = getLore.get(i+5);
        if (rawHpr.contains("*")) {
            rawHpr = rawHpr.toLowerCase().replaceAll("[^1234567890/*]", "");
            rawHpr = rawHpr.substring(1, rawHpr.indexOf("*") - 1);
        } else {
            rawHpr = rawHpr.toLowerCase().replaceAll("[^1234567890/]", "");
            rawHpr = rawHpr.substring(1, rawHpr.lastIndexOf("7"));
        }
        currentrawHpr = Double.parseDouble(rawHpr);

        double[] rawHprList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Singularity").getAsJsonObject().get("rawHpr"), double[].class);
        double rawHprWeight1 = CalcUtils.positveStats(rawHprList[1], rawHprList[0], currentrawHpr, rawHprList[2]);
        double rawHprWeight2 = CalcUtils.positveStats(rawHprList[1], rawHprList[0], currentrawHpr, rawHprList[3]);
        String spellDamage = getLore.get(i+6);
        spellDamage = spellDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        spellDamage = spellDamage.substring(1, spellDamage.lastIndexOf("%"));
        currentspellDamage = Double.parseDouble(spellDamage);

        double[] spellDamageList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Singularity").getAsJsonObject().get("spellDamage"), double[].class);
        double spellDamageWeight1 = CalcUtils.positveStats(spellDamageList[1], spellDamageList[0], currentspellDamage, spellDamageList[2]);
        double spellDamageWeight2 = CalcUtils.positveStats(spellDamageList[1], spellDamageList[0], currentspellDamage, spellDamageList[3]);
        String spelLRaw = getLore.get(i+7);
        if (spelLRaw.contains("*")) {
            spelLRaw = spelLRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            spelLRaw = spelLRaw.substring(1, spelLRaw.indexOf("*") - 1);
        } else {
            spelLRaw = spelLRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            spelLRaw = spelLRaw.substring(1, spelLRaw.lastIndexOf("7"));
        }
        currentspelLRaw = Double.parseDouble(spelLRaw);

        double[] spelLRawList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Singularity").getAsJsonObject().get("spellRaw"), double[].class);
        double spelLRawWeight1 = CalcUtils.positveStats(spelLRawList[1], spelLRawList[0], currentspelLRaw, spelLRawList[2]);
        double spelLRawWeight2 = CalcUtils.positveStats(spelLRawList[1], spelLRawList[0], currentspelLRaw, spelLRawList[3]);
        String meleePercent = getLore.get(i+8);
        meleePercent = meleePercent.toLowerCase().replaceAll("[^1234567890%]", "");
        meleePercent = meleePercent.substring(1, meleePercent.lastIndexOf("%"));
        currentmeleePercent = Double.parseDouble(meleePercent);

        double[] meleePercentList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Singularity").getAsJsonObject().get("meleePercent"), double[].class);
        double meleePercentWeight1 = CalcUtils.positveStats(meleePercentList[1], meleePercentList[0], currentmeleePercent, meleePercentList[2]);
        double meleePercentWeight2 = CalcUtils.positveStats(meleePercentList[1], meleePercentList[0], currentmeleePercent, meleePercentList[3]);
        String meleeRaw = getLore.get(i+9);
        if (meleeRaw.contains("*")) {
            meleeRaw = meleeRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            meleeRaw = meleeRaw.substring(1, meleeRaw.indexOf("*") - 1);
        } else {
            meleeRaw = meleeRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            meleeRaw = meleeRaw.substring(1, meleeRaw.lastIndexOf("7"));
        }
        currentmeleeRaw = Double.parseDouble(meleeRaw);

        double[] meleeRawList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Singularity").getAsJsonObject().get("meleeRaw"), double[].class);
        double meleeRawWeight1 = CalcUtils.positveStats(meleeRawList[1], meleeRawList[0], currentmeleeRaw, meleeRawList[2]);
        double meleeRawWeight2 = CalcUtils.positveStats(meleeRawList[1], meleeRawList[0], currentmeleeRaw, meleeRawList[3]);


        int finalWeight1 = (int) Math.round(walkSpeedWeight1 + rawHprWeight1 + spellDamageWeight1 + spelLRawWeight1 + meleePercentWeight1 + meleeRawWeight1);
        int finalWeight2 = (int) Math.round(walkSpeedWeight2 + rawHprWeight2 + spellDamageWeight2 + spelLRawWeight2 + meleePercentWeight2 + meleeRawWeight2);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight1) + " §f, " + CodingUtils.color(finalWeight2) + " §f"));
        return mainHandItem;
    }
}
