package helium.Boba;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
	public void onMessageReceived(MessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		if (args[0].equalsIgnoreCase(Boba.prefix + "test")) {
			MessageChannel channel = event.getChannel();
			channel.sendTyping().queue();
			channel.sendMessage("Boba. :bubble_tea:").queue();
		}
		else if (args[0].equalsIgnoreCase(Boba.prefix + "help") || args[0].equalsIgnoreCase(Boba.prefix + "info")) {
			EmbedBuilder help = new EmbedBuilder();
			help.setTitle("Information:");
			help.setDescription("Hello! My name is Boba. I am a bot that can do many things! I receive upgrades periodically so that I can do more things! Here's what I can do at the moment:");
			help.addField(Boba.prefix + "test", ":bubble_tea:", false);
			help.addField(Boba.prefix + "randomboba [# of toppings]", "Having trouble choosing your boba order? let me decide for you!", false);
			help.setColor(0xf45642);
			help.setFooter("Created by Helium Yang#7601", "https://cdn.discordapp.com/avatars/236612437514649612/d1f9deec400216f08455dfb5312c0a69.webp?size=300");
			
			MessageChannel channel = event.getChannel();
			channel.sendTyping().queue();
			channel.sendMessage(" ").setEmbeds(help.build()).queue();
			help.clear();
		}
		else if (args[0].equalsIgnoreCase("I'm")) {
			int rng = (int)(Math.random() * 100);
			
			if (rng == 1) {
				MessageChannel channel = event.getChannel();
				String dadMessage = "Hi";
				for (int i = 1; i < args.length; i++) {
					dadMessage = dadMessage.concat(" " + args[i]);
				}
				dadMessage = dadMessage.concat(", I'm Boba!");
				channel.sendTyping().queue();
				channel.sendMessage(dadMessage).queue();
				dadMessage = "";
			}
		}
		else if ((args[0].equalsIgnoreCase(Boba.prefix + "randomboba") || args[0].equalsIgnoreCase(Boba.prefix + "rb") || args[0].equalsIgnoreCase(Boba.prefix + "bobaorder")) && args.length < 2) {
			MessageChannel channel = event.getChannel();
			channel.sendTyping().queue();
			channel.sendMessage("Please specify how many toppings you want with your drink.\nCorrect command usage: " + Boba.prefix + "randomboba [# of toppings]").queue();
		}
		else if (args[0].equalsIgnoreCase(Boba.prefix + "randomboba") || args[0].equalsIgnoreCase(Boba.prefix + "rb") || args[0].equalsIgnoreCase(Boba.prefix + "bobaorder")) {
			MessageChannel channel = event.getChannel();
			String[] topping = new String[0];
			String[] drink = new String[0];
			File toppingList = new File("toppingList.txt");
			File drinkList = new File("drinkList.txt");
			Scanner toppingScanner;
			Scanner drinkScanner;
			try {
				toppingScanner = new Scanner(toppingList);
				while(toppingScanner.hasNextLine()) {
					topping = ArrayUtils.add(topping, toppingScanner.nextLine());
				}
				drinkScanner = new Scanner(drinkList);
				while(drinkScanner.hasNextLine()) {
					drink = ArrayUtils.add(drink, drinkScanner.nextLine());
				}
				if ((int)(Math.random()*100) == 1) {
					channel.sendTyping().queue();
					channel.sendMessage("I think you've had too much boba. You shouldn't get anything.").queue();
				}
				else {
					channel.sendTyping().queue();
					String randomBoba = drink[(int)(Math.random()*drink.length)];
					int[] toppingsAlreadyInBoba = new int[0];
					try {
						if (Integer.parseInt(args[1]) > topping.length) {
							channel.sendTyping().queue();
							channel.sendMessage("Waaaaaa. That's too many toppings :< Try again with a smaller number of toppings.").queue();
						}
						else {
							for (int i = 0; i < Integer.parseInt(args[1]); i++) {
								int toppingNumber = (int)(Math.random()*topping.length);
								if (i == 0) {
									toppingNumber = (int)(Math.random()*topping.length);
									toppingsAlreadyInBoba = ArrayUtils.add(toppingsAlreadyInBoba, toppingNumber);
									randomBoba = randomBoba.concat(" with " + topping[toppingNumber]);
								}
								else {
									
									for (int j = 0; j < i; j++) {
										if (toppingNumber == toppingsAlreadyInBoba[j]) {
											j = -1;
											toppingNumber = (int)(Math.random()*topping.length);
										}
									}
									toppingsAlreadyInBoba = ArrayUtils.add(toppingsAlreadyInBoba, toppingNumber);
									randomBoba = randomBoba.concat(" and " + topping[toppingNumber]);
								}
							}
							channel.sendTyping().queue();
							channel.sendMessage("Let's go with a " + randomBoba + ".").queue();
							toppingsAlreadyInBoba = null;
						}
					}
			        catch (NumberFormatException ex){
			            ex.printStackTrace();
			            channel.sendTyping().queue();
						channel.sendMessage("Please put a valid number of toppings so I can actually give you a boba order :<").queue();
			        }
				}
		
				toppingList = null;
				drinkList = null;
				toppingScanner = null;
				drinkScanner = null;
				topping = null;
				drink = null;
			}
			catch (FileNotFoundException e) {
				channel.sendTyping().queue();
				channel.sendMessage("I'm sorry, but for some reason I can't find a menu. Once I get the list, I can give you my boba suggestion!").queue();
				e.printStackTrace();
			}
		}
		System.gc();
	}
}
