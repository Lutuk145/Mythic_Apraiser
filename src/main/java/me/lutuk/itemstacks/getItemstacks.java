package me.lutuk.itemstacks;

import me.lutuk.utils.CodingUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;

import java.io.IOException;

import static me.lutuk.ExampleMod.debugMode;

public class getItemstacks {
    public static void tick() throws IOException {
        if (debugMode) {
            getInventoryItemDebug();
            getContainerItemDebug();
        } else {
            getInventoryItem();
            getContainerItem();
        }
    }

    public static void getInventoryItem() throws IOException {
        if (MinecraftClient.getInstance() == null || MinecraftClient.getInstance().player == null) {
            return;
        }
        for (int i = 0; i < MinecraftClient.getInstance().player.getInventory().size(); i++) {
            if (MinecraftClient.getInstance().player.getInventory().getStack(i).isEmpty()){
                MinecraftClient.getInstance().player.getInventory().setStack(i, ItemStack.EMPTY);
            }else {
                ItemStack currentItem = MinecraftClient.getInstance().player.getInventory().getStack(i);
                CodingUtils.mythicCheck(currentItem);
                MinecraftClient.getInstance().player.getInventory().updateItems();
            }

        }
    }

    public static void getContainerItem() throws IOException {
        if (MinecraftClient.getInstance() == null || MinecraftClient.getInstance().player == null) {
            return;
        }
        if (MinecraftClient.getInstance().player.currentScreenHandler == null) {
            return;
        }
        for (int i = 0; i < MinecraftClient.getInstance().player.currentScreenHandler.slots.size(); i++) {
            ItemStack stack = MinecraftClient.getInstance().player.currentScreenHandler.slots.get(i).getStack();
            if (stack.isEmpty()){
                MinecraftClient.getInstance().player.currentScreenHandler.slots.get(i).setStack(ItemStack.EMPTY);
            }
            stack = CodingUtils.mythicCheck(stack);
            MinecraftClient.getInstance().player.currentScreenHandler.slots.get(i).setStack(stack);
            MinecraftClient.getInstance().player.currentScreenHandler.updateToClient();


        }
    }

    public static void getContainerItemDebug() throws IOException {
        if (MinecraftClient.getInstance() == null || MinecraftClient.getInstance().player == null) {
            return;
        }
        if (MinecraftClient.getInstance().player.currentScreenHandler == null) {
            return;
        }
        for (int i = 0; i < MinecraftClient.getInstance().player.currentScreenHandler.slots.size(); i++) {
            ItemStack stack = MinecraftClient.getInstance().player.currentScreenHandler.slots.get(i).getStack();
            if (stack.isEmpty()) {
                MinecraftClient.getInstance().player.currentScreenHandler.slots.get(i).setStack(ItemStack.EMPTY);
            } else {
                stack = CodingUtils.mythicCheck(stack);
                MinecraftClient.getInstance().player.currentScreenHandler.slots.get(i).setStack(stack);
                MinecraftClient.getInstance().player.currentScreenHandler.updateToClient();
                System.out.println(stack.getName().getString() + " " + i + "/" + MinecraftClient.getInstance().player.currentScreenHandler.slots.size());

            }
        }
    }

    public static void getInventoryItemDebug() throws IOException {
        if (MinecraftClient.getInstance() == null || MinecraftClient.getInstance().player == null) {
            return;
        }
        for (int i = 0; i < MinecraftClient.getInstance().player.getInventory().size(); i++) {
            if (MinecraftClient.getInstance().player.getInventory().getStack(i).isEmpty()){
                MinecraftClient.getInstance().player.getInventory().setStack(i, ItemStack.EMPTY);
            }else {
                ItemStack currentItem = MinecraftClient.getInstance().player.getInventory().getStack(i);
                CodingUtils.mythicCheck(currentItem);
                MinecraftClient.getInstance().player.getInventory().updateItems();
                System.out.println(currentItem.getName().getString() + " " + i + "/" + MinecraftClient.getInstance().player.getInventory().size());
            }
        }
    }

}
