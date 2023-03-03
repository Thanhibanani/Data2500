package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.API.AdminKundeController;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestAdminKundeController {

    @InjectMocks
    // denne skal testes
    private AdminKundeController adminKundeController;

    @Mock
    // denne skal Mock'es
    private AdminRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;





    @Test
    public void hentAlle_loggetInn(){

        // arrange

        List<Kunde> kundeliste = new ArrayList<>();

        Kunde kunde1 = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        kundeliste.add(kunde1);


        Kunde kunde2 = new Kunde("11010110523",
                    "Ole", "Nordmann", "Askerveien 21", "3210",
                    "Asker", "22224444", "HeiHei");

        kundeliste.add(kunde2);


        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentAlleKunder()).thenReturn(kundeliste);

        // act
        List<Kunde> resultat = adminKundeController.hentAlle();

        // assert
        assertEquals(kundeliste, resultat);
    }

    @Test
    public void hentAlle_IkkeLoggetInn() {

        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        List<Kunde> resultat = adminKundeController.hentAlle();

        // assert
        assertNull(resultat);
    }


    @Test
    public void lagreKunde_loggetInn(){

        // arrange

        List<Kunde> kundeliste = new ArrayList<>();

        Kunde kunde1 = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        kundeliste.add(kunde1);


        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.registrerKunde(kunde1)).thenReturn("OK");

        // act
        String resultat = adminKundeController.lagreKunde(kunde1);

        // assert
        assertEquals("OK", resultat);
    }



}
















