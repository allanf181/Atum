package com.teammetallurgy.atum.worldgen;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class AtumChunkProvider implements IChunkProvider {
	private Random rand;
	private NoiseGeneratorOctaves noiseGen1;
	private NoiseGeneratorOctaves noiseGen2;
	private NoiseGeneratorOctaves noiseGen3;
	private NoiseGeneratorOctaves noiseGen4;
	public NoiseGeneratorOctaves noiseGen5;
	public NoiseGeneratorOctaves noiseGen6;
	public NoiseGeneratorOctaves mobSpawnerNoise;
	private World worldObj;
	private double[] noiseArray;
	private double[] stoneNoise = new double[256];
	private MapGenBase caveGenerator = new AtumMapGenCaves();
	private MapGenBase ravineGenerator = new MapGenRavine();
	private BiomeGenBase[] biomesForGeneration;
	double[] noise3;
	double[] noise1;
	double[] noise2;
	double[] noise5;
	double[] noise6;
	float[] parabolicField;

	public AtumChunkProvider(World par1World, long par2) {
		this.caveGenerator = TerrainGen.getModdedMapGen(this.caveGenerator, EventType.CAVE);
		this.worldObj = par1World;
		this.rand = new Random(par2);
		this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
		this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
		this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
		this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
		this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
		NoiseGeneratorOctaves[] noiseGens = new NoiseGeneratorOctaves[]{this.noiseGen1, this.noiseGen2, this.noiseGen3, this.noiseGen4, this.noiseGen5, this.noiseGen6, this.mobSpawnerNoise};
		noiseGens = TerrainGen.getModdedNoiseGenerators(par1World, this.rand, noiseGens);
		this.noiseGen1 = noiseGens[0];
		this.noiseGen2 = noiseGens[1];
		this.noiseGen3 = noiseGens[2];
		this.noiseGen4 = noiseGens[3];
		this.noiseGen5 = noiseGens[4];
		this.noiseGen6 = noiseGens[5];
		this.mobSpawnerNoise = noiseGens[6];
	}

	public void generateTerrain(int par1, int par2, byte[] par3ArrayOfByte) {
		byte b0 = 4;
		byte b1 = 16;
		byte b2 = 63;
		int k = b0 + 1;
		byte b3 = 17;
		int l = b0 + 1;
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, par1 * 4 - 2, par2 * 4 - 2, k + 5, l + 5);
		this.noiseArray = initializeNoiseField(this.noiseArray, par1 * b0, 0, par2 * b0, k, b3, l);

		for(int i1 = 0; i1 < b0; i1++) {
			for(int j1 = 0; j1 < b0; j1++) {
				for(int k1 = 0; k1 < b1; k1++) {
					double d0 = 0.125D;
					double d1 = this.noiseArray[(((i1 + 0) * l + j1 + 0) * b3 + k1 + 0)];
					double d2 = this.noiseArray[(((i1 + 0) * l + j1 + 1) * b3 + k1 + 0)];
					double d3 = this.noiseArray[(((i1 + 1) * l + j1 + 0) * b3 + k1 + 0)];
					double d4 = this.noiseArray[(((i1 + 1) * l + j1 + 1) * b3 + k1 + 0)];
					double d5 = (this.noiseArray[(((i1 + 0) * l + j1 + 0) * b3 + k1 + 1)] - d1) * d0;
					double d6 = (this.noiseArray[(((i1 + 0) * l + j1 + 1) * b3 + k1 + 1)] - d2) * d0;
					double d7 = (this.noiseArray[(((i1 + 1) * l + j1 + 0) * b3 + k1 + 1)] - d3) * d0;
					double d8 = (this.noiseArray[(((i1 + 1) * l + j1 + 1) * b3 + k1 + 1)] - d4) * d0;

					for(int l1 = 0; l1 < 8; l1++) {
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;

						for(int i2 = 0; i2 < 4; i2++) {
							int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + l1;
							short short1 = 128;
							j2 -= short1;
							double d14 = 0.25D;
							double d15 = (d11 - d10) * d14;
							double d16 = d10 - d15;

							for(int k2 = 0; k2 < 4; k2++) {
								if((d16 += d15) > 0.0D) {
									int temp = (j2 + short1);
									j2 = temp;
									par3ArrayOfByte[temp] = (byte) Block.stone.blockID;
								} else if(k1 * 8 + l1 < b2) {
									int temp = (j2 + short1);
									j2 = temp;
									par3ArrayOfByte[temp] = 3;
								} else {
									int temp = (j2 + short1);
									j2 = temp;
									par3ArrayOfByte[temp] = 0;
								}
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	public void replaceBlocksForBiome(int par1, int par2, byte[] par3ArrayOfByte, BiomeGenBase[] par4ArrayOfBiomeGenBase) {
		ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, par1, par2, par3ArrayOfByte, par4ArrayOfBiomeGenBase);
		MinecraftForge.EVENT_BUS.post(event);
		if(event.getResult() == Event.Result.DENY)
			return;

		byte b0 = 63;
		double d0 = 0.03125D;
		this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

		for(int k = 0; k < 16; k++) {
			for(int l = 0; l < 16; l++) {
				BiomeGenBase biomegenbase = par4ArrayOfBiomeGenBase[(l + k * 16)];
				float f = biomegenbase.getFloatTemperature();
				int i1 = (int) (this.stoneNoise[(k + l * 16)] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
				int j1 = -1;
				byte b1 = biomegenbase.topBlock;
				byte b2 = biomegenbase.fillerBlock;

				for(int k1 = 127; k1 >= 0; k1--) {
					int l1 = (l * 16 + k) * 128 + k1;

					if(k1 <= 0) {
						par3ArrayOfByte[l1] = ((byte) Block.bedrock.blockID);
					} else {
						byte b3 = par3ArrayOfByte[l1];

						if(b3 == 0) {
							j1 = -1;
						} else if(b3 == Block.stone.blockID) {
							if(j1 == -1) {
								if(i1 <= 0) {
									b1 = 0;
									b2 = (byte) Block.stone.blockID;
								} else if((k1 >= b0 - 4) && (k1 <= b0 + 1)) {
									b1 = biomegenbase.topBlock;
									b2 = biomegenbase.fillerBlock;
								}

								j1 = i1;

								if(k1 >= b0 - 1) {
									par3ArrayOfByte[l1] = b1;
								} else {
									par3ArrayOfByte[l1] = b2;
								}
							} else if(j1 > 0) {
								j1--;
								par3ArrayOfByte[l1] = b2;

								if((j1 == 0) && (b2 == Block.sand.blockID)) {
									j1 = this.rand.nextInt(4);
									b2 = (byte) Block.sandStone.blockID;
								}
							}
						}
					}
				}
			}
		}
	}

	public Chunk loadChunk(int par1, int par2) {
		return provideChunk(par1, par2);
	}

	public Chunk provideChunk(int par1, int par2) {
		this.rand.setSeed(par1 * 341873128712L + par2 * 132897987541L);
		byte[] abyte = new byte[32768];
		generateTerrain(par1, par2, abyte);
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
		replaceBlocksForBiome(par1, par2, abyte, this.biomesForGeneration);
		this.caveGenerator.generate(this, this.worldObj, par1, par2, abyte);
		this.ravineGenerator.generate(this, this.worldObj, par1, par2, abyte);
		Chunk chunk = new Chunk(this.worldObj, abyte, par1, par2);
		byte[] abyte1 = chunk.getBiomeArray();
		for(int k = 0; k < abyte1.length; ++k) {
			abyte1[k] = ((byte) this.biomesForGeneration[k].biomeID);
		}
		chunk.generateSkylightMap();
		return chunk;
	}

	private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7) {
		ChunkProviderEvent.InitNoiseField event = new ChunkProviderEvent.InitNoiseField(this, par1ArrayOfDouble, par2, par3, par4, par5, par6, par7);
		MinecraftForge.EVENT_BUS.post(event);
		if(event.getResult() == Result.DENY)
			return event.noisefield;
		if(par1ArrayOfDouble == null) {
			par1ArrayOfDouble = new double[par5 * par6 * par7];
		}
		if(this.parabolicField == null) {
			this.parabolicField = new float[25];
			for(int d0 = -2; d0 <= 2; ++d0) {
				for(int l1 = -2; l1 <= 2; ++l1) {
					float d1 = 10.0F / MathHelper.sqrt_float((float) (d0 * d0 + l1 * l1) + 0.2F);
					this.parabolicField[d0 + 2 + (l1 + 2) * 5] = d1;
				}
			}
		}
		double var45 = 684.412D;
		double var46 = 684.412D;
		this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, par2, par4, par5, par7, 1.121D, 1.121D, 0.5D);
		this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, par2, par4, par5, par7, 200.0D, 200.0D, 0.5D);
		this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, par2, par3, par4, par5, par6, par7, var45 / 80.0D, var46 / 160.0D, var45 / 80.0D);
		this.noise1 = this.noiseGen1.generateNoiseOctaves(this.noise1, par2, par3, par4, par5, par6, par7, var45, var46, var45);
		this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, par2, par3, par4, par5, par6, par7, var45, var46, var45);
		boolean flag = false;
		boolean flag1 = false;
		int i2 = 0;
		int j2 = 0;
		for(int k2 = 0; k2 < par5; ++k2) {
			for(int l2 = 0; l2 < par7; ++l2) {
				float f1 = 0.0F;
				float f2 = 0.0F;
				float f3 = 0.0F;
				byte b0 = 2;
				BiomeGenBase biomegenbase = this.biomesForGeneration[k2 + 2 + (l2 + 2) * (par5 + 5)];
				for(int d2 = -b0; d2 <= b0; ++d2) {
					for(int j3 = -b0; j3 <= b0; ++j3) {
						BiomeGenBase k3 = this.biomesForGeneration[k2 + d2 + 2 + (l2 + j3 + 2) * (par5 + 5)];
						float d3 = this.parabolicField[d2 + 2 + (j3 + 2) * 5] / (k3.minHeight + 2.0F);
						if(k3.minHeight > biomegenbase.minHeight) {
							d3 /= 2.0F;
						}
						f1 += k3.maxHeight * d3;
						f2 += k3.minHeight * d3;
						f3 += d3;
					}
				}
				f1 /= f3;
				f2 /= f3;
				f1 = f1 * 0.9F + 0.1F;
				f2 = (f2 * 4.0F - 1.0F) / 8.0F;
				double var47 = this.noise6[j2] / 8000.0D;
				if(var47 < 0.0D) {
					var47 = -var47 * 0.3D;
				}
				var47 = var47 * 3.0D - 2.0D;
				if(var47 < 0.0D) {
					var47 /= 2.0D;
					if(var47 < -1.0D) {
						var47 = -1.0D;
					}
					var47 /= 1.4D;
					var47 /= 2.0D;
				} else {
					if(var47 > 1.0D) {
						var47 = 1.0D;
					}
					var47 /= 8.0D;
				}
				++j2;
				for(int var49 = 0; var49 < par6; ++var49) {
					double var48 = (double) f2;
					double d4 = (double) f1;
					var48 += var47 * 0.2D;
					var48 = var48 * (double) par6 / 16.0D;
					double d5 = (double) par6 / 2.0D + var48 * 4.0D;
					double d6 = 0.0D;
					double d7 = ((double) var49 - d5) * 12.0D * 128.0D / 128.0D / d4;
					if(d7 < 0.0D) {
						d7 *= 4.0D;
					}
					double d8 = this.noise1[i2] / 512.0D;
					double d9 = this.noise2[i2] / 512.0D;
					double d10 = (this.noise3[i2] / 10.0D + 1.0D) / 2.0D;
					if(d10 < 0.0D) {
						d6 = d8;
					} else if(d10 > 1.0D) {
						d6 = d9;
					} else {
						d6 = d8 + (d9 - d8) * d10;
					}
					d6 -= d7;
					if(var49 > par6 - 4) {
						double d11 = (double) ((float) (var49 - (par6 - 4)) / 3.0F);
						d6 = d6 * (1.0D - d11) + -10.0D * d11;
					}
					par1ArrayOfDouble[i2] = d6;
					++i2;
				}
			}
		}
		return par1ArrayOfDouble;
	}

	public boolean chunkExists(int par1, int par2) {
		return true;
	}

	public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
		BlockSand.fallInstantly = true;
		int k = par2 * 16;
		int l = par3 * 16;
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(k + 16, l + 16);
		this.rand.setSeed(this.worldObj.getSeed());
		long i1 = this.rand.nextLong() / 2L * 2L + 1L;
		long j1 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed(par2 * i1 + par3 * j1 ^ this.worldObj.getSeed());
		boolean flag = false;
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(par1IChunkProvider, this.worldObj, this.rand, par2, par3, flag));
		biomegenbase.decorate(this.worldObj, this.rand, k, l);
		SpawnerAnimals.performWorldGenSpawning(this.worldObj, biomegenbase, k + 8, l + 8, 16, 16, this.rand);
		k += 8;
		l += 8;
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(par1IChunkProvider, this.worldObj, this.rand, par2, par3, flag));
		BlockSand.fallInstantly = false;
	}

	public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
		return true;
	}

	public void saveExtraData() {
	}

	public boolean unloadQueuedChunks() {
		return false;
	}

	public boolean canSave() {
		return true;
	}

	public String makeString() {
		return "RandomLevelSource";
	}

	public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(par2, par4);
		return biomegenbase.getSpawnableList(par1EnumCreatureType);
	}

	public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5) {
		return null;
	}

	public int getLoadedChunkCount() {
		return 0;
	}

	public void recreateStructures(int par1, int par2) {
	}
}