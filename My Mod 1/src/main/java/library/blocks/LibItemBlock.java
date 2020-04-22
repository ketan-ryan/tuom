package library.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class LibItemBlock extends ItemBlock {

    public LibItemBlock(Block block) {
        super(block);
        this.setRegistryName(block.getRegistryName());
    }
}
