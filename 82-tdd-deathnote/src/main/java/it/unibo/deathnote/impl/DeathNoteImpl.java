package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;

import java.util.Map;
import java.util.HashMap;

public class DeathNoteImpl implements DeathNote {

    final Map<String , CauseAndDetails> map = new HashMap<>();

    /*
    public DeathNoteImpl(String name, String cause, String details) {
        map.put(details, new CauseAndDetails(cause, details));
    } */

    @Override
    public String getRule(int ruleNumber) {
        if(ruleNumber < 1) {
            throw new IllegalArgumentException(
                "The number in input [" + ruleNumber + "] is below 1"
            );
        } else  if (ruleNumber > RULES.size()) {
            throw new IllegalArgumentException(
                "The number in input [" + ruleNumber + "] is bigger than the max (size of the list)"
            );
        } else {
            return RULES.get(ruleNumber);
        }
    }

    @Override
    public void writeName(String name) {
        //map.putIfAbsent(name);
    }

    @Override
    public boolean writeDeathCause(String cause) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDeathCause'");
    }

    @Override
    public boolean writeDetails(String details) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    @Override
    public String getDeathCause(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public String getDeathDetails(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public boolean isNameWritten(String name) {
        return map.containsKey(name);
    }


    class CauseAndDetails {

        final Map<String , String> miniMap = new HashMap<>();

        public CauseAndDetails(String cause, String details) {
            miniMap.put(cause, details);
        }

    }
}