package Wekker;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.*;

public class AlarmModel extends Observable
{

    private boolean on;
    private boolean ringing;
    private Clip clip;

    protected int klokUur;
    protected int klokMinuut;
    protected int alarmUur;
    protected int alarmMinuut;


    public AlarmModel(int klokUur, int klokMinuut)
    {
        this.klokUur = klokUur;
        this.klokMinuut = klokMinuut;
        this.alarmUur = klokUur;
        this.alarmMinuut = klokMinuut;
    }

    public void volgendeKlokMinuut()
    {
        klokMinuut++;

        if (klokMinuut > 59)
        {
            klokMinuut = 0;
            klokUur = volgendUur(klokUur);
        }

        setChanged();
        notifyObservers();
    }

    public void addAlarmMinuut()
    {
        alarmMinuut++;

        if (alarmMinuut > 59)
        {
            alarmMinuut = 0;
            alarmUur = volgendUur(alarmUur);
        }

        setChanged();
        notifyObservers();
    }

    public void substractAlarmMinuut()
    {
        alarmMinuut--;

        if (alarmMinuut < 0)
        {
            alarmMinuut = 59;
            alarmUur = vorigUur(alarmUur);
        }

        setChanged();
        notifyObservers();
    }

    private int vorigUur(int uur)
    {

        uur--;

        if (uur < 0)
        {
            uur = 23;
        }

        return uur;
    }

    private int volgendUur(int uur)
    {

        uur++;

        if (uur > 23)
        {
            uur = 0;
        }

        return uur;
    }

    public String getClock()
    {
        return String.format("[%02d:%02d]", this.klokUur, this.klokMinuut);
    }

    public String getAlarm()
    {
        return String.format("[%02d:%02d]", this.alarmUur, this.alarmMinuut);
    }

    public void playSound()
    {
            try
            {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Week 3/resources/alarm.wav").getAbsoluteFile());
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            }
            catch(Exception ex)
            {
                System.out.println("Alarm sound error in constructor");
                ex.printStackTrace();
            }

    }

    public void stopSound(){
        try
        {
            if(clip != null)
                clip.stop();
        }
        catch(Exception ex){
            System.out.println("Error with stopping sound.");
            ex.printStackTrace();
        }
    }

    public boolean isRinging()
    {
        return ringing;
    }

    public void setRinging(boolean ringing)
    {
        this.ringing = ringing;

        setChanged();
        notifyObservers();
    }

    public boolean isOn()
    {
        return on;
    }

    public void setOn(boolean on)
    {
        this.on = on;

        setChanged();
        notifyObservers();
    }
}
