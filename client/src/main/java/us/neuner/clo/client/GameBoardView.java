package us.neuner.clo.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GameBoardView extends JPanel {

	/**
	 * serialization class version number
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html">java.io.Serializable</a>
	 */
	private static final long serialVersionUID = 1L;
	
	private final URL boardImageUrl = this.getClass().getResource("/img/ClientGUI/Board Resized.jpeg");
	private final ImageIcon boardImage = new ImageIcon(boardImageUrl);
	private JLabel board = new JLabel();

	private ArrayList<JLabel> gamePiece = new ArrayList<JLabel>();
	private PlayerDetail player;

	GameBoardView(PlayerDetail player) {
		this.player = player;
		JLayeredPane layPane = new JLayeredPane();
		layPane.setPreferredSize(new Dimension(boardImage.getIconWidth(), boardImage.getIconHeight()));
		board.setIcon(getImage());
		layPane.add(board, new Integer(5));

		gamePiece.add(player.getGamePiece());
		layPane.add(gamePiece.get(0), new Integer(10));

		add(layPane, JPanel.CENTER_ALIGNMENT);
		setBackground(Color.BLACK);

		/*
		 * for(int i=0; i<players.length; i++) {
		 * gamePiece.add(players[i].getGamePiece());
		 * layPane.add(players[i].getGamePiece(), new Integer(10)); }
		 */

		/*
		 * for(int i=0; i<players.length; i++) {
		 * gamePiece.get(i).setBounds(players[i].getMovement().getXPos(),
		 * players[i].getMovement().getYPos(),
		 * gamePiece.get(i).getIcon().getIconWidth(),
		 * gamePiece.get(i).getIcon().getIconHeight()); }
		 */

		gamePiece.get(0).setBounds(player.getMovement().getXPos(), player.getMovement().getYPos(),
				gamePiece.get(0).getIcon().getIconWidth(), gamePiece.get(0).getIcon().getIconHeight());

		board.setBounds(0, 0, boardImage.getIconWidth(), boardImage.getIconHeight());

	}
	
	
	
	
	
	public boolean isInRoom(){
		 return player.getMovement().getLocation()!=null;
	 }

	public Point getGamePiecePoint(int index) {
		return new Point(gamePiece.get(index).getX(), gamePiece.get(index).getY());
	}

	public void setBoardIcon(ImageIcon image) {
		board.setIcon(image);
	}

	public void resetBoardIcon() {
		board.setIcon(boardImage);
	}

	public ImageIcon getImage() {
		return this.boardImage;
	}

	
}
