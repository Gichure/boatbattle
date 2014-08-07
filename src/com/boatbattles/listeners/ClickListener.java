package com.boatbattles.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.boatbattles.main.BoatBattle;
import com.boatbattles.models.Boat;

public class ClickListener extends BoatBattle implements ActionListener {
	public ArrayList<JButton> occupiedButtons = new ArrayList<>();
	public ArrayList<Boat> arrangedBoats = new ArrayList<>();
	public ArrayList<JButton> neighborButtons = new ArrayList<>();
	
	public ArrayList<JButton> occupiedButtons2 = new ArrayList<>();
	public ArrayList<Boat> arrangedBoats2 = new ArrayList<>();
	public ArrayList<JButton> neighborButtons2 = new ArrayList<>();
	
    public ArrayList<JButton> boat1Buttons = new ArrayList<>();
	public ArrayList<JButton> boat2Buttons = new ArrayList<>();
	public ArrayList<JButton> boat3Buttons = new ArrayList<>();
	public ArrayList<JButton> boat4Buttons = new ArrayList<>();
	
	
	public ArrayList<JButton> boat1Buttons2 = new ArrayList<>();
	public ArrayList<JButton> boat2Buttons2 = new ArrayList<>();
	public ArrayList<JButton> boat3Buttons2 = new ArrayList<>();
	public ArrayList<JButton> boat4Buttons2 = new ArrayList<>();
	
	private int hit1Buttons = 0;
	private int hit2Buttons = 0;
	private int hit3Buttons = 0;
	private int hit4Buttons = 0;
	
	private int hitBoats = 0;
	private int hitBoats2 = 0;
	
	private int hit1Buttons2 = 0;
	private int hit2Buttons2 = 0;
	private int hit3Buttons2 = 0;
	private int hit4Buttons2 = 0;
	
	private Color hitColor = Color.ORANGE;
	private Color missColor = Color.GREEN;
	private Color criticalHitColor = Color.RED;
	
	public ClickListener(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 String classname = getObjectName(e.getSource());
	     //JComponent component = (JComponent)(e.getSource());

	        if (classname.equals("JMenuItem"))
	        {
	            JMenuItem menusource = (JMenuItem)(e.getSource());
	            String menutext  = menusource.getText();

	            // Determine which menu option was chosen
	            if (menutext.equals("Place Boats"))
	            {
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
	                    	ArrangeWindow aw =new ArrangeWindow();
	       	             // TODO Auto-generated method stub
	       	      		  JFrame f = new JFrame("Place a boat");
	       	              f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	       	              f.getContentPane().add(aw, BorderLayout.NORTH);
	       	              // Panel with the button
	       	              JPanel p = new JPanel();
	       	              f.getContentPane().add(p, BorderLayout.SOUTH);
	       	              // Show the frame
	       	              f.setLocationRelativeTo(frame);
	       	              f.pack();
	       	              f.setVisible(true);
	                    }
	                });
	            }
	            else if (menutext.equals("Reset"))
	            {
		               //Hide boats
		            	ResetGame();
		        }
	            else if (menutext.equals("Switch Player"))
	            {
	            	if(allBoatsOK())SwitchPlayers();
	            	
		        }
	            
	            else if (menutext.equals("Quit"))
	            {
	            	//Quit the application
	            	QuitGame();
	            }
	            else
	            {
	            	//show default error message for non selection
	            }
	        }
	        // Handle the event from the user clicking on a command button
	        else if (classname.equals("JButton"))
	        {
	        	JButton button = (JButton)(e.getSource());
	        	clickEvent(getSelectedRow(button), getSelectedColumn(button));
	        }  
	}
	/**
     *  Returns the object name of a jswing variable
     */
	protected int getSelectedRow(JButton button) {
		String bc = button.getActionCommand();
    	return Integer.parseInt(bc.substring(0, bc.length()-1));
	}
	
	protected int getSelectedColumn(JButton button) {
		String bc = button.getActionCommand();
    	return getIndexForAChar(bc.substring(bc.length()-1));
	}
    protected String getObjectName(Object o) 
    {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }
    private void ResetGame()
    {
    	neighborButtons.clear();
    	arrangedBoats.clear();
    	occupiedButtons.clear();
    	
    	neighborButtons2.clear();
    	arrangedBoats2.clear();
    	occupiedButtons2.clear();
    	
    	boat1Buttons.clear();
    	boat2Buttons.clear();
    	boat3Buttons.clear();
    	boat4Buttons.clear();
    	
    	boat1Buttons2.clear();
    	boat2Buttons2.clear();
    	boat3Buttons2.clear();
    	boat4Buttons2.clear();;
    	
    	  for (int i = 0; i < colChars.length; i++) {
              for (int j = 0; j < rowChars.length; j++) {
            	  primaryPlayerOneButtons[i][j].setBackground(Color.WHITE);
            	  primaryPlayerTwoButtons[i][j].setBackground(Color.WHITE);
            	  primaryPlayerOneButtons[i][j].setEnabled(true);
            	  primaryPlayerTwoButtons[i][j].setEnabled(true);
            	  
            	  secondaryPlayerOneButtons[i][j].setBackground(Color.WHITE);
            	  secondaryPlayerTwoButtons[i][j].setBackground(Color.WHITE);
            	  secondaryPlayerOneButtons[i][j].setEnabled(true);
            	  secondaryPlayerTwoButtons[i][j].setEnabled(true);
              }
          }
    }
    
    private boolean allBoatsOK(){
    	 ArrayList<Boat> sourceBoats = arrangedBoats;
			if(currentPlayer == 2) sourceBoats = arrangedBoats2;
			if(sourceBoats.size() < 4){
			//	getStatusLabel().setText("Boats for Player "+currentPlayer+" not fully configured!");
			SetStatusMessage("Boats for Player "+currentPlayer+" not fully configured!", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			return true;
    }
    
    private void QuitGame()
    {
    	//SetStatusMessage("Quiting...");
        System.exit(0);
    }

    /**
     * This method is called from the Mouse Click event.
     *
     */
    public void clickEvent(int row, int col)
    {
    	if(currentPlayer == 2 && arrangedBoats.size() < 4){
    		//I need to arrange the boats for player one
        		SetStatusMessage("Player One has not placed all boats on board.", JOptionPane.ERROR_MESSAGE);
        		return;
    	}
    	if(currentPlayer == 1 && arrangedBoats2.size() < 4){
    		//I need to arrange the boats for player one
    		
    		SetStatusMessage("Player Two has not placed all boats on board.", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	
    	
    	JButton button;
    	JButton button2;
    	if (currentPlayer == 2) {
    		button  = getSelectedPlayer2SecondaryButton(row-1, col-1);
    		button2  = getSelectedPlayer1PrimaryButton(row-1, col-1);
    	if(hitSuccess(occupiedButtons, button2)){
			button.setBackground(hitColor);
    	   // button2.setBackground(hitColor);
    	    //check if all has been hit
			 ArrayList<JButton> bts = getButtons(button);
   			 System.out.println("Boat size: "+bts.size());
   			 setButtonColor(button);
   			 colourAllButtons(bts);
    	    }
    	else{
			button.setBackground(missColor);
		//	button2.setBackground(missColor);
		}
    	}
    	else{
    		button  = getSelectedPlayer1SecondaryButton(row-1, col-1);
    		button2  = getSelectedPlayer2PrimaryButton(row-1, col-1);
    		if(hitSuccess(occupiedButtons2, button2)){
    			button.setBackground(hitColor);
        	   // button2.setBackground(hitColor);
        	  //check if all has been hit
   			 ArrayList<JButton> bts = getButtons(button);
   			 System.out.println("Boat size: "+bts.size());
   			setButtonColor(button);
   			 colourAllButtons(bts);
        	  }
        	else{
    			button.setBackground(missColor);
    		//	button2.setBackground(missColor);
    		}	
    	}
    	button.setEnabled(false);
    	SwitchPlayers();
    }
    public boolean hitSuccess(ArrayList< JButton> sourceBtns, JButton button){
    	for(JButton jb : sourceBtns){
 			if (jb.getActionCommand().equalsIgnoreCase(button.getActionCommand())){
 				return true;
 			}
 		}
    	return false;
    }
    public int getIndexForAChar(String charc)
    {
    	switch (charc) {
		case "A":
			return 1;
		case "B":
			return 2;
		case "C":
			return 3;
		case "D": 
			return 4;
		case "E":
			return 5;
		case "F":
			return 6;
		case "G":
			return 7;
		case "H":
			return 8;
		case "I":
			return 9;
		case "0J":
			return 10;
		default:
			return 10;
		}
    }
	public class ArrangeWindow extends JPanel{
		 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JComboBox<?> boatComboBox;
		 private JComboBox<?> rowComboBox;
		 private JComboBox<?> colComboBox;
		 
		 private JComboBox<?> orientComboBox = new JComboBox<Object>();
		 public ArrangeWindow() {
			   super(new BorderLayout());
		        // Panel for the labels
		        JPanel labelPanel = new JPanel(new GridLayout(5, 1)); // 5 rows 1 column
		        add(labelPanel, BorderLayout.WEST);
		        // Panel for the fields
		        JPanel fieldPanel = new JPanel(new GridLayout(5, 1)); // 5 rows 1 column
		        add(fieldPanel, BorderLayout.EAST);

		        // Options in the combobox
		        String[] options = { "Aircraft Carrier", "Battleship", "Destroyer","Patrol Boat"};
		        boatComboBox = new JComboBox<Object>(options);
		        boatComboBox.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
		        rowComboBox = new JComboBox<Object>(colChars);
		        rowComboBox.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
								}
							});
				colComboBox = new JComboBox<Object>(rowChars);
				colComboBox.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
					}
				});
				 String[] orientations = { "Horizontal", "Vertical"};
			     orientComboBox = new JComboBox<Object>(orientations);
			     orientComboBox.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							Boat b;
							switch (boatComboBox.getSelectedIndex()) {
							case 0:
								b=Boat.AIRCRAFT;
								break;
							case 1:
								b=Boat.BATTLESHIP;
								break;
							case 2:
								b=Boat.DESTROYER;
								break;
							case 3:
								b=Boat.PATROL;
								break;
							default:
								b=Boat.AIRCRAFT;
								break;
							}
							boolean goaAhead = true;
							 ArrayList<Boat> sourceBoats = arrangedBoats;
							if(currentPlayer == 2) sourceBoats = arrangedBoats2;
							
							for (Iterator<Boat> iterator = sourceBoats.iterator(); iterator
									.hasNext();) {
								if(b == (Boat) iterator.next()){
									SetStatusMessage("Boat already placed on board!",JOptionPane.ERROR_MESSAGE);
									goaAhead = false;
									break;
								}
							}
								int x = rowComboBox.getSelectedIndex();
								int y = colComboBox.getSelectedIndex();
								int o = orientComboBox.getSelectedIndex();
								if(goaAhead && validate(b,x,y,o )){
									//Paint the grid buttons
					 				paintAdjacentButtons(x, y, b, o);
								}
							
						}
					});
			     //button
			     JButton bt = new JButton("Done");
			     bt.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton button = (JButton)(e.getSource());
						Window w = SwingUtilities.getWindowAncestor(button);
						if(allBoatsOK()){
							SwitchPlayers();
							w.dispose();
						}
					}
				});
			     
		     // Labels
			    JLabel boatLabelCombo = new JLabel("Boat");
		        JLabel rowLabelTextField = new JLabel("Row");
		        JLabel columnLabelTextField = new JLabel("Column");
		        JLabel orientLabelTextField = new JLabel("Orientation");
		        // Add labels
		        labelPanel.add(boatLabelCombo);
		        fieldPanel.add(boatComboBox);
		        labelPanel.add(rowLabelTextField);
		        fieldPanel.add(rowComboBox);
		        labelPanel.add(columnLabelTextField);
		        fieldPanel.add(colComboBox);
		        labelPanel.add(orientLabelTextField);
		        labelPanel.add(new JLabel(""));
		        fieldPanel.add(orientComboBox);
		        fieldPanel.add(bt);

		        
		    }
		 
		 public boolean validate(Boat b, int x, int y, int orientation){
			 	if((orientation == 0) && (x+b.getSize() > rowChars.length) ||(orientation == 1) && (x+b.getSize() > colChars.length) ){
			 		SetStatusMessage("Placing a boat at "+x+","+y +" is NOT valid due to length",JOptionPane.ERROR_MESSAGE);
			 		return false;
			 	}
			 	else{
			 		JButton bt = getSelectedPlayer1PrimaryButton(x, y);
			 		ArrayList<JButton> sourceBtns = occupiedButtons;
			 		if(currentPlayer==2){
			 			sourceBtns= occupiedButtons2;
			 			bt = getSelectedPlayer2PrimaryButton(x, y);
			 		}
			 	    //place it on the grid
			 		for(JButton jb : sourceBtns){
			 			if (jb.getActionCommand().equalsIgnoreCase(bt.getActionCommand())){
			 				SetStatusMessage(jb.getActionCommand() + " already occupied.",JOptionPane.ERROR_MESSAGE);
			 				return false;
			 			}
			 		}
			 		ArrayList<JButton> sourceneighBtns = neighborButtons;
			 		if(currentPlayer==2)sourceneighBtns= neighborButtons2;
			 		for(JButton jb : sourceneighBtns){
			 			if (jb.getActionCommand().equalsIgnoreCase(bt.getActionCommand())){
			 				SetStatusMessage("Invalid placement.",JOptionPane.ERROR_MESSAGE);
			 				return false;
			 			}
			 		}
		    	return true;
			 		}
			 	}
		
		 public ArrayList<JButton> paintAdjacentButtons(int x, int y, Boat b, int orientation){
			 ArrayList<JButton> bts = new ArrayList<>(b.getSize());
			 JButton jb = null;
			 if(orientation == 0){
				 //horizontal
				 for (int t = y; t < (y+b.getSize()); t++){
					 if(currentPlayer == 1) jb = getSelectedPlayer1PrimaryButton(x, t);
					 else jb = getSelectedPlayer2PrimaryButton(x, t);  
					    jb.setBackground(b.getColour());
					    setNeighborButtons(x, t);
		 				bts.add(jb);
		 			}
			 }
			 else {
				 for (int t = x; t < x+b.getSize(); t++){
					 if(currentPlayer == 1) jb = getSelectedPlayer1PrimaryButton(t, y);
					 else jb = getSelectedPlayer2PrimaryButton(t,y); 
					setNeighborButtons(t, y);
				    jb.setBackground(b.getColour());
	 				bts.add(jb);
		 			}
			 }
			 b.setStartXPosition(x);
			 b.setStartYPosition(y);
			 b.setOrientation(orientation);
			 if(currentPlayer ==1){
				 occupiedButtons.addAll(bts);
				 arrangedBoats.add(b); 
				 if(b == Boat.AIRCRAFT){
					 boat1Buttons.addAll(bts);
					 System.out.println("Added : "+b.getName()+ "- "+boat1Buttons.size());
				 }
				 else if(b== Boat.BATTLESHIP){
					 boat2Buttons.addAll(bts);
					 System.out.println("Added : "+b.getName()+ "- "+boat2Buttons.size());
				 }
				 else if(b== Boat.DESTROYER){
					 boat3Buttons.addAll(bts);
					 System.out.println("Added : "+b.getName()+ "- "+boat3Buttons.size());
				 }
				 else{
					 boat4Buttons.addAll(bts);
					 System.out.println("Added : "+b.getName()+ "- "+boat4Buttons.size());
				 }
				 
			 }
			 else{
				 occupiedButtons2.addAll(bts);
				 arrangedBoats2.add(b);
				 if(b == Boat.AIRCRAFT){
					 boat1Buttons2.addAll(bts);
					 System.out.println("Added : "+b.getName()+ "- "+boat1Buttons2.size());
				 }
				 else if(b== Boat.BATTLESHIP){
					 boat2Buttons2.addAll(bts);
					 System.out.println("Added : "+b.getName()+ "- "+boat2Buttons2.size());
				 }
				 else if(b== Boat.DESTROYER){
					 boat3Buttons2.addAll(bts);
					 System.out.println("Added : "+b.getName()+ "- "+boat3Buttons2.size());
				 }
				 else{
					 boat4Buttons2.addAll(bts);
					 System.out.println("Added : "+b.getName()+ "- "+boat4Buttons2.size());
				 } 
			 
			 }
			 return bts;
		 }
			 
	 }
	 
	 public JButton getSelectedPlayer1PrimaryButton(int x, int y){
			 return (JButton) primaryPlayerOneButtons[x][y];
	 }
	 
	 public JButton getSelectedPlayer2PrimaryButton(int x, int y){
			 return (JButton) primaryPlayerTwoButtons[x][y];
	 }
	 
	 public JButton getSelectedPlayer1SecondaryButton(int x, int y){
			 return (JButton) secondaryPlayerOneButtons[x][y];
	 }
	 
	 public JButton getSelectedPlayer2SecondaryButton(int x, int y){
		 return (JButton) secondaryPlayerTwoButtons[x][y];
 }
	 
	 public void setNeighborButtons(int x, int y){
		 if(currentPlayer ==1){
			 if(x != rowChars.length-1) neighborButtons.add(getSelectedPlayer1PrimaryButton(x+1, y));
			 if(x !=0) neighborButtons.add(getSelectedPlayer1PrimaryButton(x-1, y));
			 if(y != colChars.length-1) neighborButtons.add(getSelectedPlayer1PrimaryButton(x, y+1));
			 if(y !=0) neighborButtons.add(getSelectedPlayer1PrimaryButton(x, y-1));
		 }
		 else{
			 if(x != rowChars.length-1) neighborButtons2.add(getSelectedPlayer2PrimaryButton(x+1, y));
			 if(x !=0) neighborButtons2.add(getSelectedPlayer2PrimaryButton(x-1, y));
			 if(y != colChars.length-1) neighborButtons2.add(getSelectedPlayer2PrimaryButton(x, y+1));
			 if(y !=0) neighborButtons2.add(getSelectedPlayer2PrimaryButton(x, y-1));
		 }
	 } 
	 public Boat getBoat(JButton btn){
		 if(currentPlayer ==1){
				for(JButton jb : boat1Buttons){
		 			if (jb.getActionCommand().equalsIgnoreCase(btn.getActionCommand())){
		 				return Boat.AIRCRAFT;
		 			}
		 		}	
		 		for(JButton jb : boat2Buttons){
	 			if (jb.getActionCommand().equalsIgnoreCase(btn.getActionCommand())){
	 				return Boat.BATTLESHIP;
	 				}
	 			}
	 			for(JButton jb : boat3Buttons){
		 			if (jb.getActionCommand().equalsIgnoreCase(btn.getActionCommand())){
		 				return Boat.DESTROYER;
		 			}
	 			}
	 			for(JButton jb : boat4Buttons){
		 			if (jb.getActionCommand().equalsIgnoreCase(btn.getActionCommand())){
		 				return Boat.PATROL;
		 			}
	 			}
		 			
	 		}
		 else{
			 for(JButton jb : boat1Buttons2){
		 			if (jb.getActionCommand().equalsIgnoreCase(btn.getActionCommand())){
		 				return Boat.AIRCRAFT;
		 			}
		 		}	
		 		for(JButton jb : boat2Buttons2){
	 			if (jb.getActionCommand().equalsIgnoreCase(btn.getActionCommand())){
	 				return Boat.BATTLESHIP;
	 				}
	 			}
	 			for(JButton jb : boat3Buttons2){
		 			if (jb.getActionCommand().equalsIgnoreCase(btn.getActionCommand())){
		 				return Boat.DESTROYER;
		 			}
	 			}
	 			for(JButton jb : boat4Buttons2){
		 			if (jb.getActionCommand().equalsIgnoreCase(btn.getActionCommand())){
		 				return Boat.PATROL;
		 			}
	 			}
		 }
		return null; 
	 }
	 private ArrayList<JButton> getButtons(JButton b){
			 if(currentPlayer == 2){
				 System.out.println("Finding1 >> "+b.getActionCommand());
				for(JButton jb : boat1Buttons){
					if (jb.getActionCommand().equalsIgnoreCase(b.getActionCommand())){
		 				return boat1Buttons;
		 			}
				}
				for(JButton jb : boat2Buttons){
					if (jb.getActionCommand().equalsIgnoreCase(b.getActionCommand())){
		 				return boat2Buttons;
		 			}
				}
				for(JButton jb : boat3Buttons){
					if (jb.getActionCommand().equalsIgnoreCase(b.getActionCommand())){
		 				return boat3Buttons;
		 			}
				}
				for(JButton jb : boat4Buttons){
					if (jb.getActionCommand().equalsIgnoreCase(b.getActionCommand())){
		 				return boat4Buttons;
		 			}
				}
			 }
			 else{
				 System.out.println("Finding2 >> "+b.getActionCommand());
					for(JButton jb : boat1Buttons2){
						if (jb.getActionCommand().equalsIgnoreCase(b.getActionCommand())){
			 				return boat1Buttons2;
			 			}
					}
					for(JButton jb : boat2Buttons2){
						if (jb.getActionCommand().equalsIgnoreCase(b.getActionCommand())){
			 				return boat2Buttons2;
			 			}
					}
					for(JButton jb : boat3Buttons2){
						if (jb.getActionCommand().equalsIgnoreCase(b.getActionCommand())){
			 				return boat3Buttons2;
			 			}
					}
					for(JButton jb : boat4Buttons2){
						if (jb.getActionCommand().equalsIgnoreCase(b.getActionCommand())){
			 				return boat4Buttons2;
			 			}
					}
				 }
			 return null;
	 }
	 private void setButtonColor(JButton b){
		 if(currentPlayer == 1){
			 System.out.println("Colouring: "+b.getActionCommand());
			for(JButton jb : boat1Buttons){
				if (jb.getActionCommand().equalsIgnoreCase(b.getActionCommand())){
	 				hit1Buttons++;
	 			}
			}
			for(JButton jb : boat2Buttons){
				if (jb.getActionCommand().equalsIgnoreCase(b.getActionCommand())){
					hit2Buttons++;
	 			}
			}
			for(JButton jb : boat3Buttons){
				if (jb.getActionCommand().equalsIgnoreCase(b.getActionCommand())){
					hit3Buttons++;
	 			}
			}
			for(JButton jb : boat4Buttons){
				if (jb.getActionCommand().equalsIgnoreCase(b.getActionCommand())){
					hit4Buttons++;
	 			}
			}
			System.out.println("Coloured so far: "+hit1Buttons+","+hit2Buttons+","+hit3Buttons+","+hit4Buttons);
		 }
		 else{
			 System.out.println("Colouring for Player 2: "+b.getActionCommand());
				for(JButton jb : boat1Buttons2){
					if (jb.getActionCommand().equalsIgnoreCase(b.getActionCommand())){
						hit1Buttons2++;
		 			}
				}
				for(JButton jb : boat2Buttons2){
					if (jb.getActionCommand().equalsIgnoreCase(b.getActionCommand())){
						hit2Buttons2++;
		 			}
				}
				for(JButton jb : boat3Buttons2){
					if (jb.getActionCommand().equalsIgnoreCase(b.getActionCommand())){
						hit3Buttons2++;
		 			}
				}
				for(JButton jb : boat4Buttons2){
					if (jb.getActionCommand().equalsIgnoreCase(b.getActionCommand())){
						hit4Buttons2++;
		 			}
				}
				System.out.println("Coloured so far 2: "+hit1Buttons2+","+hit2Buttons2+","+hit3Buttons2+","+hit4Buttons2);
			 }
		 
 }
	 private void colourAllButtons(ArrayList<JButton>  buttons){
		 int counter = 0;
		 if(currentPlayer == 2){
			 //look up in the player two's grid
			 if(buttons.size() == Boat.AIRCRAFT.getSize()){
				 counter = hit1Buttons2;
				 if(hit1Buttons2 == buttons.size()){
					 for(JButton j : buttons){
		   				j.setBackground(criticalHitColor);
		   			 }
					 SetStatusMessage("Player One\'s "+Boat.AIRCRAFT.getName()+" sunk.", JOptionPane.INFORMATION_MESSAGE);
					 hitBoats2++;
				 }
				 
			 }
			 if(buttons.size() == Boat.BATTLESHIP.getSize()){
				 counter = hit2Buttons2;
				 if(hit2Buttons2 == buttons.size()){
					 for(JButton j : buttons){
		   				j.setBackground(criticalHitColor);
		   			 }
		   				SetStatusMessage("Player One\'s "+Boat.BATTLESHIP.getName()+" sunk.", JOptionPane.INFORMATION_MESSAGE);
		   				hitBoats2++;
		   			}
			 }
			 if(buttons.size() == Boat.DESTROYER.getSize()){
				 counter = hit3Buttons2;
				 if(hit3Buttons2 == buttons.size()){
					 for(JButton j : buttons){
		   				j.setBackground(criticalHitColor);
		   			 }
					 SetStatusMessage("Player One\'s "+Boat.DESTROYER.getName()+" sunk.", JOptionPane.INFORMATION_MESSAGE);
					 hitBoats2++;
					 }
			 }
			 if(buttons.size() == Boat.PATROL.getSize()){
				 counter = hit4Buttons2;
				 if(hit4Buttons2 == buttons.size()){
					 for(JButton j : buttons){
		   				j.setBackground(criticalHitColor);
		   			 }
					 SetStatusMessage("Player One\'s "+Boat.PATROL.getName()+" sunk.", JOptionPane.INFORMATION_MESSAGE);
					 hitBoats2++;
					 }
			 }
		 }
		 else{
			 //look up in the player Two's grid
			 if(buttons.size() == Boat.AIRCRAFT.getSize()){
				 counter = hit1Buttons;
				 if(hit1Buttons == buttons.size()){
					 for(JButton j : buttons){
		   				j.setBackground(criticalHitColor);
		   			 }
					 SetStatusMessage("Player Two\'s "+Boat.AIRCRAFT.getName()+" sunk.", JOptionPane.INFORMATION_MESSAGE);
					 hitBoats++;
				 }
				 }
			 if(buttons.size() == Boat.BATTLESHIP.getSize()){
				 counter = hit2Buttons;
				 if(hit2Buttons == buttons.size()){
					 for(JButton j : buttons){
		   				j.setBackground(criticalHitColor);
		   			 }
						SetStatusMessage("Player Two\'s "+Boat.BATTLESHIP.getName()+" sunk.", JOptionPane.INFORMATION_MESSAGE);
						hitBoats++;
					}
			 }
			 if(buttons.size() == Boat.DESTROYER.getSize()){
				 counter = hit3Buttons;
				 if(hit3Buttons == buttons.size()){
					 for(JButton j : buttons){
		   				j.setBackground(criticalHitColor);
		   			 }
					 SetStatusMessage("Player Two\'s "+Boat.DESTROYER.getName()+" sunk.", JOptionPane.INFORMATION_MESSAGE);
					 hitBoats++;
					 }
			 }
			 if(buttons.size() == Boat.PATROL.getSize()){
				counter = hit4Buttons;
				 if(hit4Buttons == buttons.size()){
					 for(JButton j : buttons){
		   				j.setBackground(criticalHitColor);
		   			 }
					 SetStatusMessage("Player Two\'s "+Boat.PATROL.getName()+" sunk.", JOptionPane.INFORMATION_MESSAGE);
					 hitBoats++;
					 }
			 }
		 }
		 System.out.println("Hit buttons: "+counter);
		 System.out.println("Sunk Boats 1: "+hitBoats);
		 System.out.println("Sunk Boats 2: "+hitBoats2);
		 if(hitBoats == 4){
			 SetStatusMessage("Game Complete!", JOptionPane.INFORMATION_MESSAGE);
			 SetStatusMessage("Player Two wins the game.", JOptionPane.INFORMATION_MESSAGE);
		 }
		 else if(hitBoats2 == 4){
			 SetStatusMessage("Game Complete!", JOptionPane.INFORMATION_MESSAGE);
			 SetStatusMessage("Player One wins the game.", JOptionPane.INFORMATION_MESSAGE);
			 
		 }
	 }
}
