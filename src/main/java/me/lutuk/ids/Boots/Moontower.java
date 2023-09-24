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

public class Moontower {
    private static double currentwalkSpeed;
    private static double currentwaterDefense;
    private static double currentairDefense;
    public static ItemStack Moontower(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String walkSpeed= getLore.get(i+8);
        walkSpeed= walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Moontower").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight = CalcUtils.positveStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[2]);
        String waterDefense= getLore.get(i+9);
        waterDefense= waterDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        waterDefense = waterDefense.substring(1, waterDefense.lastIndexOf("%"));
        currentwaterDefense = Double.parseDouble(waterDefense);

        double[] waterDefenseList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Moontower").getAsJsonObject().get("waterDefense"), double[].class);
        double waterDefenseWeight = CalcUtils.positveStats(waterDefenseList[1],waterDefenseList[0],currentwaterDefense,waterDefenseList[2]);
        String airDefense= getLore.get(i+10);
        airDefense= airDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        airDefense = airDefense.substring(1, airDefense.lastIndexOf("%"));
        currentairDefense = Double.parseDouble(airDefense);

        double[] airDefenseList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Moontower").getAsJsonObject().get("airDefense"), double[].class);
        double airDefenseWeight = CalcUtils.positveStats(airDefenseList[1],airDefenseList[0],currentairDefense,airDefenseList[2]);

        int finalWeight = (int) (walkSpeedWeight + waterDefenseWeight + airDefenseWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString()+ CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
