package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.Assert.assertEquals;
import static org.junit.jupiter.Assert.assertNotEquals;
import static org.junit.jupiter.Assert.assertTrue;

import org.junit.jupiter.AfterEach;
import org.junit.jupiter.BeforeEach;
import org.junit.jupiter.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.fixture.Fixture;

public class ComandoVaiTest {

	private Stanza s1;
	private Stanza s2;
	private Comando vai;
	private Partita p;
	
	@BeforeEach
	public void setUp() throws Exception {
		s1 = new Stanza("aula 1");
		s2 = new Stanza("aula 2");
		vai = new ComandoVai();
		p = new Partita();
		vai.setIo(new IOConsole());
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testVaiNull() {
		p.setStanzaCorrente(s1);
		vai.esegui(p);
		assertEquals(s1, p.getStanzaCorrente());
	}
	
	@Test
	public void testVaiDirezioneEsistente() {
		p.setStanzaCorrente(s1);
		s1.impostaStanzaAdiacente("sud-ovest", s2);
		vai.setParametro("sud-ovest");
		vai.esegui(p);
		assertEquals(s2, p.getStanzaCorrente());
	}
	
	@Test
	public void testVaiDirezioneInesistente() {
		p.setStanzaCorrente(s1);
		s1.impostaStanzaAdiacente("sud-ovest", s2);
		vai.setParametro("in fondo a destra");
		vai.esegui(p);
		assertNotEquals(s2, p.getStanzaCorrente());
	}
	
	@Test
	public void testPartitaConComandoVai() {
		String[] righeDaLeggere = {"vai nord", "fine"};
		IOSimulator io = Fixture.creaSimulazionePartitaEGioca(righeDaLeggere);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Biblioteca", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Hai vinto!", io.nextMessaggio());

	}
}

