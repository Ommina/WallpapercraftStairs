package net.ommina.wallpapercraftstairs.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.ommina.wallpapercraft.util.MathUtil;
import net.ommina.wallpapercraftstairs.blocks.ModBlocks;
import net.ommina.wallpapercraftstairs.blocks.WallpaperStairBlock;
import net.ommina.wallpapercraftstairs.items.StairItem;
import net.ommina.wallpapercraftstairs.network.Network;
import net.ommina.wallpapercraftstairs.network.VariantScrollRequest;

@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT )
public class MouseScrollHandler {

    @SubscribeEvent
    public static void onScroll( InputEvent.MouseScrollEvent event ) {

        final ClientPlayerEntity player = Minecraft.getInstance().player;
        final ItemStack held = player.getHeldItem( Hand.MAIN_HAND );

        if ( !held.isEmpty() && held.getItem() instanceof StairItem && player.isSneaking() ) {

            final int delta = MathUtil.clamp( (int) Math.round( event.getScrollDelta() ), -1, 1 );

            cycleVariant( held, delta );

            event.setCanceled( true );

        }

    }

    private static void cycleVariant( ItemStack stack, int delta ) {

        final WallpaperStairBlock block = ModBlocks.STAIRS_BLOCKS.get( stack.getItem().getRegistryName().getPath() );

        if ( block != null )
            Network.channel.sendToServer( new VariantScrollRequest( delta ) );

    }

}