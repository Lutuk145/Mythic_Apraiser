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


/**TODO add input panels for changing color milestones
 * TODO change the name of the item completly if its perfect
 * TODO add config for all these things
 * TODO revamp the gui to make it look more clean
 */
    @Override
    protected void init() {
        super.init();
        addDrawableChild(
                toggleButton = ButtonWidget.builder(Text.literal(""), b -> {
                    if (toggle){
                        toggleButton.setMessage(Text.literal("Toggle: §cfalse"));
                        toggle = false;
                        assert MinecraftClient.getInstance().player != null;
                        MinecraftClient.getInstance().player.getInventory().updateItems();
                        MinecraftClient.getInstance().player.currentScreenHandler.updateToClient();
                    }
                    else {
                        toggleButton.setMessage(Text.literal("Toggle: §atrueÀ"));
                        toggle = true;
                        MinecraftClient.getInstance().player.getInventory().updateItems();
                        MinecraftClient.getInstance().player.currentScreenHandler.updateToClient();
                    }
                }).dimensions(width / 2 , this.height/2+10, 100, 20).build());


        if (toggle){
            toggleButton.setMessage(Text.literal("Toggle: §atrueÀ"));
        }
        else {
            toggleButton.setMessage(Text.literal("Toggle: §cfalse"));
        }

    }



}
