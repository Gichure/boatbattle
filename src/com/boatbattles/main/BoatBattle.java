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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.boatbattles.listeners.ClickListener;

public class BoatBattle extends JFrame {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static int gridSize = 10;
	
	public static int currentPlayer = 1 ;
	public static String title = "Battling Boats Game";
    GridLayout playerTwoLayout = new GridLayout(gridSize,gridSize);
    GridLayout playerOneLayout = new GridLayout(gridSize,gridSize);
    GridLayout detailsLayout = new GridLayout(1,2);
    Color white =Color.WHITE;
    Color green =Color.GREEN;
  /*  public static JButton[][] gridTwobuttons  = new JButton[10][10];
    public static JButton[][] gridOnebuttons  = new JButton[10][10];*/
    public static JButton[][] primaryPlayerOneButtons  = new JButton[10][10];
    public static JButton[][] primaryPlayerTwoButtons  = new JButton[10][10];
    public static JButton[][] secondaryPlayerOneButtons  = new JButton[10][10];
    public static JButton[][] secondaryPlayerTwoButtons  = new JButton[10][10];
    public static JLabel currentPlayerLabel = new JLabel("Player One");
    public JLabel statusLabel1 = new JLabel("Not Started");
    public JLabel statusLabel2 = new JLabel("Not Started");
    
    public JLabel secondaryGridLabel = new JLabel("Secondary Grid");
    public JLabel primaryGridLabel = new JLabel("Primary Grid");
    
    public final JPanel primaryGrid = new JPanel();
    public final JPanel secondaryGrid = new JPanel();
    static ActionListener clickListener = new ClickListener("");
    public  JPanel footer = new JPanel();
    public String rowChars[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    public String colChars[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    
    public static BoatBattle frame;
    private static BoatBattle frame2;
    
    public BoatBattle(String name) {
        super(name);
        setResizable(false);
    }
  
    public void addComponentsToPane(final Container pane) {

        
        primaryGrid.setLayout(playerTwoLayout);
        secondaryGrid.setLayout(playerOneLayout);
        secondaryGrid.setBackground(white);
        primaryGrid.setBackground(white);
        
       
        footer.setLayout(detailsLayout);
        
        JPanel header = new JPanel();
        header.setLayout(detailsLayout);
        
        //Set up components preferred size
        JButton b = new JButton("                   ");
        Dimension buttonSize = b.getPreferredSize();
        primaryGrid.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.6)+30,
                (int)(buttonSize.getHeight() * 8)+85));
        secondaryGrid.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.6)+30,
                (int)(buttonSize.getHeight() * 8)+85)); 
        if(currentPlayer ==1){
        	//Add buttons to player one's primary grid
          for (int i = 0; i < colChars.length; i++) {
            for (int j = 0; j < rowChars.length; j++) {
            	primaryPlayerOneButtons[i][j] = new JButton(colChars[i]+rowChars[j]);
            	primaryPlayerOneButtons[i][j].setBackground(white);
            	primaryPlayerOneButtons[i][j].setMargin(new Insets(0, 0, 0, 0));
            	primaryPlayerOneButtons[i][j].addActionListener(clickListener);
                primaryGrid.add(primaryPlayerOneButtons[i][j]);
            }
        }
        //Add buttons to player one's secondary grid
        for (int i = 0; i < colChars.length; i++) {
            for (int j = 0; j < rowChars.length; j++) {
            	secondaryPlayerOneButtons[i][j] = new JButton(colChars[i]+rowChars[j]);
            	secondaryPlayerOneButtons[i][j].setBackground(white);
            	secondaryPlayerOneButtons[i][j].setMargin(new Insets(0, 0, 0, 0));
            	secondaryPlayerOneButtons[i][j].addActionListener(clickListener);
              	secondaryGrid.add(secondaryPlayerOneButtons[i][j]);
            }
        }
        }
        else{
        	//Add buttons to player two's primary grid
            for (int i = 0; i < colChars.length; i++) {
              for (int j = 0; j < rowChars.length; j++) {
              	primaryPlayerTwoButtons[i][j] = new JButton(colChars[i]+rowChars[j]);
              	primaryPlayerTwoButtons[i][j].setBackground(white);
              	primaryPlayerTwoButtons[i][j].setMargin(new Insets(0, 0, 0, 0));
              	primaryPlayerTwoButtons[i][j].addActionListener(clickListener);
                primaryGrid.add(primaryPlayerTwoButtons[i][j]);
              }
          }
          //Add buttons to player two's secondary grid
          for (int i = 0; i < colChars.length; i++) {
              for (int j = 0; j < rowChars.length; j++) {
              	secondaryPlayerTwoButtons[i][j] = new JButton(colChars[i]+rowChars[j]);
              	secondaryPlayerTwoButtons[i][j].setBackground(white);
              	secondaryPlayerTwoButtons[i][j].setMargin(new Insets(0, 0, 0, 0));
              	secondaryPlayerTwoButtons[i][j].addActionListener(clickListener);
                secondaryGrid.add(secondaryPlayerTwoButtons[i][j]);
              }
          }
        }
         //Add items to the header
        header.add(primaryGridLabel);
        header.add(secondaryGridLabel);
        
        //Add items to the footer
        footer.add(new JLabel("Current Player:"));
        footer.add(currentPlayerLabel);
        footer.add(new JLabel("Status Message:"));
        if(currentPlayer == 1)
        	footer.add(statusLabel1);
        else
        	footer.add(statusLabel2);
        
        pane.add(header, BorderLayout.NORTH);
        pane.add(primaryGrid, BorderLayout.WEST);
        pane.add(secondaryGrid, BorderLayout.EAST);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(footer, BorderLayout.SOUTH);
    }
     
    /**
     * 
     * Show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void showPlayerOneWindow() {
        //Create and set up the window for Player One
    	frame = new BoatBattle(title);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
    	currentPlayerLabel.setText("Player One");
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
                showPlayerOneWindow();
            }
        });
    }
    private static void createPlayerTwoWindow() {
    	currentPlayer = 2;
        //Create and set up the window for Player One
    	frame2 = new BoatBattle(title);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
    	currentPlayerLabel.setText("Player Two");
    	frame2.addComponentsToPane(frame2.getContentPane());
    	frame2.setBackground(Color.WHITE);
        JMenuBar mb = createMenu();
        frame2.setJMenuBar(mb);
        frame2.setResizable(false);
        //Place it at the center of the screen
        frame2.setLocationRelativeTo(null);
        //Display the window.
        frame2.pack();
    }
    public static JMenuBar createMenu() 
    {
    	
        JMenuBar menuBar  = new JMenuBar();
        JMenu menu = new JMenu("Game Menu");
        JMenuItem menuItem;

        menuBar.add(menu);

        menuItem = new JMenuItem("Place Boats");
        menuItem.addActionListener(clickListener);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Reset");
        menuItem.addActionListener(clickListener);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Switch Player");
        menuItem.addActionListener(clickListener);
        menu.add(menuItem);

        menuItem = new JMenuItem("Quit");
        menuItem.addActionListener(clickListener);
        menu.add(menuItem);        

        //a submenu
        menu.addSeparator();
        return menuBar;
    }
    
 public void SwitchPlayers(){
	 if(currentPlayer == 1){
 		currentPlayer = 2;
 	//	currentPlayerLabel.setText("Player Two");
 		if(frame2 == null)createPlayerTwoWindow();
 		frame2.setVisible(true);
 		frame.setVisible(false);
 		}
 	else{
 		currentPlayer = 1;
 	//	currentPlayerLabel.setText("Player One");
 		frame.setVisible(true);
 		if(frame2 == null)createPlayerTwoWindow();
 		frame2.setVisible(false);
 	}
	 footer.repaint();
	footer.paintImmediately(footer.getVisibleRect());
	System.out.println("Current Player: "+currentPlayer);
 }
 public void SetStatusMessage(final String statusMessage,final int msgType){
	 if(currentPlayer ==1){
		 getStatusLabel1().setText(statusMessage);
		 System.out.println(getStatusLabel1().getText());
	 	}
	 else{
		 getStatusLabel2().setText(statusMessage);
		 System.out.println(getStatusLabel2().getText());
	 	}
	 		footer.revalidate();
			JOptionPane.showMessageDialog(rootPane, statusMessage, title, msgType);
	   }

public JLabel getStatusLabel1() {
	return statusLabel1;
}

public void setStatusLabel1(JLabel statusLabel1) {
	this.statusLabel1 = statusLabel1;
}

public JLabel getStatusLabel2() {
	return statusLabel2;
}

public void setStatusLabel2(JLabel statusLabel2) {
	this.statusLabel2 = statusLabel2;
}


}

