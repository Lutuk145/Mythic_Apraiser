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

public class Grandmother {
    private static double currenthprPercent;
    private static double currentxpBonus;
    private static double currentlootBonus;
    private static double currentwalkSpeed;
    private static double currentrawHpr;
    private static double currentspellPercent;
    private static double currentmeleePercent;
    public static ItemStack Grandmother(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String hprPercent= getLore.get(i+5);
        hprPercent= hprPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        hprPercent = hprPercent.substring(1, hprPercent.lastIndexOf("%"));
        currenthprPercent = Double.parseDouble(hprPercent);

        double[] hprPercentList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Grandmother").getAsJsonObject().get("hprPercent"), double[].class);
        double hprPercentWeight = CalcUtils.negativeStats(hprPercentList[1],hprPercentList[0],currenthprPercent,hprPercentList[2]);
        String xpBonus= getLore.get(i+6);
        xpBonus= xpBonus.toLowerCase().replaceAll("[^1234567890%]", "");
        xpBonus = xpBonus.substring(1, xpBonus.lastIndexOf("%"));
        currentxpBonus = Double.parseDouble(xpBonus);

        double[] xpBonusList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Grandmother").getAsJsonObject().get("xpBonus"), double[].class);
        double xpBonusWeight = CalcUtils.positveStats(xpBonusList[1],xpBonusList[0],currentxpBonus,xpBonusList[2]);
        String lootBonus= getLore.get(i+7);
        lootBonus= lootBonus.toLowerCase().replaceAll("[^1234567890%]", "");
        lootBonus = lootBonus.substring(1, lootBonus.lastIndexOf("%"));
        currentlootBonus = Double.parseDouble(lootBonus);

        double[] lootBonusList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Grandmother").getAsJsonObject().get("lootBonus"), double[].class);
        double lootBonusWeight = CalcUtils.positveStats(lootBonusList[1],lootBonusList[0],currentlootBonus,lootBonusList[2]);
        String walkSpeed= getLore.get(i+8);
        walkSpeed= walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Grandmother").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight = CalcUtils.negativeStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[2]);
        String rawHpr= getLore.get(i+9);
        if (rawHpr.contains("*")){
            rawHpr=rawHpr.toLowerCase().replaceAll("[^1234567890/*]", "");
            rawHpr=rawHpr.substring(1,rawHpr.indexOf("*")-1);
        }else {
            rawHpr=rawHpr.toLowerCase().replaceAll("[^1234567890/]", "");
            rawHpr=rawHpr.substring(1,rawHpr.lastIndexOf("7"));
        }
        currentrawHpr = Double.parseDouble(rawHpr);

        double[] rawHprList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Grandmother").getAsJsonObject().get("rawHpr"), double[].class);
        double rawHprWeight = CalcUtils.negativeStats(rawHprList[1],rawHprList[0],currentrawHpr,rawHprList[2]);
        String spellPercent= getLore.get(i+10);
        spellPercent= spellPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        spellPercent = spellPercent.substring(1, spellPercent.lastIndexOf("%"));
        currentspellPercent = Double.parseDouble(spellPercent);

        double[] spellPercentList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Grandmother").getAsJsonObject().get("spellPercent"), double[].class);
        double spellPercentWeight = CalcUtils.positveStats(spellPercentList[1],spellPercentList[0],currentspellPercent,spellPercentList[2]);
        String meleePercent= getLore.get(i+11);
        meleePercent= meleePercent.toLowerCase().replaceAll("[^1234567890%]", "");
        meleePercent = meleePercent.substring(1, meleePercent.lastIndexOf("%"));
        currentmeleePercent = Double.parseDouble(meleePercent);

        double[] meleePercentList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Grandmother").getAsJsonObject().get("meleePercent"), double[].class);
        double meleePercentWeight = CalcUtils.positveStats(meleePercentList[1],meleePercentList[0],currentmeleePercent,meleePercentList[2]);

        int finalWeight1 = (int) (hprPercentWeight + xpBonusWeight + lootBonusWeight + walkSpeedWeight + rawHprWeight + spellPercentWeight + meleePercentWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight1)));
        return mainHandItem;
    }
}
