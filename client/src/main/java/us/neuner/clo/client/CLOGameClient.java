package us.neuner.clo.client;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Toolkit;

public class CLOGameClient extends JFrame implements MouseListener, ActionListener, MouseMotionListener {

	/**
	 * serialization class version number
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html">java.io.Serializable</a>
	 */
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 1200, HEIGHT = 1200;
	private GameBoardView gameBoardView;
	private PlayerView playerView;
	private SuggestView suggestView;
	private PlayerDetail player1; // All players in the game.

	private GameEntityID[] envelope; // Array containing the mystery answer.
	private ArrayList<GameEntityID> weapons;
	private ArrayList<GameEntityID> rooms;
	private ArrayList<GameEntityID> suspects;

	public void buildDeck() { // initializes and shuffles the playing cards.
		weapons = GameEntityID.getWeapons();
		rooms = GameEntityID.getRooms();
		suspects = GameEntityID.getSuspects();

		Collections.shuffle(weapons);
		Collections.shuffle(rooms);
		Collections.shuffle(suspects);
	}

	public void createEnvelope() {
		envelope = new GameEntityID[3];
		envelope[0] = drawWeapon();
		envelope[1] = drawRoom();
		envelope[2] = drawSuspect();

	}

	public GameEntityID drawWeapon() {

		try {
			GameEntityID drawn = weapons.get(0); // Shallow copy of Card.
			weapons.remove(0); // Remove Card from container.
			return drawn; // Return Card.
		}
		// Catch if ArrayList is Empty (all cards have been drawn).
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public GameEntityID drawSuspect() {
		try {
			GameEntityID drawn = suspects.get(0); // Shallow copy of Card.
			suspects.remove(0); // Remove Card from container.
			return drawn; // Return Card.
		}
		// Catch is ArrayList is Empty (all cards have been drawn).
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public GameEntityID drawRoom() {
		try {
			GameEntityID drawn = rooms.get(0); // Shallow copy of Card.
			rooms.remove(0); // Remove Card from container.
			return drawn; // Return Card.
		}
		// Catch if ArrayList is Empty (all cards have been drawn).
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<GameEntityID> dealHand() {
		ArrayList<GameEntityID> hand = new ArrayList<GameEntityID>();
		for (int i = 1; i <= 6; i++) {
			hand.add(drawCard());
		}
		return hand;
	}

	public boolean allEmpty() {
		return (weapons.isEmpty() && rooms.isEmpty() && suspects.isEmpty());
	}

	public GameEntityID drawCard() {

		GameEntityID toReturn = null;

		while (toReturn == null && !allEmpty()) {
			Random ran = new Random();
			int i = ran.nextInt(3) + 1;

			switch (i) {
			case 1:
				if (!weapons.isEmpty())
					toReturn = drawWeapon();
				break;
			case 2:
				if (!rooms.isEmpty())
					toReturn = drawRoom();
				break;

			case 3:
				if (!suspects.isEmpty())
					toReturn = drawSuspect();
				break;
			}
		}
		return toReturn;
	}

	public CLOGameClient(PlayerInfo player) {
		super("CLOGameClient");
		buildDeck();

		createEnvelope();

		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 1));
		setBackground(Color.BLACK);

		player1 = new PlayerDetail(0, dealHand(), player);

		suggestView = new SuggestView(player1);
		gameBoardView = new GameBoardView(player1);
		playerView = new PlayerView(player1);

		add(gameBoardView);
		add(suggestView);
		add(playerView);

		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/img/ClientGUI/Board Resized.jpeg")));
		setSize(800, 810);
		setResizable(false);
		setName("CLO GameClient");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public void setFrame(int width, int height) {
		this.setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) throws Exception {
		try {
			PlayerInfo p = new PlayerInfo("null", GameEntityID.SCARLET);
			new CLOGameClient(p);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
