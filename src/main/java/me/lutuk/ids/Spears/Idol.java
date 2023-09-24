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

public class Idol {
    private static double currentmanaRegen;
    private static double currentreflection;
    private static double currentsoulpointRegen;
    private static double currentrawSpell;
    private static double currentwaterDefense;
    private static double currentsecondSpellRaw;

    public static ItemStack Idol(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));

        if (getLore.isEmpty()) {
            return null;
        }
//120","","§7§a+26 §7Intelligence","","§7§a+5/5s §7Mana Regen","§7§a+27% §7Reflection","§7§a+22% §7Soul Point Regen","§7§a+338§2** §7Spell Damage","§7§a+13% §7Water Defence","§7§a-61§2* §7Charge Cost","","§7[3/3] Powder Slots [§b❉ §b❉ §b❉§7]","§5Mythic Item [3]","§8§8Intricate carvings and","§8images, almost like that","§8of a totem pole, cover the","§8shaft of this marvelous","§8trident, dredged up from","§8the Seavale Reef. Foreign","§8magics heighten the senses","§8of its user in battle, but","§8also instills a longing","§8for the ocean. Many have","§8succumbed to this urge and","§8drowned themselves."]}}
        String manaRegen = getLore.get(i+4);
        manaRegen = manaRegen.toLowerCase().replaceAll("[^1234567890/]", "");
        manaRegen = manaRegen.substring(1, manaRegen.lastIndexOf("/"));
        currentmanaRegen = Integer.parseInt(manaRegen);


        double[] manaRegenList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Idol").getAsJsonObject().get("manaRegen"), double[].class);
        double manaRegenWeight1 = CalcUtils.positveStats(manaRegenList[1], manaRegenList[0], currentmanaRegen, manaRegenList[2]);
        double manaRegenWeight2 = CalcUtils.positveStats(manaRegenList[1], manaRegenList[0], currentmanaRegen, manaRegenList[3]);

        String reflection = getLore.get(i+5);
        reflection = reflection.toLowerCase().replaceAll("[^1234567890%]", "");
        reflection = reflection.substring(1, reflection.lastIndexOf("%"));
        currentreflection = Double.parseDouble(reflection);

        double[] reflectionList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Idol").getAsJsonObject().get("reflection"), double[].class);
        double reflectionWeight1 = CalcUtils.positveStats(reflectionList[1], reflectionList[0], currentreflection, reflectionList[2]);
        double reflectionWeight2 = CalcUtils.positveStats(reflectionList[1], reflectionList[0], currentreflection, reflectionList[3]);
        String soulpointRegen = getLore.get(i+6);
        soulpointRegen = soulpointRegen.toLowerCase().replaceAll("[^1234567890%]", "");
        soulpointRegen = soulpointRegen.substring(1, soulpointRegen.lastIndexOf("%"));
        currentsoulpointRegen = Double.parseDouble(soulpointRegen);

        double[] soulpointRegenList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Idol").getAsJsonObject().get("soulpointRegen"), double[].class);
        double soulpointRegenWeight1 = CalcUtils.positveStats(soulpointRegenList[1], soulpointRegenList[0], currentsoulpointRegen, soulpointRegenList[2]);
        double soulpointRegenWeight2 = CalcUtils.positveStats(soulpointRegenList[1], soulpointRegenList[0], currentsoulpointRegen, soulpointRegenList[3]);
        String rawSpell = getLore.get(i+7);
        if (rawSpell.contains("§2*")) {
            rawSpell = rawSpell.toLowerCase().replaceAll("[^1234567890/*]", "");
            rawSpell = rawSpell.substring(1, rawSpell.indexOf("*")-1);
        }
        else {
            rawSpell = rawSpell.toLowerCase().replaceAll("[^1234567890/]", "");
            rawSpell = rawSpell.substring(1, rawSpell.lastIndexOf("7"));
        }
        currentrawSpell = Double.parseDouble(rawSpell);

        double[] rawSpellList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Idol").getAsJsonObject().get("rawSpell"), double[].class);
        double rawSpellWeight1 = CalcUtils.positveStats(rawSpellList[1], rawSpellList[0], currentrawSpell, rawSpellList[2]);
        double rawSpellWeight2 = CalcUtils.positveStats(rawSpellList[1], rawSpellList[0], currentrawSpell, rawSpellList[3]);
        String waterDefense = getLore.get(i+8);
        waterDefense = waterDefense.toLowerCase().replaceAll("[^1234567890%]", "");
        waterDefense = waterDefense.substring(1, waterDefense.lastIndexOf("%"));
        currentwaterDefense = Double.parseDouble(waterDefense);

        double[] waterDefenseList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Idol").getAsJsonObject().get("waterDefense"), double[].class);
        double waterDefenseWeight1 = CalcUtils.positveStats(waterDefenseList[1], waterDefenseList[0], currentwaterDefense, waterDefenseList[2]);
        double waterDefenseWeight2 = CalcUtils.positveStats(waterDefenseList[1], waterDefenseList[0], currentwaterDefense, waterDefenseList[3]);
        String secondSpellRaw = getLore.get(i+9);
        if (secondSpellRaw.contains("§2*")) {
            secondSpellRaw = secondSpellRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            secondSpellRaw = secondSpellRaw.substring(1, secondSpellRaw.indexOf("*")-1);
        }
        else {
            secondSpellRaw = secondSpellRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            secondSpellRaw = secondSpellRaw.substring(1, secondSpellRaw.lastIndexOf("7"));
        }


        currentsecondSpellRaw = Double.parseDouble(secondSpellRaw);

        double[] secondSpellRawList = gson.fromJson(jsonObject.get("Spears").getAsJsonObject().get("Idol").getAsJsonObject().get("secondSpellRaw"), double[].class);
        double secondSpellRawWeight1 = CalcUtils.positveStats(secondSpellRawList[1], secondSpellRawList[0], currentsecondSpellRaw, secondSpellRawList[2]);
        double secondSpellRawWeight2 = CalcUtils.positveStats(secondSpellRawList[1], secondSpellRawList[0], currentsecondSpellRaw, secondSpellRawList[3]);

        int finalWeight1 = (int) Math.round(manaRegenWeight1 + reflectionWeight1 + soulpointRegenWeight1 + rawSpellWeight1 + waterDefenseWeight1 + secondSpellRawWeight1);
        int finalWeight2 =(int) Math.round(manaRegenWeight2 + reflectionWeight2 + soulpointRegenWeight2 + rawSpellWeight2 + waterDefenseWeight2 + secondSpellRawWeight2);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight1) + " §f, " + CodingUtils.color(finalWeight2) + " §f"));

        return mainHandItem;
    }
}
