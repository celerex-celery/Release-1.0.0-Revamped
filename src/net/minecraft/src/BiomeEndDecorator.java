package net.minecraft.src;

public class BiomeEndDecorator extends BiomeDecorator {
	protected WorldGenerator spikeGen = new WorldGenSpikes(Block.whiteStone.blockID);

	public BiomeEndDecorator(BiomeGenBase var1) {
		super(var1);
	}

	protected void decorate_do() {
		this.generateOres();
		if(this.decoRNG.nextInt(5) == 0) {
			int var1 = this.chunk_X + this.decoRNG.nextInt(16) + 8;
			int var2 = this.chunk_Z + this.decoRNG.nextInt(16) + 8;
			int var3 = this.currentWorld.getTopSolidOrLiquidBlock(var1, var2);
			if(var3 > 0) {
			}

			this.spikeGen.generate(this.currentWorld, this.decoRNG, var1, var3, var2);
		}

		if(this.chunk_X == 0 && this.chunk_Z == 0) {
			EntityDragon var4 = new EntityDragon(this.currentWorld);
			var4.setLocationAndAngles(0.0D, 128.0D, 0.0D, this.decoRNG.nextFloat() * 360.0F, 0.0F);
			this.currentWorld.entityJoinedWorld(var4);
		}

	}
}
