package net.minecraft.src;

class EnumOptionsMappingHelper {
	static final int[] enumOptionsMappingHelperArray = new int[EnumOptions.values().length];

	static {
		try {
			enumOptionsMappingHelperArray[EnumOptions.INVERT_MOUSE.ordinal()] = 1;
		} catch (NoSuchFieldError var10) {
		}

		try {
			enumOptionsMappingHelperArray[EnumOptions.VIEW_BOBBING.ordinal()] = 2;
		} catch (NoSuchFieldError var9) {
		}

		try {
			enumOptionsMappingHelperArray[EnumOptions.ANAGLYPH.ordinal()] = 3;
		} catch (NoSuchFieldError var8) {
		}

		try {
			enumOptionsMappingHelperArray[EnumOptions.ADVANCED_OPENGL.ordinal()] = 4;
		} catch (NoSuchFieldError var7) {
		}

		try {
			enumOptionsMappingHelperArray[EnumOptions.AMBIENT_OCCLUSION.ordinal()] = 5;
		} catch (NoSuchFieldError var6) {
		}

		try {
			enumOptionsMappingHelperArray[EnumOptions.RENDER_CLOUDS.ordinal()] = 6;
		} catch (NoSuchFieldError var5) {
		}

		try {
			enumOptionsMappingHelperArray[EnumOptions.VSYNC.ordinal()] = 7;
		} catch (NoSuchFieldError var4) {
		}

		try {
			enumOptionsMappingHelperArray[EnumOptions.SPRINT_TOGGLE.ordinal()] = 8;
		} catch (NoSuchFieldError var3) {
		}

		try {
			enumOptionsMappingHelperArray[EnumOptions.CONTROLLER_ENABLED.ordinal()] = 9;
		} catch (NoSuchFieldError var2) {
		}

		try {
			enumOptionsMappingHelperArray[EnumOptions.CONTROLLER_INVERT_Y.ordinal()] = 10;
		} catch (NoSuchFieldError var1) {
		}

	}
}
