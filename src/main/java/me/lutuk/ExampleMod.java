package me.lutuk;

import me.lutuk.itemstacks.getItemstacks;
import me.lutuk.utils.FileUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ExampleMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("mythicapraiser");
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static KeyBinding getItemstack;

    @Override
    public void onInitialize() {
        FileUtils.copyDataFiles();
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Hello Fabric world!");
        getItemstack = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.examplemod.spook", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_KP_8, "category.examplemod.bindings"

        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (getItemstack.wasPressed()) {
                System.out.println(MinecraftClient.getInstance().player.getInventory().getMainHandStack().getNbt());

            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            try {
                getItemstacks.getInventoryItem();
                getItemstacks.getContainerItem();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}