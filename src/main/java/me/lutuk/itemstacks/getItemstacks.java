package me.lutuk.itemstacks;

import me.lutuk.utils.CodingUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;

import java.io.IOException;
import java.util.List;

public class getItemstacks {

    public static void getInventoryItem() throws IOException {
        if (MinecraftClient.getInstance() == null || MinecraftClient.getInstance().player == null) {
            return;
        }
        for (int i = 0; i < MinecraftClient.getInstance().player.getInventory().size(); i++) {
            ItemStack currentItem = MinecraftClient.getInstance().player.getInventory().getStack(i);
            CodingUtils.mythicCheck(currentItem);
        }
    }

    public static void getContainerItem() throws IOException {
        if (MinecraftClient.getInstance() == null || MinecraftClient.getInstance().player == null) {
            return;
        }
        if (MinecraftClient.getInstance().player.currentScreenHandler == null){
            return;
        }
        for (int i = 0; i < MinecraftClient.getInstance().player.currentScreenHandler.slots.size(); i++) {
            ItemStack stack = MinecraftClient.getInstance().player.currentScreenHandler.slots.get(i).getStack();
            if (stack.isEmpty()) {
                return;
            } else {
                stack = CodingUtils.mythicCheck(stack);
                MinecraftClient.getInstance().player.currentScreenHandler.slots.get(i).setStack(stack);

            }
        }
    }

}
