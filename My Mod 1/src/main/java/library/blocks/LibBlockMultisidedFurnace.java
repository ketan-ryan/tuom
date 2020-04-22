package library.blocks;

import net.minecraft.tileentity.TileEntity;

public class LibBlockMultisidedFurnace extends LibBlockMultisidedAndTileEntity {

    public LibBlockMultisidedFurnace(String registryName, int guiID, Class<? extends TileEntity> tileEntityClass) {
        super(registryName, guiID, tileEntityClass);
    }
}
