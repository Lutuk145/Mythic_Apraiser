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

public class Resurgence {
    private static double currentmanaRegen;
    private static double currentwalkSpeed;
    private static double currentsoulPointRegen;
    private static double currenthprRaw;
    private static double currentspellDamage;
    private static double currentmeleeDamage;
    public static ItemStack Resurgence(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String manaRegen= getLore.get(i+4);
        manaRegen= manaRegen.toLowerCase().replaceAll("[^1234567890/]", "");
        manaRegen = manaRegen.substring(1, manaRegen.lastIndexOf("/"));
        currentmanaRegen = Double.parseDouble(manaRegen);

        double[] manaRegenList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Resurgence").getAsJsonObject().get("manaRegen"), double[].class);
        double manaRegenWeight = CalcUtils.positveStats(manaRegenList[1],manaRegenList[0],currentmanaRegen,manaRegenList[2]);

        String walkSpeed= getLore.get(i+5);
        walkSpeed= walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Resurgence").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight = CalcUtils.negativeStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[2]);
        String soulPointRegen= getLore.get(i+6);
        soulPointRegen= soulPointRegen.toLowerCase().replaceAll("[^1234567890%]", "");
        soulPointRegen = soulPointRegen.substring(1, soulPointRegen.lastIndexOf("%"));
        currentsoulPointRegen = Double.parseDouble(soulPointRegen);

        double[] soulPointRegenList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Resurgence").getAsJsonObject().get("soulPointRegen"), double[].class);
        double soulPointRegenWeight = CalcUtils.positveStats(soulPointRegenList[1],soulPointRegenList[0],currentsoulPointRegen,soulPointRegenList[2]);
        String hprRaw= getLore.get(i+7);
        if (hprRaw.contains("*")){
            hprRaw=hprRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            hprRaw=hprRaw.substring(1,hprRaw.indexOf("*")-1);
        }else {
            hprRaw=hprRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            hprRaw=hprRaw.substring(1,hprRaw.lastIndexOf("7"));
        }
        currenthprRaw = Double.parseDouble(hprRaw);

        double[] hprRawList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Resurgence").getAsJsonObject().get("hprRaw"), double[].class);
        double hprRawWeight = CalcUtils.positveStats(hprRawList[1],hprRawList[0],currenthprRaw,hprRawList[2]);
        String spellDamage= getLore.get(i+8);
        spellDamage= spellDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        spellDamage = spellDamage.substring(1, spellDamage.lastIndexOf("%"));
        currentspellDamage = Double.parseDouble(spellDamage);

        double[] spellDamageList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Resurgence").getAsJsonObject().get("spellDamage"), double[].class);
        double spellDamageWeight = CalcUtils.negativeStats(spellDamageList[1],spellDamageList[0],currentspellDamage,spellDamageList[2]);
        String meleeDamage= getLore.get(i+9);
        meleeDamage= meleeDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        meleeDamage = meleeDamage.substring(1, meleeDamage.lastIndexOf("%"));
        currentmeleeDamage = Double.parseDouble(meleeDamage);

        double[] meleeDamageList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Resurgence").getAsJsonObject().get("meleeDamage"), double[].class);
        double meleeDamageWeight = CalcUtils.negativeStats(meleeDamageList[1],meleeDamageList[0],currentmeleeDamage,meleeDamageList[2]);

        int finalWeight = (int) (manaRegenWeight + walkSpeedWeight + soulPointRegenWeight + hprRawWeight + spellDamageWeight + meleeDamageWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString()+ CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
