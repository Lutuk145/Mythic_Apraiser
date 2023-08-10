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

public class Immolation {
    private static double currenthprPercent;
    private static double currenthealth;
    private static double currentfireDamage;
    private static double currentwaterDamage;
    private static double currentairDamage;
    private static double currentthirdSpellPercent;
    public static ItemStack Immolation(ItemStack mainHandItem) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String hprPercent = getLore.get(5);
        hprPercent = hprPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        hprPercent = hprPercent.substring(1, hprPercent.lastIndexOf("%"));
        currenthprPercent = Double.parseDouble(hprPercent);

        double[] hprPercentList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Immolation").getAsJsonObject().get("hprPercent"), double[].class);
        double hprPercentWeight = CalcUtils.negativeStats(hprPercentList[1], hprPercentList[0], currenthprPercent, hprPercentList[2]);
        String health = getLore.get(6);
        if (health.contains("*")) {
            health = health.toLowerCase().replaceAll("[^1234567890/*]", "");
            health = health.substring(1, health.indexOf("*") - 1);
        } else {
            health = health.toLowerCase().replaceAll("[^1234567890/]", "");
            health = health.substring(1, health.lastIndexOf("7"));
        }
        currenthealth = Double.parseDouble(health);

        double[] healthList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Immolation").getAsJsonObject().get("health"), double[].class);
        double healthWeight = CalcUtils.negativeStats(healthList[1], healthList[0], currenthealth, healthList[2]);
        String fireDamage = getLore.get(7);
        fireDamage = fireDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        fireDamage = fireDamage.substring(1, fireDamage.lastIndexOf("%"));
        currentfireDamage = Double.parseDouble(fireDamage);

        double[] fireDamageList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Immolation").getAsJsonObject().get("fireDamage"), double[].class);
        double fireDamageWeight = CalcUtils.positveStats(fireDamageList[1], fireDamageList[0], currentfireDamage, fireDamageList[2]);
        String waterDamage = getLore.get(8);
        waterDamage = waterDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        waterDamage = waterDamage.substring(1, waterDamage.lastIndexOf("%"));
        currentwaterDamage = Double.parseDouble(waterDamage);

        double[] waterDamageList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Immolation").getAsJsonObject().get("waterDamage"), double[].class);
        double waterDamageWeight = CalcUtils.negativeStats(waterDamageList[1], waterDamageList[0], currentwaterDamage, waterDamageList[2]);
        String airDamage = getLore.get(9);
        airDamage = airDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        airDamage = airDamage.substring(1, airDamage.lastIndexOf("%"));
        currentairDamage = Double.parseDouble(airDamage);

        double[] airDamageList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Immolation").getAsJsonObject().get("airDamage"), double[].class);
        double airDamageWeight = CalcUtils.positveStats(airDamageList[1], airDamageList[0], currentairDamage, airDamageList[2]);
        String thirdSpellPercent = getLore.get(10);
        thirdSpellPercent = thirdSpellPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        thirdSpellPercent = thirdSpellPercent.substring(1, thirdSpellPercent.lastIndexOf("%"));
        currentthirdSpellPercent = Double.parseDouble(thirdSpellPercent);

        double[] thirdSpellPercentList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Immolation").getAsJsonObject().get("thirdSpellPercent"), double[].class);
        double thirdSpellPercentWeight = CalcUtils.positveStats(thirdSpellPercentList[1], thirdSpellPercentList[0], currentthirdSpellPercent, thirdSpellPercentList[2]);

        int finalWeight = (int) Math.round(hprPercentWeight + healthWeight + fireDamageWeight + waterDamageWeight + airDamageWeight + thirdSpellPercentWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
