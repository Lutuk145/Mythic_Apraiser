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

public class Revenant {
    private static double currentmanaSteal;
    private static double currentreflection;
    private static double currentwalkSpeed;
    private static double currenthealth;
    private static double currentairDamage;
    private static double currentearthDamage;
    private static double currentmeleePercent;
    private static double currentmeleeRaw;
    private static double currentfourthSpellPercent;
    public static ItemStack Revenant(ItemStack mainHandItem,int i) throws IOException {
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

        double[] manaStealList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Revenant").getAsJsonObject().get("manaSteal"), double[].class);
        double manaStealWeight1 = CalcUtils.positveStats(manaStealList[1],manaStealList[0],currentmanaSteal,manaStealList[2]);
        double manaStealWeight2 = CalcUtils.positveStats(manaStealList[1],manaStealList[0],currentmanaSteal,manaStealList[3]);

        String reflection= getLore.get(i+3);
        reflection= reflection.toLowerCase().replaceAll("[^1234567890%]", "");
        reflection = reflection.substring(1, reflection.lastIndexOf("%"));
        currentreflection = Double.parseDouble(reflection);

        double[] reflectionList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Revenant").getAsJsonObject().get("reflection"), double[].class);
        double reflectionWeight1 = CalcUtils.positveStats(reflectionList[1],reflectionList[0],currentreflection,reflectionList[2]);
        double reflectionWeight2 = CalcUtils.positveStats(reflectionList[1],reflectionList[0],currentreflection,reflectionList[3]);
        String walkSpeed= getLore.get(i+4);
        walkSpeed= walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Revenant").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight1 = CalcUtils.positveStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[2]);
        double walkSpeedWeight2 = CalcUtils.positveStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[3]);
        String health= getLore.get(i+5);
        if (health.contains("*")){
            health=health.toLowerCase().replaceAll("[^1234567890/*]", "");
            health=health.substring(1,health.indexOf("*")-1);
        }else {
            health=health.toLowerCase().replaceAll("[^1234567890/]", "");
            health=health.substring(1,health.lastIndexOf("7"));
        }
        currenthealth = Double.parseDouble(health);

        double[] healthList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Revenant").getAsJsonObject().get("health"), double[].class);
        double healthWeight1 = CalcUtils.negativeStats(healthList[1],healthList[0],currenthealth,healthList[2]);
        double healthWeight2 = CalcUtils.negativeStats(healthList[1],healthList[0],currenthealth,healthList[3]);
        String airDamage= getLore.get(i+6);
        airDamage= airDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        airDamage = airDamage.substring(1, airDamage.lastIndexOf("%"));
        currentairDamage = Double.parseDouble(airDamage);

        double[] airDamageList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Revenant").getAsJsonObject().get("airDamage"), double[].class);
        double airDamageWeight1 = CalcUtils.positveStats(airDamageList[1],airDamageList[0],currentairDamage,airDamageList[2]);
        double airDamageWeight2 = CalcUtils.positveStats(airDamageList[1],airDamageList[0],currentairDamage,airDamageList[3]);
        String earthDamage= getLore.get(i+7);
        earthDamage= earthDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        earthDamage = earthDamage.substring(1, earthDamage.lastIndexOf("%"));
        currentearthDamage = Double.parseDouble(earthDamage);

        double[] earthDamageList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Revenant").getAsJsonObject().get("earthDamage"), double[].class);
        double earthDamageWeight1 = CalcUtils.positveStats(earthDamageList[1],earthDamageList[0],currentearthDamage,earthDamageList[2]);
        double earthDamageWeight2 = CalcUtils.positveStats(earthDamageList[1],earthDamageList[0],currentearthDamage,earthDamageList[3]);
        String meleePercent= getLore.get(i+8);
        meleePercent= meleePercent.toLowerCase().replaceAll("[^1234567890%]", "");
        meleePercent = meleePercent.substring(1, meleePercent.lastIndexOf("%"));
        currentmeleePercent = Double.parseDouble(meleePercent);

        double[] meleePercentList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Revenant").getAsJsonObject().get("meleePercent"), double[].class);
        double meleePercentWeight1 = CalcUtils.negativeStats(meleePercentList[1],meleePercentList[0],currentmeleePercent,meleePercentList[2]);
        double meleePercentWeight2 = CalcUtils.negativeStats(meleePercentList[1],meleePercentList[0],currentmeleePercent,meleePercentList[3]);
        String meleeRaw= getLore.get(i+9);
        if (meleeRaw.contains("*")){
            meleeRaw=meleeRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            meleeRaw=meleeRaw.substring(1,meleeRaw.indexOf("*")-1);
        }else {
            meleeRaw=meleeRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            meleeRaw=meleeRaw.substring(1,meleeRaw.lastIndexOf("7"));
        }
        currentmeleeRaw = Double.parseDouble(meleeRaw);

        double[] meleeRawList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Revenant").getAsJsonObject().get("meleeRaw"), double[].class);
        double meleeRawWeight1 = CalcUtils.positveStats(meleeRawList[1],meleeRawList[0],currentmeleeRaw,meleeRawList[2]);
        double meleeRawWeight2 = CalcUtils.positveStats(meleeRawList[1],meleeRawList[0],currentmeleeRaw,meleeRawList[3]);
        String fourthSpellPercent= getLore.get(i+10);
        fourthSpellPercent= fourthSpellPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        fourthSpellPercent = fourthSpellPercent.substring(1, fourthSpellPercent.lastIndexOf("%"));
        currentfourthSpellPercent = Double.parseDouble(fourthSpellPercent);

        double[] fourthSpellPercentList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Revenant").getAsJsonObject().get("fourthSpellPercent"), double[].class);
        double fourthSpellPercentWeight1 = CalcUtils.positveStats(fourthSpellPercentList[1],fourthSpellPercentList[0],currentfourthSpellPercent,fourthSpellPercentList[2]);
        double fourthSpellPercentWeight2 = CalcUtils.positveStats(fourthSpellPercentList[1],fourthSpellPercentList[0],currentfourthSpellPercent,fourthSpellPercentList[3]);

        int finalWeight1 = (int) (reflectionWeight1+manaStealWeight1+walkSpeedWeight1+healthWeight1+airDamageWeight1+earthDamageWeight1+meleePercentWeight1+meleeRawWeight1+fourthSpellPercentWeight1);
        int finalWeight2 = (int) (reflectionWeight2+manaStealWeight2+walkSpeedWeight2+healthWeight2+airDamageWeight2+earthDamageWeight2+meleePercentWeight2+meleeRawWeight2+fourthSpellPercentWeight2);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString()+ CodingUtils.color(finalWeight1) + " §f, " + CodingUtils.color(finalWeight2) + " §f"));
        return mainHandItem;
    }
}
