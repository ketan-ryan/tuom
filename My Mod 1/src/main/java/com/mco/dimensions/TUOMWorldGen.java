package com.mco.dimensions;

import java.util.Random;

import javax.annotation.Nullable;

import com.mco.dimensions.opal.OpalWorldType;
import com.mco.dimensions.opal.OpaliteWorldProvider;
import com.mco.generation.OpalOreGen;
import com.mco.generation.TUOMStructureGen;
import com.mco.main.TUOMConfig;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TUOMWorldGen 
{

	public static final String OPAL_NAME = "opal";
	public static final int OPAL_DIM_ID = TUOMConfig.DIMENSION.opalDimID == 0 ? findFreeDimensionID() : TUOMConfig.DIMENSION.opalDimID;
	public static final DimensionType OPAL_DIMENSION_TYPE = DimensionType.register(OPAL_NAME, "_" + OPAL_NAME, OPAL_DIM_ID, OpaliteWorldProvider.class, true);
	public static final WorldType OPAL_WORLD_TYPE = new OpalWorldType();
	static Random rand = new Random();
	
    private static final long SEED = 21052088057241959L;
	
	@Nullable
    private static Integer findFreeDimensionID()
    {
        for (int i = 2; i < Integer.MAX_VALUE; i++)
        {
            if (!DimensionManager.isDimensionRegistered(i))
            {
                System.out.println("Found free dimension ID = " + i);
                return i;
            }
        }
        
        // DEBUG
        System.out.println("ERROR: Could not find free dimension ID");
        return null;
    }
	
	public static final void registerDimensions() {
		DimensionManager.registerDimension(OPAL_DIM_ID, OPAL_DIMENSION_TYPE);
	}
	
	public static void registerWorldGenerators() {
		GameRegistry.registerWorldGenerator(new TUOMStructureGen(), 0);
		GameRegistry.registerWorldGenerator(new OpalOreGen(), 1);
	}
	
	public static void init() {
		registerDimensions();
		registerWorldGenerators();
	}

}
