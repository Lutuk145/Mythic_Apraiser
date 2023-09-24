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

public class Hadal {
    private static double currentmanaRegen;
    private static double currentspellDamage;
    private static double currentthirdSpellPercent;
    private static double currentfourthSpellPercent;
    public static ItemStack Hadal(ItemStack mainHandItem,int i) throws IOException {
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

        double[] manaRegenList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Hadal").getAsJsonObject().get("manaRegen"), double[].class);
        double manaRegenWeight = CalcUtils.positveStats(manaRegenList[1],manaRegenList[0],currentmanaRegen,manaRegenList[2]);

        String spellDamage= getLore.get(i+3);
        spellDamage= spellDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        spellDamage = spellDamage.substring(1, spellDamage.lastIndexOf("%"));
        currentspellDamage = Double.parseDouble(spellDamage);

        double[] spellDamageList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Hadal").getAsJsonObject().get("spellDamage"), double[].class);
        double spellDamageWeight = CalcUtils.positveStats(spellDamageList[1],spellDamageList[0],currentspellDamage,spellDamageList[2]);
        String thirdSpellPercent= getLore.get(i+4);
        thirdSpellPercent= thirdSpellPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        thirdSpellPercent = thirdSpellPercent.substring(1, thirdSpellPercent.lastIndexOf("%"));
        currentthirdSpellPercent = Double.parseDouble(thirdSpellPercent);

        double[] thirdSpellPercentList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Hadal").getAsJsonObject().get("thirdSpellPercent"), double[].class);
        double thirdSpellPercentWeight = CalcUtils.negativeStats(thirdSpellPercentList[1],thirdSpellPercentList[0],currentthirdSpellPercent,thirdSpellPercentList[2]);
        String fourthSpellPercent= getLore.get(i+5);
        fourthSpellPercent= fourthSpellPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        fourthSpellPercent = fourthSpellPercent.substring(1, fourthSpellPercent.lastIndexOf("%"));
        currentfourthSpellPercent = Double.parseDouble(fourthSpellPercent);

        double[] fourthSpellPercentList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Hadal").getAsJsonObject().get("fourthSpellPercent"), double[].class);
        double fourthSpellPercentWeight = CalcUtils.negativeStats(fourthSpellPercentList[1],fourthSpellPercentList[0],currentfourthSpellPercent,fourthSpellPercentList[2]);

        int finalWeight = (int) Math.round(manaRegenWeight + spellDamageWeight + thirdSpellPercentWeight + fourthSpellPercentWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
