package com.boatbattles.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.boatbattles.main.BoatBattle;
import com.boatbattles.models.Boat;

public class ClickListener extends BoatBattle implements ActionListener {
	public ArrayList<JButton> occupiedButtons = new ArrayList<>();
	public ArrayList<Boat> arrangedBoats = new ArrayList<>();
	public ArrayList<JButton> neighborButtons = new ArrayList<>();
	
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
	       	              f.setLocationRelativeTo(null);
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
	            else if (menutext.equals("Quit"))
	            {
	            	//Quit the application
	            	QuitGame();
	            }
	            else
	            {
	            	//show default error message for non selection
	            	System.out.println("No selection made");
	            }
	        }
	        // Handle the event from the user clicking on a command button
	        else if (classname.equals("JButton"))
	        {
	        	JButton button = (JButton)(e.getSource());
	        	clickEvent(getSelectedRow(button), getSelectedColumn(button));
	        	button.setEnabled(false);
	        	//Check if there is a boat
	        	
	        	//if there is, paint green
	        	//else paint it yellow
	        	
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
    public void ResetGame()
    {
    	neighborButtons.clear();
    	arrangedBoats.clear();
    	occupiedButtons.clear();
    	  for (int i = 0; i < colChars.length; i++) {
              for (int j = 0; j < rowChars.length; j++) {
              	gridTwobuttons[i][j].setBackground(Color.WHITE);
              	gridOnebuttons[i][j].setBackground(Color.WHITE);
              }
          }
    	  System.out.println("Game Reset");
    }
    
    public void QuitGame()
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
    	SetStatusMessage("Clicked: " + row + "," + col,JOptionPane.INFORMATION_MESSAGE);
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
		        JPanel labelPanel = new JPanel(new GridLayout(4, 1)); // 4 rows 1 column
		        add(labelPanel, BorderLayout.WEST);

		        // Panel for the fields
		        JPanel fieldPanel = new JPanel(new GridLayout(4, 1)); // 4 rows 1 column
		        add(fieldPanel, BorderLayout.EAST);

		        // Options in the combobox
		        String[] options = { "Aircraft Carrier", "Battleship", "Destroyer","Patrol Boat"};
		        boatComboBox = new JComboBox(options);
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
							for (Iterator iterator = arrangedBoats.iterator(); iterator
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
		        fieldPanel.add(orientComboBox);

		        
		    }
		 
		 public boolean validate(Boat b, int x, int y, int orientation){
			 	if((orientation == 0) && (x+b.getSize() > rowChars.length) ||(orientation == 1) && (x+b.getSize() > colChars.length) ){
			 		SetStatusMessage("Placing a boat at "+x+","+y +" is NOT valid due to length",JOptionPane.ERROR_MESSAGE);
			 		return false;
			 	}
			 	else{
			 		JButton bt = getSelectedButton(x, y);
			 	    //place it on the grid
			 		for(JButton jb : occupiedButtons){
			 			if (jb.getActionCommand().equalsIgnoreCase(bt.getActionCommand())){
			 				SetStatusMessage(jb.getActionCommand() + " already occupied.",JOptionPane.ERROR_MESSAGE);
			 				return false;
			 			}
			 		}
			 		for(JButton jb : neighborButtons){
			 			if (jb.getActionCommand().equalsIgnoreCase(bt.getActionCommand())){
			 				SetStatusMessage(jb.getActionCommand() + " is neighbour to occupied position.",JOptionPane.ERROR_MESSAGE);
			 				return false;
			 			}
			 		}
			 	//SetStatusMessage("Placing a boat at "+x+","+y +" is valid",JOptionPane.INFORMATION_MESSAGE);
		    	return true;
			 		}
			 	}
		 
		 public JButton getSelectedButton(int x, int y){
			 JButton bt = new JButton();
			 if(currentPlayer == 1) bt = (JButton) gridOnebuttons[x][y];
			 else bt = (JButton) gridTwobuttons[x][y];
			 return bt;
		 }
		 public void setNeighborButtons(int x, int y){
				 if(x != rowChars.length-1) neighborButtons.add(getSelectedButton(x+1, y));
				 if(x !=0) neighborButtons.add(getSelectedButton(x-1, y));
				 if(y != colChars.length-1) neighborButtons.add(getSelectedButton(x, y+1));
				 if(y !=0) neighborButtons.add(getSelectedButton(x, y-1));
			 System.out.println("Neighbours size: "+neighborButtons.size());
		 } 
		 public ArrayList<JButton> paintAdjacentButtons(int x, int y, Boat b, int orientation){
			 System.out.println("Length:" +b.getSize());
			 ArrayList<JButton> bts = new ArrayList<>(b.getSize());
			 JButton jb = null;
			 if(orientation == 0){
				 //horizontal
				 for (int t = y; t < (y+b.getSize()); t++){
					    jb = getSelectedButton(x, t);
					    jb.setBackground(b.getColour());
					    setNeighborButtons(x, t);
		 				bts.add(jb);
		 			}
			 }
			 else {
				 for (int t = x; t < x+b.getSize(); t++){
					jb = getSelectedButton(t, y);
					setNeighborButtons(t, y);
				    jb.setBackground(b.getColour());
	 				bts.add(jb);
		 			}
			 }
			 occupiedButtons.addAll(bts);
			 arrangedBoats.add(b);
			 return bts;
		 }
			 
	 }
}
