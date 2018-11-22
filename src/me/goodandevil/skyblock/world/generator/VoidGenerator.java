package me.goodandevil.skyblock.world.generator;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import me.goodandevil.skyblock.Main;
import me.goodandevil.skyblock.config.FileManager.Config;
import me.goodandevil.skyblock.island.Location;
import me.goodandevil.skyblock.utils.version.Materials;

public class VoidGenerator extends ChunkGenerator {
	
	@Override
	public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
		ChunkData chunkData = createChunkData(world);
		
		Main plugin = Main.getInstance();
		
		Config config = plugin.getFileManager().getConfig(new File(plugin.getDataFolder(), "config.yml"));
		FileConfiguration configLoad = config.getFileConfiguration();
		
		for (Location.World worldList : Location.World.values()) {
			if (world.getEnvironment().name().equals(worldList.name().toUpperCase())) {
				if (configLoad.getBoolean("Island.World." + worldList.name() + ".Liquid.Enable")) {
					setBlock(chunkData, Materials.LEGACY_STATIONARY_WATER.parseMaterial(), configLoad.getInt("Island.World." + worldList.name() + ".Liquid.Height"));
				}
				
				break;
			}
		}
		
		return chunkData;
	}
	
    @Override
    public List<BlockPopulator> getDefaultPopulators(final World world) {
        return Arrays.asList(new BlockPopulator[0]);
    }
	
    @Override
    public boolean canSpawn(World world, int x, int z) {
        return true;
    }
	
    public byte[][] generateBlockSections(World world, Random random, int chunkX, int chunkZ, BiomeGrid biomeGrid) {
    	return new byte[world.getMaxHeight() / 16][];
    }
    
    private void setBlock(ChunkData chunkData, Material material, int height) {
    	for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < height; y++) {
                    chunkData.setBlock(x, y, z, material);
                }
            }
        }
    }
}
