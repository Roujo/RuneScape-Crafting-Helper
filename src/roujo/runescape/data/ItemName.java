package roujo.runescape.data;


public enum ItemName {
	
	// Herblore materials
	Coconut(5976L),
	
	// Runes
	Nature_rune(561L),
	
	// Ores
	Copper_ore(436L),
	Tin_ore(438L),
	Coal(453L),
	Iron_ore(440L),
	Mithril_ore(447L),
	Adamantite_ore(449L),
	Runite_ore(451L),
	Silver_ore(442L),
	Gold_ore(444L),
	
	// Bars
	Bronze_bar(2349L, new Component[]{new Component(Copper_ore, 1), new Component(Tin_ore, 1)}),
	Iron_bar(2351L, new Component[]{new Component(Iron_ore, 1)}),
	Steel_bar(2353L, new Component[]{new Component(Coal, 2), new Component(Iron_ore, 1)}),
	Mithril_bar(2359L, new Component[]{new Component(Coal, 4), new Component(Mithril_ore, 1)}),
	Adamant_bar(2361L, new Component[]{new Component(Coal, 6), new Component(Adamantite_ore, 1)}),
	Rune_bar(2363L, new Component[]{new Component(Coal, 8), new Component(Runite_ore, 1)}),
	Silver_bar(2355L, new Component[]{new Component(Silver_ore, 1)}),
	Gold_bar(2355L, new Component[]{new Component(Gold_ore, 1)})
	;
	
	private final long id;
	private final Component[] components;
	
	private ItemName(Long id) {
		this(id, null);
	}
	
	private ItemName(Long id, Component[] components) {
		this.id = id;
		this.components = components;
	}
	
	public long getId() {
		return id;
	}
	
	public boolean isCraftable() {
		return components != null;
	}
	
	public Component[] getComponents() {
		return components;
	}
}
