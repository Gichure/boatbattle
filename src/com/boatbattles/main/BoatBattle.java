package com.boatbattles.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;

import com.boatbattles.listeners.ClickListener;
import com.boatbattles.models.Boat;

public class BoatBattle extends JFrame {
	 final static int gridSize = 10;
	   
    JButton switchButton = new JButton("Pass");
    GridLayout playerTwoLayout = new GridLayout(gridSize,gridSize);
    GridLayout playerOneLayout = new GridLayout(gridSize,gridSize);
    GridLayout detailsLayout = new GridLayout(1,2);
    Color white =Color.WHITE;
    Color green =Color.GREEN;
    private static JButton[][] buttons  = new JButton[10][10];
    
   static ActionListener clickListener = new ClickListener();
    
    String rowChars[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    String colChars[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    
    public BoatBattle(String name) {
        super(name
        		
        		);
        setResizable(false);
    }
  
    public void addComponentsToPane(final Container pane) {

        final JPanel playerTwoGrid = new JPanel();
        final JPanel playerOneGrid = new JPanel();
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
        playerTwoGrid.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+20,
                (int)(buttonSize.getHeight() * 8)+80));
        playerOneGrid.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+20,
                (int)(buttonSize.getHeight() * 8)+80)); 
        //Add buttons to player two's grid
        
        for (int i = 0; i < colChars.length; i++) {
            for (int j = 0; j < rowChars.length; j++) {
            	 buttons[i][j] = new JButton(colChars[i]+rowChars[j]);
            	 buttons[i][j].setBackground(white);
            	 buttons[i][j].addActionListener(clickListener);
                playerTwoGrid.add( buttons[i][j]);
            }
        }
       
        //Add buttons to player one's grid
        for (int i = 0; i < colChars.length; i++) {
            for (int j = 0; j < rowChars.length; j++) {
            	buttons[i][j] = new JButton(colChars[i]+rowChars[j]);
              	buttons[i][j].setBackground(white);
              	buttons[i][j].addActionListener(clickListener);
              	playerOneGrid.add( buttons[i][j]);
            }
        }
       
         //Add items to the header
        header.add(new JLabel("Player One\'s Sea"));
        header.add(new JLabel("My Sea"));
        //Add items to the footer
        footer.add(new JLabel("Current Player:"));
        JLabel currentPlayerLabel = new JLabel("Player One");
        footer.add(currentPlayerLabel);
        footer.add(new JLabel("Status Message:"));
        JLabel statusLabel = new JLabel("Not Started");
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
    	BoatBattle frame = new BoatBattle("Battle Boat Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        frame.setBackground(Color.WHITE);
        JMenuBar mb = createMenu();
        frame.setJMenuBar(mb);
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

        menuItem = new JMenuItem("Start");
        menuItem.addActionListener(clickListener);
        menu.add(menuItem);

        menuItem = new JMenuItem("Quit");
        menuItem.addActionListener(clickListener);
        menu.add(menuItem);        

        //a submenu
        menu.addSeparator();
        return menuBar;
    }
    
 public boolean validate(Boat b, int x, int y, String orientation, JButton jb){
	 	if(("V".equalsIgnoreCase(orientation) && x+b.getLength() > rowChars.length) ||("H".equalsIgnoreCase(orientation) && x+b.getLength() > colChars.length) ){
	 		return false;
	 	}
	 	else{
	 		//Validate against the Background colour of the button
	 			if(jb.getBackground() != white) return false;
	 	}
    	return true;
    }
}
