package com.codeindie.non.plugins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.codeindie.non.Non;
import java.io.IOException;
import java.util.HashSet;

public class Audio extends Plugin {
    public String author() { return "Thomas Slusny"; }
    public String license() { return "MIT"; }
    public String description() { return "Plugin for playing audio and music files."; }
    
    private Music currentMusic;
    private final HashSet<Sound> sounds = new HashSet<Sound>();
    
    public Audio play(Music music) {
        currentMusic = music;
        music.play();
        return this;
    }
    
    public Audio play(Sound sound) {
        sounds.add(sound);
        sound.play();
        return this;
    }
    
    public Audio stopMusic() {
        currentMusic.stop();
		return this;
    }
    
    public Audio stopSounds() {
        for(Sound sound: sounds) sound.stop();
		return this;
    }
    
    public Music newMusic(String file) throws IOException {
        return Gdx.audio.newMusic(Non.getResource(file));
    }
    
    public Sound newSound(String file) throws IOException {
        return Gdx.audio.newSound(Non.getResource(file));
    }
    
    public void dispose() {
        stopMusic().stopSounds();
        currentMusic.dispose();
        for(Sound sound: sounds) sound.dispose();
    }
}
