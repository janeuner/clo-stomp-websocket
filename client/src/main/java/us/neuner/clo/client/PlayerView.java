package us.neuner.clo.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PlayerView extends JTabbedPane implements ChangeListener {

	/**
	 * serialization class version number
	 * 
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html">java.io.Serializable</a>
	 */
	private static final long serialVersionUID = 1L;

	private JLabel playerView;
	private JPanel handTab;
	private JPanel playerTab, playerArea;
	private JButton enterButton;

	private JButton[] hand;

	private final ImageIcon BACK_IMAGE = new ImageIcon(this.getClass().getResource("/img/ClientGUI/Cardback.jpg"));
	private final ImageIcon PAPER = new ImageIcon(this.getClass().getResource("/img/ClientGUI/Paper.jpg"));
	private final ImageIcon CARD_TAB_ICON = new ImageIcon(
			this.getClass().getResource("/img/ClientGUI/CardIcon Small.png"));
	private final ImageIcon CONSOLE_TAB_ICON = new ImageIcon(
			this.getClass().getResource("/img/ClientGUI/SpeechIcon Small.png"));

	private PlayerDetail player;

	// components for making a suggestion
	private JLabel suspectAssumption, weaponAssumption, roomAssumption;
	private JPanel suggestArea;
	private GameEntityID weaponAssumptionCard;
	private GameEntityID suspectAssumptionCard;
	private GameEntityID roomAssumptionCard;

	public PlayerView(PlayerDetail player) {
		this.player = player;

		setTabPlacement(LEFT);

		playerView = new JLabel();
		playerView.setIcon(PAPER);
		playerView.setHorizontalTextPosition(JLabel.CENTER);
		playerView.setVerticalTextPosition(JLabel.CENTER);

		enterButton = new JButton("Enter");

		playerArea = new JPanel(new BorderLayout());
		playerArea.add(playerView, BorderLayout.CENTER);
		playerArea.add(enterButton, BorderLayout.SOUTH);

		suggestArea = new JPanel(new FlowLayout(10, 1, 1));
		handTab = new JPanel(new FlowLayout(10, 1, 1));
		playerTab = new JPanel(new GridLayout(1, 2, 1, 1));

		suspectAssumption = new JLabel();
		suspectAssumption.setIcon(BACK_IMAGE);
		suggestArea.add(suspectAssumption);

		// Create and add weaponAssumption.
		weaponAssumption = new JLabel();
		weaponAssumption.setIcon(BACK_IMAGE);
		suggestArea.add(weaponAssumption);

		roomAssumption = new JLabel();
		roomAssumption.setIcon(BACK_IMAGE);
		suggestArea.add(roomAssumption);

		playerTab.add(playerArea);
		playerTab.add(suggestArea);

		hand = new JButton[6];
		for (int i = 0; i < hand.length; i++) {
			GameEntityID card = player.getHand().get(i);
			hand[i] = new JButton();
			hand[i].setIcon(card.getImage());
			handTab.add(hand[i]);
			hand[i].setVerticalAlignment(TOP);
		}

		addChangeListener(this);

		addTab("", CARD_TAB_ICON, handTab);
		addTab("", CONSOLE_TAB_ICON, playerTab);
		setBackgroundAt(0, Color.BLUE);
		setBackgroundAt(1, Color.RED);
		setTabLayoutPolicy(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

	}

	public JButton getEnterButton() {
		return enterButton;
	}

	public JButton[] getHand() {
		return hand;
	}

	public void setWeaponAssumption(GameEntityID assumption) {
		weaponAssumptionCard = assumption;
		weaponAssumption.setIcon(assumption.getImage());
	}

	public void setSuspectAssumption(GameEntityID assumption) {
		suspectAssumptionCard = assumption;
		suspectAssumption.setIcon(assumption.getImage());
	}

	public void setRoomAssumption(GameEntityID assumption) {
		roomAssumptionCard = assumption;
		roomAssumption.setIcon(assumption.getImage());
	}

	public GameEntityID getWeaponAssumption() {
		return weaponAssumptionCard;
	}

	public GameEntityID getSuspectAssumption() {
		return suspectAssumptionCard;
	}

	public GameEntityID getRoomAssumption() {
		return roomAssumptionCard;
	}

	public void changeConsoleMessage(String message) {
		setSelectedIndex(1);
		playerView.setText(message);
	}

	public void clearConsoleMessage() {
		playerView.setText("");
	}

	public boolean messageConfirmed() {
		weaponAssumption.setEnabled(true);
		suspectAssumption.setEnabled(true);
		roomAssumption.setEnabled(true);
		return playerView.getText().equals("");
	}

	public void resetAssumption() {
		suspectAssumption.setIcon(BACK_IMAGE);
		weaponAssumption.setIcon(BACK_IMAGE);
		roomAssumption.setIcon(BACK_IMAGE);
	}

	public void highlightDisprovables(ArrayList<GameEntityID> guess) {
		setSelectedIndex(0);

		for (int i = 0; i < player.getHand().size(); i++) {
			GameEntityID check = player.getHand().get(i);
			if (!guess.contains(check))
				hand[i].setEnabled(false);
		}
	}

	/**
	 * highlightDisproval disables all assumption cards other than the one chosen by
	 * other player.
	 * 
	 * @param card
	 *            Card used to disprove player's assumption.
	 */
	public void highlightDisproval(GameEntityID card) {
		if (card.getType() == 1) {
			suspectAssumption.setEnabled(false);
			roomAssumption.setEnabled(false);
		} else if (card.getType() == 2) {
			weaponAssumption.setEnabled(false);
			suspectAssumption.setEnabled(false);
		} else {
			weaponAssumption.setEnabled(false);
			roomAssumption.setEnabled(false);
		}
	}

	/** resetButtons re-enables all hand buttons after disproving is completed. */
	public void resetButtons() {
		for (int i = 0; i < hand.length; i++)
			hand[i].setEnabled(true);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

}
