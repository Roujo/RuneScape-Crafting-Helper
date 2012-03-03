package roujo.runescape.data;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Item {
	private String type, name, description;
	private URL iconURL, icon_largeURL;//, typeIconURL;
	private Image icon, icon_large;//, typeIcon;
	private boolean members;
	private long id, price;
	
	// TODO: Add trend management

	protected Item() {
	}
	
	public String getType() {
		return type;
	}

	protected void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

	public Image getIcon() {
		return icon;
	}

	protected void setIcon(String iconURL) throws IOException {
		this.iconURL = new URL(iconURL);
		icon = ImageIO.read(this.iconURL);
	}

	public Image getIcon_large() {
		return icon_large;
	}

	protected void setIcon_large(String icon_largeURL) throws IOException {
		this.icon_largeURL = new URL(icon_largeURL);
		icon_large = ImageIO.read(this.icon_largeURL);
	}

	/*public Image getTypeIcon() {
		return typeIcon;
	}

	protected void setTypeIcon(String typeIconURL) throws IOException {
		this.typeIconURL = new URL(typeIconURL);
		typeIcon = ImageIO.read(this.typeIconURL);
	}*/

	public boolean isMembers() {
		return members;
	}

	protected void setMembers(boolean members) {
		this.members = members;
	}

	public long getId() {
		return id;
	}

	protected void setId(long id) {
		this.id = id;
	}

	public long getPrice() {
		return price;
	}

	protected void setPrice(long price) {
		this.price = price;
	}

	public String toString() {
		return getName() + " (" + getType() + "): " + getPrice();
	}
}