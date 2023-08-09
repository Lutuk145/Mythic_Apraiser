package me.lutuk.ids.Wands;

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

public class Monster {
    private static double currentlifeSteal;
    private static double currentmanaSteal;
    private static double currenthealth;
    private static double currentfireDamage;
    private static double currentmeleePercent;
    private static double currentfirstSpellRaw;
    public static ItemStack Monster(ItemStack mainHandItem) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String lifeSteal= getLore.get(4);
        lifeSteal= lifeSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        lifeSteal = lifeSteal.substring(1, lifeSteal.lastIndexOf("/"));
        currentlifeSteal = Double.parseDouble(lifeSteal);

        double[] lifeStealList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Monster").getAsJsonObject().get("lifeSteal"), double[].class);
        double lifeStealWeight = CalcUtils.positveStats(lifeStealList[1],lifeStealList[0],currentlifeSteal,lifeStealList[2]);


        String manaSteal= getLore.get(5);
        manaSteal= manaSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        manaSteal = manaSteal.substring(1, manaSteal.lastIndexOf("/"));
        currentmanaSteal = Double.parseDouble(manaSteal);

        double[] manaStealList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Monster").getAsJsonObject().get("manaSteal"), double[].class);
        double manaStealWeight = CalcUtils.positveStats(manaStealList[1],manaStealList[0],currentmanaSteal,manaStealList[2]);

        String health= getLore.get(6);
        if (health.contains("*")){
            health=health.toLowerCase().replaceAll("[^1234567890/*]", "");
            health=health.substring(1,health.indexOf("*")-1);
        }else {
            health=health.toLowerCase().replaceAll("[^1234567890/]", "");
            health=health.substring(1,health.lastIndexOf("7"));
        }
        currenthealth = Double.parseDouble(health);

        double[] healthList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Monster").getAsJsonObject().get("health"), double[].class);
        double healthWeight = CalcUtils.positveStats(healthList[1],healthList[0],currenthealth,healthList[2]);
        String fireDamage= getLore.get(7);
        fireDamage= fireDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        fireDamage = fireDamage.substring(1, fireDamage.lastIndexOf("%"));
        currentfireDamage = Double.parseDouble(fireDamage);

        double[] fireDamageList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Monster").getAsJsonObject().get("fireDamage"), double[].class);
        double fireDamageWeight = CalcUtils.positveStats(fireDamageList[1],fireDamageList[0],currentfireDamage,fireDamageList[2]);
        String meleePercent= getLore.get(8);
        meleePercent= meleePercent.toLowerCase().replaceAll("[^1234567890%]", "");
        meleePercent = meleePercent.substring(1, meleePercent.lastIndexOf("%"));
        currentmeleePercent = Double.parseDouble(meleePercent);

        double[] meleePercentList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Monster").getAsJsonObject().get("meleePercent"), double[].class);
        double meleePercentWeight = CalcUtils.positveStats(meleePercentList[1],meleePercentList[0],currentmeleePercent,meleePercentList[2]);
        String firstSpellRaw= getLore.get(9);
        if (firstSpellRaw.contains("*")){
            firstSpellRaw=firstSpellRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            firstSpellRaw=firstSpellRaw.substring(1,firstSpellRaw.indexOf("*")-1);
        }else {
            firstSpellRaw=firstSpellRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            firstSpellRaw=firstSpellRaw.substring(1,firstSpellRaw.lastIndexOf("7"));
        }
        currentfirstSpellRaw = Double.parseDouble(firstSpellRaw);

        double[] firstSpellRawList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Monster").getAsJsonObject().get("firstSpellRaw"), double[].class);
        double firstSpellRawWeight = CalcUtils.negativeStats(firstSpellRawList[1],firstSpellRawList[0],currentfirstSpellRaw,firstSpellRawList[2]);

        int finalWeight = (int) Math.round(lifeStealWeight + manaStealWeight + healthWeight + fireDamageWeight + meleePercentWeight + firstSpellRawWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
