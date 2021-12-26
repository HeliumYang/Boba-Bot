package commands;

import helium.Boba.Boba;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
	public void onMessageReceived(MessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		//TODO make this into a case switch for more efficient execution
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
			help.addField(Boba.prefix + "randomboba [# of toppings] [boba place (optional)]", "Having trouble choosing your boba order? let me decide for you!", false);
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
		else if (args[0].equalsIgnoreCase(Boba.prefix + "randomboba") || args[0].equalsIgnoreCase(Boba.prefix + "rb") || args[0].equalsIgnoreCase(Boba.prefix + "bobaorder")) {
			RandomBobaGenerator.randomBoba(args, event);
		}
		System.gc();
	}
}
