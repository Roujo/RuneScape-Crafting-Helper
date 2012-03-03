package roujo.runescape.data;

public enum ItemName {

	// Herblore materials
	Coconut(5976L, false),
	
	// Bars
	Steel_bar(2353L, true),
	
	// Ores
	Coal(453L, false),
	Iron_ore(440L, false)
	;
	
	public final long id;
	public final boolean isCraftable;
	
	private ItemName(Long id, boolean isCraftable) {
		this.id = id;
		this.isCraftable = isCraftable;
	}
}
