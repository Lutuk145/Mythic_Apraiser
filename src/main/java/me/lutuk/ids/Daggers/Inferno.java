package me.lutuk.ids.Daggers;

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

public class Inferno {
    private static double currenthealthRegenPercent;
    private static double currentwalkSpeed;
    private static double currenthealth;
    private static double currentfireDamage;
    private static double currentmeleePercent;
    private static double currentmeleeRaw;
    private static double currentwaterDefense;
    public static ItemStack Inferno(ItemStack mainHandItem,int i) throws IOException {

        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String healthRegenPercent= getLore.get(i+4);
        healthRegenPercent= healthRegenPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        healthRegenPercent = healthRegenPercent.substring(1, healthRegenPercent.lastIndexOf("%"));
        currenthealthRegenPercent = Double.parseDouble(healthRegenPercent);

        double[] healthRegenPercentList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Inferno").getAsJsonObject().get("healthRegenPercent"), double[].class);
        double healthRegenPercentWeight = CalcUtils.negativeStats(healthRegenPercentList[1],healthRegenPercentList[0],currenthealthRegenPercent,healthRegenPercentList[2]);
        String walkSpeed= getLore.get(i+6);
        walkSpeed= walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Inferno").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight = CalcUtils.positveStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[2]);
        String health= getLore.get(i+7);
        if (health.contains("*")){
            health=health.toLowerCase().replaceAll("[^1234567890/*]", "");
            health=health.substring(1,health.indexOf("*")-1);
        }else {
            health=health.toLowerCase().replaceAll("[^1234567890/]", "");
            health=health.substring(1,health.lastIndexOf("7"));
        }
        currenthealth = Double.parseDouble(health);

        double[] healthList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Inferno").getAsJsonObject().get("health"), double[].class);
        double healthWeight = CalcUtils.positveStats(healthList[1],healthList[0],currenthealth,healthList[2]);
        String fireDamage= getLore.get(i+8);
        fireDamage= fireDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        fireDamage = fireDamage.substring(1, fireDamage.lastIndexOf("%"));
        currentfireDamage = Double.parseDouble(fireDamage);

        double[] fireDamageList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Inferno").getAsJsonObject().get("fireDamage"), double[].class);
        double fireDamageWeight = CalcUtils.positveStats(fireDamageList[1],fireDamageList[0],currentfireDamage,fireDamageList[2]);
        String meleePercent= getLore.get(i+9);
        meleePercent= meleePercent.toLowerCase().replaceAll("[^1234567890%]", "");
        meleePercent = meleePercent.substring(1, meleePercent.lastIndexOf("%"));
        currentmeleePercent = Double.parseDouble(meleePercent);

        double[] meleePercentList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Inferno").getAsJsonObject().get("meleePercent"), double[].class);
        double meleePercentWeight = CalcUtils.positveStats(meleePercentList[1],meleePercentList[0],currentmeleePercent,meleePercentList[2]);
        String meleeRaw= getLore.get(i+10);
        if (meleeRaw.contains("*")){
            meleeRaw=meleeRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            meleeRaw=meleeRaw.substring(1,meleeRaw.indexOf("*")-1);
        }else {
            meleeRaw=meleeRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            meleeRaw=meleeRaw.substring(1,meleeRaw.lastIndexOf("7"));
        }
        currentmeleeRaw = Double.parseDouble(meleeRaw);

        double[] meleeRawList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Inferno").getAsJsonObject().get("meleeRaw"), double[].class);
        double meleeRawWeight = CalcUtils.positveStats(meleeRawList[1],meleeRawList[0],currentmeleeRaw,meleeRawList[2]);
        String waterDefense= getLore.get(i+11);
        waterDefense= waterDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        waterDefense = waterDefense.substring(1, waterDefense.lastIndexOf("%"));
        currentwaterDefense = Double.parseDouble(waterDefense);

        double[] waterDefenseList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Inferno").getAsJsonObject().get("waterDefense"), double[].class);
        double waterDefenseWeight = CalcUtils.negativeStats(waterDefenseList[1],waterDefenseList[0],currentwaterDefense,waterDefenseList[2]);

        int finalWeight = (int) (healthRegenPercentWeight + walkSpeedWeight + healthWeight + fireDamageWeight + meleePercentWeight + meleeRawWeight + waterDefenseWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
