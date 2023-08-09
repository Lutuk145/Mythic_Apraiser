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

public class Convergence {
    private static double currenthealthRegenPercent;
    private static double currentthunderDamage;
    private static double currentearthDamage;
    private static double currentairDefense;
    private static double currentsprintRegen;
    private static double currentthirdSpellPercent;

    public static ItemStack Convergence(ItemStack mainHandItem) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String healthRegenPercent = getLore.get(2);
        healthRegenPercent = healthRegenPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        healthRegenPercent = healthRegenPercent.substring(1, healthRegenPercent.lastIndexOf("%"));
        currenthealthRegenPercent = Double.parseDouble(healthRegenPercent);

        double[] healthRegenPercentList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Convergence").getAsJsonObject().get("healthRegenPercent"), double[].class);
        double healthRegenPercentWeight = CalcUtils.positveStats(healthRegenPercentList[1], healthRegenPercentList[0], currenthealthRegenPercent, healthRegenPercentList[2]);
        String thunderDamage = getLore.get(3);
        thunderDamage = thunderDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        thunderDamage = thunderDamage.substring(1, thunderDamage.lastIndexOf("%"));
        currentthunderDamage = Double.parseDouble(thunderDamage);

        double[] thunderDamageList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Convergence").getAsJsonObject().get("thunderDamage"), double[].class);
        double thunderDamageWeight = CalcUtils.positveStats(thunderDamageList[1], thunderDamageList[0], currentthunderDamage, thunderDamageList[2]);
        String earthDamage = getLore.get(4);
        earthDamage = earthDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        earthDamage = earthDamage.substring(1, earthDamage.lastIndexOf("%"));
        currentearthDamage = Double.parseDouble(earthDamage);

        double[] earthDamageList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Convergence").getAsJsonObject().get("earthDamage"), double[].class);
        double earthDamageWeight = CalcUtils.positveStats(earthDamageList[1], earthDamageList[0], currentearthDamage, earthDamageList[2]);
        String airDefense = getLore.get(5);
        airDefense = airDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        airDefense = airDefense.substring(1, airDefense.lastIndexOf("%"));
        currentairDefense = Double.parseDouble(airDefense);

        double[] airDefenseList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Convergence").getAsJsonObject().get("airDefense"), double[].class);
        double airDefenseWeight = CalcUtils.positveStats(airDefenseList[1], airDefenseList[0], currentairDefense, airDefenseList[2]);
        String sprintRegen = getLore.get(6);
        sprintRegen = sprintRegen.toLowerCase().replaceAll("[^1234567890%]", "");
        sprintRegen = sprintRegen.substring(1, sprintRegen.lastIndexOf("%"));
        currentsprintRegen = Double.parseDouble(sprintRegen);

        double[] sprintRegenList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Convergence").getAsJsonObject().get("sprintRegen"), double[].class);
        double sprintRegenWeight = CalcUtils.positveStats(sprintRegenList[1], sprintRegenList[0], currentsprintRegen, sprintRegenList[2]);
        String thirdSpellPercent = getLore.get(7);
        thirdSpellPercent = thirdSpellPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        thirdSpellPercent = thirdSpellPercent.substring(1, thirdSpellPercent.lastIndexOf("%"));
        currentthirdSpellPercent = Double.parseDouble(thirdSpellPercent);

        double[] thirdSpellPercentList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Convergence").getAsJsonObject().get("thirdSpellPercent"), double[].class);
        double thirdSpellPercentWeight = CalcUtils.positveStats(thirdSpellPercentList[1], thirdSpellPercentList[0], currentthirdSpellPercent, thirdSpellPercentList[2]);

        int finalWeight = (int) Math.round(healthRegenPercentWeight + thunderDamageWeight + earthDamageWeight + airDefenseWeight + sprintRegenWeight + thirdSpellPercentWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
