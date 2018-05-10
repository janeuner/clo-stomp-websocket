package us.neuner.clo.client;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public enum GameEntityID {
	STUDY(new Area(new Rectangle(40,50,160,90)), 55, 75, "Study"),
	LIBRARY(new Area(new Rectangle(40,180,160,100)), 55, 215, "Library"),
	BILLIARD(new Area(new Rectangle(40,300,140,105)), 55, 340, "Billiard Room"),
    CONSERVATORY(new Area(new Rectangle(40,455,140,110)), 55, 490, "Conservatory"),
    HALL(new Area(new Rectangle(250,60,130,135)), 255, 125, "Hall"),
    BALLROOM(new Area(new Rectangle(230,415,175,125)), 255, 470, "Ballroom"),
    LOUNGE(new Area(new Rectangle(430,50,155,125)), 460, 105, "Lounge"),
    DININGROOM(new Area(new Rectangle(405,240,185,145)), 460, 300, "Dining Room"),
    KITCHEN(new Area(new Rectangle(450,435,140,140)), 460, 490, "Kitchen"),
	    
    HALL_A(167,127), 
    HALL_B(234,168), 
    HALL_C(166,295), 
    HALL_D(234,360),
    HALL_E(144,402), 
    HALL_F(368, 189), 
    HALL_G(368, 359),

    KNIFE("Knife", 1), 
    ROPE("Rope", 1), 
    CANDLESTICK("Candlestick", 1),
    REVOLVER("Revolver", 1), 
    PIPE("Lead Pipe", 1), 
    WRENCH("Wrench", 1),

    Kitchen("Kitchen", 2), 
    Ballroom("Ballroom", 2), 
    Conservatory("Conservatory", 2),
    Billiard("Billiard Room", 2), 
    Library("Library", 2), 
    Study("Study", 2),
    Hall("Hall", 2), 
    Lounge("Lounge", 2), 
    DiningRoom("Dining Room", 2),
    
    
	SCARLET("Miss Scarlet", 3), 
	GREEN("Mr Green", 3), 
	PLUM("Prof Plum", 3),
    PEACOCK("Mrs Peacock", 3), 
    MUSTARD("Col Mustard", 3), 
    WHITE("Mrs White", 3),
    
    ScarletStart("Scarlet Piece", new Area(new Rectangle(230,415,175,125)), 255, 470),
    ColMustardStart("Mustard Piece", new Area(new Rectangle(430,50,155,125)), 460, 105),
    MrsWhiteStart("White Piece", new Area(new Rectangle(450,435,140,140)), 460, 490),
    MrGreenStart("Green Piece", new Area(new Rectangle(40,300,140,105)), 55, 340),
    MrsPeacockStart("Peacock Piece", new Area(new Rectangle(40,455,140,110)), 55, 490),
	ProfPlumStart("Plum Piece", new Area(new Rectangle(40,50,160,90)), 55, 75);
    
    ;
	
	private String name;
	private static ArrayList<GameEntityID> suspects; 
	private static ArrayList<GameEntityID> rooms;
	private static ArrayList<GameEntityID> weapons;
	private static ArrayList<GameEntityID> cards;
	
	
	private int type;
	private URL imageUrl;
	private ImageIcon image;
	private Area boundary;
	private int xposition;
	private int yposition;
	private Point position;
	
	GameEntityID(String name, Area boundary, int xposition, int yposition) {
		this.boundary = boundary;
		this.xposition = xposition;
		this.yposition = yposition;
		this.name = name;
		this.imageUrl = this.getClass().getResource("/img/Gamepieces/" + name + ".png");
		this.image = new ImageIcon(this.imageUrl);
	}
		
	GameEntityID(String name, int type){
		this.name = name;
		this.type = type;
		switch (type){
			case 1:     //Weapon
	    		this.imageUrl = this.getClass().getResource("/img/Weapons/" + name + ".jpg");
	    		break;
	
			case 2:     //Location
	    		this.imageUrl = this.getClass().getResource("/img/Locations/" + name + ".jpg");
				break;
			
			case 3:     //Suspect
	    		this.imageUrl = this.getClass().getResource("/img/Suspects/" + name + ".jpg");
				break;
			
			default:
				assert(false);
		}
		image = new ImageIcon(this.imageUrl);
	}
	
	GameEntityID(Area boundary, int xposition, int yposition, String name) {
		this.boundary = boundary;
		this.xposition = xposition;
		this.yposition = yposition;
		position = new Point(xposition, yposition);
		this.name = name;
	}
	
	GameEntityID(int xposition, int yposition){
		this.position = new Point(xposition, yposition);
	}
	
	public static ArrayList<GameEntityID> getSuspects() {
		suspects = new ArrayList<GameEntityID>();
		suspects.add(SCARLET);
		suspects.add(GREEN);
		suspects.add(PLUM);
		suspects.add(PEACOCK);
		suspects.add(MUSTARD);
		suspects.add(WHITE);
		return suspects;
	
	}

	public static ArrayList<GameEntityID> getRooms(){
		rooms = new ArrayList<GameEntityID>();
		rooms.add(STUDY);
		rooms.add(LIBRARY);
		rooms.add(Billiard);
		rooms.add(CONSERVATORY);
		rooms.add(HALL);
		rooms.add(BALLROOM);
		rooms.add(LOUNGE);
		rooms.add(DININGROOM);
		rooms.add(KITCHEN);
		return rooms;
		
	}
	
	public static ArrayList<GameEntityID> getWeapons(){
		weapons = new ArrayList<GameEntityID>();
		weapons.add(KNIFE);
		weapons.add(ROPE);
		weapons.add(CANDLESTICK);
		weapons.add(REVOLVER);
		weapons.add(PIPE);
		weapons.add(WRENCH);
		
		return weapons;
	}

	public static ArrayList<GameEntityID> getCards(){
		cards = new ArrayList<GameEntityID>();
		
		//weapons
		cards.add(KNIFE);
		cards.add(ROPE);
		cards.add(CANDLESTICK);
		cards.add(REVOLVER);
		cards.add(PIPE);
		cards.add(WRENCH);
		
		//rooms
		cards.add(STUDY);
		cards.add(LIBRARY);
		cards.add(Billiard);
		cards.add(CONSERVATORY);
		cards.add(HALL);
		cards.add(BALLROOM);
		cards.add(LOUNGE);
		cards.add(DININGROOM);
		cards.add(KITCHEN);
		
		//suspects
		cards.add(SCARLET);
		cards.add(GREEN);
		cards.add(PLUM);
		cards.add(PEACOCK);
		cards.add(MUSTARD);
		cards.add(WHITE);
		
		return cards;
	}
	
	public Area getBoundaryBox() {
		return boundary;
	}
	
	public int getXPos() {
		return xposition; 
	}
	
	public int getYPos() {
		return yposition; 
	}
	
	public Point getPosition() { 
		return position; 
	}
	
	public String getName() {
		return name;
	}
	
	public ImageIcon getImage() { 
		return image; 
	}
	
	public int getType() { 
		return type; 
	}

	
}
