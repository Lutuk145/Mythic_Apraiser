package me.lutuk.ids.Bows;

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

public class Stratiformis {
    private static double currentreflection;
    private static double currentwalkSpeed;
    private static double currenthealth;
    private static double currentspellDmg;
    private static double currentmeleeAttack;

    public static ItemStack Stratiformis(ItemStack mainHandItem) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String reflection = getLore.get(4);
        reflection = reflection.toLowerCase().replaceAll("[^1234567890%]", "");
        reflection = reflection.substring(1, reflection.lastIndexOf("%"));
        currentreflection = Double.parseDouble(reflection);

        double[] reflectionList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Stratiformis").getAsJsonObject().get("reflection"), double[].class);
        double reflectionWeight = CalcUtils.positveStats(reflectionList[1], reflectionList[0], currentreflection, reflectionList[2]);
        String walkSpeed = getLore.get(5);
        walkSpeed = walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Stratiformis").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight = CalcUtils.positveStats(walkSpeedList[1], walkSpeedList[0], currentwalkSpeed, walkSpeedList[2]);
        String health = getLore.get(6);
        if (health.contains("*")) {
            health = health.toLowerCase().replaceAll("[^1234567890/*]", "");
            health = health.substring(1, health.indexOf("*") - 1);
        } else {
            health = health.toLowerCase().replaceAll("[^1234567890/]", "");
            health = health.substring(1, health.lastIndexOf("7"));
        }
        currenthealth = Double.parseDouble(health);

        double[] healthList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Stratiformis").getAsJsonObject().get("health"), double[].class);
        double healthWeight = CalcUtils.negativeStats(healthList[1], healthList[0], currenthealth, healthList[2]);
        String spellDmg = getLore.get(7);
        spellDmg = spellDmg.toLowerCase().replaceAll("[^1234567890%]", "");
        spellDmg = spellDmg.substring(1, spellDmg.lastIndexOf("%"));
        currentspellDmg = Double.parseDouble(spellDmg);

        double[] spellDmgList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Stratiformis").getAsJsonObject().get("spellDmg"), double[].class);
        double spellDmgWeight = CalcUtils.positveStats(spellDmgList[1], spellDmgList[0], currentspellDmg, spellDmgList[2]);
        String meleeAttack = getLore.get(8);
        meleeAttack = meleeAttack.toLowerCase().replaceAll("[^1234567890%]", "");
        meleeAttack = meleeAttack.substring(1, meleeAttack.lastIndexOf("%"));
        currentmeleeAttack = Double.parseDouble(meleeAttack);

        double[] meleeAttackList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Stratiformis").getAsJsonObject().get("meleeAttack"), double[].class);
        double meleeAttackWeight = CalcUtils.positveStats(meleeAttackList[1], meleeAttackList[0], currentmeleeAttack, meleeAttackList[2]);

        int finalWeight1 = (int) (reflectionWeight + walkSpeedWeight + healthWeight + spellDmgWeight + meleeAttackWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight1)));
        return mainHandItem;
    }
}
