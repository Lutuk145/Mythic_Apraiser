package me.lutuk.ids.Reliks;

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

public class Aftershock {
    private static double currenthealth;
    private static double currentearthDamage;
    private static double currentspellDamage;
    private static double currentearthDefense;
    private static double currentfourthSpellPercent;
    public static ItemStack AfterShock(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String health= getLore.get(i+5);
        if (health.contains("*")){
            health=health.toLowerCase().replaceAll("[^1234567890/*]", "");
            health=health.substring(1,health.indexOf("*")-1);
        }else {
            health=health.toLowerCase().replaceAll("[^1234567890/]", "");
            health=health.substring(1,health.lastIndexOf("7"));
        }
        currenthealth = Double.parseDouble(health);

        double[] healthList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Aftershock").getAsJsonObject().get("health"), double[].class);
        double healthWeight = CalcUtils.positveStats(healthList[1],healthList[0],currenthealth,healthList[2]);
        String earthDamage= getLore.get(i+6);
        earthDamage= earthDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        earthDamage = earthDamage.substring(1, earthDamage.lastIndexOf("%"));
        currentearthDamage = Double.parseDouble(earthDamage);

        double[] earthDamageList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Aftershock").getAsJsonObject().get("earthDamage"), double[].class);
        double earthDamageWeight = CalcUtils.positveStats(earthDamageList[1],earthDamageList[0],currentearthDamage,earthDamageList[2]);
        String spellDamage= getLore.get(i+7);
        spellDamage= spellDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        spellDamage = spellDamage.substring(1, spellDamage.lastIndexOf("%"));
        currentspellDamage = Double.parseDouble(spellDamage);

        double[] spellDamageList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Aftershock").getAsJsonObject().get("spellDamage"), double[].class);
        double spellDamageWeight = CalcUtils.negativeStats(spellDamageList[1],spellDamageList[0],currentspellDamage,spellDamageList[2]);
        String earthDefense= getLore.get(i+8);
        earthDefense= earthDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        earthDefense = earthDefense.substring(1, earthDefense.lastIndexOf("%"));
        currentearthDefense = Double.parseDouble(earthDefense);

        double[] earthDefenseList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Aftershock").getAsJsonObject().get("earthDefense"), double[].class);
        double earthDefenseWeight = CalcUtils.positveStats(earthDefenseList[1],earthDefenseList[0],currentearthDefense,earthDefenseList[2]);
        String fourthSpellPercent= getLore.get(i+9);
        fourthSpellPercent= fourthSpellPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        fourthSpellPercent = fourthSpellPercent.substring(1, fourthSpellPercent.lastIndexOf("%"));
        currentfourthSpellPercent = Double.parseDouble(fourthSpellPercent);

        double[] fourthSpellPercentList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Aftershock").getAsJsonObject().get("fourthSpellPercent"), double[].class);
        double fourthSpellPercentWeight = CalcUtils.positveStats(fourthSpellPercentList[1],fourthSpellPercentList[0],currentfourthSpellPercent,fourthSpellPercentList[2]);

        int finalWeight = (int) Math.round( healthWeight + earthDamageWeight + spellDamageWeight + earthDefenseWeight + fourthSpellPercentWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
