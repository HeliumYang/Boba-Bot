package commands;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class NonPrefixCommands {
	public static void NoPrefix(String[] args, MessageReceivedEvent event) {
		if (args[0].equalsIgnoreCase("I'm")) {
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
	}
}
