package us.neuner.clo.client;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import us.neuner.clo.message.ChatEntry;
import us.neuner.clo.message.ChatMessage;
import us.neuner.clo.message.ChatMessageHistory;
import us.neuner.clo.message.ErrorMessage;
import us.neuner.clo.message.GameSetupMessage;
import us.neuner.clo.message.Message;

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
	
	private static class ChatMessageHandler implements CloServerMessageNotifier {

		private final ChatView parent;
		
		ChatMessageHandler(ChatView parent) {
			this.parent = parent;
		}
		
		@Override
		public void onMessageReceived(Message msg) {

			if (msg instanceof ChatMessageHistory) {
				ChatMessageHistory cmh = (ChatMessageHistory)msg;

				parent.chatHistory.setText("");
				for (int idx = 0; idx < cmh.getEntryCount(); idx++) {
					ChatEntry ce = cmh.getEntry(idx);
					String txt = String.format("[%s] %s\n", ce.getPlayerName(), ce.getMsg());
					parent.chatHistory.append(txt);
				}
			}
		}
		
	}	
	

	public ChatView(PlayerInfo player) {
		super();
		this.playerSendingMessage = player;
		this.playerName = playerSendingMessage.getPlayerName();
		this.chatHistory.setBorder(ch);
		
		messageSend.setLayout(new FlowLayout());
		messageSend.add(chatMessage); messageSend.add(send);
		
		JScrollPane sp = new JScrollPane(chatHistory);
		
		setLayout(new GridLayout(2, 1));
		add(sp);
		add(messageSend);
		
		send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//chatHistory.append(playerName + ": " + chatMessage.getText() + "\n");
				CloApplication.sendChat(chatMessage.getText());
			}
		});

		CloApplication.registerMessageNotifier(new ChatMessageHandler(this));
	}



}