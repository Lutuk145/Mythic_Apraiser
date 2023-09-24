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

public class Nirvana {
    private static double currentmanaRegen;
    private static double currentmanaSteal;
    private static double currentreflection;
    private static double currenthealth;
    private static double currentspellDamage;
    private static double currentmeleeDamage;
    public static ItemStack Nirvana(ItemStack mainHandItem,int i) throws IOException {

        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String manaRegen= getLore.get(i+4);
        manaRegen= manaRegen.toLowerCase().replaceAll("[^1234567890/]", "");
        manaRegen = manaRegen.substring(1, manaRegen.lastIndexOf("/"));
        currentmanaRegen = Double.parseDouble(manaRegen);

        double[] manaRegenList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Nirvana").getAsJsonObject().get("manaRegen"), double[].class);
        double manaRegenWeight = CalcUtils.positveStats(manaRegenList[1],manaRegenList[0],currentmanaRegen,manaRegenList[2]);


        String manaSteal= getLore.get(i+5);
        manaSteal= manaSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        manaSteal = manaSteal.substring(1, manaSteal.lastIndexOf("/"));
        currentmanaSteal = Double.parseDouble(manaSteal);

        double[] manaStealList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Nirvana").getAsJsonObject().get("manaSteal"), double[].class);
        double manaStealWeight = CalcUtils.negativeStats(manaStealList[1],manaStealList[0],currentmanaSteal,manaStealList[2]);

        String reflection= getLore.get(i+6);
        reflection= reflection.toLowerCase().replaceAll("[^1234567890%]", "");
        reflection = reflection.substring(1, reflection.lastIndexOf("%"));
        currentreflection = Double.parseDouble(reflection);

        double[] reflectionList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Nirvana").getAsJsonObject().get("reflection"), double[].class);
        double reflectionWeight = CalcUtils.positveStats(reflectionList[1],reflectionList[0],currentreflection,reflectionList[2]);
        String health= getLore.get(i+7);
        if (health.contains("*")){
            health=health.toLowerCase().replaceAll("[^1234567890/*]", "");
            health=health.substring(1,health.indexOf("*")-1);
        }else {
            health=health.toLowerCase().replaceAll("[^1234567890/]", "");
            health=health.substring(1,health.lastIndexOf("7"));
        }
        currenthealth = Double.parseDouble(health);

        double[] healthList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Nirvana").getAsJsonObject().get("health"), double[].class);
        double healthWeight = CalcUtils.negativeStats(healthList[1],healthList[0],currenthealth,healthList[2]);
        String spellDamage= getLore.get(i+8);
        spellDamage= spellDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        spellDamage = spellDamage.substring(1, spellDamage.lastIndexOf("%"));
        currentspellDamage = Double.parseDouble(spellDamage);

        double[] spellDamageList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Nirvana").getAsJsonObject().get("spellDamage"), double[].class);
        double spellDamageWeight = CalcUtils.positveStats(spellDamageList[1],spellDamageList[0],currentspellDamage,spellDamageList[2]);
        String meleeDamage= getLore.get(i+9);
        meleeDamage= meleeDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        meleeDamage = meleeDamage.substring(1, meleeDamage.lastIndexOf("%"));
        currentmeleeDamage = Double.parseDouble(meleeDamage);

        double[] meleeDamageList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Nirvana").getAsJsonObject().get("meleeDamage"), double[].class);
        double meleeDamageWeight = CalcUtils.negativeStats(meleeDamageList[1],meleeDamageList[0],currentmeleeDamage,meleeDamageList[2]);

        int finalWeight = (int) Math.round(manaRegenWeight + manaStealWeight + reflectionWeight + healthWeight + spellDamageWeight + meleeDamageWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
