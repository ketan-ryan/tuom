package com.mco.proxies;

import org.apache.logging.log4j.Logger;

import com.mco.blocks.biomes.dark.DarkLeaves;

import net.minecraftforge.fml.relauncher.Side;

public interface IProxy {

	default void logPhysicalSide(Logger logger) {
		logger.debug("Physical Side: " + getPhysicalSide());
	}

	Side getPhysicalSide();
	
	default void setGraphicsLevel(DarkLeaves leaves, boolean isFancyEnabled) {
    }
}
