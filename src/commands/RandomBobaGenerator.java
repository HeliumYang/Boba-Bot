package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;

import helium.Boba.Boba;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class RandomBobaGenerator {
	public static void randomBoba(String[] args, MessageReceivedEvent event) {
		if (args.length < 2) {
			MessageChannel channel = event.getChannel();
			channel.sendTyping().queue();
			channel.sendMessage("Please specify how many toppings you want with your drink.\nCorrect command usage: " + Boba.prefix + "randomboba [# of toppings]").queue();
			return;
		}
		MessageChannel channel = event.getChannel();
		String[] topping = new String[0];
		String[] drink = new String[0];
		File toppingList;
		File drinkList;
		String orderingPlace = "";
		if (args.length > 2) {
			for (int i = 2; i < args.length; i++) {
				orderingPlace = orderingPlace.concat(" " + args[i]).trim();
			}
			toppingList = new File("bobaMemory/toppingList_" + orderingPlace.toLowerCase().replace(" ", "") + ".txt");
			drinkList = new File("bobaMemory/drinkList_" + orderingPlace.toLowerCase().replace(" ", "") + ".txt");
		}
		else {
			toppingList = new File("bobaMemory/toppingList.txt");
			drinkList = new File("bobaMemory/drinkList.txt");
		}
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
			if (topping.length == 0 || drink.length == 0) {
				channel.sendTyping().queue();
				if (args.length > 2) {
				channel.sendMessage("I'm sorry, but it seems like the menu I have from " + args[2] +" is corrupt.").queue();
				}
				else {
					channel.sendMessage("I'm sorry, but it seems like my memory of boba is corrupt.").queue();
				}
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
						EmbedBuilder bobaOrder = new EmbedBuilder();
						bobaOrder.setTitle(":bubble_tea: Let's go with a...");
						bobaOrder.setDescription(randomBoba + ".");
						bobaOrder.setFooter("Random boba generation brought to you by Boba Bot", "https://cdn.discordapp.com/avatars/923736863443918878/8672ec3e9cebe15243d00c29101714e3.webp?size=300");
						if (orderingPlace.equals("")) {
							channel.sendMessage("BEEP BOOP. Generating random boba order...").setEmbeds(bobaOrder.build()).queue();
						}
						else {
							channel.sendMessage("BEEP BOOP. Generating random boba order for " + orderingPlace + "...").setEmbeds(bobaOrder.build()).queue();
							orderingPlace = "";
						}
						bobaOrder.clear();
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
			orderingPlace = "";
		}
		catch (FileNotFoundException e) {
			if (args.length < 3) {
				channel.sendTyping().queue();
				channel.sendMessage("I'm sorry, but it seems like I don't have a menu from " + args[2] +".").queue();
				e.printStackTrace();
			}
			else {
				channel.sendTyping().queue();
				channel.sendMessage("I'm sorry, but for some reason I cannot remember any boba orders at the moment. Please ask me again later.").queue();
				e.printStackTrace();
			}
		}
	}
}
