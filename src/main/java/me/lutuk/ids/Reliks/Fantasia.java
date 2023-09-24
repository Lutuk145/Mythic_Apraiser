package me.lutuk.ids.Reliks;

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

public class Fantasia {
    private static double currentmanaRegen;
    private static double currentmanaSteal;
    private static double currentspellDamage;
    private static double currentfirstSpellPercent;
    private static double currentseconSpellPercent;
    private static double currentthirdSpellPercent;
    private static double currentfourthSpellPercent;

    public static ItemStack Fantasia(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String manaRegen = getLore.get(i+4);
        manaRegen = manaRegen.toLowerCase().replaceAll("[^1234567890/]", "");
        manaRegen = manaRegen.substring(1, manaRegen.lastIndexOf("/"));
        currentmanaRegen = Double.parseDouble(manaRegen);

        double[] manaRegenList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Fantasia").getAsJsonObject().get("manaRegen"), double[].class);
        double manaRegenWeight = CalcUtils.negativeStats(manaRegenList[1], manaRegenList[0], currentmanaRegen, manaRegenList[2]);


        String manaSteal = getLore.get(i+5);
        manaSteal = manaSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        manaSteal = manaSteal.substring(1, manaSteal.lastIndexOf("/"));
        currentmanaSteal = Double.parseDouble(manaSteal);

        double[] manaStealList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Fantasia").getAsJsonObject().get("manaSteal"), double[].class);
        double manaStealWeight = CalcUtils.negativeStats(manaStealList[1], manaStealList[0], currentmanaSteal, manaStealList[2]);

        String spellDamage = getLore.get(i+6);
        spellDamage = spellDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        spellDamage = spellDamage.substring(1, spellDamage.lastIndexOf("%"));
        currentspellDamage = Double.parseDouble(spellDamage);

        double[] spellDamageList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Fantasia").getAsJsonObject().get("spellDamage"), double[].class);
        double spellDamageWeight = CalcUtils.positveStats(spellDamageList[1], spellDamageList[0], currentspellDamage, spellDamageList[2]);
        String firstSpellPercent = getLore.get(i+7);
        firstSpellPercent = firstSpellPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        firstSpellPercent = firstSpellPercent.substring(1, firstSpellPercent.lastIndexOf("%"));
        currentfirstSpellPercent = Double.parseDouble(firstSpellPercent);

        double[] firstSpellPercentList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Fantasia").getAsJsonObject().get("firstSpellPercent"), double[].class);
        double firstSpellPercentWeight = CalcUtils.positveStats(firstSpellPercentList[1], firstSpellPercentList[0], currentfirstSpellPercent, firstSpellPercentList[2]);
        String seconSpellPercent = getLore.get(i+8);
        seconSpellPercent = seconSpellPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        seconSpellPercent = seconSpellPercent.substring(1, seconSpellPercent.lastIndexOf("%"));
        currentseconSpellPercent = Double.parseDouble(seconSpellPercent);

        double[] seconSpellPercentList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Fantasia").getAsJsonObject().get("secondSpellPercent"), double[].class);
        double seconSpellPercentWeight = CalcUtils.positveStats(seconSpellPercentList[1], seconSpellPercentList[0], currentseconSpellPercent, seconSpellPercentList[2]);
        String thirdSpellPercent = getLore.get(i+9);
        thirdSpellPercent = thirdSpellPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        thirdSpellPercent = thirdSpellPercent.substring(1, thirdSpellPercent.lastIndexOf("%"));
        currentthirdSpellPercent = Double.parseDouble(thirdSpellPercent);

        double[] thirdSpellPercentList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Fantasia").getAsJsonObject().get("thirdSpellPercent"), double[].class);
        double thirdSpellPercentWeight = CalcUtils.positveStats(thirdSpellPercentList[1], thirdSpellPercentList[0], currentthirdSpellPercent, thirdSpellPercentList[2]);
        String fourthSpellPercent = getLore.get(i+10);
        fourthSpellPercent = fourthSpellPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        fourthSpellPercent = fourthSpellPercent.substring(1, fourthSpellPercent.lastIndexOf("%"));
        currentfourthSpellPercent = Double.parseDouble(fourthSpellPercent);

        double[] fourthSpellPercentList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Fantasia").getAsJsonObject().get("fourthSpellPercent"), double[].class);
        double fourthSpellPercentWeight = CalcUtils.positveStats(fourthSpellPercentList[1], fourthSpellPercentList[0], currentfourthSpellPercent, fourthSpellPercentList[2]);

        int finalWeight = (int) Math.round(manaRegenWeight + manaStealWeight + spellDamageWeight + firstSpellPercentWeight + seconSpellPercentWeight + thirdSpellPercentWeight + fourthSpellPercentWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
