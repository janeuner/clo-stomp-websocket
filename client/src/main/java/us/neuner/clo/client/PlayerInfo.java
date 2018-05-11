package us.neuner.clo.client;

import java.net.URL;

import javax.swing.ImageIcon;

public class PlayerInfo {
private String playerName;
private GameEntityGraphic pieceName;
private URL imageUrl;
private ImageIcon image;
private GameEntityGraphic card;
private GameEntityGraphic room;
private boolean active;
private boolean hasAccused;

public PlayerInfo (String name, GameEntityGraphic piece){
	this.playerName = name;
	this.pieceName = piece;
	this.card = piece;
	
	switch (piece.getName()){
		case "Miss Scarlet":
			this.imageUrl = this.getClass().getResource("/img/Gamepieces/Scarlet Piece.png");
			this.image = new ImageIcon(this.imageUrl);
			this.room = GameEntityGraphic.BALLROOM;
			break;
		case "Mr Green":
			this.imageUrl = this.getClass().getResource("/img/Gamepieces/Green Piece.png");
			this.image = new ImageIcon(this.imageUrl);
			this.room = GameEntityGraphic.BILLIARD;
			break;
		case "Prof Plum":
			this.imageUrl = this.getClass().getResource("/img/Gamepieces/Plum Piece.png");
			this.image = new ImageIcon(this.imageUrl);
			this.room = GameEntityGraphic.STUDY;
			break;
		case "Mrs Peacock":
			this.imageUrl = this.getClass().getResource("/img/Gamepieces/Peacock Piece.png");
			this.image = new ImageIcon(this.imageUrl);	
			this.room = GameEntityGraphic.CONSERVATORY;
			break;
		case "Col Mustard":
			this.imageUrl = this.getClass().getResource("/img/Gamepieces/Mustard Piece.png");
			this.image = new ImageIcon(this.imageUrl);
			this.room = GameEntityGraphic.LOUNGE;
			break;
		case "Mrs White":
			this.imageUrl = this.getClass().getResource("/img/Gamepieces/White Piece.png");
			this.image = new ImageIcon(this.imageUrl);
			this.room = GameEntityGraphic.KITCHEN;
			break;
	}
	
}

	public GameEntityGraphic getPiece() {
		return pieceName;
	}
	
	public GameEntityGraphic getCard() {
		return card;
	}
	
	public GameEntityGraphic getRoom() {
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
