/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.gfx;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author Robert
 */
public class SoundEffect {

    Clip clip;

    public SoundEffect(String path, float volume) {
        setFile(path, volume);
    }

    public void setFile(String path, float volume) {
        try {
            File file = new File(path);
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            this.clip = AudioSystem.getClip();
            this.clip.open(sound);
        } catch (Exception e) {
            System.out.println("Error setting sound file path: "+path);
        }
    }

    public void playSound() {
        clip.setFramePosition(0);
        clip.start();
    }
    
    public void stopSound() {
        clip.stop();
        clip.close();
    }

    public void loopSound() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        setVolume(0.5f);
    }

    public void setVolume(float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
}
