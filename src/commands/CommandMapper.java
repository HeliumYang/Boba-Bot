package commands;

import helium.Boba.Boba;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandMapper extends ListenerAdapter {
	public void onMessageReceived(MessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if (String.valueOf(args[0].charAt(0)).equals(Boba.prefix)) {
			String command = args[0].substring(1).toLowerCase();
			switch (command) {
				case "help":
					Help.HelpMenu(args, event);
					break;
				case "info":
					Help.HelpMenu(args, event);
					break;
				case "randomboba":
					RandomBobaGenerator.randomBoba(args, event);
					break;
				case "rb":
					RandomBobaGenerator.randomBoba(args, event);
					break;
				case "bobaorder":
					RandomBobaGenerator.randomBoba(args, event);
					break;
				case "bm":
					bobamon.BobamonCommands.Commands(args, event);
					break;
				case "bobamon":
					bobamon.BobamonCommands.Commands(args, event);
					break;
				case "bobamonsters":
					bobamon.BobamonCommands.Commands(args, event);
					break;
			}
		}
		else {
			NonPrefixCommands.NoPrefix(args, event);
		}
		
		System.gc();
	}
}
