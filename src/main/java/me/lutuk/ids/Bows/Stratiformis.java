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

public class Stratiformis {
    private static double currentrReflection;
    private static double currentHealth;
    private static double currentWs;
    private static double currentSpellDamage;
    private static double currentMainAttackDamage;
    //115","","§7§a+25 §7Agility","","§7§a+8% §7Reflection","§7§a+39% §7Walk Speed","§7§c-2000 §7Health","§7§a+1% §7Spell Damage","§7§a+12% §7Main Attack Damage","","§7[0/3] Powder Slots","§5Mythic Item [4]","§8§8The Elves managed to harness","§8the power of air itself,","§8forming a bow made entirely","§8of air currents, allowing","§8the user to move swift like","§8the wind, but vulnerable","§8to attacks.
    //115","","§7§a+25 §7Agility","","§7§a+7% §7Reflection","§7§a+70% §7Walk Speed","§7§c-2120 §7Health","§7§a+5% §7Spell Damage","§7§a+10% §7Main Attack Damage","","§7[0/3] Powder Slots","§5Mythic Item [3]","§8§8The Elves managed to harness","§8the power of air itself,","§8forming a bow made entirely","§8of air currents, allowing","§8the user to move swift like","§8the wind, but vulnerable","§8to attacks.
    public static ItemStack Stratiformis(ItemStack mainHandItem) throws IOException {
        String output = String.valueOf(mainHandItem.getNbt());
        boolean tm = output.contains("Price");
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        if (tm){

        }
        String reflection = getLore.get(4);
        String ws = getLore.get(5);
        String hp = getLore.get(6);
        String spellDamage = getLore.get(7);
        String meleDamage = getLore.get(8);

        reflection = reflection.toLowerCase().replaceAll("[^1234567890%]", "");
        reflection = reflection.substring(1, reflection.lastIndexOf("%"));
        currentrReflection = Integer.parseInt(reflection);

        ws = ws.toLowerCase().replaceAll("[^1234567890%]", "");
        ws = ws.substring(1, ws.lastIndexOf("%"));
        currentWs = Integer.parseInt(ws);

        hp = hp.toLowerCase().replaceAll("[^1234567890-]", "");
        hp = hp.substring(hp.indexOf("-") + 1, hp.lastIndexOf("-") + 5);
        currentHealth = Integer.parseInt(hp);

        spellDamage = spellDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        spellDamage = spellDamage.substring(1, spellDamage.lastIndexOf("%"));
        currentSpellDamage = Integer.parseInt(spellDamage);

        meleDamage = meleDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        meleDamage = meleDamage.substring(1, meleDamage.lastIndexOf("%"));
        currentMainAttackDamage = Integer.parseInt(meleDamage);

        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        double[] reflectionList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Stratiformis").getAsJsonObject().get("reflection"), double[].class);
        double[] hpList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Stratiformis").getAsJsonObject().get("health"), double[].class);
        double[] spellDamageList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Stratiformis").getAsJsonObject().get("spellDmg"), double[].class);
        double[] walkSpeedList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Stratiformis").getAsJsonObject().get("walkSpeed"), double[].class);
        double[] mainAttackList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Stratiformis").getAsJsonObject().get("meleeAttack"), double[].class);

        double reflectionWeight = CalcUtils.positveStats(reflectionList[1], reflectionList[0], currentrReflection, reflectionList[2]);
        double hpWeight = CalcUtils.negativeStats(hpList[1], hpList[0], currentHealth, hpList[2]);
        double spellWeight = CalcUtils.negativeStats(spellDamageList[1], spellDamageList[0], currentSpellDamage, spellDamageList[2]);
        double wsWeight = CalcUtils.negativeStats(walkSpeedList[1], walkSpeedList[0], currentWs, walkSpeedList[2]);
        double mainAttackWeight = CalcUtils.negativeStats(mainAttackList[1], mainAttackList[0], currentMainAttackDamage, mainAttackList[2]);

        int finalWeight1 = (int) Math.round(wsWeight + hpWeight + spellWeight+reflectionWeight+mainAttackWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight1)));
        System.out.println(mainHandItem.getNbt());
        return mainHandItem;
    }
}
