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

public class Epoch {
    private static double currentlifeSteal;
    private static double currentwalkSpeed;
    private static double currentspellDamage;
    private static double currentrawMelee;
    private static double currentsprint;
    public static ItemStack Epoch(ItemStack mainHandItem) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String lifeSteal= getLore.get(2);
        lifeSteal= lifeSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        lifeSteal = lifeSteal.substring(1, lifeSteal.lastIndexOf("/"));
        currentlifeSteal = Double.parseDouble(lifeSteal);

        double[] lifeStealList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Epoch").getAsJsonObject().get("lifeSteal"), double[].class);
        double lifeStealWeight = CalcUtils.positveStats(lifeStealList[1],lifeStealList[0],currentlifeSteal,lifeStealList[2]);

        String walkSpeed= getLore.get(4);
        walkSpeed= walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Epoch").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight = CalcUtils.negativeStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[2]);
        String spellDamage= getLore.get(5);
        spellDamage= spellDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        spellDamage = spellDamage.substring(1, spellDamage.lastIndexOf("%"));
        currentspellDamage = Double.parseDouble(spellDamage);

        double[] spellDamageList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Epoch").getAsJsonObject().get("spellDamage"), double[].class);
        double spellDamageWeight = CalcUtils.positveStats(spellDamageList[1],spellDamageList[0],currentspellDamage,spellDamageList[2]);
        String rawMelee= getLore.get(6);
        if (rawMelee.contains("*")){
            rawMelee=rawMelee.toLowerCase().replaceAll("[^1234567890/*]", "");
            rawMelee=rawMelee.substring(1,rawMelee.indexOf("*")-1);
        }else {
            rawMelee=rawMelee.toLowerCase().replaceAll("[^1234567890/]", "");
            rawMelee=rawMelee.substring(1,rawMelee.lastIndexOf("7"));
        }
        currentrawMelee = Double.parseDouble(rawMelee);

        double[] rawMeleeList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Epoch").getAsJsonObject().get("rawMelee"), double[].class);
        double rawMeleeWeight = CalcUtils.positveStats(rawMeleeList[1],rawMeleeList[0],currentrawMelee,rawMeleeList[2]);
        String sprint= getLore.get(7);
        sprint= sprint.toLowerCase().replaceAll("[^1234567890%]", "");
        sprint = sprint.substring(1, sprint.lastIndexOf("%"));
        currentsprint = Double.parseDouble(sprint);

        double[] sprintList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Epoch").getAsJsonObject().get("sprintBonus"), double[].class);
        double sprintWeight = CalcUtils.positveStats(sprintList[1],sprintList[0],currentsprint,sprintList[2]);

        int finalWeight = (int) (lifeStealWeight + walkSpeedWeight + spellDamageWeight + rawMeleeWeight + sprintWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
