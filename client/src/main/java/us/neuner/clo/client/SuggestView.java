package us.neuner.clo.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SuggestView extends JPanel implements ChangeListener {

	/**
	 * serialization class version number
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html">java.io.Serializable</a>
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel playerInfo; // Display player's image. Name kept in border.
	private JButton makeAccusation; // Opens Accusation window.
	private JButton makeAssumption; // Opens Assumption window.
	private JButton endTurn;
	private JButton openNotebook; // Opens NoteBook window

	// Panel Components that are class extensions.
	private NotebookView noteBookWindow; // NoteBook Pop-Up window.
	private AccuseView assumptionWindow; // Assumption Pop-Up window.
	private AccuseView accusationWindow; // Accusation Pop-Up window.

	public SuggestView(PlayerDetail player) {
		noteBookWindow = new NotebookView();
		assumptionWindow = new AccuseView("Make Assumption", player.getHand());
		accusationWindow = new AccuseView("Make Accusation", player.getHand());

		setLayout(new GridLayout(2, 1, 0, 2));

		playerInfo = new JLabel();
		playerInfo.setIcon(player.getPlayerImage());
		playerInfo.setBorder(BorderFactory.createTitledBorder(player.getName() + "'s Turn:"));
		playerInfo.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel optionArea = new JPanel(new GridLayout(6, 1, 10, 2));

		// Buttons for optionArea.
		makeAccusation = new JButton("Make Accusation");
		makeAccusation.setEnabled(false);
		makeAssumption = new JButton("Make Assumption");
		makeAssumption.setEnabled(false);
		endTurn = new JButton("End Turn");
		endTurn.setEnabled(false);
		openNotebook = new JButton("Open NoteBook");

		// Add buttons to optionArea Panel.
		optionArea.add(makeAccusation);
		optionArea.add(makeAssumption);
		optionArea.add(openNotebook);
		optionArea.add(endTurn);

		// Add all components to main JPanel.
		add(playerInfo);
		add(optionArea);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openNotebook) {
			if (!noteBookWindow.isVisible())
				noteBookWindow.setVisible(true);
		} else if (e.getSource() == makeAssumption) {
			if (!assumptionWindow.isVisible())
				assumptionWindow.setVisible(true);
		} else if (e.getSource() == makeAccusation) {
			if (!accusationWindow.isVisible())
				accusationWindow.setVisible(true);
		}

	}

	public void changeTurnIndicator(GameEntityGraphic nextPlayer) {
		playerInfo.setBorder(BorderFactory.createTitledBorder(nextPlayer.getName() + "'s Turn:"));
		playerInfo.setIcon(nextPlayer.getImage());
	}

	public void toggleButtonsEnabled(boolean toggle) {
		makeAssumption.setEnabled(toggle);
		makeAccusation.setEnabled(toggle);
		endTurn.setEnabled(toggle);
	}

	public AccuseView getAssumptionWindow() {
		return assumptionWindow;
	}

	public AccuseView getAccusationWindow() {
		return accusationWindow;
	}

	public NotebookView getNoteBookWindow() {
		return noteBookWindow;
	}

	public JButton getMakeAccusation() {
		return makeAccusation;
	}

	public JButton getMakeAssumption() {
		return makeAssumption;
	}

	public JButton getEndTurn() {
		return endTurn;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

}
