import ca.tristan.jdacommands.JDACommands;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import commands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.cache.CacheFlag;


public class App extends AudioEventAdapter {

    public static void main(String[] args) throws Exception {

        JDACommands jdaCommands = new JDACommands("!");
        jdaCommands.registerCommand(new AudioCommands());
        jdaCommands.registerCommand(new StopCommands());
        jdaCommands.registerCommand(new SkipCommand());
        jdaCommands.registerCommand(new RepeatCommand());


        JDA jda = JDABuilder.createDefault("OTc2NTczNzMwOTYyNjgxOTc2.G4bo2d.WtdzJxKOHljUVcGz2pPPK4fxoPF2Pe9C9wn4_o")
                .setActivity(Activity.listening("Твою маму"))
                .addEventListeners(new BotCommands())
                .enableCache(CacheFlag.VOICE_STATE)
                .addEventListeners(jdaCommands)
                .build();


        Guild test1 = jda.getGuildById("500316970919395338");

        if (test1 != null) {
            jda.upsertCommand("fart", "fart with power").queue();
            jda.upsertCommand("food", "Name your favorite food").
                    addOption(OptionType.STRING, "name", "the name is your favorite food", true)
                    .queue();
            jda.upsertCommand("sum", "add two numbers")
                    .addOptions(
                            new OptionData(OptionType.INTEGER, "operator1", "the first number", true)
                                    .setRequiredRange(1, Integer.MAX_VALUE),
                            new OptionData(OptionType.INTEGER, "operator2", "the second number", true)
                                    .setRequiredRange(1, Integer.MAX_VALUE)
                    )
                    .queue();

        }


    }

}
