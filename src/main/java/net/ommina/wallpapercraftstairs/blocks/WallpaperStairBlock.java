package net.ommina.wallpapercraftstairs.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.ommina.wallpapercraft.blocks.IDecorativeBlock;

public class WallpaperStairBlock extends StairsBlock implements IDecorativeBlock {

    private final String pattern;
    private final String colour;
    private final String suffix;

    public WallpaperStairBlock( final String pattern, final String colour, final int suffix, BlockState blockState, Properties properties ) {
        super( blockState, properties );

        this.pattern = pattern;
        this.colour = colour;
        this.suffix = "-" + suffix;

    }

//region Overrides
    @Override
    public String getName() {
        return this.pattern + this.colour + this.suffix;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }

    @Override
    public String getColour() {
        return this.colour;
    }

    @Override
    public String getSuffix() {
        return this.suffix;
    }
//endregion Overrides

}
