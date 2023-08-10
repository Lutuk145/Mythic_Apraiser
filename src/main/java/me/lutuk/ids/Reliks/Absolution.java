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

public class Absolution {
    private static double currentmanaRegen;
    private static double currenthealth;
    private static double currentsoulPointRegen;
    private static double currentfireDamage;
    private static double currentwaterDamage;
    private static double currentthunderDefense;
    private static double currentearthDefense;
    private static double currentfirstSpellRaw;
    public static ItemStack Absolution(ItemStack mainHandItem) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String manaRegen= getLore.get(2);
        manaRegen= manaRegen.toLowerCase().replaceAll("[^1234567890/]", "");
        manaRegen = manaRegen.substring(1, manaRegen.lastIndexOf("/"));
        currentmanaRegen = Double.parseDouble(manaRegen);

        double[] manaRegenList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Absolution").getAsJsonObject().get("manaRegen"), double[].class);
        double manaRegenWeight = CalcUtils.positveStats(manaRegenList[1],manaRegenList[0],currentmanaRegen,manaRegenList[2]);

        String health= getLore.get(3);
        if (health.contains("*")){
            health=health.toLowerCase().replaceAll("[^1234567890/*]", "");
            health=health.substring(1,health.indexOf("*")-1);
        }else {
            health=health.toLowerCase().replaceAll("[^1234567890/]", "");
            health=health.substring(1,health.lastIndexOf("7"));
        }
        currenthealth = Double.parseDouble(health);

        double[] healthList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Absolution").getAsJsonObject().get("health"), double[].class);
        double healthWeight = CalcUtils.positveStats(healthList[1],healthList[0],currenthealth,healthList[2]);
        String soulPointRegen= getLore.get(4);
        soulPointRegen= soulPointRegen.toLowerCase().replaceAll("[^1234567890%]", "");
        soulPointRegen = soulPointRegen.substring(1, soulPointRegen.lastIndexOf("%"));
        currentsoulPointRegen = Double.parseDouble(soulPointRegen);

        double[] soulPointRegenList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Absolution").getAsJsonObject().get("soulPointRegen"), double[].class);
        double soulPointRegenWeight = CalcUtils.positveStats(soulPointRegenList[1],soulPointRegenList[0],currentsoulPointRegen,soulPointRegenList[2]);
        String fireDamage= getLore.get(5);
        fireDamage= fireDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        fireDamage = fireDamage.substring(1, fireDamage.lastIndexOf("%"));
        currentfireDamage = Double.parseDouble(fireDamage);

        double[] fireDamageList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Absolution").getAsJsonObject().get("fireDamage"), double[].class);
        double fireDamageWeight = CalcUtils.positveStats(fireDamageList[1],fireDamageList[0],currentfireDamage,fireDamageList[2]);
        String waterDamage= getLore.get(6);
        waterDamage= waterDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        waterDamage = waterDamage.substring(1, waterDamage.lastIndexOf("%"));
        currentwaterDamage = Double.parseDouble(waterDamage);

        double[] waterDamageList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Absolution").getAsJsonObject().get("waterDamage"), double[].class);
        double waterDamageWeight = CalcUtils.positveStats(waterDamageList[1],waterDamageList[0],currentwaterDamage,waterDamageList[2]);
        String thunderDefense= getLore.get(7);
        thunderDefense= thunderDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        thunderDefense = thunderDefense.substring(1, thunderDefense.lastIndexOf("%"));
        currentthunderDefense = Double.parseDouble(thunderDefense);

        double[] thunderDefenseList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Absolution").getAsJsonObject().get("thunderDefense"), double[].class);
        double thunderDefenseWeight = CalcUtils.positveStats(thunderDefenseList[1],thunderDefenseList[0],currentthunderDefense,thunderDefenseList[2]);
        String earthDefense= getLore.get(8);
        earthDefense= earthDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        earthDefense = earthDefense.substring(1, earthDefense.lastIndexOf("%"));
        currentearthDefense = Double.parseDouble(earthDefense);

        double[] earthDefenseList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Absolution").getAsJsonObject().get("earthDefense"), double[].class);
        double earthDefenseWeight = CalcUtils.positveStats(earthDefenseList[1],earthDefenseList[0],currentearthDefense,earthDefenseList[2]);

        String firstSpellRaw= getLore.get(9);
        if (firstSpellRaw.contains("*")){
            firstSpellRaw=firstSpellRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            firstSpellRaw=firstSpellRaw.substring(1,firstSpellRaw.indexOf("*")-1);
        }else {
            firstSpellRaw=firstSpellRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            firstSpellRaw=firstSpellRaw.substring(1,firstSpellRaw.lastIndexOf("7"));
        }
        currentfirstSpellRaw = Double.parseDouble(firstSpellRaw);

        double[] firstSpellRawList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Absolution").getAsJsonObject().get("firstSpellRaw"), double[].class);
        double firstSpellRawWeight = CalcUtils.positveStats(firstSpellRawList[1],firstSpellRawList[0],currentfirstSpellRaw,firstSpellRawList[2]);

        int finalWeight = (int) Math.round( healthWeight + manaRegenWeight + soulPointRegenWeight + fireDamageWeight + waterDamageWeight + thunderDefenseWeight + earthDefenseWeight + firstSpellRawWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
