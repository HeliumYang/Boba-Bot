package commands;

import helium.Boba.Boba;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help {
	public static void HelpMenu(String[] args, MessageReceivedEvent event) {
		EmbedBuilder help = new EmbedBuilder();
		if (args.length > 1) {
			switch (args[1]) {
				case "bm":
					
					break;
				case "bobamon":
					
					break;
				case "bobamonsters":
					
					break;
			}
		}
		help.setTitle("Information:");
		help.setDescription("Hello! My name is Boba. I am a bot that can do many things! I receive upgrades periodically so that I can do more things! Here's what I can do at the moment:");
		help.addField(Boba.prefix + "rb [# of toppings] [boba place (optional)]", "Having trouble choosing your boba order? let me decide for you!", false);
		help.setColor(0x34cceb);
		help.setFooter("Created by Helium Yang#7601", "https://cdn.discordapp.com/avatars/236612437514649612/d1f9deec400216f08455dfb5312c0a69.webp?size=300");
		
		MessageChannel channel = event.getChannel();
		channel.sendTyping().queue();
		channel.sendMessage(" ").setEmbeds(help.build()).queue();
		help.clear();
	}
}
