package net.ommina.wallpapercraftstairs.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.ommina.wallpapercraft.Wallpapercraft;
import net.ommina.wallpapercraftstairs.WallpapercraftStairs;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.MOD )
public class ModBlocks {

    public static final String[] COLOURS = { "blue", "brown", "cyan", "gray", "green", "purple", "red", "yellow" };
    public static final String[] STAIRS = { "solid", "brick", "checkeredwool", "clay", "colouredbrick",
         "damask", "diagonallydotted", "dotted", "fancytiles", "floral", "rippled", "stonebrick", "striped", "woodplank", "wool" };

    public static final Map<String, WallpaperStairBlock> STAIRS_BLOCKS = new HashMap<String, WallpaperStairBlock>();

    @SubscribeEvent
    public static void registerStairs( final RegistryEvent.Register<Block> event ) {

        for ( String pattern : STAIRS ) {

            for ( String colour : COLOURS ) {

                final int suffixCount = colour.equals( "cyan" ) ? 9 : 14;

                for ( int suffix = 0; suffix <= suffixCount; suffix++ ) {

                    String name = getName( pattern, colour, suffix );

                    Block sourceBlock = ForgeRegistries.BLOCKS.getValue( Wallpapercraft.getId( name ) ); // Yes, this SHOULD be "Wallpapercraft".

                    if ( sourceBlock == null )
                        WallpapercraftStairs.LOGGER.warn( "Source block not found: " + name + " -- unable to create stairs for it.  Panic!  This shouldn't happen!" );
                    else {

                        WallpaperStairBlock block = new WallpaperStairBlock( pattern, colour, suffix, sourceBlock.defaultBlockState(), Block.Properties.copy( sourceBlock ) );
                        block.setRegistryName( WallpapercraftStairs.getId( name ) );

                        event.getRegistry().register( block );
                        STAIRS_BLOCKS.put( name, block );

                    }

                }
            }

        }

    }

    private static String getName( String pattern, String colour, int suffix ) {
        return pattern + colour + "-" + suffix;
    }

}
