package sait.frs.exception;

public class InvalidFlightCodeException extends Exception{
	public InvalidFlightCodeException(){
		super("The following flight contians a non-valid airline");
	}
}
