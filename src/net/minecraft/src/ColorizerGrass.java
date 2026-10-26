package net.minecraft.src;

public class ColorizerGrass {
	private static int[] grassBuffer = new int[65536];

	public static void setGrassBiomeColorizer(int[] var0) {
		grassBuffer = var0;
	}

	public static int getGrassColor(double var0, double var2) {
		return 0xFF80FF00;
	}
}
