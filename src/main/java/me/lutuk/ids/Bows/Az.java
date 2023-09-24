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

public class Az {
    private static double currentxpBonus;
    private static double currentfireDamage;
    private static double currentwaterDamage;
    private static double currentfirstSpellCost;
    public static ItemStack Az(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String xpBonus= getLore.get(i+5);
        xpBonus= xpBonus.toLowerCase().replaceAll("[^1234567890%]", "");
        xpBonus = xpBonus.substring(1, xpBonus.lastIndexOf("%"));
        currentxpBonus = Double.parseDouble(xpBonus);

        double[] xpBonusList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Az").getAsJsonObject().get("xpBonus"), double[].class);
        double xpBonusWeight1 = CalcUtils.positveStats(xpBonusList[1],xpBonusList[0],currentxpBonus,xpBonusList[2]);
        double xpBonusWeight2 = CalcUtils.positveStats(xpBonusList[1],xpBonusList[0],currentxpBonus,xpBonusList[3]);
        String fireDamage= getLore.get(i+6);
        fireDamage= fireDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        fireDamage = fireDamage.substring(1, fireDamage.lastIndexOf("%"));
        currentfireDamage = Double.parseDouble(fireDamage);

        double[] fireDamageList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Az").getAsJsonObject().get("fireDamage"), double[].class);
        double fireDamageWeight1 = CalcUtils.positveStats(fireDamageList[1],fireDamageList[0],currentfireDamage,fireDamageList[2]);
        double fireDamageWeight2 = CalcUtils.positveStats(fireDamageList[1],fireDamageList[0],currentfireDamage,fireDamageList[3]);
        String waterDamage= getLore.get(i+7);
        waterDamage= waterDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        waterDamage = waterDamage.substring(1, waterDamage.lastIndexOf("%"));
        currentwaterDamage = Double.parseDouble(waterDamage);

        double[] waterDamageList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Az").getAsJsonObject().get("waterDamage"), double[].class);
        double waterDamageWeight1 = CalcUtils.positveStats(waterDamageList[1],waterDamageList[0],currentwaterDamage,waterDamageList[2]);
        double waterDamageWeight2 = CalcUtils.positveStats(waterDamageList[1],waterDamageList[0],currentwaterDamage,waterDamageList[3]);
        String firstSpellCost= getLore.get(i+8);
        firstSpellCost= firstSpellCost.toLowerCase().replaceAll("[^1234567890%]", "");
        firstSpellCost = firstSpellCost.substring(1, firstSpellCost.lastIndexOf("%"));
        currentfirstSpellCost = Double.parseDouble(firstSpellCost);

        double[] firstSpellCostList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Az").getAsJsonObject().get("firstSpellCost"), double[].class);
        double firstSpellCostWeight1 = CalcUtils.positveStats(firstSpellCostList[1],firstSpellCostList[0],currentfirstSpellCost,firstSpellCostList[2]);
        double firstSpellCostWeight2 = CalcUtils.positveStats(firstSpellCostList[1],firstSpellCostList[0],currentfirstSpellCost,firstSpellCostList[3]);

        int finalWeight1 = (int) (xpBonusWeight1 + fireDamageWeight1 + waterDamageWeight1 + firstSpellCostWeight1);
        int finalWeight2 = (int) (xpBonusWeight2 + fireDamageWeight2 + waterDamageWeight2 + firstSpellCostWeight2);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight1)+"§f, "+ CodingUtils.color(finalWeight2)));
        return mainHandItem;
    }
}
