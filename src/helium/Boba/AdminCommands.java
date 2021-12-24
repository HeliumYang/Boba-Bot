package helium.Boba;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class AdminCommands extends ListenerAdapter {
	public void onMessageReceived(MessageReceivedEvent event) {
		
	//	Permission userPermission = event.getMember().getPermissionsExplicit(event.getChannel());
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		if (args[0].equalsIgnoreCase(Boba.prefix + "admintest")) {
			MessageChannel channel = event.getChannel();
			channel.sendTyping().queue();
			channel.sendMessage("boba").queue();
		}
	}
}