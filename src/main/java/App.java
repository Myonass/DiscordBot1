import commands.BotCommands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class App {

    public static void main(String[] args) throws Exception{
        JDA jda = JDABuilder.createDefault("OTc2NTczNzMwOTYyNjgxOTc2.Gf2WO8.2jg7mf5DH-DI9yiCT1-agemYaZQKTe1auEvBFA")
                .setActivity(Activity.listening("Твою маму"))
                .addEventListeners(new BotCommands())
        .build().awaitReady();


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
