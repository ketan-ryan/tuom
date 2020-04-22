package library.blocks;

public class LibBlockOre extends LibBlockSimple {

    public LibBlockOre(String registryName, String harvestTool, int harvestLevel) {
        super(registryName);

        setHarvestLevel(harvestTool, harvestLevel);
    }
}
