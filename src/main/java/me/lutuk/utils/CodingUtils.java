package me.lutuk.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class CodingUtils {
    public static void msg(String msg){
        MinecraftClient.getInstance().player.sendMessage(Text.of(msg));
    }
}
