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

public class Olympic {
    private static double currentwalkSpeed;
    private static double currentairDamage;
    private static double currentairDefense;
    private static double currentfirstSpellRaw;
    private static double currentsecondSpellRaw;
    private static double currentjumpHeight;
    public static ItemStack  Olympic(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String walkSpeed= getLore.get(i+4);
        walkSpeed= walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Olympic").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight = CalcUtils.positveStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[2]);
        String airDamage= getLore.get(i+5);
        airDamage= airDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        airDamage = airDamage.substring(1, airDamage.lastIndexOf("%"));
        currentairDamage = Double.parseDouble(airDamage);

        double[] airDamageList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Olympic").getAsJsonObject().get("airDamage"), double[].class);
        double airDamageWeight = CalcUtils.positveStats(airDamageList[1],airDamageList[0],currentairDamage,airDamageList[2]);
        String airDefense= getLore.get(i+6);
        airDefense= airDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        airDefense = airDefense.substring(1, airDefense.lastIndexOf("%"));
        currentairDefense = Double.parseDouble(airDefense);

        double[] airDefenseList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Olympic").getAsJsonObject().get("airDefense"), double[].class);
        double airDefenseWeight = CalcUtils.positveStats(airDefenseList[1],airDefenseList[0],currentairDefense,airDefenseList[2]);
        String firstSpellRaw= getLore.get(i+7);
        if (firstSpellRaw.contains("*")){
            firstSpellRaw=firstSpellRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            firstSpellRaw=firstSpellRaw.substring(1,firstSpellRaw.indexOf("*")-1);
        }else {
            firstSpellRaw=firstSpellRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            firstSpellRaw=firstSpellRaw.substring(1,firstSpellRaw.lastIndexOf("7"));
        }
        currentfirstSpellRaw = Double.parseDouble(firstSpellRaw);

        double[] firstSpellRawList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Olympic").getAsJsonObject().get("firstSpellRaw"), double[].class);
        double firstSpellRawWeight = CalcUtils.positveStats(firstSpellRawList[1],firstSpellRawList[0],currentfirstSpellRaw,firstSpellRawList[2]);
        String secondSpellRaw= getLore.get(i+8);
        if (secondSpellRaw.contains("*")){
            secondSpellRaw=secondSpellRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            secondSpellRaw=secondSpellRaw.substring(1,secondSpellRaw.indexOf("*")-1);
        }else {
            secondSpellRaw=secondSpellRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            secondSpellRaw=secondSpellRaw.substring(1,secondSpellRaw.lastIndexOf("7"));
        }
        currentsecondSpellRaw = Double.parseDouble(secondSpellRaw);

        double[] secondSpellRawList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Olympic").getAsJsonObject().get("secondSpellRaw"), double[].class);
        double secondSpellRawWeight = CalcUtils.positveStats(secondSpellRawList[1],secondSpellRawList[0],currentsecondSpellRaw,secondSpellRawList[2]);
        String jumpHeight= getLore.get(i+9);
        if (jumpHeight.contains("*")){
            jumpHeight=jumpHeight.toLowerCase().replaceAll("[^1234567890/*]", "");
            jumpHeight=jumpHeight.substring(1,jumpHeight.indexOf("*")-1);
        }else {
            jumpHeight=jumpHeight.toLowerCase().replaceAll("[^1234567890/]", "");
            jumpHeight=jumpHeight.substring(1,jumpHeight.lastIndexOf("7"));
        }
        currentjumpHeight = Double.parseDouble(jumpHeight);

        double[] jumpHeightList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Olympic").getAsJsonObject().get("jumpHeight"), double[].class);
        double jumpHeightWeight = CalcUtils.positveStats(jumpHeightList[1],jumpHeightList[0],currentjumpHeight,jumpHeightList[2]);

        int finalWeight = (int) Math.round( walkSpeedWeight + airDamageWeight + airDefenseWeight + firstSpellRawWeight + secondSpellRawWeight + jumpHeightWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
