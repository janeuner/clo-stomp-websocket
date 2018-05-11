package us.neuner.clo.client;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ChatView extends JPanel{/**
	 * serialization class version number
	 * 
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html">java.io.Serializable</a>
	 */
	private static final long serialVersionUID = 1L;

	private JTextField chatMessage = new JTextField(15);
	private JTextArea chatHistory= new JTextArea("", 10, 5);
	private TitledBorder ch = new TitledBorder("ChatView");
	private JButton send = new JButton("Send");
	private JPanel messageSend = new JPanel();
	private PlayerInfo playerSendingMessage;
	private String playerName;
	

	public ChatView(PlayerInfo player) {
		super();
		this.playerSendingMessage = player;
		this.playerName = playerSendingMessage.getPlayerName();
		this.chatHistory.setBorder(ch);
		
		messageSend.setLayout(new FlowLayout());
		messageSend.add(chatMessage); messageSend.add(send);
		
		
		setLayout(new GridLayout(2, 1));
		add(chatHistory);
		add(messageSend);
		
		send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				chatHistory.append(playerName + ": " + chatMessage.getText());
			}
		});

	}



}