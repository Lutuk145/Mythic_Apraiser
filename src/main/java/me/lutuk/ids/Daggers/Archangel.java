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

public class Archangel {
    private static double currenthprPercent;
    private static double currentwalkSpeed;
    private static double currenthealth;
    private static double currentrawHpr;
    public static ItemStack Archangel(ItemStack mainHandItem,int i) throws IOException {
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

        double[] hprPercentList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Archangel").getAsJsonObject().get("hprPercent"), double[].class);
        double hprPercentWeight1 = CalcUtils.positveStats(hprPercentList[1],hprPercentList[0],currenthprPercent,hprPercentList[2]);
        double hprPercentWeight2 = CalcUtils.positveStats(hprPercentList[1],hprPercentList[0],currenthprPercent,hprPercentList[3]);
        String walkSpeed= getLore.get(i+6);
        walkSpeed= walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Archangel").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight1 = CalcUtils.positveStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[2]);
        double walkSpeedWeight2 = CalcUtils.positveStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[3]);
        String health= getLore.get(i+7);
        if (health.contains("*")){
            health=health.toLowerCase().replaceAll("[^1234567890/*]", "");
            health=health.substring(1,health.indexOf("*")-1);
        }else {
            health=health.toLowerCase().replaceAll("[^1234567890/]", "");
            health=health.substring(1,health.lastIndexOf("7"));
        }
        currenthealth = Double.parseDouble(health);

        double[] healthList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Archangel").getAsJsonObject().get("health"), double[].class);
        double healthWeight1 = CalcUtils.positveStats(healthList[1],healthList[0],currenthealth,healthList[2]);
        double healthWeight2 = CalcUtils.positveStats(healthList[1],healthList[0],currenthealth,healthList[3]);
        String rawHpr= getLore.get(i+8);
        if (rawHpr.contains("*")){
            rawHpr=rawHpr.toLowerCase().replaceAll("[^1234567890/*]", "");
            rawHpr=rawHpr.substring(1,rawHpr.indexOf("*")-1);
        }else {
            rawHpr=rawHpr.toLowerCase().replaceAll("[^1234567890/]", "");
            rawHpr=rawHpr.substring(1,rawHpr.lastIndexOf("7"));
        }
        currentrawHpr = Double.parseDouble(rawHpr);

        double[] rawHprList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Archangel").getAsJsonObject().get("rawHpr"), double[].class);
        double rawHprWeight1 = CalcUtils.positveStats(rawHprList[1],rawHprList[0],currentrawHpr,rawHprList[2]);
        double rawHprWeight2 = CalcUtils.positveStats(rawHprList[1], rawHprList[0], currentrawHpr, rawHprList[3]);

        int finalWeight1 = (int) Math.round(hprPercentWeight1 + walkSpeedWeight1 + healthWeight1 + rawHprWeight1);
        int finalWeight2 = (int) Math.round(hprPercentWeight2 + walkSpeedWeight2 + healthWeight2 + rawHprWeight2);

        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight1)+"§f, "+CodingUtils.color(finalWeight2)));
        return mainHandItem;
    }
}
