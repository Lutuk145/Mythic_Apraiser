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

public class Sunstar {
    private static double currentlifeSteal;
    private static double currentreflection;
    private static double currentthorns;
    private static double currentwaterDamage;
    private static double currentthunderDamage;
    private static double currentmeleeRaw;
    public static ItemStack  Sunstar(ItemStack mainHandItem) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String lifeSteal= getLore.get(2);
        lifeSteal= lifeSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        lifeSteal = lifeSteal.substring(1, lifeSteal.lastIndexOf("/"));
        currentlifeSteal = Double.parseDouble(lifeSteal);

        double[] lifeStealList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Sunstar").getAsJsonObject().get("lifeSteal"), double[].class);
        double lifeStealWeight = CalcUtils.positveStats(lifeStealList[1],lifeStealList[0],currentlifeSteal,lifeStealList[2]);

        String reflection= getLore.get(3);
        reflection= reflection.toLowerCase().replaceAll("[^1234567890%]", "");
        reflection = reflection.substring(1, reflection.lastIndexOf("%"));
        currentreflection = Double.parseDouble(reflection);

        double[] reflectionList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Sunstar").getAsJsonObject().get("reflection"), double[].class);
        double reflectionWeight = CalcUtils.positveStats(reflectionList[1],reflectionList[0],currentreflection,reflectionList[2]);
        String thorns= getLore.get(4);
        thorns= thorns.toLowerCase().replaceAll("[^1234567890%]", "");
        thorns = thorns.substring(1, thorns.lastIndexOf("%"));
        currentthorns = Double.parseDouble(thorns);

        double[] thornsList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Sunstar").getAsJsonObject().get("thorns"), double[].class);
        double thornsWeight = CalcUtils.positveStats(thornsList[1],thornsList[0],currentthorns,thornsList[2]);
        String waterDamage= getLore.get(5);
        waterDamage= waterDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        waterDamage = waterDamage.substring(1, waterDamage.lastIndexOf("%"));
        currentwaterDamage = Double.parseDouble(waterDamage);

        double[] waterDamageList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Sunstar").getAsJsonObject().get("waterDamage"), double[].class);
        double waterDamageWeight = CalcUtils.negativeStats(waterDamageList[1],waterDamageList[0],currentwaterDamage,waterDamageList[2]);
        String thunderDamage= getLore.get(6);
        thunderDamage= thunderDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        thunderDamage = thunderDamage.substring(1, thunderDamage.lastIndexOf("%"));
        currentthunderDamage = Double.parseDouble(thunderDamage);

        double[] thunderDamageList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Sunstar").getAsJsonObject().get("thunderDamage"), double[].class);
        double thunderDamageWeight = CalcUtils.positveStats(thunderDamageList[1],thunderDamageList[0],currentthunderDamage,thunderDamageList[2]);
        String meleeRaw= getLore.get(7);
        if (meleeRaw.contains("*")){
            meleeRaw=meleeRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            meleeRaw=meleeRaw.substring(1,meleeRaw.indexOf("*")-1);
        }else {
            meleeRaw=meleeRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            meleeRaw=meleeRaw.substring(1,meleeRaw.lastIndexOf("7"));
        }
        currentmeleeRaw = Double.parseDouble(meleeRaw);

        double[] meleeRawList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Sunstar").getAsJsonObject().get("meleeRaw"), double[].class);
        double meleeRawWeight = CalcUtils.positveStats(meleeRawList[1],meleeRawList[0],currentmeleeRaw,meleeRawList[2]);

        int finalWeight = (int) (lifeStealWeight + reflectionWeight + thornsWeight + waterDamageWeight + thunderDamageWeight + meleeRawWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
