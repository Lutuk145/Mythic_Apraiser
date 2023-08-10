package me.lutuk.ids.Bows;

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

public class Divzer {
    private static double currentlifeSteal;
    private static double currentmanaSteal;
    private static double currentfireDamage;
    private static double currentwaterDamage;
    private static double currentrawSpell;
    private static double currentrawMelee;
    public static ItemStack Divzer(ItemStack mainHandItem) throws IOException{
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String lifeSteal= getLore.get(6);
        lifeSteal= lifeSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        lifeSteal = lifeSteal.substring(1, lifeSteal.lastIndexOf("/"));
        currentlifeSteal = Double.parseDouble(lifeSteal);

        double[] lifeStealList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Divzer").getAsJsonObject().get("lifeSteal"), double[].class);
        double lifeStealWeight = CalcUtils.positveStats(lifeStealList[1],lifeStealList[0],currentlifeSteal,lifeStealList[2]);


        String manaSteal= getLore.get(7);
        manaSteal= manaSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        manaSteal = manaSteal.substring(1, manaSteal.lastIndexOf("/"));
        currentmanaSteal = Double.parseDouble(manaSteal);

        double[] manaStealList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Divzer").getAsJsonObject().get("manaSteal"), double[].class);
        double manaStealWeight = CalcUtils.positveStats(manaStealList[1],manaStealList[0],currentmanaSteal,manaStealList[2]);

        String fireDamage= getLore.get(9);
        fireDamage= fireDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        fireDamage = fireDamage.substring(1, fireDamage.lastIndexOf("%"));
        currentfireDamage = Double.parseDouble(fireDamage);

        double[] fireDamageList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Divzer").getAsJsonObject().get("fireDamage"), double[].class);
        double fireDamageWeight = CalcUtils.negativeStats(fireDamageList[1],fireDamageList[0],currentfireDamage,fireDamageList[2]);
        String waterDamage= getLore.get(10);
        waterDamage= waterDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        waterDamage = waterDamage.substring(1, waterDamage.lastIndexOf("%"));
        currentwaterDamage = Double.parseDouble(waterDamage);

        double[] waterDamageList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Divzer").getAsJsonObject().get("waterDamage"), double[].class);
        double waterDamageWeight = CalcUtils.negativeStats(waterDamageList[1],waterDamageList[0],currentwaterDamage,waterDamageList[2]);
        String rawSpell= getLore.get(11);
        if (rawSpell.contains("*")){
            rawSpell=rawSpell.toLowerCase().replaceAll("[^1234567890/*]", "");
            rawSpell=rawSpell.substring(1,rawSpell.indexOf("*")-1);
        }else {
            rawSpell=rawSpell.toLowerCase().replaceAll("[^1234567890/]", "");
            rawSpell=rawSpell.substring(1,rawSpell.lastIndexOf("7"));
        }
        currentrawSpell = Double.parseDouble(rawSpell);

        double[] rawSpellList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Divzer").getAsJsonObject().get("rawSpell"), double[].class);
        double rawSpellWeight = CalcUtils.positveStats(rawSpellList[1],rawSpellList[0],currentrawSpell,rawSpellList[2]);
        String rawMelee= getLore.get(12);
        if (rawMelee.contains("*")){
            rawMelee=rawMelee.toLowerCase().replaceAll("[^1234567890/*]", "");
            rawMelee=rawMelee.substring(1,rawMelee.indexOf("*")-1);
        }else {
            rawMelee=rawMelee.toLowerCase().replaceAll("[^1234567890/]", "");
            rawMelee=rawMelee.substring(1,rawMelee.lastIndexOf("7"));
        }
        currentrawMelee = Double.parseDouble(rawMelee);

        double[] rawMeleeList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Divzer").getAsJsonObject().get("rawMelee"), double[].class);
        double rawMeleeWeight = CalcUtils.positveStats(rawMeleeList[1],rawMeleeList[0],currentrawMelee,rawMeleeList[2]);

        int finalWeight = (int) (lifeStealWeight + manaStealWeight + fireDamageWeight + waterDamageWeight + rawSpellWeight + rawMeleeWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
