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

public class Toxoplasmosis {
    private static double currentlifeSteal;
    private static double currentmanaSteal;
    private static double currentlootBonus;
    private static double currentwalkSpeed;
    private static double currentpoison;
    public static ItemStack Toxoplasmosis(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String lifeSteal= getLore.get(i+4);
        lifeSteal= lifeSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        lifeSteal = lifeSteal.substring(1, lifeSteal.lastIndexOf("/"));
        currentlifeSteal = Double.parseDouble(lifeSteal);

        double[] lifeStealList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Toxoplasmosis").getAsJsonObject().get("lifeSteal"), double[].class);
        double lifeStealWeight = CalcUtils.positveStats(lifeStealList[1],lifeStealList[0],currentlifeSteal,lifeStealList[2]);


        String manaSteal= getLore.get(i+5);
        manaSteal= manaSteal.toLowerCase().replaceAll("[^1234567890/]", "");
        manaSteal = manaSteal.substring(1, manaSteal.lastIndexOf("/"));
        currentmanaSteal = Double.parseDouble(manaSteal);

        double[] manaStealList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Toxoplasmosis").getAsJsonObject().get("manaSteal"), double[].class);
        double manaStealWeight = CalcUtils.positveStats(manaStealList[1],manaStealList[0],currentmanaSteal,manaStealList[2]);

        String lootBonus= getLore.get(i+6);
        lootBonus= lootBonus.toLowerCase().replaceAll("[^1234567890%]", "");
        lootBonus = lootBonus.substring(1, lootBonus.lastIndexOf("%"));
        currentlootBonus = Double.parseDouble(lootBonus);

        double[] lootBonusList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Toxoplasmosis").getAsJsonObject().get("lootBonus"), double[].class);
        double lootBonusWeight = CalcUtils.positveStats(lootBonusList[1],lootBonusList[0],currentlootBonus,lootBonusList[2]);
        String walkSpeed= getLore.get(i+7);
        walkSpeed= walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Toxoplasmosis").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight = CalcUtils.positveStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[2]);

        String poison= getLore.get(i+8);
        poison= poison.toLowerCase().replaceAll("[^1234567890/]", "");
        poison = poison.substring(1, poison.lastIndexOf("/"));
        currentpoison = Double.parseDouble(poison);

        double[] poisonList = gson.fromJson(jsonObject.get("Reliks").getAsJsonObject().get("Toxoplasmosis").getAsJsonObject().get("poison"), double[].class);
        double poisonWeight = CalcUtils.positveStats(poisonList[1],poisonList[0],currentpoison,poisonList[2]);

        int finalWeight = (int) (lifeStealWeight + manaStealWeight + lootBonusWeight + walkSpeedWeight + poisonWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
