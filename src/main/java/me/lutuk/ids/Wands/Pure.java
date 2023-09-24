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

public class Pure {
    private static double currentmanaSteal;
    private static double currentxpBonus;
    private static double currentreflection;
    private static double currentspellDamage;
    private static double currentmeleeDamage;
    public static ItemStack Pure(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String manaSteal= getLore.get(i+2);
        manaSteal= manaSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        manaSteal = manaSteal.substring(1, manaSteal.lastIndexOf("/"));
        currentmanaSteal = Double.parseDouble(manaSteal);

        double[] manaStealList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Pure").getAsJsonObject().get("manaSteal"), double[].class);
        double manaStealWeight = CalcUtils.positveStats(manaStealList[1],manaStealList[0],currentmanaSteal,manaStealList[2]);

        String xpBonus= getLore.get(i+3);
        xpBonus= xpBonus.toLowerCase().replaceAll("[^1234567890%]", "");
        xpBonus = xpBonus.substring(1, xpBonus.lastIndexOf("%"));
        currentxpBonus = Double.parseDouble(xpBonus);

        double[] xpBonusList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Pure").getAsJsonObject().get("xpBonus"), double[].class);
        double xpBonusWeight = CalcUtils.positveStats(xpBonusList[1],xpBonusList[0],currentxpBonus,xpBonusList[2]);
        String reflection= getLore.get(i+4);
        reflection= reflection.toLowerCase().replaceAll("[^1234567890%]", "");
        reflection = reflection.substring(1, reflection.lastIndexOf("%"));
        currentreflection = Double.parseDouble(reflection);

        double[] reflectionList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Pure").getAsJsonObject().get("reflection"), double[].class);
        double reflectionWeight = CalcUtils.positveStats(reflectionList[1],reflectionList[0],currentreflection,reflectionList[2]);
        String spellDamage= getLore.get(i+5);
        spellDamage= spellDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        spellDamage = spellDamage.substring(1, spellDamage.lastIndexOf("%"));
        currentspellDamage = Double.parseDouble(spellDamage);

        double[] spellDamageList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Pure").getAsJsonObject().get("spellDamage"), double[].class);
        double spellDamageWeight = CalcUtils.positveStats(spellDamageList[1],spellDamageList[0],currentspellDamage,spellDamageList[2]);
        String meleeDamage= getLore.get(i+6);
        meleeDamage= meleeDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        meleeDamage = meleeDamage.substring(1, meleeDamage.lastIndexOf("%"));
        currentmeleeDamage = Double.parseDouble(meleeDamage);

        double[] meleeDamageList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Pure").getAsJsonObject().get("meleeDamage"), double[].class);
        double meleeDamageWeight = CalcUtils.negativeStats(meleeDamageList[1],meleeDamageList[0],currentmeleeDamage,meleeDamageList[2]);

        int finalWeight = (int) Math.round(manaStealWeight + xpBonusWeight + reflectionWeight + spellDamageWeight + meleeDamageWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
