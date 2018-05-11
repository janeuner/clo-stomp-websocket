package us.neuner.clo.client;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Toolkit;

public class CLOGameClient extends JFrame implements MouseListener, ActionListener, MouseMotionListener {

	/**
	 * serialization class version number
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html">java.io.Serializable</a>
	 */
	private static final long serialVersionUID = 1L;
	
	
	private GameBoardView gameBoardView;
	private PlayerView playerView;
	private SuggestView suggestView;
	private PlayerDetail player1; // All players in the game.
	private PlayerInfo playerInfo1;
	private ChatView chatView;

	private GameEntityGraphic[] envelope; // Array containing the mystery answer.
	private ArrayList<GameEntityGraphic> weapons;
	private ArrayList<GameEntityGraphic> rooms;
	private ArrayList<GameEntityGraphic> suspects;
	
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();

	public void buildDeck() { // initializes and shuffles the playing cards.
		weapons = GameEntityGraphic.getWeapons();
		rooms = GameEntityGraphic.getRooms();
		suspects = GameEntityGraphic.getSuspects();

		Collections.shuffle(weapons);
		Collections.shuffle(rooms);
		Collections.shuffle(suspects);
	}

	public void createEnvelope() {
		envelope = new GameEntityGraphic[3];
		envelope[0] = drawWeapon();
		envelope[1] = drawRoom();
		envelope[2] = drawSuspect();

	}

	public GameEntityGraphic drawWeapon() {

		try {
			GameEntityGraphic drawn = weapons.get(0); // Shallow copy of Card.
			weapons.remove(0); // Remove Card from container.
			return drawn; // Return Card.
		}
		// Catch if ArrayList is Empty (all cards have been drawn).
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public GameEntityGraphic drawSuspect() {
		try {
			GameEntityGraphic drawn = suspects.get(0); // Shallow copy of Card.
			suspects.remove(0); // Remove Card from container.
			return drawn; // Return Card.
		}
		// Catch is ArrayList is Empty (all cards have been drawn).
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public GameEntityGraphic drawRoom() {
		try {
			GameEntityGraphic drawn = rooms.get(0); // Shallow copy of Card.
			rooms.remove(0); // Remove Card from container.
			return drawn; // Return Card.
		}
		// Catch if ArrayList is Empty (all cards have been drawn).
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<GameEntityGraphic> dealHand() {
		ArrayList<GameEntityGraphic> hand = new ArrayList<GameEntityGraphic>();
		for (int i = 1; i <= 6; i++) {
			hand.add(drawCard());
		}
		return hand;
	}

	public boolean allEmpty() {
		return (weapons.isEmpty() && rooms.isEmpty() && suspects.isEmpty());
	}

	public GameEntityGraphic drawCard() {

		GameEntityGraphic toReturn = null;

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
		this.playerInfo1 = player;
		buildDeck();

		createEnvelope();

		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 1));
		setBackground(Color.BLACK);

		player1 = new PlayerDetail(0, dealHand(), playerInfo1);

		suggestView = new SuggestView(player1);
		gameBoardView = new GameBoardView(player1);
		playerView = new PlayerView(player1);
		chatView = new ChatView(playerInfo1);
		
		p1.setLayout(new BorderLayout());
		p1.add(suggestView, BorderLayout.NORTH);
		p1.add(chatView, BorderLayout.SOUTH);
		
		p2.add(gameBoardView);
		p2.add(p1);
		p2.setLayout(new FlowLayout());
		
		add(p2);
		add(playerView);

		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/img/ClientGUI/Board Resized.jpeg")));
		setSize(900, 1000);
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
			PlayerInfo p = new PlayerInfo("null", GameEntityGraphic.SCARLET);
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
