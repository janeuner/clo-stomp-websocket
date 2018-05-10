package us.neuner.clo.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AccuseView extends JFrame implements ActionListener {

	/**
	 * serialization class version number
	 * 
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html">java.io.Serializable</a>
	 */
	private static final long serialVersionUID = 1L;

	// Components for JFrame.
	private final JComboBox<String> personBox, weaponBox;
	private final JButton button;

	private GameEntityID[] guess; // Container for inputs chosen by player.

	public AccuseView(String title, ArrayList<GameEntityID> hand) {

		guess = new GameEntityID[2];
		JPanel panel = new JPanel();
		personBox = new JComboBox<String>();
		weaponBox = new JComboBox<String>();
		button = new JButton("Accuse");

		panel.add(personBox);
		panel.add(weaponBox);
		panel.add(button);

		for (GameEntityID card : GameEntityID.getCards()) {
			if (card.getType() == 1 && !hand.contains(card))
				weaponBox.addItem(card.getName());
			if (card.getType() == 3 && !hand.contains(card))
				personBox.addItem(card.getName());
		}

		for (GameEntityID card : GameEntityID.getCards()) {
			if (weaponBox.getItemAt(0).equals(card.getName()))
				guess[1] = card;
			if (personBox.getItemAt(0).equals(card.getName()))
				guess[0] = card;
		}

		add(panel);
		setTitle(title);
		setSize(280, 100);
		setLocation(200, 300);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(false);
		setResizable(false);

		// Add ActionListeners for combo boxes.
		personBox.addActionListener(this);
		weaponBox.addActionListener(this);
	}

	public GameEntityID getSuspectGuess() {
		return guess[0];
	}

	public GameEntityID getWeaponGuess() {
		return guess[1];
	}

	public JButton getButton() {
		return button;
	}

	/** Action Listener assigns values to guess based off of user input. */
	public void actionPerformed(ActionEvent e) {

		for (GameEntityID card : GameEntityID.getCards()) {

			// Suspect Guess Selected.
			if (card.getName().equals(personBox.getSelectedItem()))
				guess[0] = card;

			// Weapon Guess Selected.
			else if (card.getName().equals(weaponBox.getSelectedItem()))
				guess[1] = card;
		}
	}

}
