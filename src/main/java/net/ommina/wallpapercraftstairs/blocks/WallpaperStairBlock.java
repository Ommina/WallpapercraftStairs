package net.ommina.wallpapercraftstairs.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

public class WallpaperStairBlock extends StairsBlock implements IDecorativeBlock {

    private final String pattern;
    private final String colour;
    private final String suffix;

    public WallpaperStairBlock( final String pattern, final String colour, final int suffix, BlockState p_i48321_1_, Properties p_i48321_2_ ) {
        super( p_i48321_1_, p_i48321_2_ );

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
