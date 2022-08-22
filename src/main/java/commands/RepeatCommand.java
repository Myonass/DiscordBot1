package commands;

import audio.GuildMusicManager;
import audio.PlayerManager;
import ca.tristan.jdacommands.ExecuteArgs;
import ca.tristan.jdacommands.ICommand;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import java.net.URI;
import java.net.URISyntaxException;

public class RepeatCommand implements ICommand {
    @Override
    public void execute(ExecuteArgs event) {
        if (!event.getMemberVoiceState().inAudioChannel()) {
            event.getTextChannel().sendMessage("Тебе нужно быть в голосовом канале, чтобы запустить бота").queue();
            return;
        }

        if (!event.getSelfVoiceState().inAudioChannel()) {
            final AudioManager audioManager = event.getGuild().getAudioManager();
            final VoiceChannel memberChannel = (VoiceChannel) event.getMemberVoiceState().getChannel();

            audioManager.openAudioConnection(memberChannel);
        }

        String link = String.join(" ", event.getArgs());

        if (!isUrl(link)) {
            link = "ytsearch:" + link + " audio";
        }

        final GuildMusicManager musicManager = PlayerManager.getINSTANCE().getMusicManager(event.getGuild());
        final boolean newRepeating = !musicManager.scheduler.repeating;

        musicManager.scheduler.repeating = newRepeating;

        event.getTextChannel().sendMessageFormat("Трек поставлен на повтор").queue();
    }
    public boolean isUrl(String url) {
        try {
            new URI(url);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }


    @Override
    public String getName() {
        return "repeat";
    }

    @Override
    public String helpMessage() {
        return "Повтор трека";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
