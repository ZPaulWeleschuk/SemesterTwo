package sait.frs.exception;

import javax.swing.JOptionPane;

public class NoMoreSeatsException extends Exception{
	public NoMoreSeatsException(){
		super("There are no more seats available on this Flight");
		JOptionPane.showMessageDialog(null, "You Must Enter a Citizenship");
	}
}
