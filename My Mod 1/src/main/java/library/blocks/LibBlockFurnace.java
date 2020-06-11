package library.blocks;

import net.minecraft.tileentity.TileEntity;

public class LibBlockFurnace extends LibBlockMultisidedFurnace  {

    public LibBlockFurnace(Class<? extends TileEntity> tileEntityClass) {
        super(guiIDToUse++, tileEntityClass);
    }

}
