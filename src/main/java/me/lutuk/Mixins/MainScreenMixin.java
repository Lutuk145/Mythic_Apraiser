package me.lutuk.Mixins;

import me.lutuk.Screens.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.gui.screen.GameMenuScreen.class)
public abstract class MainScreenMixin extends Screen {
    protected MainScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "initWidgets", at = @At("RETURN"))
    private void initMixin(CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("Mythic appraiser"), (button) -> {
            assert this.client != null;
            this.client.setScreen(new Config());
        }).dimensions(this.width-100, this.height-20, 100, 20).build());
    }
}
