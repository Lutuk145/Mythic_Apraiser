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

public class Thrundacrack {
    private static double currentHpr;
    private static double currentWs;
    private static double currentWaterDamage;
    private static double currentThunderDamage;
    private static double currentThirdSpell;
    public static ItemStack Thrundacrack(ItemStack mainHandItem,int i) throws IOException {
        String output = String.valueOf(mainHandItem.getNbt());

        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String hpr = getLore.get(i+4);
        String ws = getLore.get(i+5);
        String waterDamage = getLore.get(i+6);
        String thunderDamage = getLore.get(i+7);
        String thirdSpell = getLore.get(i+8);

        hpr = hpr.toLowerCase().replaceAll("[^1234567890%]", "");
        hpr = hpr.substring(1, hpr.lastIndexOf("%"));
        currentHpr = Integer.parseInt(hpr);

        ws = ws.toLowerCase().replaceAll("[^1234567890%]", "");
        ws = ws.substring(1, ws.lastIndexOf("%"));
        currentWs = Integer.parseInt(ws);

        waterDamage = waterDamage.toLowerCase().replaceAll("[^1234567890-]", "");
        waterDamage = waterDamage.substring(1, 3);
        currentWaterDamage = Integer.parseInt(waterDamage);

        thunderDamage= thunderDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        thunderDamage= thunderDamage.substring(1, thunderDamage.lastIndexOf("%"));
        currentThunderDamage = Integer.parseInt(thunderDamage);

        thirdSpell = thirdSpell.toLowerCase().replaceAll("[^1234567890]", "");
        thirdSpell = thirdSpell.substring(1, 2);
        currentThirdSpell = Integer.parseInt(thirdSpell);

        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        double[] hprList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Thrundacrack").getAsJsonObject().get("healthRegenPercent"), double[].class);
        double[] wsList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Thrundacrack").getAsJsonObject().get("walkSpeed"), double[].class);
        double[] waterDamageList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Thrundacrack").getAsJsonObject().get("waterDamage"), double[].class);
        double[] thunderDamageList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Thrundacrack").getAsJsonObject().get("thunderDamage"), double[].class);
        double[] thirdSpellList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Thrundacrack").getAsJsonObject().get("thirdSpellRaw"), double[].class);

        double wsWeight = CalcUtils.positveStats(wsList[1], wsList[0], currentWs, wsList[2]);
        double hprWeight = CalcUtils.negativeStats(hprList[1], hprList[0], currentHpr, hprList[2]);
        double waterDamageWeight = CalcUtils.positveStats(waterDamageList[1], waterDamageList[0], currentWaterDamage, waterDamageList[2]);
        double  thunderDamageWeight = CalcUtils.positveStats(thunderDamageList[1], thunderDamageList[0], currentThunderDamage, thunderDamageList[2]);
        double thirdSpellWeight = CalcUtils.positveStats(thirdSpellList[1], thirdSpellList[0], currentThirdSpell, thirdSpellList[2]);

        int finalWeight1 = (int) Math.round(wsWeight + hprWeight + waterDamageWeight+thunderDamageWeight+thirdSpellWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight1)));
        System.out.println(mainHandItem.getNbt());
        return mainHandItem;
    }
}
