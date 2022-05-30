import ca.tristan.jdacommands.JDACommands;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import commands.AudioCommands;
import commands.BotCommands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.cache.CacheFlag;


public class App extends AudioEventAdapter {

    public static void main(String[] args) throws Exception{

        JDACommands jdaCommands = new JDACommands("!");
        jdaCommands.registerCommand(new AudioCommands());

        JDA jda = JDABuilder.createDefault("OTc2NTczNzMwOTYyNjgxOTc2.GeB84C.uJXSd_BswClFO9H5hdDziRVIzTR5jPCfgug5gM")
                .setActivity(Activity.listening("Твою маму"))
                .addEventListeners(new BotCommands())
                .enableCache(CacheFlag.VOICE_STATE)
                .addEventListeners(jdaCommands)
                .build();


        Guild test1 = jda.getGuildById("500316970919395338");

        if (test1 != null){
            jda.upsertCommand("fart", "fart with power").queue();
            jda.upsertCommand("food", "Name your favorite food").
                    addOption(OptionType.STRING, "name" , "the name is your favorite food", true)
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
