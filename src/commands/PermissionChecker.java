package commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PermissionChecker {
	public static boolean checkPermission(String[] args, MessageReceivedEvent event, Permission permissionRequired) {
		if (!event.getMember().hasPermission(permissionRequired)) {
		MessageChannel channel = event.getChannel();
		channel.sendTyping().queue();
		channel.sendMessage("I'm sorry, but it doesn't look like you have the permission to do that.").queue();
		return false;
		}
		return true;
	}
}
