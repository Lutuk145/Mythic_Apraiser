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

public class Crusade_Sabatons {
    private static double currenthprPercent;
    private static double currentthorns;
    private static double currentwalkSpeed;
    private static double currenthealth;
    private static double currentfireDefense;
    private static double currentearthDefense;
    public static ItemStack Crusade_Sabatons(ItemStack mainHandItem,int i) throws IOException {
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

        double[] hprPercentList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Crusade Sabatons").getAsJsonObject().get("hprPercent"), double[].class);
        double hprPercentWeight = CalcUtils.positveStats(hprPercentList[1],hprPercentList[0],currenthprPercent,hprPercentList[2]);
        String thorns= getLore.get(i+6);
        thorns= thorns.toLowerCase().replaceAll("[^1234567890%]", "");
        thorns = thorns.substring(1, thorns.lastIndexOf("%"));
        currentthorns = Double.parseDouble(thorns);

        double[] thornsList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Crusade Sabatons").getAsJsonObject().get("thorns"), double[].class);
        double thornsWeight = CalcUtils.positveStats(thornsList[1],thornsList[0],currentthorns,thornsList[2]);
        String walkSpeed= getLore.get(i+7);
        walkSpeed= walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Crusade Sabatons").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight = CalcUtils.negativeStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[2]);
        String health= getLore.get(i+8);
        if (health.contains("*")){
            health=health.toLowerCase().replaceAll("[^1234567890/*]", "");
            health=health.substring(1,health.indexOf("*")-1);
        }else {
            health=health.toLowerCase().replaceAll("[^1234567890/]", "");
            health=health.substring(1,health.lastIndexOf("7"));
        }
        currenthealth = Double.parseDouble(health);

        double[] healthList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Crusade Sabatons").getAsJsonObject().get("health"), double[].class);
        double healthWeight = CalcUtils.positveStats(healthList[1],healthList[0],currenthealth,healthList[2]);
        String fireDefense= getLore.get(i+9);
        fireDefense= fireDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        fireDefense = fireDefense.substring(1, fireDefense.lastIndexOf("%"));
        currentfireDefense = Double.parseDouble(fireDefense);

        double[] fireDefenseList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Crusade Sabatons").getAsJsonObject().get("fireDefense"), double[].class);
        double fireDefenseWeight = CalcUtils.positveStats(fireDefenseList[1],fireDefenseList[0],currentfireDefense,fireDefenseList[2]);
        String earthDefense= getLore.get(i+10);
        earthDefense= earthDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        earthDefense = earthDefense.substring(1, earthDefense.lastIndexOf("%"));
        currentearthDefense = Double.parseDouble(earthDefense);

        double[] earthDefenseList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Crusade Sabatons").getAsJsonObject().get("earthDefense"), double[].class);
        double earthDefenseWeight = CalcUtils.positveStats(earthDefenseList[1],earthDefenseList[0],currentearthDefense,earthDefenseList[2]);

        int finalWeight = (int) (hprPercentWeight + thornsWeight + walkSpeedWeight + healthWeight + fireDefenseWeight + earthDefenseWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString()+ CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
