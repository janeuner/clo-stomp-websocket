package us.neuner.clo.client;

import java.awt.Point;

import javax.swing.JLabel;

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
	 
	 public void gamePieceMovement(int movement, GameEntityGraphic destination){
		 this.destination = destination;
		 if (isInRoom()){
			 setDistance();
		 }
		 if (movement>=stepsToLocation){
			 gamePiece.setLocation((destination.getPosition().x) , destination.getPosition().y);
			 gamePiece.repaint();
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
		 GameEntityGraphic nearest = GameEntityGraphic.HALL_A;
		 
		 double nearestValue = location.getPosition().distance(nearest.getPosition())
					+ destination.getPosition().distance(nearest.getPosition());

			//Check all passageways for closest.
			for(GameEntityGraphic pass : GameEntityGraphic.values()) {
				double nextValue = location.getPosition().distance(pass.getPosition())
						+ destination.getPosition().distance(pass.getPosition());

				if (nextValue < nearestValue) {
					nearest = pass;
					nearestValue = nextValue;
				}
			}
			Point hallwayPoint = nearest.getPosition();
			gamePiece.setLocation((hallwayPoint.x), hallwayPoint.y);
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
