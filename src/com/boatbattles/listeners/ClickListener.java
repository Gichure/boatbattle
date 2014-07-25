package com.boatbattles.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;


public class ClickListener implements ActionListener {
	public JFrame frame;
	@Override
	public void actionPerformed(ActionEvent e) {
		 String classname = getObjectName(e.getSource());
	     //JComponent component = (JComponent)(e.getSource());

	        if (classname.equals("JMenuItem"))
	        {
	            JMenuItem menusource = (JMenuItem)(e.getSource());
	            String menutext  = menusource.getText();

	            // Determine which menu option was chosen
	            if (menutext.equals("Arrange Boat"))
	            {
	               //Hide boats
	            	StartGame();
	            }
	            else if (menutext.equals("Reset"))
	            {
		               //Hide boats
		            	StartGame();
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
	        	String bc = button.getActionCommand();
	        	System.out.println("Row: "+Integer.parseInt(bc.substring(0, bc.length()-1)));
	        	System.out.println(getIndexForAChar(bc.substring(bc.length()-1)));
	        	System.out.println("Column: "+getIndexForAChar(bc.substring(bc.length()-1)));
	        	button.setEnabled(false);
	        	//Check if there is a boat
	        	
	        	//if there is, paint green
	        	//else paint it yellow
	        	
	        }  
	}
	/**
     *  Returns the object name of a jswing variable
     */
    protected String getObjectName(Object o) 
    {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }
    public void StartGame()
    {
          System.out.println("Start game selected");
    }
    
    public void QuitGame()
    {
          System.out.println("Quiting...");
    }

    /**
     * This method is called from the Mouse Click event.
     *
     */
    public void clickEvent(int row, int col)
    {
          System.out.println("Clicked: " + row + ", " + col);
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
			return -1;
		}
    }
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
    
    
}
