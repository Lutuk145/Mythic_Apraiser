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

public class Grimtrap {
    private static double currentlifeSteal;
    private static double currentmanaSteal;
    private static double currentthorns;
    private static double currentpoison;
    private static double currentfourthSpellRaw;


    public static ItemStack Grimtrap(ItemStack mainHandItem) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String lifeSteal= getLore.get(4);
        lifeSteal= lifeSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        lifeSteal = lifeSteal.substring(1, lifeSteal.lastIndexOf("/"));
        currentlifeSteal = Double.parseDouble(lifeSteal);

        double[] lifeStealList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Grimtrap").getAsJsonObject().get("lifeSteal"), double[].class);
        double lifeStealWeight1 = CalcUtils.positveStats(lifeStealList[1],lifeStealList[0],currentlifeSteal,lifeStealList[2]);
        double lifeStealWeight2 = CalcUtils.positveStats(lifeStealList[1],lifeStealList[0],currentlifeSteal,lifeStealList[3]);


        String manaSteal= getLore.get(5);
        manaSteal= manaSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        manaSteal = manaSteal.substring(1, manaSteal.lastIndexOf("/"));
        currentmanaSteal = Double.parseDouble(manaSteal);

        double[] manaStealList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Grimtrap").getAsJsonObject().get("manaSteal"), double[].class);
        double manaStealWeight1 = CalcUtils.negativeStats(manaStealList[1],manaStealList[0],currentmanaSteal,manaStealList[2]);
        double manaStealWeight2 = CalcUtils.negativeStats(manaStealList[1],manaStealList[0],currentmanaSteal,manaStealList[3]);

        String thorns= getLore.get(6);
        thorns= thorns.toLowerCase().replaceAll("[^1234567890%]", "");
        thorns = thorns.substring(1, thorns.lastIndexOf("%"));
        currentthorns = Double.parseDouble(thorns);

        double[] thornsList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Grimtrap").getAsJsonObject().get("thorns"), double[].class);
        double thornsWeight1 = CalcUtils.positveStats(thornsList[1],thornsList[0],currentthorns,thornsList[2]);
        double thornsWeight2 = CalcUtils.positveStats(thornsList[1],thornsList[0],currentthorns,thornsList[3]);

        String poison= getLore.get(7);
        poison= poison.toLowerCase().replaceAll("[^1234567890/]", "");
        poison = poison.substring(1, poison.lastIndexOf("/"));
        currentpoison = Double.parseDouble(poison);

        double[] poisonList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Grimtrap").getAsJsonObject().get("poison"), double[].class);
        double poisonWeight1 = CalcUtils.positveStats(poisonList[1],poisonList[0],currentpoison,poisonList[2]);
        double poisonWeight2 = CalcUtils.positveStats(poisonList[1],poisonList[0],currentpoison,poisonList[3]);

        String fourthSpellRaw= getLore.get(9);
        if (fourthSpellRaw.contains("*")){
            fourthSpellRaw=fourthSpellRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            fourthSpellRaw=fourthSpellRaw.substring(1,fourthSpellRaw.indexOf("*")-1);
        }else {
            fourthSpellRaw=fourthSpellRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            fourthSpellRaw=fourthSpellRaw.substring(1,fourthSpellRaw.lastIndexOf("7"));
        }
        currentfourthSpellRaw = Double.parseDouble(fourthSpellRaw);

        double[] fourthSpellRawList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Grimtrap").getAsJsonObject().get("fourthSpellRaw"), double[].class);
        double fourthSpellRawWeight1 = CalcUtils.positveStats(fourthSpellRawList[1],fourthSpellRawList[0],currentfourthSpellRaw,fourthSpellRawList[2]);
        double fourthSpellRawWeight2 = CalcUtils.positveStats(fourthSpellRawList[1],fourthSpellRawList[0],currentfourthSpellRaw,fourthSpellRawList[3]);

        int finalWeight1 = (int) Math.round(lifeStealWeight1 + manaStealWeight1 + thornsWeight1 + poisonWeight1 + fourthSpellRawWeight1);
        int finalWeight2 = (int) Math.round(lifeStealWeight2 + manaStealWeight2 + thornsWeight2 + poisonWeight2 + fourthSpellRawWeight2);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight1)+"§f, "+ CodingUtils.color(finalWeight2)));
        return mainHandItem;
    }
}
