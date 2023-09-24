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

public class Oblivion {
    private static double currentmanaRegen;
    private static double currentmanaSteal;
    private static double currentexploding;
    private static double currentsoulPointRegen;
    private static double currentspellRaw;
    private static double currentsecondSpellRaw;
    public static ItemStack Oblivion(ItemStack mainHandItem,int i) throws IOException {
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

        double[] manaRegenList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Oblivion").getAsJsonObject().get("manaRegen"), double[].class);
        double manaRegenWeight1 = CalcUtils.negativeStats(manaRegenList[1],manaRegenList[0],currentmanaRegen,manaRegenList[2]);
        double manaRegenWeight2 = CalcUtils.negativeStats(manaRegenList[1],manaRegenList[0],currentmanaRegen,manaRegenList[3]);


        String manaSteal= getLore.get(i+5);
        manaSteal= manaSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        manaSteal = manaSteal.substring(1, manaSteal.lastIndexOf("/"));
        currentmanaSteal = Double.parseDouble(manaSteal);

        double[] manaStealList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Oblivion").getAsJsonObject().get("manaSteal"), double[].class);
        double manaStealWeight1 = CalcUtils.positveStats(manaStealList[1],manaStealList[0],currentmanaSteal,manaStealList[2]);
        double manaStealWeight2 = CalcUtils.positveStats(manaStealList[1],manaStealList[0],currentmanaSteal,manaStealList[3]);

        String exploding= getLore.get(i+6);
        exploding= exploding.toLowerCase().replaceAll("[^1234567890%]", "");
        exploding = exploding.substring(1, exploding.lastIndexOf("%"));
        currentexploding = Double.parseDouble(exploding);

        double[] explodingList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Oblivion").getAsJsonObject().get("exploding"), double[].class);
        double explodingWeight1 = CalcUtils.positveStats(explodingList[1],explodingList[0],currentexploding,explodingList[2]);
        double explodingWeight2 = CalcUtils.positveStats(explodingList[1],explodingList[0],currentexploding,explodingList[3]);
        String soulPointRegen= getLore.get(i+7);
        soulPointRegen= soulPointRegen.toLowerCase().replaceAll("[^1234567890%]", "");
        soulPointRegen = soulPointRegen.substring(1, soulPointRegen.lastIndexOf("%"));
        currentsoulPointRegen = Double.parseDouble(soulPointRegen);

        double[] soulPointRegenList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Oblivion").getAsJsonObject().get("soulPointRegen"), double[].class);
        double soulPointRegenWeight1 = CalcUtils.positveStats(soulPointRegenList[1],soulPointRegenList[0],currentsoulPointRegen,soulPointRegenList[2]);
        double soulPointRegenWeight2 = CalcUtils.positveStats(soulPointRegenList[1],soulPointRegenList[0],currentsoulPointRegen,soulPointRegenList[3]);
        String spellRaw= getLore.get(i+8);
        if (spellRaw.contains("*")){
            spellRaw=spellRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            spellRaw=spellRaw.substring(1,spellRaw.indexOf("*")-1);
        }else {
            spellRaw=spellRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            spellRaw=spellRaw.substring(1,spellRaw.lastIndexOf("7"));
        }
        currentspellRaw = Double.parseDouble(spellRaw);

        double[] spellRawList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Oblivion").getAsJsonObject().get("spellRaw"), double[].class);
        double spellRawWeight1 = CalcUtils.positveStats(spellRawList[1],spellRawList[0],currentspellRaw,spellRawList[2]);
        double spellRawWeight2 = CalcUtils.positveStats(spellRawList[1],spellRawList[0],currentspellRaw,spellRawList[3]);
        String secondSpellRaw= getLore.get(i+9);
        if (secondSpellRaw.contains("*")){
            secondSpellRaw=secondSpellRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            secondSpellRaw=secondSpellRaw.substring(1,secondSpellRaw.indexOf("*")-1);
        }else {
            secondSpellRaw=secondSpellRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            secondSpellRaw=secondSpellRaw.substring(1,secondSpellRaw.lastIndexOf("7"));
        }
        currentsecondSpellRaw = Double.parseDouble(secondSpellRaw);

        double[] secondSpellRawList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Oblivion").getAsJsonObject().get("secondSpellRaw"), double[].class);
        double secondSpellRawWeight1 = CalcUtils.positveStats(secondSpellRawList[1],secondSpellRawList[0],currentsecondSpellRaw,secondSpellRawList[2]);
        double secondSpellRawWeight2 = CalcUtils.positveStats(secondSpellRawList[1],secondSpellRawList[0],currentsecondSpellRaw,secondSpellRawList[3]);

        int finalWeight1 = (int) Math.round(manaRegenWeight1 + manaStealWeight1 + explodingWeight1 + soulPointRegenWeight1 + spellRawWeight1 + secondSpellRawWeight1);
        int finalWeight2 = (int) Math.round(manaRegenWeight2 + manaStealWeight2 + explodingWeight2 + soulPointRegenWeight2 + spellRawWeight2 + secondSpellRawWeight2);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight1)+"§f, "+ CodingUtils.color(finalWeight2)));
        return mainHandItem;
    }

}
