package net.ommina.wallpapercraftstairs.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.ommina.wallpapercraft.util.MathUtil;
import net.ommina.wallpapercraftstairs.blocks.ModBlocks;
import net.ommina.wallpapercraftstairs.blocks.WallpaperStairBlock;
import net.ommina.wallpapercraftstairs.items.ModItems;
import net.ommina.wallpapercraftstairs.items.StairItem;

import java.util.function.Supplier;

public class VariantScrollRequest {

    private int delta;

    public VariantScrollRequest() {
    }

    public VariantScrollRequest( final int delta ) {
        this.delta = delta;
    }

    public static VariantScrollRequest fromBytes( FriendlyByteBuf buf ) {

        VariantScrollRequest packet = new VariantScrollRequest();

        packet.delta = MathUtil.clamp( buf.readInt(), -1, 1 );

        return packet;

    }

    public static void handle( VariantScrollRequest packet, Supplier<NetworkEvent.Context> ctx ) {

        ctx.get().enqueueWork( () -> {

            final ServerPlayer player = ctx.get().getSender();
            final int delta = packet.delta;

            if ( player != null && player.isCrouching() && player.getMainHandItem() != ItemStack.EMPTY ) {

                ItemStack stack = player.getMainHandItem();

                if ( stack.getItem() instanceof StairItem ) {

                    final WallpaperStairBlock block = ModBlocks.STAIRS_BLOCKS.get( stack.getItem().getRegistryName().getPath() );

                    if ( block != null ) {

                        final int suffix = MathUtil.rollOver( Math.abs( Integer.parseInt( block.getSuffix() ) ) + delta, 0, block.getColour().contains( "cyan" ) ? 9 : 14 );
                        final StairItem item = ModItems.get( block.getPattern(), block.getColour(), suffix );

                        if ( item != null )
                            player.setItemInHand( InteractionHand.MAIN_HAND, new ItemStack( item, stack.getCount() ) );

                    }

                }

            }

        } );

        ctx.get().setPacketHandled( true );

    }

    public void toBytes( FriendlyByteBuf buf ) {
        buf.writeInt( this.delta );
    }

}
