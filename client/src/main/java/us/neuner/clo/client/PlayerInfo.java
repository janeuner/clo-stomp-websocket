package us.neuner.clo.client;

import javax.swing.ImageIcon;

public class PlayerInfo {
private String playerName;
private GameEntityID pieceName;
private ImageIcon image;
private GameEntityID card;
private GameEntityID room;
private boolean active;
private boolean hasAccused;

public PlayerInfo (String name, GameEntityID piece){
	this.playerName = name;
	this.pieceName = piece;
	this.card = piece;
	
	switch (piece.getName()){
		case "Miss Scarlet":
			this.image = new ImageIcon("C:\\CLO\\Gamepieces\\Scarlet Piece.png");
			this.room = GameEntityID.BALLROOM;
		case "Mr Green":
			this.image = new ImageIcon("C:\\CLO\\Gamepieces\\Green Piece.png");
			this.room = GameEntityID.BILLIARD;
		case "Prof Plum":
			this.image = new ImageIcon("C:\\CLO\\Gamepieces\\Plum Piece.png");
			this.room = GameEntityID.STUDY;
		case "Mrs Peacock":
			this.image = new ImageIcon("C:\\CLO\\Gamepieces\\Peacock Piece.png");	
			this.room = GameEntityID.CONSERVATORY;
		case "Col Mustard":
			this.image = new ImageIcon("C:\\CLO\\Gamepieces\\Mustard Piece.png");
			this.room = GameEntityID.LOUNGE;
		case "Mrs White":
			this.image = new ImageIcon("C:\\CLO\\Gamepieces\\White Piece.png");
			this.room = GameEntityID.KITCHEN;
	}
	
}

public GameEntityID getPiece(){return pieceName;}
public GameEntityID getCard(){return card;}
public GameEntityID getRoom(){return room;}
public String getPlayerName(){return playerName;}
public void setActive(){active = true;}
public boolean getActive(){return active;}
public void setAccused(){hasAccused = true;}
public boolean getAccused(){return hasAccused;}
public ImageIcon getImage(){return image;}

}
