package application;

public class DateException extends Exception {
	
	public String toString() {
		return "Vous ne pouvez pas choisir la date d'aujourd'hui ou une date ant�rieur � aujourd'hui.\nVeuillez introduire une date future";
	}

}
