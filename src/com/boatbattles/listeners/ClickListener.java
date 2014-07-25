package com.boatbattles.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;

public class ClickListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		 String classname = getObjectName(e.getSource());
	     //JComponent component = (JComponent)(e.getSource());

	        if (classname.equals("JMenuItem"))
	        {
	            JMenuItem menusource = (JMenuItem)(e.getSource());
	            String menutext  = menusource.getText();

	            // Determine which menu option was chosen
	            if (menutext.equals("Start"))
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
	            int bnum = Integer.parseInt(button.getActionCommand());
	            int row = bnum / 10;
	            int col = bnum % 10;

	            /* BATTLEGUI    Add your code here to handle user clicking on the grid ***********/
	            clickEvent(row, col);
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
}
