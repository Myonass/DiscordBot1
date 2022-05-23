package commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;


public class BotCommands extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if(event.getName().equals("fart")){

            event.deferReply().queue();

            event.getHook().sendMessage("dude, are you so fart").queue();

        } else if (event.getName().equals("food")) {

            OptionMapping option = event.getOption("name");
            if (option == null){
                event.reply("for some reason, a food name was not provided");

                return;
            }
            event.deferReply().queue();

            String favoritefood = option.getAsString();

            event.getHook().sendMessage("Your favorite food is: " + favoritefood).queue();
        } else if (event.getName().equals("sum")) {

            OptionMapping operand1 = event.getOption("operator1");
            OptionMapping operand2 = event.getOption("operator2");

            if(operand1 == null || operand2 == null){

                event.reply("no numbers were provided").queue();

                return;
            }

            int sum = operand1.getAsInt() + operand2.getAsInt();

            event.reply("The sum is: " + sum).queue();
        }
    }
}

