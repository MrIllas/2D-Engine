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

    public SoundEffect(String path, int volume) {
        setFile(path, volume);
    }

    public void setFile(String path, int volume) {
        try {
            File file = new File(path);
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            this.clip = AudioSystem.getClip();
            this.clip.open(sound);
        } catch (Exception e) {
            System.out.println("ffffff");
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
    }

    public void setVolume(int level) {
        Objects.requireNonNull(this.clip);
        FloatControl volume = (FloatControl) this.clip.getControl(FloatControl.Type.VOLUME);
        if (volume != null) {
            volume.setValue((float) (level / 100.0));
        }
    }
}
