package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class MapGenStronghold extends MapGenStructure {
	private BiomeGenBase[] allowedBiomeGenBases = new BiomeGenBase[]{BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.hills, BiomeGenBase.swampland, BiomeGenBase.taiga, BiomeGenBase.icePlains, BiomeGenBase.iceMountains};
	private boolean ranBiomeCheck;
	private ChunkCoordIntPair[] structureCoords = new ChunkCoordIntPair[3];

	protected boolean canSpawnStructureAtCoords(int var1, int var2) {
		int var5;
		if(!this.ranBiomeCheck) {
			this.rand.setSeed(this.worldObj.getWorldSeed());
			double var3 = this.rand.nextDouble() * Math.PI * 2.0D;

			for(var5 = 0; var5 < this.structureCoords.length; ++var5) {
				double var6 = (1.25D + this.rand.nextDouble()) * 32.0D;
				int var8 = (int)Math.round(Math.cos(var3) * var6);
				int var9 = (int)Math.round(Math.sin(var3) * var6);
				ArrayList var10 = new ArrayList();
				BiomeGenBase[] var11 = this.allowedBiomeGenBases;
				int var12 = var11.length;

				for(int var13 = 0; var13 < var12; ++var13) {
					BiomeGenBase var14 = var11[var13];
					var10.add(var14);
				}

				ChunkPosition var17 = this.worldObj.getWorldChunkManager().func_35556_a((var8 << 4) + 8, (var9 << 4) + 8, 112, var10, this.rand);
				if(var17 != null) {
					var8 = var17.x >> 4;
					var9 = var17.z >> 4;
				} else {
					System.out.println("Placed stronghold in INVALID biome at (" + var8 + ", " + var9 + ")");
				}

				this.structureCoords[var5] = new ChunkCoordIntPair(var8, var9);
				var3 += Math.PI * 2.0D / (double)this.structureCoords.length;
			}

			this.ranBiomeCheck = true;
		}

		ChunkCoordIntPair[] var15 = this.structureCoords;
		int var4 = var15.length;

		for(var5 = 0; var5 < var4; ++var5) {
			ChunkCoordIntPair var16 = var15[var5];
			if(var1 == var16.chunkXPos && var2 == var16.chunkZPos) {
				return true;
			}
		}

		return false;
	}

	protected List func_40482_a() {
		ArrayList var1 = new ArrayList();
		ChunkCoordIntPair[] var2 = this.structureCoords;
		int var3 = var2.length;

		for(int var4 = 0; var4 < var3; ++var4) {
			ChunkCoordIntPair var5 = var2[var4];
			if(var5 != null) {
				var1.add(var5.func_40737_a(64));
			}
		}

		return var1;
	}

	protected StructureStart getStructureStart(int var1, int var2) {
		StructureStrongholdStart var3;
		for(var3 = new StructureStrongholdStart(this.worldObj, this.rand, var1, var2); var3.func_40560_b().isEmpty() || ((ComponentStrongholdStairs2)var3.func_40560_b().get(0)).field_40009_b == null; var3 = new StructureStrongholdStart(this.worldObj, this.rand, var1, var2)) {
		}

		return var3;
	}
}
