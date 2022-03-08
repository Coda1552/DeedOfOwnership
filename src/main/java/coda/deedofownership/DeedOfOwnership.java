package coda.deedofownership;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DeedOfOwnership.MOD_ID)
public class DeedOfOwnership {
    public static final String MOD_ID = "deedofownership";

    public DeedOfOwnership() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        forgeBus.addListener(this::deathEvent);
    }

    private void deathEvent(LivingDeathEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (entity.hasCustomName()) {
            Component component = new TranslatableComponent("entity." + entity.getCustomName());
            for (Player player :  entity.level.players()) {
                player.sendMessage(new TranslatableComponent("entity.death", component), Util.NIL_UUID);
                System.out.println(component);
            }
        }
    }
}
