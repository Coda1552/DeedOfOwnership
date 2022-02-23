package coda.deedofownership;

import coda.deedofownership.registry.DOItems;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.HotbarManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Overlay;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.player.inventory.Hotbar;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.ContainerScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.List;

@Mod(DeedOfOwnership.MOD_ID)
public class DeedOfOwnership {
    public static final String MOD_ID = "deedofownership";

    public DeedOfOwnership() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        DOItems.ITEMS.register(bus);

        forgeBus.addListener(this::preRenderGuiEvent);
        forgeBus.addListener(this::renderGameOverlayEvent);
        forgeBus.addListener(this::postRenderGuiEvent);
    }

    private void preRenderGuiEvent(RenderGameOverlayEvent.Pre event) {
        RenderGameOverlayEvent.ElementType type = event.getType();
        PoseStack stack = event.getMatrixStack();

        if (type == RenderGameOverlayEvent.ElementType.LAYER) {
            stack.pushPose();
        }
    }

    private void postRenderGuiEvent(RenderGameOverlayEvent.Post event) {
        RenderGameOverlayEvent.ElementType type = event.getType();
        PoseStack stack = event.getMatrixStack();

        if (type == RenderGameOverlayEvent.ElementType.LAYER) {
            stack.popPose();
        }
    }

    // TODO - i think i have to mixin for this to work
    private void renderGameOverlayEvent(RenderGameOverlayEvent event) {
        Gui gui = new Gui(Minecraft.getInstance());
        PoseStack stack = event.getMatrixStack();

        gui.renderHotbar(0.5F, stack);
        RenderSystem.setShaderColor(0.0F, 0.5F, 0.1F, 0.5F);
    }
}
