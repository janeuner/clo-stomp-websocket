package us.neuner.clo.client;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ChatView extends JPanel {

	/**
	 * serialization class version number
	 * 
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html">java.io.Serializable</a>
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<ChatEntry> history = new ArrayList<ChatEntry>();
	private JTextField chatMessages = new JTextField();
	private TitledBorder ch = new TitledBorder("ChatView");

	public ChatView() {
		super();
		chatMessages.setBorder(ch);
		add(chatMessages);
		setLayout(new GridLayout(1, 1));

	}

}
