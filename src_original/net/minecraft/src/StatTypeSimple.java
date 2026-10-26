package net.minecraft.src;

class StatTypeSimple implements IStatType {
	public String format(int var1) {
		return StatBase.getNumberFormat().format((long)var1);
	}
}
