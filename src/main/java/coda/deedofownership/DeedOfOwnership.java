package coda.deedofownership;

import coda.deedofownership.registry.DOItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.List;

@Mod(DeedOfOwnership.MOD_ID)
public class DeedOfOwnership {
    public static final String MOD_ID = "deedofownership";

    public DeedOfOwnership() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        DOItems.ITEMS.register(bus);

        forgeBus.addListener(this::entityInteract);
    }

    private void entityInteract(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getPlayer();
        Entity entity = event.getTarget();
        InteractionHand hand = event.getHand();

        if (entity instanceof TamableAnimal target) {
            if (player != null && target.getOwner() != null && target.getOwner().is(player)) {
                List<ServerPlayer> list = player.getServer().getPlayerList().getPlayers();

                for (Player player1 : list) {
                    if (player.getItemInHand(hand).is(DOItems.DEED_OF_OWNERSHIP.get()) && player.getItemInHand(hand).getDisplayName().getString().contains(player1.getName().toString())) {
                        target.setOwnerUUID(player1.getUUID());
                    }
                }
            }
        }
    }
}
