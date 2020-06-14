package library.biomes;

import com.mco.TUOM;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;

public class LibBiome extends Biome
{
    public BiomeManager.BiomeType type = BiomeManager.BiomeType.WARM;
    
    public int skyColor = -1;
    public int waterColor = -1;
    public int grassColor = -1;
    public int foliageColor = -1;
    

    public LibBiome(String registryName, BiomeProperties properties)
    {
        super(properties);
        setRegistryName(TUOM.MODID + ":" + registryName);
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
    }

    public BiomeManager.BiomeType getType() {
        return type;
    }

    public void setType(BiomeManager.BiomeType type) {
        this.type = type;
    }

    /**
     * Add spawnable creature that will spawn if there is room, used for entities that are supposed to despawn over time
     *
     * @param entityclassIn
     * @param weight
     * @param groupCountMin
     * @param groupCountMax
     */
    public void addMonsterSpawn(Class <? extends EntityLiving> entityclassIn, int weight, int groupCountMin, int groupCountMax) {
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(entityclassIn, weight, groupCountMin, groupCountMax));
    }

    /**
     * Add a spawnable creature that will spawn on generation of a chunk, used for entities that don't naturally despawn like animals
     *
     * @param entityclassIn
     * @param weight
     * @param groupCountMin
     * @param groupCountMax
     */
    public void addCreatureSpawn(Class <? extends EntityLiving> entityclassIn, int weight, int groupCountMin, int groupCountMax) {
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(entityclassIn, weight, groupCountMin, groupCountMax));
    }
    
    @Override
    public int getSkyColorByTemp(float currentTemperature) {
    	if (skyColor == -1) {
    		return super.getSkyColorByTemp(currentTemperature);
    	} else {
    		return skyColor;
    	}
    }

    /**
     * Sets the color of the sky, not including sunset
     *
     * @param color
     */
    public void setSkyColor(int color) {
    	this.skyColor = color;
    }

    /**
     * Sets the tint of the water, tinted from the default blue
     *
     * @param color
     */
    public void setWaterColor(int color) {
    	this.waterColor = color;
    }

    /**
     * Sets the color of grass
     *
     * @param color
     */
    public void setGrassColor(int color) {
    	this.grassColor = color;
    }

    /**
     * Sets the color of foliage like plants and leaves
     *
     * @param color
     */
    public void setFoliageColor(int color) {
    	this.foliageColor = color;
    }
    
    @Override
    public int getWaterColorMultiplier() {
    	if (waterColor != -1) {
    		return waterColor;
    	} else {
    		return super.getWaterColorMultiplier();
    	}
    }
    
    @Override
    public int getModdedBiomeGrassColor(int original) {
    	if (grassColor != -1) {
    		return grassColor;
    	} else {
        	return super.getModdedBiomeGrassColor(original);
    	}
    }
    
    @Override
    public int getModdedBiomeFoliageColor(int original) {
    	if (foliageColor != -1) {
    		return foliageColor;
    	} else {
        	return super.getModdedBiomeFoliageColor(original);
    	}
    }
}