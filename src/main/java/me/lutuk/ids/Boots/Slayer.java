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
    private static double currentwalkSpeed;
    private static double currentstealing;
    private static double currenthealthRegen;
    private static double currentmainAttackDamage;
    private static double currentthirdSpellCost;

    public static ItemStack Slayer(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String walkSpeed= getLore.get(i+4);
        walkSpeed= walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Slayer").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight1 = CalcUtils.positveStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[2]);
        double walkSpeedWeight2 = CalcUtils.positveStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[3]);
        String stealing= getLore.get(i+6);
        stealing= stealing.toLowerCase().replaceAll("[^1234567890%]", "");
        stealing = stealing.substring(1, stealing.lastIndexOf("%"));
        currentstealing = Double.parseDouble(stealing);

        double[] stealingList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Slayer").getAsJsonObject().get("stealing"), double[].class);
        double stealingWeight1 = CalcUtils.positveStats(stealingList[1],stealingList[0],currentstealing,stealingList[2]);
        double stealingWeight2 = CalcUtils.positveStats(stealingList[1],stealingList[0],currentstealing,stealingList[3]);
        String healthRegen= getLore.get(i+7);
        if (healthRegen.contains("*")){
            healthRegen=healthRegen.toLowerCase().replaceAll("[^1234567890/*]", "");
            healthRegen=healthRegen.substring(1,healthRegen.indexOf("*")-1);
        }else {
            healthRegen=healthRegen.toLowerCase().replaceAll("[^1234567890/]", "");
            healthRegen=healthRegen.substring(1,healthRegen.lastIndexOf("7"));
        }
        currenthealthRegen = Double.parseDouble(healthRegen);

        double[] healthRegenList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Slayer").getAsJsonObject().get("healthRegen"), double[].class);
        double healthRegenWeight1 = CalcUtils.negativeStats(healthRegenList[1],healthRegenList[0],currenthealthRegen,healthRegenList[2]);
        double healthRegenWeight2 = CalcUtils.negativeStats(healthRegenList[1],healthRegenList[0],currenthealthRegen,healthRegenList[3]);
        String mainAttackDamage= getLore.get(i+8);
        if (mainAttackDamage.contains("*")){
            mainAttackDamage=mainAttackDamage.toLowerCase().replaceAll("[^1234567890/*]", "");
            mainAttackDamage=mainAttackDamage.substring(1,mainAttackDamage.indexOf("*")-1);
        }else {
            mainAttackDamage=mainAttackDamage.toLowerCase().replaceAll("[^1234567890/]", "");
            mainAttackDamage=mainAttackDamage.substring(1,mainAttackDamage.lastIndexOf("7"));
        }
        currentmainAttackDamage = Double.parseDouble(mainAttackDamage);

        double[] mainAttackDamageList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Slayer").getAsJsonObject().get("mainAttackDamage"), double[].class);
        double mainAttackDamageWeight1 = CalcUtils.positveStats(mainAttackDamageList[1],mainAttackDamageList[0],currentmainAttackDamage,mainAttackDamageList[2]);
        double mainAttackDamageWeight2 = CalcUtils.positveStats(mainAttackDamageList[1],mainAttackDamageList[0],currentmainAttackDamage,mainAttackDamageList[3]);
        String thirdSpellCost= getLore.get(i+9);
        thirdSpellCost= thirdSpellCost.toLowerCase().replaceAll("[^1234567890%]", "");
        thirdSpellCost = thirdSpellCost.substring(1, thirdSpellCost.lastIndexOf("%"));
        currentthirdSpellCost = Double.parseDouble(thirdSpellCost);

        double[] thirdSpellCostList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Slayer").getAsJsonObject().get("thirdSpellCost"), double[].class);
        double thirdSpellCostWeight1 = CalcUtils.positveStats(thirdSpellCostList[1],thirdSpellCostList[0],currentthirdSpellCost,thirdSpellCostList[2]);
        double thirdSpellCostWeight2 = CalcUtils.positveStats(thirdSpellCostList[1],thirdSpellCostList[0],currentthirdSpellCost,thirdSpellCostList[3]);

        int finalWeight1 = (int) (walkSpeedWeight1 + stealingWeight1 + healthRegenWeight1 + mainAttackDamageWeight1 + thirdSpellCostWeight1);
        int finalWeight2 = (int) (walkSpeedWeight2 + stealingWeight2 + healthRegenWeight2 + mainAttackDamageWeight2 + thirdSpellCostWeight2);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight1) + " §f, " + CodingUtils.color(finalWeight2) + " §f"));
        return mainHandItem;
    }

}
