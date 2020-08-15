package net.ommina.wallpapercraftssp.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.ommina.wallpapercraft.Wallpapercraft;
import net.ommina.wallpapercraftssp.WallpapercraftSSP;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.MOD )
public class ModBlocks {

    public static final String[] COLOURS = { "blue", "brown", "cyan", "gray", "green", "purple", "red", "yellow" };
    public static final String[] STAIRS = { "solid", "brick", "checkeredwool", "clay", "colouredbrick",
         "damask", "diagonallydotted", "dotted", "fancytiles", "floral", "jewel", "rippled", "stamp", "stonebrick", "striped", "woodplank", "wool" };

    public static final Map<String, Block> STAIRS_BLOCKS = new HashMap<String, Block>();

    @SubscribeEvent
    public static void registerBlocks( final RegistryEvent.Register<Block> event ) {

        registerStairs2( event );

    }

    private static void registerStairs( final RegistryEvent.Register<Block> event ) {

        for ( String pattern : STAIRS ) {

            for ( String colour : COLOURS ) {

                final int suffixCount = colour.equals( "cyan" ) ? 9 : 14;

                for ( int suffix = 0; suffix <= suffixCount; suffix++ ) {

                    String name = getName( pattern, colour, suffix );

                    Block sourceBlock = ForgeRegistries.BLOCKS.getValue( Wallpapercraft.getId( name ) );

                    if ( sourceBlock == null )
                        WallpapercraftSSP.LOGGER.warn( "Source block not found: " + name + " -- unable to create stairs for it.  Panic!  This shouldn't happen!" );
                    else {

                        Block block = new WallpaperStairs( sourceBlock.getDefaultState(), Block.Properties.from( sourceBlock ) );
                        block.setRegistryName( WallpapercraftSSP.getId( name ) );

                        event.getRegistry().register( block );
                        STAIRS_BLOCKS.put( name, block );

                    }

                }
            }

        }

    }

    private static void registerStairs2( final RegistryEvent.Register<Block> event ) {

        String pattern = "wool";
        String colour = "yellow";
        int suffix = 0;

        String name = getName( pattern, colour, suffix );

        Block sourceBlock = ForgeRegistries.BLOCKS.getValue( Wallpapercraft.getId( name ) );

        if ( sourceBlock == Blocks.AIR )
            WallpapercraftSSP.LOGGER.warn( "Source block not found: " + name + " -- unable to create stairs for it.  Panic!  This shouldn't happen!" );
        else {

            Block block = new WallpaperStairs( sourceBlock.getDefaultState(), Block.Properties.from( sourceBlock ) );
            block.setRegistryName( WallpapercraftSSP.getId( name ) + "_stairs" );

            event.getRegistry().register( block );
            STAIRS_BLOCKS.put( name, block );

        }

    }

    private static String getName( String pattern, String colour, int suffix ) {
        return pattern + colour + "-" + suffix;
    }

}
