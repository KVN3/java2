package klok.Models;

import javafx.beans.Observable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class WereldKlokModel extends KlokModel
{
    private int gmtVerschil;

    public WereldKlokModel(int uur, int minuut)
    {
        super(uur, minuut);

        ZonedDateTime date = ZonedDateTime.now(ZoneId.of("GMT"));
        this.gmtVerschil = date.getHour() - uur;
    }

    @Override
    public String toString()
    {
        return String.format("WERELD KLOK: [%02d:%02d]", (this.uur + this.gmtVerschil), this.minuut);
    }
}
