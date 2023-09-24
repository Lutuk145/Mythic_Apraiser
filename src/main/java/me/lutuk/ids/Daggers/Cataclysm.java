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

public class Cataclysm {
    private static double currentthorns;
    private static double currenthealth;
    private static double currentstealing;
    private static double currentthunderDamage;
    public static ItemStack Cataclysm(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String thorns= getLore.get(i+4);
        thorns= thorns.toLowerCase().replaceAll("[^1234567890%]", "");
        thorns = thorns.substring(1, thorns.lastIndexOf("%"));
        currentthorns = Double.parseDouble(thorns);

        double[] thornsList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Cataclysm").getAsJsonObject().get("thorns"), double[].class);
        double thornsWeight = CalcUtils.positveStats(thornsList[1],thornsList[0],currentthorns,thornsList[2]);
        String health= getLore.get(i+5);
        if (health.contains("*")){
            health=health.toLowerCase().replaceAll("[^1234567890/*]", "");
            health=health.substring(1,health.indexOf("*")-1);
        }else {
            health=health.toLowerCase().replaceAll("[^1234567890/]", "");
            health=health.substring(1,health.lastIndexOf("7"));
        }
        currenthealth = Double.parseDouble(health);

        double[] healthList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Cataclysm").getAsJsonObject().get("health"), double[].class);
        double healthWeight = CalcUtils.negativeStats(healthList[1],healthList[0],currenthealth,healthList[2]);
        String stealing= getLore.get(i+6);
        stealing= stealing.toLowerCase().replaceAll("[^1234567890%]", "");
        stealing = stealing.substring(1, stealing.lastIndexOf("%"));
        currentstealing = Double.parseDouble(stealing);

        double[] stealingList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Cataclysm").getAsJsonObject().get("stealing"), double[].class);
        double stealingWeight = CalcUtils.positveStats(stealingList[1],stealingList[0],currentstealing,stealingList[2]);
        String thunderDamage= getLore.get(i+7);
        thunderDamage= thunderDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        thunderDamage = thunderDamage.substring(1, thunderDamage.lastIndexOf("%"));
        currentthunderDamage = Double.parseDouble(thunderDamage);

        double[] thunderDamageList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Cataclysm").getAsJsonObject().get("thunderDamage"), double[].class);
        double thunderDamageWeight = CalcUtils.positveStats(thunderDamageList[1],thunderDamageList[0],currentthunderDamage,thunderDamageList[2]);

        int finalWeight = (int) Math.round(thornsWeight + healthWeight + stealingWeight + thunderDamageWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
