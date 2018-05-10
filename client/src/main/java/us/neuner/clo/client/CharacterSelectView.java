package us.neuner.clo.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CharacterSelectView extends JFrame implements ActionListener {

	/**
	 * serialization class version number
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html">java.io.Serializable</a>
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<GameEntityID> characters;
	private ArrayList<JButton> charButtons; 
	private PlayerInfo selection;
	private int playerSelectNum;
	private String name;
	
	public CharacterSelectView(String playerName){
		JPanel displayBox = new JPanel (new GridLayout(2, 3, 5, 5));
		this.name = playerName;
		
		characters = new ArrayList<GameEntityID>(6);
		charButtons = new ArrayList<JButton>(6);
		playerSelectNum = 0;
		
		for (GameEntityID gamePiece: GameEntityID.getSuspects()){
			JButton option = new JButton();
			option.setIcon(gamePiece.getImage());
			option.addActionListener(this);
			charButtons.add(option);
			characters.add(gamePiece);
			displayBox.add(option);
		}
		add(displayBox);
		setSize(380, 400);
        setTitle("Select Your Character");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(520, 200);
        setVisible(true);
        setAlwaysOnTop(true);
	}

	
	public void actionPerformed(ActionEvent e) {
		//adjust for server client architecture
		playerSelectNum++;
		for (int i = 0; i<charButtons.size(); i++){
			if (e.getSource() == charButtons.get(i)){
				charButtons.get(i).setEnabled(false);
				selection = new PlayerInfo(name, characters.get(i));
				
			}
		}	
		if (playerSelectNum == 1){
			new CLOGameClient(selection);
			dispose();
		}
	}
	}

