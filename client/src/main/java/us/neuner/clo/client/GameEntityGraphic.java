package us.neuner.clo.client;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public enum GameEntityGraphic {
	STUDY(new Area(new Rectangle(40,160,160,90)), 55, 75, "Study"),
	LIBRARY(new Area(new Rectangle(40,280,160,100)), 55, 215, "Library"),
	BILLIARD(new Area(new Rectangle(40,400,140,105)), 55, 340, "Billiard Room"),
    CONSERVATORY(new Area(new Rectangle(40,550,140,110)), 55, 490, "Conservatory"),
    HALL(new Area(new Rectangle(250,160,130,135)), 255, 125, "Hall"),
    BALLROOM(new Area(new Rectangle(230,500,175,125)), 255, 470, "Ballroom"),
    LOUNGE(new Area(new Rectangle(430,160,155,125)), 460, 105, "Lounge"),
    DININGROOM(new Area(new Rectangle(405,350,185,145)), 460, 300, "Dining Room"),
    KITCHEN(new Area(new Rectangle(450,520,140,140)), 460, 490, "Kitchen"),
	    
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
    
	private String name;
	private int type;
	private URL imageUrl;
	private ImageIcon image;
	private Area boundary;
	private int xposition;
	private int yposition;
	private Point position;
	
	GameEntityGraphic(String name, Area boundary, int xposition, int yposition) {
		this.boundary = boundary;
		this.xposition = xposition;
		this.yposition = yposition;
		this.name = name;
		this.imageUrl = this.getClass().getResource("/img/Gamepieces/" + name + ".png");
		this.image = new ImageIcon(this.imageUrl);
		this.type = 0;
	}
		
	GameEntityGraphic(String name, int type){
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
	
	GameEntityGraphic(Area boundary, int xposition, int yposition, String name) {
		this.boundary = boundary;
		this.xposition = xposition;
		this.yposition = yposition;
		position = new Point(xposition, yposition);
		this.name = name;
	}
	
	GameEntityGraphic(int xposition, int yposition){
		this.position = new Point(xposition, yposition);
	}
	
	public static ArrayList<GameEntityGraphic> getSuspectCards() {
		ArrayList<GameEntityGraphic> suspects; 
		suspects = new ArrayList<GameEntityGraphic>();
		suspects.add(SCARLET);
		suspects.add(GREEN);
		suspects.add(PLUM);
		suspects.add(PEACOCK);
		suspects.add(MUSTARD);
		suspects.add(WHITE);
		return suspects;
	}

	public static ArrayList<GameEntityGraphic> getRoomCards(){
		ArrayList<GameEntityGraphic> roomCards;
		roomCards = new ArrayList<GameEntityGraphic>();
		roomCards.add(Study);
		roomCards.add(Library);
		roomCards.add(Billiard);
		roomCards.add(Conservatory);
		roomCards.add(Hall);
		roomCards.add(Ballroom);
		roomCards.add(Lounge);
		roomCards.add(DiningRoom);
		roomCards.add(Kitchen);
		return roomCards;
	}
	
	public static ArrayList<GameEntityGraphic> getWeaponCards(){
		ArrayList<GameEntityGraphic> weapons;
		weapons = new ArrayList<GameEntityGraphic>();
		weapons.add(KNIFE);
		weapons.add(ROPE);
		weapons.add(CANDLESTICK);
		weapons.add(REVOLVER);
		weapons.add(PIPE);
		weapons.add(WRENCH);
		
		return weapons;
	}
	
	public static ArrayList<GameEntityGraphic> getHalls(){
		ArrayList<GameEntityGraphic> Halls;
		Halls = new ArrayList<GameEntityGraphic>();
		Halls.add(HALL_A);
		Halls.add(HALL_B);
		Halls.add(HALL_C);
		Halls.add(HALL_D);
		Halls.add(HALL_E);
		Halls.add(HALL_F);
		Halls.add(HALL_G);
		return Halls;
		
	}

	public static ArrayList<GameEntityGraphic> getCards(){
		ArrayList<GameEntityGraphic> cards;
		cards = new ArrayList<GameEntityGraphic>();
		
		//weapons
		cards.add(KNIFE);
		cards.add(ROPE);
		cards.add(CANDLESTICK);
		cards.add(REVOLVER);
		cards.add(PIPE);
		cards.add(WRENCH);
		
		//rooms
		cards.add(Study);
		cards.add(Library);
		cards.add(Billiard);
		cards.add(Conservatory);
		cards.add(Hall);
		cards.add(Ballroom);
		cards.add(Lounge);
		cards.add(DiningRoom);
		cards.add(Kitchen);
		
		//suspects
		cards.add(SCARLET);
		cards.add(GREEN);
		cards.add(PLUM);
		cards.add(PEACOCK);
		cards.add(MUSTARD);
		cards.add(WHITE);
		
		return cards;
	}
	
	public static ArrayList<GameEntityGraphic> getRooms(){
		ArrayList<GameEntityGraphic> rooms;
		rooms = new ArrayList<GameEntityGraphic>();
		rooms.add(STUDY);
		rooms.add(LIBRARY);
		rooms.add(BILLIARD);
		rooms.add(CONSERVATORY);
		rooms.add(HALL);
		rooms.add(BALLROOM);
		rooms.add(LOUNGE);
		rooms.add(DININGROOM);
		rooms.add(KITCHEN);
		return rooms;
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
