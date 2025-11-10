package it.unibo.deathnote;

import org.junit.jupiter.api.Test;

import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {
    final DeathNoteImpl deathnote = new DeathNoteImpl();

    @Test
    void testGetRule() {
        try {
            deathnote.getRule(0);
            deathnote.getRule(-1);
        } catch (final IllegalArgumentException e) {
            
        }
    }
}