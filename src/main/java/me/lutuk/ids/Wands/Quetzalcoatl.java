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

public class Quetzalcoatl {
    private static double currentlifeSteal;
    private static double currentwalkSpeed;
    private static double currentwaterDamage;
    private static double currentspellRaw;
    private static double currentmeleeRaw;
    private static double currentjumpHeighy;

    public static ItemStack Quetzalcoatl(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String lifeSteal = getLore.get(i+2);
        lifeSteal = lifeSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        lifeSteal = lifeSteal.substring(1, lifeSteal.lastIndexOf("/"));
        currentlifeSteal = Double.parseDouble(lifeSteal);

        double[] lifeStealList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Quetzalcoatl").getAsJsonObject().get("lifeSteal"), double[].class);
        double lifeStealWeight = CalcUtils.positveStats(lifeStealList[1], lifeStealList[0], currentlifeSteal, lifeStealList[2]);

        String walkSpeed = getLore.get(i+3);
        walkSpeed = walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Quetzalcoatl").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight = CalcUtils.positveStats(walkSpeedList[1], walkSpeedList[0], currentwalkSpeed, walkSpeedList[2]);
        String waterDamage = getLore.get(i+4);
        waterDamage = waterDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        waterDamage = waterDamage.substring(1, waterDamage.lastIndexOf("%"));
        currentwaterDamage = Double.parseDouble(waterDamage);

        double[] waterDamageList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Quetzalcoatl").getAsJsonObject().get("waterDamage"), double[].class);
        double waterDamageWeight = CalcUtils.negativeStats(waterDamageList[1], waterDamageList[0], currentwaterDamage, waterDamageList[2]);
        String spellRaw = getLore.get(i+5);
        if (spellRaw.contains("*")) {
            spellRaw = spellRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            spellRaw = spellRaw.substring(1, spellRaw.indexOf("*") - 1);
        } else {
            spellRaw = spellRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            spellRaw = spellRaw.substring(1, spellRaw.lastIndexOf("7"));
        }
        currentspellRaw = Double.parseDouble(spellRaw);

        double[] spellRawList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Quetzalcoatl").getAsJsonObject().get("spellRaw"), double[].class);
        double spellRawWeight = CalcUtils.positveStats(spellRawList[1], spellRawList[0], currentspellRaw, spellRawList[2]);
        String meleeRaw = getLore.get(i+6);
        if (meleeRaw.contains("*")) {
            meleeRaw = meleeRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            meleeRaw = meleeRaw.substring(1, meleeRaw.indexOf("*") - 1);
        } else {
            meleeRaw = meleeRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            meleeRaw = meleeRaw.substring(1, meleeRaw.lastIndexOf("7"));
        }
        currentmeleeRaw = Double.parseDouble(meleeRaw);

        double[] meleeRawList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Quetzalcoatl").getAsJsonObject().get("meleeRaw"), double[].class);
        double meleeRawWeight = CalcUtils.positveStats(meleeRawList[1], meleeRawList[0], currentmeleeRaw, meleeRawList[2]);
        String jumpHeighy = getLore.get(i+7);
        if (jumpHeighy.contains("*")) {
            jumpHeighy = jumpHeighy.toLowerCase().replaceAll("[^1234567890/*]", "");
            jumpHeighy = jumpHeighy.substring(1, jumpHeighy.indexOf("*") - 1);
        } else {
            jumpHeighy = jumpHeighy.toLowerCase().replaceAll("[^1234567890/]", "");
            jumpHeighy = jumpHeighy.substring(1, jumpHeighy.lastIndexOf("7"));
        }
        currentjumpHeighy = Double.parseDouble(jumpHeighy);

        double[] jumpHeighyList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Quetzalcoatl").getAsJsonObject().get("jumpHeight"), double[].class);
        double jumpHeighyWeight = CalcUtils.positveStats(jumpHeighyList[1], jumpHeighyList[0], currentjumpHeighy, jumpHeighyList[2]);

        int finalWeight = (int) Math.round(lifeStealWeight + walkSpeedWeight + waterDamageWeight + spellRawWeight + meleeRawWeight + jumpHeighyWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
