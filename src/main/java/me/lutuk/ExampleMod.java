package me.lutuk;

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


import static me.lutuk.itemstacks.getItemstacks.tick;

public class ExampleMod implements ModInitializer {
    /**
     * TODO
     */
    public static final Logger LOGGER = LoggerFactory.getLogger("mythicapraiser");
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static KeyBinding getItemstack;
    public static boolean toggle;
    public static boolean debugMode;

    public static boolean debugModeInventory;
    public static boolean debugModeContainer;

    @Override
    public void onInitialize() {
        toggle = true;
        debugMode = false;
        debugModeInventory = false;
        debugModeContainer = false;
        FileUtils.copyDataFiles();
        getItemstack = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.examplemod.spook", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_KP_8, "category.examplemod.bindings"

        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (getItemstack.wasPressed()) {
                System.out.println(MinecraftClient.getInstance().player.getInventory().getMainHandStack().getNbt());

            }
        });
        /**TODO
         * fix bug where the mod does nto work sometimes
         * make debug mode more clean
         */
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            try {
                tick();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

}