package us.neuner.clo.client;

import java.net.URL;

import javax.swing.ImageIcon;

public class PlayerInfo {
private String playerName;
private GameEntityID pieceName;
private URL imageUrl;
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
			this.imageUrl = this.getClass().getResource("/img/Gamepieces/Scarlet Piece.png");
			this.image = new ImageIcon(this.imageUrl);
			this.room = GameEntityID.BALLROOM;
		case "Mr Green":
			this.imageUrl = this.getClass().getResource("/img/Gamepieces/Green Piece.png");
			this.image = new ImageIcon(this.imageUrl);
			this.room = GameEntityID.BILLIARD;
		case "Prof Plum":
			this.imageUrl = this.getClass().getResource("/img/Gamepieces/Plum Piece.png");
			this.image = new ImageIcon(this.imageUrl);
			this.room = GameEntityID.STUDY;
		case "Mrs Peacock":
			this.imageUrl = this.getClass().getResource("/img/Gamepieces/Peacock Piece.png");
			this.image = new ImageIcon(this.imageUrl);	
			this.room = GameEntityID.CONSERVATORY;
		case "Col Mustard":
			this.imageUrl = this.getClass().getResource("/img/Gamepieces/Mustard Piece.png");
			this.image = new ImageIcon(this.imageUrl);
			this.room = GameEntityID.LOUNGE;
		case "Mrs White":
			this.imageUrl = this.getClass().getResource("/img/Gamepieces/White Piece.png");
			this.image = new ImageIcon(this.imageUrl);
			this.room = GameEntityID.KITCHEN;
	}
	
}

	public GameEntityID getPiece() {
		return pieceName;
	}
	
	public GameEntityID getCard() {
		return card;
	}
	
	public GameEntityID getRoom() {
		return room;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setActive() {
		active = true;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public void setAccused() {
		hasAccused = true;
	}
	
	public boolean getAccused() {
		return hasAccused;
	}
	
	public ImageIcon getImage() {
		return image;
	}
	
}
