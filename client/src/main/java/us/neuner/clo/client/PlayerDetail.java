package us.neuner.clo.client;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PlayerDetail {
	private GameEntityGraphic playerIcon;
	private PlayerInfo player;
	private ArrayList<GameEntityGraphic> hand = new ArrayList<GameEntityGraphic>();
	private Movement movement;
	private JLabel gamePiece;
	private int playerNum;
	
	public PlayerDetail(int num, ArrayList<GameEntityGraphic> hand, PlayerInfo player){
		this.player = player;
		playerIcon = player.getCard();
		this.gamePiece = new JLabel();
		this.gamePiece.setIcon(player.getImage());
		this.hand = hand;
		movement = new Movement(player.getRoom(), this.gamePiece, playerNum); 
		
	}
	public Movement getMovement(){return movement;}
	public int getPlayerNum(){return playerNum;}
	public ArrayList<GameEntityGraphic> getHand(){return this.hand;}
	public GameEntityGraphic getPlayerIcon(){return playerIcon;}
	public String getPlayerName(){return player.getPlayerName();}
	public ImageIcon getPlayerImage(){return playerIcon.getImage();}
	public JLabel getGamePiece(){return this.gamePiece;}
	
	
	public class Movement {
		 private GameEntityGraphic destination;
		 private GameEntityGraphic location;
		 private int stepsToLocation;
		 private int playerNum;
		 private JLabel gamePiece;
		 
		
		 public Movement(GameEntityGraphic start, JLabel gamePiece, int playerNum){
			 this.gamePiece = gamePiece;
			 this.destination = null;
			 this.location = start; 
			 this.playerNum = playerNum;
			 stepsToLocation = 0;
		 }
		
		 public GameEntityGraphic getDestination(){return destination;}
		 public GameEntityGraphic getLocation(){return location;}
		 public int getXPos(){return location.getXPos();}
		 public int getYPos(){return location.getYPos();}
		 
		 
		 
		 public void setDestination(GameEntityGraphic destination){this.destination = destination;}
		 public void setLocation(GameEntityGraphic location){this.location = location;}
		 
		 public void setDistance(){
			 if (destination.getPosition().distance(location.getPosition())/20 >=12){
				 stepsToLocation = 7;
			 }else{
				 stepsToLocation = 3;
			 }
		 }
		 
		 public void gamePieceMovement(int movement, GameEntityGraphic roomClicked){
			 this.destination = roomClicked;
			 if (isInRoom()){
				 setDistance();
			 }
			 if (movement>=stepsToLocation){
				 gamePiece.setLocation((destination.getPosition().x) , destination.getPosition().y);
					location = destination;
					destination = null;
					stepsToLocation = 0;
			 }else{
				 if(isInRoom()) {
						moveToHallway();
					}
				
					location = null;
					stepsToLocation -= movement;
				}
			 }
		 
		 public void moveToHallway(){
			 GameEntityGraphic nearest = GameEntityGraphic.HALL_A;
			 
			 double nearestValue = location.getPosition().distance(nearest.getPosition())
						+ destination.getPosition().distance(nearest.getPosition());

				
				for(GameEntityGraphic pass : GameEntityGraphic.getHalls()) {
					double nextValue = location.getPosition().distance(pass.getPosition())
							+ destination.getPosition().distance(pass.getPosition());

					if (nextValue < nearestValue) {
						nearest = pass;
						nearestValue = nextValue;
					}
				}
				Point hallwayPoint = nearest.getPosition();
				gamePiece.setLocation((hallwayPoint.x), hallwayPoint.y);
				gamePiece.repaint();
		 }
		 
		 public boolean isInRoom(){
			 return location!=null;
		 }
		 public GameEntityGraphic getEqRoom(){
			 for (GameEntityGraphic card: GameEntityGraphic.values()){
				 if (card.getName().equals(location.getName())){
					 return card;
				 }
			 }
			 return null;
		 }
		
	}


	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}


	

}
