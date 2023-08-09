package me.lutuk.ids.Spears;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.lutuk.utils.CalcUtils;
import me.lutuk.utils.CodingUtils;
import me.lutuk.utils.JsonUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Hero {
    private static double hprCurrent;
    private static double wsCurrent;
    private static double mainAttackCurrent;

    public static ItemStack Hero(ItemStack mainHandItem) throws IOException {
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String hpr = getLore.get(6);
        String ws = getLore.get(7);
        String mainAttack = getLore.get(8);

        hpr = hpr.toLowerCase().replaceAll("[^1234567890]", "").substring(1, 3);
        hprCurrent = Integer.parseInt(hpr);

        ws = ws.toLowerCase().replaceAll("[^1234567890]", "").substring(1, 3);
        wsCurrent = Integer.parseInt(ws);

        mainAttack = mainAttack.toLowerCase().replaceAll("[^1234567890]", "").substring(1, 3);
        mainAttackCurrent = Integer.parseInt(mainAttack);

        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        double[] hprList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Hero").getAsJsonObject().get("hpr"),  double[].class);
        double[] mainAttackList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Hero").getAsJsonObject().get("mainAttack"), double[].class);
        double[] walkSpeedList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Hero").getAsJsonObject().get("walkSpeed"), double[].class);

        double hprWeight = CalcUtils.positveStats(hprList[1], hprList[0], hprCurrent, hprList[2]);
        double mainAttackWeight = CalcUtils.positveStats(mainAttackList[1], mainAttackList[0], mainAttackCurrent, mainAttackList[2]);
        double wsWeight = CalcUtils.positveStats(walkSpeedList[1], walkSpeedList[0], wsCurrent, walkSpeedList[2]);

        int finalWeight = (int) Math.round(hprWeight + wsWeight + mainAttackWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() +" " + CodingUtils.color(finalWeight)));

        return mainHandItem;
    }

}
