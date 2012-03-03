package roujo.runescape.data;


public enum ItemName {
	
	// Herblore materials
	Coconut(5976L),
	
	// Ores
	Coal(453L),
	Iron_ore(440L),
	Mithril_ore(447L),
	Adamantite_ore(449L),
	
	// Bars
	Steel_bar(2353L, new Component[]{new Component(Coal, 2), new Component(Iron_ore, 1)}),
	Mithril_bar(2359L, new Component[]{new Component(Coal, 4), new Component(Mithril_ore, 1)})
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
