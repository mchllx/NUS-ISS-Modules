package console;

import java.io.Console;

public class ReadLine {
	public static void main(String[] args) {
	Console cons = System.console(); //Instantiate a console object, only from terminal
	String name = cons.readLine("What is your name?");
	System.out.printf("Hello %s. Pleased to meet you.\n", name);
	}
}
