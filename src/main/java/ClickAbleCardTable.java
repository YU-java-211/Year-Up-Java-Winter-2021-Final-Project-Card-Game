import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;


// got base for GUI code at this excellent site here:
// https://zetcode.com/javaswing/draganddrop/


public class ClickAbleCardTable extends CardTable 
                    implements DragGestureListener {
	//vars
	// Going to need to work on making this generic.  Right now EightsGUI is a separate file, I need to make Players generic
	// And refer to Games, not specific games like Eights.  That's a new step.  Then replace all the eights guis with game and all the
	// player eights gui with player (sigh)
	private PlayerEightsGUI player;
	private EightsGUI game;
	private Deck deck;
	private ArrayList<Hand> piles;
	private ArrayList<PlayerEightsGUI> playerList;
	private JPanel leftPanel;
    private Image[][] images;
    private int cardWidth, cardHeight;
	
	//constructor
	public ClickAbleCardTable(EightsGUI game){
		// will need to change the above to take like a games Class
		super(); // Long-term, the card image info should be in the card object, but till then.....
		this.game = game;
		
		playerList = game.getPlayers();
		piles = game.getPiles();
		deck = game.getDeck();
		
		
		// need to collect this info right before playing 8's
		Scanner in = new Scanner(System.in);
		// hacky workaround, fix later
		System.out.println("Which name are YOU on the list?  If your name doesn't match, you'll be playing as the first name you enterd");
		String playerName = in.nextLine();
		// Note, if the player in question is not found in the array list, it will return the first player.
		
		player = game.getPlayer(playerName);
		
		initUI();

	}
	
    private void initUI() {
    	// must associate card with image... probobly via extending the card class...

        var colourBtn = new JButton("Choose Color");
        colourBtn.setFocusable(false);

        leftPanel = new JPanel();
        leftPanel.setBackground(Color.red);
        leftPanel.setPreferredSize(new Dimension(100, 100));

        colourBtn.addActionListener(event -> {

            var color = JColorChooser.showDialog(this, "Choose Color", Color.white);
            leftPanel.setBackground(color);
        });

        var rightPanel = new JPanel();
        rightPanel.setBackground(Color.white);
        rightPanel.setPreferredSize(new Dimension(100, 100));

        var mtl = new MyDropTargetListener(rightPanel);

        var ds = new DragSource();
        ds.createDefaultDragGestureRecognizer(leftPanel,
                DnDConstants.ACTION_COPY, this);

        createLayout(colourBtn, leftPanel, rightPanel);

        setTitle("Complex drag and drop example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
	
 
    
    public void playGame() {
        // changed from one to players.get(0);
        // then rename the player in this to playerTurn so it doesn't clash
        // with other code we have in our foreach looops
    	
        PlayerEightsGUI playerTurn = playerList.get(0);

        // keep playing until there's a winner
        while (!game.isDone()) {
            game.displayState();
            game.takeTurn(playerTurn);
            playerTurn = game.nextPlayer(playerTurn);
        }

        // display the final score
        for (PlayerEightsGUI player : playerList) {
            player.displayScore();
        }

    }
    
    public static void main(String [] args) {
        EightsGUI game = new EightsGUI();
        ClickAbleCardTable showGame = new ClickAbleCardTable(game);
    }

}
