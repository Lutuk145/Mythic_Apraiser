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

public class Lament{
    private static double currentlifeSteal;
    private static double currentmanaSteal;
    private static double currentwaterdamage;
    private static double currentfirstSpellPercent;
    public static ItemStack Lament(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String lifeSteal= getLore.get(i+4);
        lifeSteal= lifeSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        lifeSteal = lifeSteal.substring(1, lifeSteal.lastIndexOf("/"));
        currentlifeSteal = Double.parseDouble(lifeSteal);

        double[] lifeStealList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Lament").getAsJsonObject().get("lifeSteal"), double[].class);
        double lifeStealWeight = CalcUtils.negativeStats(lifeStealList[1],lifeStealList[0],currentlifeSteal,lifeStealList[2]);


        String manaSteal= getLore.get(i+5);
        manaSteal= manaSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        manaSteal = manaSteal.substring(1, manaSteal.lastIndexOf("/"));
        currentmanaSteal = Double.parseDouble(manaSteal);

        double[] manaStealList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Lament").getAsJsonObject().get("manaSteal"), double[].class);
        double manaStealWeight = CalcUtils.positveStats(manaStealList[1],manaStealList[0],currentmanaSteal,manaStealList[2]);

        String waterdamage= getLore.get(i+6);
        waterdamage= waterdamage.toLowerCase().replaceAll("[^1234567890%]", "");
        waterdamage = waterdamage.substring(1, waterdamage.lastIndexOf("%"));
        currentwaterdamage = Double.parseDouble(waterdamage);

        double[] waterdamageList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Lament").getAsJsonObject().get("waterDamage"), double[].class);
        double waterdamageWeight = CalcUtils.positveStats(waterdamageList[1],waterdamageList[0],currentwaterdamage,waterdamageList[2]);
        String firstSpellPercent= getLore.get(i+7);
        firstSpellPercent= firstSpellPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        firstSpellPercent = firstSpellPercent.substring(1, firstSpellPercent.lastIndexOf("%"));
        currentfirstSpellPercent = Double.parseDouble(firstSpellPercent);

        double[] firstSpellPercentList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Lament").getAsJsonObject().get("firstSpellPercent"), double[].class);
        double firstSpellPercentWeight = CalcUtils.positveStats(firstSpellPercentList[1],firstSpellPercentList[0],currentfirstSpellPercent,firstSpellPercentList[2]);

        int finalWeight = (int) Math.round(lifeStealWeight + manaStealWeight + waterdamageWeight + firstSpellPercentWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
