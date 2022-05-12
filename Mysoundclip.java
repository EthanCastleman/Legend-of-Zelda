/*
	Name: Ethan Castleman
	Date: 3/29/2022
	Description: Mysoundclip.java
*/

import java.io.File;
import javax.sound.sampled.*;
class MySoundClip {
    Clip[] clips;
    int pos;

    MySoundClip(String filename, int copies) throws Exception {
      clips = new Clip[copies];
      for(int i = 0; i < copies; i++) {
        AudioInputStream inputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        AudioFormat format = inputStream.getFormat();
        DataLine.Info info =
          new DataLine.Info(Clip.class, format);
        clips[i] = (Clip)AudioSystem.getLine(info);
        clips[i].open(inputStream);
      }
      pos = 0;
    }

    public void play() {
        clips[pos].setFramePosition(0);
        clips[pos].loop(Clip.LOOP_CONTINUOUSLY);
        clips[pos].start();
        if(++pos >= clips.length)
            pos = 0;
    }
}
