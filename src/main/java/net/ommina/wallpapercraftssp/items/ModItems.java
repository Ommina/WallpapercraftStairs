package net.ommina.wallpapercraftssp.items;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import net.ommina.wallpapercraft.Wallpapercraft;
import net.ommina.wallpapercraft.items.DecorativeItem;
import net.ommina.wallpapercraftssp.WallpapercraftSSP;
import net.ommina.wallpapercraftssp.blocks.ModBlocks;

@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.MOD )
@ObjectHolder( WallpapercraftSSP.MODID )
public class ModItems {

    @SubscribeEvent
    public static void registerItems( final RegistryEvent.Register<Item> event ) {

        ModBlocks.STAIRS_BLOCKS.keySet().stream().sorted().forEachOrdered( s ->
             event.getRegistry().register( new DecorativeItem( ModBlocks.STAIRS_BLOCKS.get( s ), new Item.Properties().group( Wallpapercraft.TAB ) ).setRegistryName( s + "_stairs" ) )
        );

    }

}
