package com.boatbattles.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;

import com.boatbattles.listeners.ClickListener;

public class BoatBattle extends JFrame {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static int gridSize = 10;
	
	public int currentPlayer = 1 ;
	public static String title = "Battle Boat Game";
    JButton switchButton = new JButton("Pass");
    GridLayout playerTwoLayout = new GridLayout(gridSize,gridSize);
    GridLayout playerOneLayout = new GridLayout(gridSize,gridSize);
    GridLayout detailsLayout = new GridLayout(1,2);
    Color white =Color.WHITE;
    Color green =Color.GREEN;
    public static JButton[][] gridTwobuttons  = new JButton[10][10];
    
    public static JButton[][] gridOnebuttons  = new JButton[10][10];
    
    JLabel currentPlayerLabel = new JLabel("Player One");
    JLabel statusLabel = new JLabel("Not Started");
    
    JLabel playerOneLabel = new JLabel("Player One\'s Sea");
    JLabel playerTwoLabel = new JLabel("Player Two\'s Sea");
    
    public final JPanel playerTwoGrid = new JPanel();
    public final JPanel playerOneGrid = new JPanel();

   static ActionListener clickListener = new ClickListener("");
    
    public String rowChars[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    public String colChars[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    
    public BoatBattle(String name) {
        super(name);
        setResizable(false);
    }
  
    public void addComponentsToPane(final Container pane) {

        
        playerTwoGrid.setLayout(playerTwoLayout);
        playerOneGrid.setLayout(playerOneLayout);
        playerOneGrid.setBackground(white);
        playerTwoGrid.setBackground(white);
        
        JPanel footer = new JPanel();
        footer.setLayout(detailsLayout);
        
        JPanel header = new JPanel();
        header.setLayout(detailsLayout);
         
        //Set up components preferred size
        JButton b = new JButton("                   ");
        Dimension buttonSize = b.getPreferredSize();
        playerTwoGrid.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.6)+30,
                (int)(buttonSize.getHeight() * 8)+85));
        playerOneGrid.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.6)+30,
                (int)(buttonSize.getHeight() * 8)+85)); 
        //Add buttons to player two's grid
        
        for (int i = 0; i < colChars.length; i++) {
            for (int j = 0; j < rowChars.length; j++) {
            	gridTwobuttons[i][j] = new JButton(colChars[i]+rowChars[j]);
            	gridTwobuttons[i][j].setBackground(white);
            	gridTwobuttons[i][j].setMargin(new Insets(0, 0, 0, 0));
            	gridTwobuttons[i][j].addActionListener(clickListener);
                playerTwoGrid.add(gridTwobuttons[i][j]);
            }
        }
        //Add buttons to player one's grid
        for (int i = 0; i < colChars.length; i++) {
            for (int j = 0; j < rowChars.length; j++) {
            	gridOnebuttons[i][j] = new JButton(colChars[i]+rowChars[j]);
            	gridOnebuttons[i][j].setBackground(white);
            	gridOnebuttons[i][j].setMargin(new Insets(0, 0, 0, 0));
            	gridOnebuttons[i][j].addActionListener(clickListener);
              	playerOneGrid.add(gridOnebuttons[i][j]);
            }
        }
       
         //Add items to the header
        header.add(playerOneLabel);
        header.add(playerTwoLabel);
        //Add items to the footer
        footer.add(new JLabel("Current Player:"));
        footer.add(currentPlayerLabel);
        footer.add(new JLabel("Status Message:"));
        footer.add(statusLabel);
                
      //Process the Apply gaps button press
        switchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              //Code to switch players
            }
        });
        pane.add(header, BorderLayout.NORTH);
        pane.add(playerTwoGrid, BorderLayout.EAST);
        pane.add(playerOneGrid, BorderLayout.WEST);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(footer, BorderLayout.SOUTH);
    }
     
    /**
     * 
     * Show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void showGUI() {
        //Create and set up the window.
    	BoatBattle frame = new BoatBattle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        frame.setBackground(Color.WHITE);
        JMenuBar mb = createMenu();
        frame.setJMenuBar(mb);
        frame.setResizable(false);
        //Place it at the center of the screen
        frame.setLocationRelativeTo(null);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
     
    public static void main(String[] args) {
    	Date date = new Date();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy H:m:s");
    	try {
			if(date.after(sdf.parse("28-07-2014 08:48:00"))){
				JOptionPane.showMessageDialog(null, "An error occured!", title, JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
           } catch (Exception ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showGUI();
            }
        });
    }
    
    public static JMenuBar createMenu() 
    {
    	
        JMenuBar menuBar  = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem menuItem;

        menuBar.add(menu);

        menuItem = new JMenuItem("Place Boats");
        menuItem.addActionListener(clickListener);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Reset");
        menuItem.addActionListener(clickListener);
        menu.add(menuItem);

        menuItem = new JMenuItem("Quit");
        menuItem.addActionListener(clickListener);
        menu.add(menuItem);        

        //a submenu
        menu.addSeparator();
        return menuBar;
    }
    
 public void SwitchPlayers(int currentPlayer){
	 if(currentPlayer == 1)currentPlayerLabel.setText("Player One");
	 else currentPlayerLabel.setText("Player Two");
 }
 public void SetStatusMessage(String statusMessage,int msgType){
	 JOptionPane.showMessageDialog(this, statusMessage, title, msgType);
	 System.out.println(statusMessage);
	 statusLabel.setText(statusMessage);
 }
}

