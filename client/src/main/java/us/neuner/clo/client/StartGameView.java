package us.neuner.clo.client;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class StartGameView extends JFrame {

	/**
	 * serialization class version number
	 * 
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html">java.io.Serializable</a>
	 */
	private static final long serialVersionUID = 1L;

	private JTextField enterName = new JTextField(15);
	private JTextField enterServerIP = new JTextField(15);
	private JTextField enterSessionPassword = new JTextField(15);
	private JLabel screenName = new JLabel("Choose your screenname");
	private JLabel serverIP = new JLabel("Enter host:port for Server");
	private JLabel sessionPass = new JLabel("Enter Session Password");
	private JPanel name = new JPanel();
	private JPanel server = new JPanel();
	private JPanel protocol = new JPanel();
	private JPanel game = new JPanel();
	private JButton joinGame = new JButton("Join Game");

	private static class JoinGameHandler implements ActionListener {

		private final StartGameView parent;
		
		JoinGameHandler(StartGameView parent) {
			this.parent = parent;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String uname = parent.enterName.getText();
			String pword = parent.enterSessionPassword.getText();
			String host = parent.enterServerIP.getText();
			
			try {
				Application.connect(uname, pword, host);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			new CharacterSelectView(parent.enterName.getText());
			parent.dispose();
			
		}
		
	}
	
	StartGameView() {
		name.add(screenName);
		name.add(enterName);
		name.setLayout(new FlowLayout());
		server.add(serverIP);
		server.add(enterServerIP);
		server.setLayout(new FlowLayout());
		server.setToolTipText("e.g. www.neuner.us:8080");
		protocol.add(sessionPass);
		protocol.add(enterSessionPassword);
		server.setLayout(new FlowLayout());
		game.add(joinGame);
		game.setLayout(new FlowLayout());
		add(name);
		add(server);
		add(protocol);
		add(game);
		this.setLayout(new GridLayout(4, 0));
		setTitle("Join a Game");
		setSize(810, 810);
		setLocation(300, 100);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
		joinGame.addActionListener(new JoinGameHandler(this));
	}
}