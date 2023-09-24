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

public class Stardew {
    private static double currentmanaRegen;
    private static double currentmanaSteal;
    private static double currentreflection;
    private static double currentwaterDamage;
    private static double currentthunderDamage;
    private static double currentspellRaw;
    public static ItemStack Stardew(ItemStack mainHandItem,int i)throws IOException{
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String manaRegen= getLore.get(i+2);
        manaRegen= manaRegen.toLowerCase().replaceAll("[^1234567890/]", "");
        manaRegen = manaRegen.substring(1, manaRegen.lastIndexOf("/"));
        currentmanaRegen = Double.parseDouble(manaRegen);

        double[] manaRegenList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Stardew").getAsJsonObject().get("manaRegen"), double[].class);
        double manaRegenWeight = CalcUtils.negativeStats(manaRegenList[1],manaRegenList[0],currentmanaRegen,manaRegenList[2]);


        String manaSteal= getLore.get(i+3);
        manaSteal= manaSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        manaSteal = manaSteal.substring(1, manaSteal.lastIndexOf("/"));
        currentmanaSteal = Double.parseDouble(manaSteal);

        double[] manaStealList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Stardew").getAsJsonObject().get("manaSteal"), double[].class);
        double manaStealWeight = CalcUtils.positveStats(manaStealList[1],manaStealList[0],currentmanaSteal,manaStealList[2]);

        String reflection= getLore.get(i+4);
        reflection= reflection.toLowerCase().replaceAll("[^1234567890%]", "");
        reflection = reflection.substring(1, reflection.lastIndexOf("%"));
        currentreflection = Double.parseDouble(reflection);

        double[] reflectionList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Stardew").getAsJsonObject().get("reflection"), double[].class);
        double reflectionWeight = CalcUtils.positveStats(reflectionList[1],reflectionList[0],currentreflection,reflectionList[2]);
        String waterDamage= getLore.get(i+5);
        waterDamage= waterDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        waterDamage = waterDamage.substring(1, waterDamage.lastIndexOf("%"));
        currentwaterDamage = Double.parseDouble(waterDamage);

        double[] waterDamageList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Stardew").getAsJsonObject().get("waterDamage"), double[].class);
        double waterDamageWeight = CalcUtils.positveStats(waterDamageList[1],waterDamageList[0],currentwaterDamage,waterDamageList[2]);
        String thunderDamage= getLore.get(i+6);
        thunderDamage= thunderDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        thunderDamage = thunderDamage.substring(1, thunderDamage.lastIndexOf("%"));
        currentthunderDamage = Double.parseDouble(thunderDamage);

        double[] thunderDamageList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Stardew").getAsJsonObject().get("thunderDamage"), double[].class);
        double thunderDamageWeight = CalcUtils.positveStats(thunderDamageList[1],thunderDamageList[0],currentthunderDamage,thunderDamageList[2]);
        String spellRaw= getLore.get(i+7);
        if (spellRaw.contains("*")){
            spellRaw=spellRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            spellRaw=spellRaw.substring(1,spellRaw.indexOf("*")-1);
        }else {
            spellRaw=spellRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            spellRaw=spellRaw.substring(1,spellRaw.lastIndexOf("7"));
        }
        currentspellRaw = Double.parseDouble(spellRaw);

        double[] spellRawList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Stardew").getAsJsonObject().get("spellRaw"), double[].class);
        double spellRawWeight = CalcUtils.positveStats(spellRawList[1],spellRawList[0],currentspellRaw,spellRawList[2]);

        int finalWeight = (int) (manaRegenWeight + manaStealWeight + reflectionWeight + waterDamageWeight + thunderDamageWeight + spellRawWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString()+ CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
