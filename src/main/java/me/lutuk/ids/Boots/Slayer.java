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

public class Slayer {
    public static double currentWs;
    public static double currentSteeling;
    public static double currentHpr;
    public static double currentMainAttack;
    public static double currentThirdSpellCost;

    public static ItemStack Slayer(ItemStack mainHandItem) throws IOException {
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
//75","","§7§a+20 §7Dexterity","","§7§a+29%§2* §7Walk Speed","§7§a+1 tier §7Attack Speed","§7§a+7% §7Stealing","§7§c-267 §7Health Regen","§7§a+114 §7Main Attack Damage","§7§a-39%§2** §7Meteor Cost","","§7[0/2] Powder Slots","§5Mythic Item","§8§8Many artifacts are found","§8to incite rage in the wearer,","§8and wizards old and new","§8alike conjecture as to the","§8purpose of them. Very few","§8increase the latent abilities","§8of the wearers to ensure","§8havoc, and none to such","§8a degree as these boots
        String ws = getLore.get(4);
        String steeling = getLore.get(6);
        String hpr = getLore.get(7);
        String mainAttack = getLore.get(8);
        String thirdSpellCostPercent = getLore.get(9);

        ws = ws.toLowerCase().replaceAll("[^1234567890%]", "");
        ws = ws.substring(1, ws.lastIndexOf("%"));
        currentWs = Double.parseDouble(ws);

        steeling = steeling.toLowerCase().replaceAll("[^1234567890%]", "");
        steeling = steeling.substring(1, steeling.lastIndexOf("%"));
        currentSteeling = Double.parseDouble(steeling);

        hpr = hpr.toLowerCase().replaceAll("[^1234567890-]", "");
        hpr = hpr.substring(hpr.indexOf("-") + 1, hpr.lastIndexOf("-") + 4);
        currentHpr = Double.parseDouble(hpr);

        mainAttack = mainAttack.toLowerCase().replaceAll("[^1234567890+]", "");
        mainAttack = mainAttack.substring(mainAttack.indexOf("+"), mainAttack.lastIndexOf("7"));
        currentMainAttack = Double.parseDouble(mainAttack);

        thirdSpellCostPercent = thirdSpellCostPercent.toLowerCase().replaceAll("[^1234567890%-]", "");
        thirdSpellCostPercent = thirdSpellCostPercent.substring(thirdSpellCostPercent.indexOf("-") + 1, thirdSpellCostPercent.lastIndexOf("%"));
        currentThirdSpellCost = Double.parseDouble(thirdSpellCostPercent);

        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
            double[] hprList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Slayer").getAsJsonObject().get("healthRegen"), double[].class);
        double[] mainAttackList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Slayer").getAsJsonObject().get("mainAttackDamage"), double[].class);
        double[] steelingList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Slayer").getAsJsonObject().get("steeling"), double[].class);
        double[] walkSpeedList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Slayer").getAsJsonObject().get("walkSpeed"), double[].class);
        double[] thirdSpellCostPercentList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Slayer").getAsJsonObject().get("thirdSpellCost"), double[].class);

        double hprWeight = CalcUtils.negativeStats(hprList[1], hprList[0], currentHpr, hprList[2]);
        double hprWeight2 = CalcUtils.negativeStats(hprList[1], hprList[0], currentHpr, hprList[3]);

        double wsWeight = CalcUtils.positveStats(walkSpeedList[1], walkSpeedList[0], currentWs, walkSpeedList[2]);
        double wsWeight2 = CalcUtils.positveStats(walkSpeedList[1], walkSpeedList[0], currentWs, walkSpeedList[3]);

        double mainAttackWeight = CalcUtils.positveStats(mainAttackList[1], mainAttackList[0], currentMainAttack, mainAttackList[2]);
        double mainAttackWeight2 = CalcUtils.positveStats(mainAttackList[1], mainAttackList[0], currentMainAttack, mainAttackList[3]);

        double stealingWeight = CalcUtils.positveStats(steelingList[1], steelingList[0], currentSteeling, steelingList[2]);
        double stealingWeight2 = CalcUtils.positveStats(steelingList[1], steelingList[0], currentSteeling, steelingList[3]);

        double thirdSpellCostPercentWeight = CalcUtils.positveStats(thirdSpellCostPercentList[1], thirdSpellCostPercentList[0], currentThirdSpellCost, thirdSpellCostPercentList[2]);
        double thirdSpellCostPercentWeight2 = CalcUtils.positveStats(thirdSpellCostPercentList[1], thirdSpellCostPercentList[0], currentThirdSpellCost, thirdSpellCostPercentList[3]);

        var finalWeight1 = (int) Math.round(hprWeight + wsWeight + mainAttackWeight + stealingWeight + thirdSpellCostPercentWeight);
        var finalWeight2 = (int) Math.round(hprWeight2 + wsWeight2 + mainAttackWeight2 + stealingWeight2 + thirdSpellCostPercentWeight2);

        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + " " + CodingUtils.color(finalWeight1) + "§f, " + CodingUtils.color(finalWeight2)));
        return mainHandItem;

    }

}
