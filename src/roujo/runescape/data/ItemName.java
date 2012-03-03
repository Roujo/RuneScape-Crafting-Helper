package roujo.runescape.data;

public enum ItemName {

	// Herblore materials
	Coconut(5976L),
	
	// Bars
	Steel_bar(2353L, true),
	
	// Ores
	Coal(453L),
	Iron_ore(440L)
	;
	
	private final long id;
	private final boolean isCraftable;
	
	private ItemName(Long id) {
		this(id, false);
	}
	
	private ItemName(Long id, boolean isCraftable) {
		this.id = id;
		this.isCraftable = isCraftable;
	}
	
	public long getId() {
		return id;
	}
	
	public boolean isCraftable() {
		return isCraftable;
	}
}
