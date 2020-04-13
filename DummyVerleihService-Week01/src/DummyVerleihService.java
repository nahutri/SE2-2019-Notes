import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Diese Klasse implementiert das Interface VerleihService. Es handelt sich
 * lediglich um eine Dummy-Implementation, um die GUI zu testen.
 * 
 * @author GUI-Team/Anna Nguyen
 * @version SoSe 2019
 */
class DummyVerleihService extends AbstractObservableService
        implements VerleihService
{
    // Generator für Zufallszahlen und zufällige boolsche Werte
    private static final Random RANDOM = new Random();

    private static final CD MEDIUM = new CD("Titel", "Kommentar", "Interpret",
            70);
    private static final Kundennummer KUNDENNUMMER = new Kundennummer(123456);
    private static final Kunde ENTLEIHER = new Kunde(KUNDENNUMMER, "Vorname",
            "Nachname");
    private static final Verleihkarte VERLEIHKARTE = new Verleihkarte(ENTLEIHER,
            MEDIUM, Datum.heute());

    private List<Verleihkarte> _verleihkarten;
    private MedienbestandService _medienbestand;
    private KundenstammService _kundenstamm;

    public DummyVerleihService(KundenstammService kundenstamm,
            MedienbestandService medienbestand,
            List<Verleihkarte> initialBestand)
    {
        _verleihkarten = initialBestand;
        _medienbestand = medienbestand;
        _kundenstamm = kundenstamm;

    }

    @Override
    public List<Medium> getAusgelieheneMedienFuer(Kunde kunde)
    {
        List<Medium> ergebnisListe = new ArrayList<Medium>();
        ergebnisListe.add(MEDIUM);
        return ergebnisListe;
    }

    @Override
    public Kunde getEntleiherFuer(Medium medium)
    {
        return ENTLEIHER;
    }

    @Override
    public Verleihkarte getVerleihkarteFuer(Medium medium)
    {
        return VERLEIHKARTE;
    }

    @Override
    public List<Verleihkarte> getVerleihkarten()
    {
        List<Verleihkarte> ergebnisListe = new ArrayList<Verleihkarte>();
        ergebnisListe.add(VERLEIHKARTE);
        return ergebnisListe;
    }

    @Override
    public boolean istVerliehen(Medium medium)
    {
        if (VERLEIHKARTE.getEntleiher() == null)
        {
            return true;
        }
        else
        {
            return false;
        }
        //return VERLEIHKARTE.getMedium()
        //return RANDOM.nextBoolean();
    }

    @Override
    public void nimmZurueck(List<Medium> medien, Datum rueckgabeDatum)
    {
        if (sindAlleVerliehen(medien) && rueckgabeDatum != null)
        {
            sindAlleNichtVerliehen(medien);
            //Verleihkarte sollte gelöscht sein
        }

    }

    @Override
    public boolean sindAlleNichtVerliehen(List<Medium> medien)
    {
        boolean result = true;
        for (Medium medium : medien)
        {
            if (istVerliehen(medium))
            {
                result = false;
            }

        }
        return result;

    }

    @Override
    public boolean sindAlleVerliehen(List<Medium> medien)
    {
        boolean result = false;
        for (Medium medium : medien)
        {

            if (istVerliehen(medium))
            {
                result = true;

            }

        }
        return result;

    }

    private Verleihkarte NEUKARTE;

    public void verleiheAn(Kunde kunde, List<Medium> medien, Datum ausleihDatum)
    {
        if (sindAlleNichtVerliehen(medien) && kundeImBestand(kunde)
                && ausleihDatum != null)
        {
            NEUKARTE = new Verleihkarte(kunde, MEDIUM, ausleihDatum);

        }
        else
        {
            System.out.println("Fehler beim Protokollieren!");
        }
    }

    @Override
    public boolean kundeImBestand(Kunde kunde)
    {
        return ENTLEIHER.equals(kunde);
    }

    @Override
    public boolean mediumImBestand(Medium medium)
    {
        return MEDIUM.equals(medium);
    }

    @Override
    public boolean medienImBestand(List<Medium> medien)
    {
        boolean result = true;
        for (Medium medium : medien)
        {
            if (!mediumImBestand(medium))
            {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public List<Verleihkarte> getVerleihkartenFuer(Kunde kunde)
    {
        List<Verleihkarte> result = new ArrayList<Verleihkarte>();
        result.add(VERLEIHKARTE);
        return result;
    }

    @Override
    public boolean istVerleihenMoeglich(Kunde kunde, List<Medium> medien)
    {
        if (kundeImBestand(kunde) && medienImBestand(medien))
        {
            return true;

        }
        else
            return false;
    }
}
