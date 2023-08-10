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

public class Freedom {
    private static double currentmanaRegen;
    private static double currentwalkSpeed;
    private static double currenthealth;
    private static double currentrawSpell;
    private static double currentrawMelee;
    public static ItemStack Freedom(ItemStack mainHandItem)throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }

        String manaRegen= getLore.get(4);
        manaRegen= manaRegen.toLowerCase().replaceAll("[^1234567890/]", "");
        manaRegen = manaRegen.substring(1, manaRegen.lastIndexOf("/"));
        currentmanaRegen = Double.parseDouble(manaRegen);

        double[] manaRegenList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Freedom").getAsJsonObject().get("manaRegen"), double[].class);
        double manaRegenWeight = CalcUtils.positveStats(manaRegenList[1],manaRegenList[0],currentmanaRegen,manaRegenList[2]);

        String walkSpeed= getLore.get(5);
        walkSpeed= walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Freedom").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight = CalcUtils.positveStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[2]);
        String health= getLore.get(6);
        if (health.contains("*")){
            health=health.toLowerCase().replaceAll("[^1234567890/*]", "");
            health=health.substring(1,health.indexOf("*")-1);
        }else {
            health=health.toLowerCase().replaceAll("[^1234567890/]", "");
            health=health.substring(1,health.lastIndexOf("7"));
        }
        currenthealth = Double.parseDouble(health);

        double[] healthList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Freedom").getAsJsonObject().get("health"), double[].class);
        double healthWeight = CalcUtils.positveStats(healthList[1],healthList[0],currenthealth,healthList[2]);
        String rawSpell= getLore.get(7);
        if (rawSpell.contains("*")){
            rawSpell=rawSpell.toLowerCase().replaceAll("[^1234567890/*]", "");
            rawSpell=rawSpell.substring(1,rawSpell.indexOf("*")-1);
        }else {
            rawSpell=rawSpell.toLowerCase().replaceAll("[^1234567890/]", "");
            rawSpell=rawSpell.substring(1,rawSpell.lastIndexOf("7"));
        }
        currentrawSpell = Double.parseDouble(rawSpell);

        double[] rawSpellList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Freedom").getAsJsonObject().get("rawSpell"), double[].class);
        double rawSpellWeight = CalcUtils.positveStats(rawSpellList[1],rawSpellList[0],currentrawSpell,rawSpellList[2]);
        String rawMelee= getLore.get(8);
        if (rawMelee.contains("*")){
            rawMelee=rawMelee.toLowerCase().replaceAll("[^1234567890/*]", "");
            rawMelee=rawMelee.substring(1,rawMelee.indexOf("*")-1);
        }else {
            rawMelee=rawMelee.toLowerCase().replaceAll("[^1234567890/]", "");
            rawMelee=rawMelee.substring(1,rawMelee.lastIndexOf("7"));
        }
        currentrawMelee = Double.parseDouble(rawMelee);

        double[] rawMeleeList = gson.fromJson(jsonObject.get("Bows").getAsJsonObject().get("Freedom").getAsJsonObject().get("rawMelee"), double[].class);
        double rawMeleeWeight = CalcUtils.positveStats(rawMeleeList[1],rawMeleeList[0],currentrawMelee,rawMeleeList[2]);

        int finalWeight = (int) (manaRegenWeight + walkSpeedWeight + healthWeight + rawSpellWeight + rawMeleeWeight);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight)));
        return mainHandItem;
    }
}
