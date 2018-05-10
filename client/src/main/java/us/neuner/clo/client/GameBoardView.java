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

	GameBoardView(PlayerDetail player) {

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

	/*
	 * private JLabel Study; private JLabel Hall; private JLabel Lounge; private
	 * JLabel Library; private JLabel BilliardRoom; private JLabel DiningRoom;
	 * private JLabel Conservatory; private JLabel Ballroom; private JLabel Kitchen;
	 * private JLabel H01; private JLabel H02; private JLabel H03; private JLabel
	 * H04; private JLabel H05; private JLabel H06; private JLabel H07; private
	 * JLabel H08; private JLabel H09; private JLabel H10; private JLabel H11;
	 * private JLabel H12; private Area MissScarlet; private Area ProfPlum; private
	 * Area ColMustard; private Area MrsPeacock; private Area MrGreen; private Area
	 * MrsWhite; private JPanel row1; private JPanel row2; private JPanel row3;
	 * private JPanel row4; private JPanel row5;
	 */

	/*
	 * Study = new JLabel("Study"); //Study.setIcon(new
	 * ImageIcon("C:/locations1/Study.jpg"));
	 * 
	 * Study.setLocation(10, 10);
	 * Study.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); Hall = new
	 * JLabel ("Hall"); Hall.setSize(150, 150); Lounge = new JLabel ("Lounge");
	 * Lounge.setSize(150, 150); Library = new JLabel ("Library");
	 * Library.setSize(150, 150); BilliardRoom = new JLabel ("Billiard Room");
	 * BilliardRoom.setSize(150, 150); DiningRoom = new JLabel("Dining Room");
	 * DiningRoom.setSize(150, 150); Conservatory = new JLabel("Conservatory");
	 * Conservatory.setSize(150, 150); Ballroom = new JLabel("Ballroom");
	 * Ballroom.setSize(150, 150); Kitchen = new JLabel("Kitchen");
	 * Kitchen.setSize(150, 150); H01 = new JLabel("H01"); H01.setSize(50, 150); H02
	 * = new JLabel("H02"); H02.setSize(50, 150); H03 = new JLabel("H03");
	 * H03.setSize(150, 50); H04 = new JLabel("H04"); H04.setSize(150, 50); H05 =
	 * new JLabel("H05"); H05.setSize(150, 50); H06 = new JLabel("H06");
	 * H06.setSize(50, 150); H07 = new JLabel("H07"); H07.setSize(50, 150); H08 =
	 * new JLabel("H08"); H08.setSize(150, 50); H09 = new JLabel("H09");
	 * H09.setSize(150, 50); H10 = new JLabel("H10"); H10.setSize(150, 50); H11 =
	 * new JLabel("H11"); H11.setSize(50, 150); H12 = new JLabel("H12");
	 * H12.setSize(50, 150); add(Study); /*row1 = new JPanel(); row1.setLayout(new
	 * FlowLayout()); row1.add(Study); row1.add(H01); row1.add(Hall); row1.add(H02);
	 * row1.add(Lounge); row2 = new JPanel(); row2.setLayout(new FlowLayout());
	 * row2.add(H03); row2.add(H04); row2.add(H05); row3 = new JPanel();
	 * row3.setLayout(new FlowLayout()); row3.add(Library); row3.add(H06);
	 * row3.add(BilliardRoom); row3.add(H07); row3.add(DiningRoom); row4 = new
	 * JPanel(); row4.setLayout(new FlowLayout()); row4.add(H08); row4.add(H09);
	 * row4.add(H10); row5 = new JPanel(); row5.setLayout(new FlowLayout());
	 * row5.add(Conservatory); row5.add(H11); row5.add(Ballroom); row5.add(H12);
	 * row5.add(Kitchen); this.setLayout(new GridLayout(5,0)); add(row1); add(row2);
	 * add(row3); add(row4); add(row5);
	 */
}
