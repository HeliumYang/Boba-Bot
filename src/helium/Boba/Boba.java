package helium.Boba;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Boba {
	public static String prefix = "<";
	
	// Main method
	public static void main(String[] args) throws LoginException {
		File token = new File("token.txt");
		Scanner tokenScanner;
		try {
			tokenScanner = new Scanner(token);
			JDABuilder jda = JDABuilder.createDefault(tokenScanner.nextLine());
			token = null;
			tokenScanner = null;
			jda.setActivity(Activity.watching("intensely"));
			jda.setStatus(OnlineStatus.ONLINE);
			jda.addEventListeners(new Commands());
			jda.addEventListeners(new AdminCommands());
			jda.build();
			}
		catch (FileNotFoundException e) {
			System.exit(0);
			e.printStackTrace();
		}
	}
}
