package commands;

import audio.GuildMusicManager;
import audio.PlayerManager;
import audio.GuildMusicManager;
import audio.PlayerManager;
import ca.tristan.jdacommands.ExecuteArgs;
import ca.tristan.jdacommands.ICommand;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import java.net.URI;
import java.net.URISyntaxException;

public class SkipCommand implements ICommand {
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



        final GuildMusicManager musicManager = PlayerManager.getINSTANCE().getMusicManager(event.getGuild());
        final AudioPlayer audioPlayer = musicManager.audioPlayer;

        if(audioPlayer.getPlayingTrack() == null){
            event.getTextChannel().sendMessage("Нет трека для пропуска").queue();
            return;
        }

        musicManager.scheduler.nextTrack();
        event.getTextChannel().sendMessage("Трек пропущен").queue();
    }



    @Override
    public String getName() {
        return "skip";
    }

    @Override
    public String helpMessage() {
        return "Скипнуть действующий трек";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
