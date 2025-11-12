package it.unibo.deathnote;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static it.unibo.deathnote.api.DeathNote.RULES; 
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.lang.Thread.sleep;

import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private static final String DEFAULT_DEATH = "Heart attack";
    private static final String KARTING_ACCIDENT = "Karting accident";
    private static final String RAN_FOR_TOO_LONG = "Ran for too long";
    private static final String PINCO = "Pinco Pallino";
    private static final String PAPERON = "Paperon Depaperoni";
    private static final String BIANCHI = "Alex Bianchi";
    private static final int SLEEP_CAUSE = 100;
    private static final int SLEEP_DETAILS = 6100;

    private DeathNoteImpl deathnote;

    @BeforeEach
    void setUp() {
        deathnote = new DeathNoteImpl();
    }

    /* Rule number 0 and negative rules do not exist in the DeathNote rules */
    @Test
    void testGetRuleWithIncorrectNumbers() {
        try {
            deathnote.getRule(0);
            deathnote.getRule(-1);
        } catch (final IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
    }

    /* No rule is empty or null in the DeathNote rules */
    // First rule = 1, last rule = RULES.size()
    @Test
    void testGetRule() {
        for (int i = 1; i <= RULES.size(); i++) {
            assertNotNull(deathnote.getRule(i));
            assertFalse(deathnote.getRule(i).isBlank());
        }
    }

    /* The human whose name is written in the DeathNote will eventually die */
    @Test
    void testDeathNames() {
        assertFalse(deathnote.isNameWritten(PINCO));
        deathnote.writeName(PINCO);
        assertTrue(deathnote.isNameWritten(PINCO));
        assertFalse(deathnote.isNameWritten(PAPERON));
        assertFalse(deathnote.isNameWritten(""));
    }

    /*  If the cause of death is written within the next 40
        milliseconds of writing the person's name, it will happen */
    @Test
    void testWriteCauseDeathInTime() throws InterruptedException {
        try {
            deathnote.writeDeathCause(DEFAULT_DEATH);
            fail("Expected IllegalStateException because no name was written yet");
        } catch (final IllegalStateException e) {
            System.out.println(e.getMessage()); // NOPMD
            // By implementation, wanted to check other details
        }

        deathnote.writeName(PINCO);
        deathnote.writeDeathCause(DEFAULT_DEATH);
        assertEquals(DEFAULT_DEATH, deathnote.getDeathCause(PINCO));

        deathnote.writeName(PAPERON);
        deathnote.writeDeathCause(KARTING_ACCIDENT);
        assertEquals(KARTING_ACCIDENT, deathnote.getDeathCause(PAPERON));

        deathnote.writeName(BIANCHI);
        sleep(SLEEP_CAUSE);
        assertFalse(deathnote.writeDeathCause("Drown in a pound"));
        assertEquals(DEFAULT_DEATH, deathnote.getDeathCause(BIANCHI));
    }

    /*  After writing the cause of death, details of the death should be written 
        in the next 6 seconds and 40 milliseconds of writing the death's cause */
    @Test
    void testWriteDetailsDeathInTime() throws InterruptedException {
        try {
            deathnote.writeDetails("Drank poison");
            fail("Expected IllegalStateException because no name was written yet");
        } catch (final IllegalStateException e) {
            System.out.println(e.getMessage()); // NOPMD
            // By implementation, wanted to check other details
        }

        deathnote.writeName(PINCO);
        deathnote.writeDeathCause(DEFAULT_DEATH);
        assertEquals("", deathnote.getDeathDetails(PINCO));
        deathnote.writeDetails(RAN_FOR_TOO_LONG);
        assertEquals(RAN_FOR_TOO_LONG, deathnote.getDeathDetails(PINCO));

        deathnote.writeName(BIANCHI);
        sleep(SLEEP_DETAILS);
        assertFalse(deathnote.writeDetails("Eat a lot"));
        assertEquals("", deathnote.getDeathDetails(BIANCHI));
    }
}
