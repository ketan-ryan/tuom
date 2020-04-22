package library.blocks;

import net.minecraft.tileentity.TileEntity;

public class LibBlockFurnace extends LibBlockMultisidedFurnace  {

    public LibBlockFurnace(String registryName, Class<? extends TileEntity> tileEntityClass) {
        super(registryName, guiIDToUse++, tileEntityClass);
    }

}
