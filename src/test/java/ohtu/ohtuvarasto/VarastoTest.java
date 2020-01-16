package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysEiLisaaEnempaaKuinMahtuu() {
        varasto.lisaaVarastoon(12);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaEiVoiOttaaEnempaaKuinSiellaOn() {
        varasto.lisaaVarastoon(5);
        double saatuMaara = varasto.otaVarastosta(8);
        
        assertEquals(5, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void alkusaldoAsetetaanOikein() {
        Varasto alkusaldollinenVarasto = new Varasto(10,5);
        
        assertEquals(5, alkusaldollinenVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivistaMaaraaEiVoiLisata() {
        varasto.lisaaVarastoon(2);
        varasto.lisaaVarastoon(-1);
        
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivistaMaaraaEiVoiOttaa() {
        varasto.lisaaVarastoon(5);
        double saatuMaara = varasto.otaVarastosta(-3);
        
        assertEquals(0, saatuMaara, vertailuTarkkuus); 
    }
    
    @Test 
    public void toStringPalauttaaOikeanMerkkijonon() {
        String varastotieto = varasto.toString();
        
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varastotieto);
    }
    
    @Test
    public void negatiivinenTilavuusNollataanAlkuvarastottomassaKonstruktorissa() {
        Varasto virheellinenVarasto = new Varasto(-1);
        
        assertEquals(0, virheellinenVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenTilavuusNollataanAlkuvarastollisessaKonstruktorissa() {
        Varasto virheellinenVarasto = new Varasto(-10,5);
        
        assertEquals(0, virheellinenVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void alkusaldoEiNostaSaldoaTilavuuttaSuuremmaksi() {
        Varasto virheellinenVarasto = new Varasto(1,5);        
        assertEquals(1, virheellinenVarasto.getSaldo(), vertailuTarkkuus);       
    }
    
    @Test
    public void negatiivinenAlkusaldoNollataan() {
        Varasto virheellinenVarasto = new Varasto(10,-5);
        
        assertEquals(0, virheellinenVarasto.getSaldo(), vertailuTarkkuus);
        //Muutos tehtävää 11 varten 
    }

}