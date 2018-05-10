package us.neuner.clo.client;

import java.awt.Point;

import javax.swing.JLabel;

public class Movement {
	 private GameEntityID destination;
	 private GameEntityID location;
	 private int stepsToLocation;
	 private int playerNum;
	 private JLabel gamePiece;
	 
	
	 public Movement(GameEntityID start, JLabel gamePiece, int playerNum){
		 this.gamePiece = gamePiece;
		 this.destination = null;
		 this.location = start; 
		 this.playerNum = playerNum;
		 stepsToLocation = 0;
	 }
	
	 public GameEntityID getDestination(){return destination;}
	 public GameEntityID getLocation(){return location;}
	 public int getXPos(){return location.getXPos() + 23*playerNum;}
	 public int getYPos(){return location.getYPos();}
	 
	 
	 public void setDestination(GameEntityID destination){this.destination = destination;}
	 public void setLocation(GameEntityID location){this.location = location;}
	 
	 public void setDistance(){
		 if (destination.getPosition().distance(location.getPosition())/20 >=12){
			 stepsToLocation = 7;
		 }else{
			 stepsToLocation = 3;
		 }
	 }
	 
	 public void gamePieceMovement(int movement){
		 if (isInRoom()){
			 setDistance();
		 }
		 if (movement>=stepsToLocation){
			 gamePiece.setLocation((destination.getPosition().x + 23*playerNum) , destination.getPosition().y);
				location = destination;
				destination = null;
				stepsToLocation = 0;
		 }else{
			 if(isInRoom()) {
					moveToHallway();
				}
				//Readjust location and # of steps.
				location = null;
				stepsToLocation -= movement;
			}
		 }
	 
	 public void moveToHallway(){
		 GameEntityID nearest = GameEntityID.HALL_A;
		 
		 double nearestValue = location.getPosition().distance(nearest.getPosition())
					+ destination.getPosition().distance(nearest.getPosition());

			//Check all passageways for closest.
			for(GameEntityID pass : GameEntityID.values()) {
				double nextValue = location.getPosition().distance(pass.getPosition())
						+ destination.getPosition().distance(pass.getPosition());

				if (nextValue < nearestValue) {
					nearest = pass;
					nearestValue = nextValue;
				}
			}
			Point hallwayPoint = nearest.getPosition();
			gamePiece.setLocation((hallwayPoint.x + 23 * playerNum), hallwayPoint.y);
	 }
	 
	 public boolean isInRoom(){
		 return location!=null;
	 }
	 public GameEntityID getEqRoom(){
		 for (GameEntityID card: GameEntityID.values()){
			 if (card.getName().equals(location.getName())){
				 return card;
			 }
		 }
		 return null;
	 }
	
}
