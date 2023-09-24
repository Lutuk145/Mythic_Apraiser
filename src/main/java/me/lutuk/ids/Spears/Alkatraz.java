package me.lutuk.ids.Spears;

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

public class Alkatraz {
    private static double explodingCurrent;
    private static double earthDamageCurrent;
    private static double mainAttackCurrent;

    public static ItemStack Alkatraz(ItemStack mainHandItem,int i) throws IOException {
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String exploding = getLore.get(i+8);
        String earthDamage = getLore.get(i+9);
        String mainAttack = getLore.get(i+10);

        exploding = exploding.toLowerCase().replaceAll("[^1234567890]", "").substring(1, 3);
        explodingCurrent = Integer.parseInt(exploding);

        earthDamage = earthDamage.toLowerCase().replaceAll("[^1234567890]", "").substring(1, 3);
        earthDamageCurrent = Integer.parseInt(earthDamage);

        mainAttack = mainAttack.toLowerCase().replaceAll("[^1234567890]", "").substring(1, 3);
        mainAttackCurrent = Integer.parseInt(mainAttack);

        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        double[] explodingList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Alkatraz").getAsJsonObject().get("exploding"),  double[].class);
        double[] mainAttackList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Alkatraz").getAsJsonObject().get("meleePercent"), double[].class);
        double[] earthDamageList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Alkatraz").getAsJsonObject().get("earthDamage"), double[].class);

        double explodingWeight = CalcUtils.positveStats(explodingList[1], explodingList[0], explodingCurrent, explodingList[2]);
        double mainAttackWeight = CalcUtils.positveStats(mainAttackList[1], mainAttackList[0], mainAttackCurrent, mainAttackList[2]);
        double earthDamageWeight = CalcUtils.positveStats(earthDamageList[1], earthDamageList[0], earthDamageCurrent, earthDamageList[2]);

        int finalWeight = (int) Math.round (mainAttackWeight+explodingWeight+earthDamageWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() +" " + CodingUtils.color(finalWeight)));

        return mainHandItem;
    }
}
