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

public class Dawnbreak {
    private static double currentlifeSteal;
    private static double currentmanaSteal;
    private static double currentexploding;
    private static double currentattackSpeedBonus;
    private static double currentfireDamage;
    private static double currentthunderDamage;
    private static double currentmeleeRaw;
    public static ItemStack Dawnbreak(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String lifeSteal = getLore.get(i+2);
        lifeSteal = lifeSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        lifeSteal = lifeSteal.substring(1, lifeSteal.lastIndexOf("/"));
        currentlifeSteal = Double.parseDouble(lifeSteal);

        double[] lifeStealList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Dawnbreak").getAsJsonObject().get("lifeSteal"), double[].class);
        double lifeStealWeight1 = CalcUtils.positveStats(lifeStealList[1], lifeStealList[0], currentlifeSteal, lifeStealList[2]);
        double lifeStealWeight2 = CalcUtils.positveStats(lifeStealList[1], lifeStealList[0], currentlifeSteal, lifeStealList[3]);


        String manaSteal = getLore.get(i+3);
        manaSteal = manaSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        manaSteal = manaSteal.substring(1, manaSteal.lastIndexOf("/"));
        currentmanaSteal = Double.parseDouble(manaSteal);

        double[] manaStealList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Dawnbreak").getAsJsonObject().get("manaSteal"), double[].class);
        double manaStealWeight1 = CalcUtils.positveStats(manaStealList[1], manaStealList[0], currentmanaSteal, manaStealList[2]);
        double manaStealWeight2 = CalcUtils.positveStats(manaStealList[1], manaStealList[0], currentmanaSteal, manaStealList[3]);

        String exploding = getLore.get(i+4);
        exploding = exploding.toLowerCase().replaceAll("[^1234567890%]", "");
        exploding = exploding.substring(1, exploding.lastIndexOf("%"));
        currentexploding = Double.parseDouble(exploding);

        double[] explodingList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Dawnbreak").getAsJsonObject().get("exploding"), double[].class);
        double explodingWeight1 = CalcUtils.positveStats(explodingList[1], explodingList[0], currentexploding, explodingList[2]);
        double explodingWeight2 = CalcUtils.positveStats(explodingList[1], explodingList[0], currentexploding, explodingList[3]);
        String attackSpeedBonus = getLore.get(i+5);
        if (attackSpeedBonus.contains("*")) {
            attackSpeedBonus = attackSpeedBonus.toLowerCase().replaceAll("[^1234567890/*]", "");
            attackSpeedBonus = attackSpeedBonus.substring(1, attackSpeedBonus.indexOf("*") - 1);
        } else {
            attackSpeedBonus = attackSpeedBonus.toLowerCase().replaceAll("[^1234567890/]", "");
            attackSpeedBonus = attackSpeedBonus.substring(1, attackSpeedBonus.lastIndexOf("7"));
        }
        currentattackSpeedBonus = Double.parseDouble(attackSpeedBonus);

        double[] attackSpeedBonusList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Dawnbreak").getAsJsonObject().get("attackSpeedBonus"), double[].class);
        double attackSpeedBonusWeight1 = CalcUtils.negativeStats(attackSpeedBonusList[1], attackSpeedBonusList[0], currentattackSpeedBonus, attackSpeedBonusList[2]);
        double attackSpeedBonusWeight2 = CalcUtils.negativeStats(attackSpeedBonusList[1], attackSpeedBonusList[0], currentattackSpeedBonus, attackSpeedBonusList[3]);
        String fireDamage = getLore.get(i+6);
        fireDamage = fireDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        fireDamage = fireDamage.substring(1, fireDamage.lastIndexOf("%"));
        currentfireDamage = Double.parseDouble(fireDamage);

        double[] fireDamageList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Dawnbreak").getAsJsonObject().get("fireDamage"), double[].class);
        double fireDamageWeight1 = CalcUtils.positveStats(fireDamageList[1], fireDamageList[0], currentfireDamage, fireDamageList[2]);
        double fireDamageWeight2 = CalcUtils.positveStats(fireDamageList[1], fireDamageList[0], currentfireDamage, fireDamageList[3]);
        String thunderDamage = getLore.get(i+7);
        thunderDamage = thunderDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        thunderDamage = thunderDamage.substring(1, thunderDamage.lastIndexOf("%"));
        currentthunderDamage = Double.parseDouble(thunderDamage);

        double[] thunderDamageList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Dawnbreak").getAsJsonObject().get("thunderDamage"), double[].class);
        double thunderDamageWeight1 = CalcUtils.positveStats(thunderDamageList[1], thunderDamageList[0], currentthunderDamage, thunderDamageList[2]);
        double thunderDamageWeight2 = CalcUtils.positveStats(thunderDamageList[1], thunderDamageList[0], currentthunderDamage, thunderDamageList[3]);
        String meleeRaw = getLore.get(i+8);
        if (meleeRaw.contains("*")) {
            meleeRaw = meleeRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            meleeRaw = meleeRaw.substring(1, meleeRaw.indexOf("*") - 1);
        } else {
            meleeRaw = meleeRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            meleeRaw = meleeRaw.substring(1, meleeRaw.lastIndexOf("7"));
        }
        currentmeleeRaw = Double.parseDouble(meleeRaw);

        double[] meleeRawList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Dawnbreak").getAsJsonObject().get("meleeRaw"), double[].class);
        double meleeRawWeight1 = CalcUtils.positveStats(meleeRawList[1], meleeRawList[0], currentmeleeRaw, meleeRawList[2]);
        double meleeRawWeight2 = CalcUtils.positveStats(meleeRawList[1], meleeRawList[0], currentmeleeRaw, meleeRawList[3]);

        int finalWeight1 = (int) (lifeStealWeight1 + manaStealWeight1 + explodingWeight1 + attackSpeedBonusWeight1 + fireDamageWeight1 + thunderDamageWeight1 + meleeRawWeight1);
        int finalWeight2 = (int) (lifeStealWeight2 + manaStealWeight2 + explodingWeight2 + attackSpeedBonusWeight2 + fireDamageWeight2 + thunderDamageWeight2 + meleeRawWeight2);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight1) + " §f, " + CodingUtils.color(finalWeight2) + " §f"));
        return mainHandItem;
    }
}
