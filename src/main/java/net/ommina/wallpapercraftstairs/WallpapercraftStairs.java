package net.ommina.wallpapercraftstairs;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.ommina.wallpapercraftstairs.WallpapercraftStairs.MODID;

@Mod( MODID )
public class WallpapercraftStairs {

    public static final String MODID = "wallpapercraftstairs";
    public static final Logger LOGGER = LogManager.getLogger();

    public WallpapercraftStairs() {

        MinecraftForge.EVENT_BUS.register( this );

        FMLJavaModLoadingContext.get().getModEventBus().addListener( this::setup );

    }

    public static ResourceLocation getId( String path ) {
        return new ResourceLocation( MODID, path );
    }

    private void setup( final FMLCommonSetupEvent event ) {
        //Network.init();
    }

}
