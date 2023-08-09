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

public class Gaia {
    private static double currentthorns;
    private static double currentpoison;
    private static double currentspellRaw;
    private static double currentmeleePercent;
    private static double currentmeleeRaw;
    private static double currentfourthSpellRaw;
    public static ItemStack Gaia(ItemStack mainHandItem) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String thorns= getLore.get(4);
        thorns= thorns.toLowerCase().replaceAll("[^1234567890%]", "");
        thorns = thorns.substring(1, thorns.lastIndexOf("%"));
        currentthorns = Double.parseDouble(thorns);

        double[] thornsList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Gaia").getAsJsonObject().get("thorns"), double[].class);
        double thornsWeight1 = CalcUtils.positveStats(thornsList[1],thornsList[0],currentthorns,thornsList[2]);
        double thornsWeight2 = CalcUtils.positveStats(thornsList[1],thornsList[0],currentthorns,thornsList[3]);

        String poison= getLore.get(5);
        poison= poison.toLowerCase().replaceAll("[^1234567890/]", "");
        poison = poison.substring(1, poison.lastIndexOf("/"));
        currentpoison = Double.parseDouble(poison);

        double[] poisonList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Gaia").getAsJsonObject().get("poison"), double[].class);
        double poisonWeight1 = CalcUtils.positveStats(poisonList[1],poisonList[0],currentpoison,poisonList[2]);
        double poisonWeight2 = CalcUtils.positveStats(poisonList[1],poisonList[0],currentpoison,poisonList[3]);

        String spellRaw= getLore.get(6);
        if (spellRaw.contains("*")){
            spellRaw=spellRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            spellRaw=spellRaw.substring(1,spellRaw.indexOf("*")-1);
        }else {
            spellRaw=spellRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            spellRaw=spellRaw.substring(1,spellRaw.lastIndexOf("7"));
        }
        currentspellRaw = Double.parseDouble(spellRaw);

        double[] spellRawList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Gaia").getAsJsonObject().get("spellRaw"), double[].class);
        double spellRawWeight1 = CalcUtils.negativeStats(spellRawList[1],spellRawList[0],currentspellRaw,spellRawList[2]);
        double spellRawWeight2 = CalcUtils.negativeStats(spellRawList[1],spellRawList[0],currentspellRaw,spellRawList[3]);
        String meleePercent= getLore.get(7);
        meleePercent= meleePercent.toLowerCase().replaceAll("[^1234567890%]", "");
        meleePercent = meleePercent.substring(1, meleePercent.lastIndexOf("%"));
        currentmeleePercent = Double.parseDouble(meleePercent);

        double[] meleePercentList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Gaia").getAsJsonObject().get("meleePercent"), double[].class);
        double meleePercentWeight1 = CalcUtils.positveStats(meleePercentList[1],meleePercentList[0],currentmeleePercent,meleePercentList[2]);
        double meleePercentWeight2 = CalcUtils.positveStats(meleePercentList[1],meleePercentList[0],currentmeleePercent,meleePercentList[3]);
        String meleeRaw= getLore.get(8);
        if (meleeRaw.contains("*")){
            meleeRaw=meleeRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            meleeRaw=meleeRaw.substring(1,meleeRaw.indexOf("*")-1);
        }else {
            meleeRaw=meleeRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            meleeRaw=meleeRaw.substring(1,meleeRaw.lastIndexOf("7"));
        }
        currentmeleeRaw = Double.parseDouble(meleeRaw);

        double[] meleeRawList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Gaia").getAsJsonObject().get("meleeRaw"), double[].class);
        double meleeRawWeight1 = CalcUtils.positveStats(meleeRawList[1],meleeRawList[0],currentmeleeRaw,meleeRawList[2]);
        double meleeRawWeight2 = CalcUtils.positveStats(meleeRawList[1],meleeRawList[0],currentmeleeRaw,meleeRawList[3]);
        String fourthSpellRaw= getLore.get(9);
        if (fourthSpellRaw.contains("*")){
            fourthSpellRaw=fourthSpellRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            fourthSpellRaw=fourthSpellRaw.substring(1,fourthSpellRaw.indexOf("*")-1);
        }else {
            fourthSpellRaw=fourthSpellRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            fourthSpellRaw=fourthSpellRaw.substring(1,fourthSpellRaw.lastIndexOf("7"));
        }
        currentfourthSpellRaw = Double.parseDouble(fourthSpellRaw);

        double[] fourthSpellRawList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Gaia").getAsJsonObject().get("fourthSpellRaw"), double[].class);
        double fourthSpellRawWeight1 = CalcUtils.positveStats(fourthSpellRawList[1],fourthSpellRawList[0],currentfourthSpellRaw,fourthSpellRawList[2]);
        double fourthSpellRawWeight2 = CalcUtils.positveStats(fourthSpellRawList[1],fourthSpellRawList[0],currentfourthSpellRaw,fourthSpellRawList[3]);

        int finalWeight1 = (int) Math.round(thornsWeight1+poisonWeight1+spellRawWeight1+meleeRawWeight1+meleePercentWeight1+fourthSpellRawWeight1);
        int finalWeight2 = (int) Math.round(thornsWeight2+poisonWeight2+spellRawWeight2+meleeRawWeight2+meleePercentWeight2+fourthSpellRawWeight2);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight1) + " §f, " + CodingUtils.color(finalWeight2) + " §f"));
        return mainHandItem;
    }
}
