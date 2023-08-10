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

public class Weathered {
    private static double currentmanaSteal;
    private static double currentreflection;
    private static double currentexploding;
    private static double currentwalkSpeed;
    private static double currentairDamage;
    public static ItemStack Weathered(ItemStack mainHandItem) throws IOException {

        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String manaSteal= getLore.get(4);
        manaSteal= manaSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        manaSteal = manaSteal.substring(1, manaSteal.lastIndexOf("/"));
        currentmanaSteal = Double.parseDouble(manaSteal);

        double[] manaStealList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Weathered").getAsJsonObject().get("manaSteal"), double[].class);
        double manaStealWeight = CalcUtils.positveStats(manaStealList[1],manaStealList[0],currentmanaSteal,manaStealList[2]);

        String reflection= getLore.get(5);
        reflection= reflection.toLowerCase().replaceAll("[^1234567890%]", "");
        reflection = reflection.substring(1, reflection.lastIndexOf("%"));
        currentreflection = Double.parseDouble(reflection);

        double[] reflectionList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Weathered").getAsJsonObject().get("reflection"), double[].class);
        double reflectionWeight = CalcUtils.positveStats(reflectionList[1],reflectionList[0],currentreflection,reflectionList[2]);
        String exploding= getLore.get(6);
        exploding= exploding.toLowerCase().replaceAll("[^1234567890%]", "");
        exploding = exploding.substring(1, exploding.lastIndexOf("%"));
        currentexploding = Double.parseDouble(exploding);

        double[] explodingList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Weathered").getAsJsonObject().get("exploding"), double[].class);
        double explodingWeight = CalcUtils.negativeStats(explodingList[1],explodingList[0],currentexploding,explodingList[2]);
        String walkSpeed= getLore.get(7);
        walkSpeed= walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Weathered").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight = CalcUtils.positveStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[2]);
        String airDamage= getLore.get(9);
        airDamage= airDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        airDamage = airDamage.substring(1, airDamage.lastIndexOf("%"));
        currentairDamage = Double.parseDouble(airDamage);

        double[] airDamageList = gson.fromJson(jsonObject.get("Daggers").getAsJsonObject().get("Weathered").getAsJsonObject().get("airDamage"), double[].class);
        double airDamageWeight = CalcUtils.positveStats(airDamageList[1],airDamageList[0],currentairDamage,airDamageList[2]);

        int finalWeight = (int) (manaStealWeight + reflectionWeight + explodingWeight + walkSpeedWeight + airDamageWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
