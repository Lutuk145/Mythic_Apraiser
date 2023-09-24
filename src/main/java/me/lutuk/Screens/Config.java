package me.lutuk.Screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import static me.lutuk.ExampleMod.*;

public class Config extends Screen {
    private ButtonWidget toggleButton;
    private ButtonWidget debugModeButton;
    private ButtonWidget endermanButton;
    public static CheckboxWidget inventoryDebugCheck;
    public static CheckboxWidget containerDebugCheck;
    public Config() {
        super(Text.of("Test"));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    protected void init() {
        super.init();
        addDrawableChild(
                toggleButton = ButtonWidget.builder(Text.literal(""), b -> {
                    if (toggle){
                        toggleButton.setMessage(Text.literal("Toggle: §cfalse"));
                        toggle = false;
                        MinecraftClient.getInstance().player.getInventory().updateItems();
                        MinecraftClient.getInstance().player.currentScreenHandler.updateToClient();
                    }
                    else {
                        toggleButton.setMessage(Text.literal("Toggle: §atrueÀ"));
                        toggle = true;
                        MinecraftClient.getInstance().player.getInventory().updateItems();
                        MinecraftClient.getInstance().player.currentScreenHandler.updateToClient();
                    }
                }).dimensions(width / 2 -100, this.height/2+10, 200, 20).build());
        addDrawableChild(debugModeButton = ButtonWidget.builder(Text.literal(""), b -> {
            if (debugMode){
                debugModeButton.setMessage(Text.literal("Debug Mode: §cfalse"));
                debugMode = false;
            }
            else {
                debugModeButton.setMessage(Text.literal("Debug Mode: §atrueÀ"));
                debugMode = true;
            }
        }).dimensions(width / 2 -100, this.height/2+50, 200, 20).build());

        if (toggle){
            toggleButton.setMessage(Text.literal("Toggle: §atrueÀ"));
        }
        else {
            toggleButton.setMessage(Text.literal("Toggle: §cfalse"));
        }
        if (debugMode){
            debugModeButton.setMessage(Text.literal("Debug Mode: §atrueÀ"));
        }
        else {
            debugModeButton.setMessage(Text.literal("Debug Mode: §cfalse"));
        }

    }



}
