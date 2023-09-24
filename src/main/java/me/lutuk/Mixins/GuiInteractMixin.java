package me.lutuk.Mixins;

import me.lutuk.utils.CodingUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.slot.SlotActionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

import static me.lutuk.itemstacks.getItemstacks.*;

@Mixin(ClientPlayerInteractionManager.class)
public class GuiInteractMixin {

    @Inject(method = "clickSlot", at = @At("HEAD"))
    private void onItemClick(int syncId, int slotId, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) throws IOException {

        CodingUtils.mythicCheck(MinecraftClient.getInstance().player.getInventory().getStack(slotId));
        MinecraftClient.getInstance().player.getInventory().updateItems();
    }
}
