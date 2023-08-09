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

public class Fatal {
    private static double currentwalkSpeed;
    private static double currentspellDamage;
    private static double currentfirstSpellPercent;
    private static double currentsecondSpellPercent;
    public static ItemStack Fatal(ItemStack mainHandItem) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }



        String walkSpeed= getLore.get(5);
        walkSpeed= walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Fatal").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight = CalcUtils.positveStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[2]);
        String spellDamage= getLore.get(6);
        spellDamage= spellDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        spellDamage = spellDamage.substring(1, spellDamage.lastIndexOf("%"));
        currentspellDamage = Double.parseDouble(spellDamage);

        double[] spellDamageList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Fatal").getAsJsonObject().get("spellDamage"), double[].class);
        double spellDamageWeight = CalcUtils.positveStats(spellDamageList[1],spellDamageList[0],currentspellDamage,spellDamageList[2]);
        String firstSpellPercent= getLore.get(7);
        firstSpellPercent= firstSpellPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        firstSpellPercent = firstSpellPercent.substring(1, firstSpellPercent.lastIndexOf("%"));
        currentfirstSpellPercent = Double.parseDouble(firstSpellPercent);

        double[] firstSpellPercentList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Fatal").getAsJsonObject().get("firstSpellPercent"), double[].class);
        double firstSpellPercentWeight = CalcUtils.negativeStats(firstSpellPercentList[1],firstSpellPercentList[0],currentfirstSpellPercent,firstSpellPercentList[2]);
        String secondSpellPercent= getLore.get(8);
        secondSpellPercent= secondSpellPercent.toLowerCase().replaceAll("[^1234567890%]", "");
        secondSpellPercent = secondSpellPercent.substring(1, secondSpellPercent.lastIndexOf("%"));
        currentsecondSpellPercent = Double.parseDouble(secondSpellPercent);

        double[] secondSpellPercentList = gson.fromJson(jsonObject.get("Wands").getAsJsonObject().get("Fatal").getAsJsonObject().get("secondSpellPercent"), double[].class);
        double secondSpellPercentWeight = CalcUtils.positveStats(secondSpellPercentList[1],secondSpellPercentList[0],currentsecondSpellPercent,secondSpellPercentList[2]);

        int finalWeight = (int) Math.round( walkSpeedWeight + spellDamageWeight + firstSpellPercentWeight + secondSpellPercentWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
