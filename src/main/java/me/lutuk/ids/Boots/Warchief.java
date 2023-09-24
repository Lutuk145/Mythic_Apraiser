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

public class Warchief {
    private static double currentexploding;
    private static double currentwalkSpeed;
    private static double currentthunderDamage;
    private static double currentearthDamage;
    private static double currentmeleePercent;
    private static double currentmeleeRaw;
    public static ItemStack Warchief(ItemStack mainHandItem,int i) throws IOException {
        JsonObject jsonObject = JsonUtils.getFromJsonFile();
        Gson gson = new Gson();
        String output = String.valueOf(mainHandItem.getNbt());
        output = output.substring(output.lastIndexOf("Min:") + 5);
        List<String> getLore = new ArrayList<>();
        getLore = List.of(output.split("\",\""));
        if (getLore.isEmpty()) {
            return null;
        }
        String exploding= getLore.get(i+5);
        exploding= exploding.toLowerCase().replaceAll("[^1234567890%]", "");
        exploding = exploding.substring(1, exploding.lastIndexOf("%"));
        currentexploding = Double.parseDouble(exploding);

        double[] explodingList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Warchief").getAsJsonObject().get("exploding"), double[].class);
        double explodingWeight1 = CalcUtils.positveStats(explodingList[1],explodingList[0],currentexploding,explodingList[2]);
        double explodingWeight2 = CalcUtils.positveStats(explodingList[1],explodingList[0],currentexploding,explodingList[3]);
        String walkSpeed= getLore.get(i+6);
        walkSpeed= walkSpeed.toLowerCase().replaceAll("[^1234567890%]", "");
        walkSpeed = walkSpeed.substring(1, walkSpeed.lastIndexOf("%"));
        currentwalkSpeed = Double.parseDouble(walkSpeed);

        double[] walkSpeedList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Warchief").getAsJsonObject().get("walkSpeed"), double[].class);
        double walkSpeedWeight1 = CalcUtils.negativeStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[2]);
        double walkSpeedWeight2 = CalcUtils.negativeStats(walkSpeedList[1],walkSpeedList[0],currentwalkSpeed,walkSpeedList[3]);
        String thunderDamage= getLore.get(i+7);
        thunderDamage= thunderDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        thunderDamage = thunderDamage.substring(1, thunderDamage.lastIndexOf("%"));
        currentthunderDamage = Double.parseDouble(thunderDamage);

        double[] thunderDamageList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Warchief").getAsJsonObject().get("thunderDamage"), double[].class);
        double thunderDamageWeight1 = CalcUtils.positveStats(thunderDamageList[1],thunderDamageList[0],currentthunderDamage,thunderDamageList[2]);
        double thunderDamageWeight2 = CalcUtils.positveStats(thunderDamageList[1],thunderDamageList[0],currentthunderDamage,thunderDamageList[3]);
        String earthDamage= getLore.get(i+8);
        earthDamage= earthDamage.toLowerCase().replaceAll("[^1234567890%]", "");
        earthDamage = earthDamage.substring(1, earthDamage.lastIndexOf("%"));
        currentearthDamage = Double.parseDouble(earthDamage);

        double[] earthDamageList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Warchief").getAsJsonObject().get("earthDamage"), double[].class);
        double earthDamageWeight1 = CalcUtils.positveStats(earthDamageList[1],earthDamageList[0],currentearthDamage,earthDamageList[2]);
        double earthDamageWeight2 = CalcUtils.positveStats(earthDamageList[1],earthDamageList[0],currentearthDamage,earthDamageList[3]);
        String meleePercent= getLore.get(i+9);
        meleePercent= meleePercent.toLowerCase().replaceAll("[^1234567890%]", "");
        meleePercent = meleePercent.substring(1, meleePercent.lastIndexOf("%"));
        currentmeleePercent = Double.parseDouble(meleePercent);

        double[] meleePercentList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Warchief").getAsJsonObject().get("meleePercent"), double[].class);
        double meleePercentWeight1 = CalcUtils.positveStats(meleePercentList[1],meleePercentList[0],currentmeleePercent,meleePercentList[2]);
        double meleePercentWeight2 = CalcUtils.positveStats(meleePercentList[1],meleePercentList[0],currentmeleePercent,meleePercentList[3]);
        String meleeRaw= getLore.get(i+10);
        if (meleeRaw.contains("*")){
            meleeRaw=meleeRaw.toLowerCase().replaceAll("[^1234567890/*]", "");
            meleeRaw=meleeRaw.substring(1,meleeRaw.indexOf("*")-1);
        }else {
            meleeRaw=meleeRaw.toLowerCase().replaceAll("[^1234567890/]", "");
            meleeRaw=meleeRaw.substring(1,meleeRaw.lastIndexOf("7"));
        }
        currentmeleeRaw = Double.parseDouble(meleeRaw);

        double[] meleeRawList = gson.fromJson(jsonObject.get("Boots").getAsJsonObject().get("Warchief").getAsJsonObject().get("meleeRaw"), double[].class);
        double meleeRawWeight1 = CalcUtils.positveStats(meleeRawList[1],meleeRawList[0],currentmeleeRaw,meleeRawList[2]);
        double meleeRawWeight2 = CalcUtils.positveStats(meleeRawList[1],meleeRawList[0],currentmeleeRaw,meleeRawList[3]);

        int finalWeight1 = (int) (explodingWeight1+walkSpeedWeight1+thunderDamageWeight1+earthDamageWeight1+meleePercentWeight1+meleeRawWeight1);
        int finalWeight2 = (int) (explodingWeight2+walkSpeedWeight2+thunderDamageWeight2+earthDamageWeight2+meleePercentWeight2+meleeRawWeight2);
        mainHandItem.setCustomName(Text.of(mainHandItem.getName().getString() + CodingUtils.color(finalWeight1) + " §f, " + CodingUtils.color(finalWeight2) + " §f"));
        return mainHandItem;
    }
}
