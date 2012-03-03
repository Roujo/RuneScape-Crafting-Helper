package roujo.runescape.data;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Item {
	private String type, name, description;
	private URL iconURL, largeIconURL;//, typeIconURL;
	private Image icon, largeIcon;//, typeIcon;
	private boolean members;
	private long id, marketPrice;
	
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

	public Image getLargeIcon() {
		return largeIcon;
	}

	protected void setLargeIcon(String largeIconURL) throws IOException {
		this.largeIconURL = new URL(largeIconURL);
		largeIcon = ImageIO.read(this.largeIconURL);
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

	public long getMarketPrice() {
		return marketPrice;
	}

	protected void setMarketPrice(long price) {
		this.marketPrice = price;
	}

	public String toString() {
		return getName() + " (" + getType() + "): " + getMarketPrice();
	}
}