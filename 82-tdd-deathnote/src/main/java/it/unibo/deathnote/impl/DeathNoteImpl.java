package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

/**
 * Implementation of the DeathNote interface.
 */
public class DeathNoteImpl implements DeathNote {

    private static final String NO_NAME = "The name in input isn't present in the death note";
    private static final int MAX_WRITE_CAUSE = 40;
    private static final int MAX_WRITE_DETAILS = 6040;

    private final Map<String, CauseAndDetails> map;
    private String lastName;

    /**
     * Constructor of the DeathNote class.
     */
    public DeathNoteImpl() {
       map = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRule(final int ruleNumber) {
        if (ruleNumber < 1) {
            throw new IllegalArgumentException(
                "The number in input [" + ruleNumber + "] is below 1"
            );
        } else if (ruleNumber > RULES.size()) {
            throw new IllegalArgumentException(
                "The number in input [" + ruleNumber + "] is bigger than the max (size of the list)"
            );
        } else {
            return RULES.get(ruleNumber - 1);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeName(final String name) {
        Objects.requireNonNull(name);
        lastName = name;
        map.put(name, new CauseAndDetails());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDeathCause(final String cause) {
        checkOnLastName();
        if (map.get(lastName).checkTime(MAX_WRITE_CAUSE)) {
            map.get(lastName).setCause(cause);
            return true;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDetails(final String details) {
        checkOnLastName();
        if (map.get(lastName).checkTime(MAX_WRITE_DETAILS)) {
            map.get(lastName).setDetails(details);
            return true;
        } else {
            return false;
        }
    }

    /* Support method. It controls if the param lastName exist and 
     * if its written in the death note.
     */
    private void checkOnLastName() {
        if (lastName == null || !isNameWritten(lastName)) {
            throw new IllegalStateException(NO_NAME);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathCause(final String name) {
        checkOnName(name);
        // We have to operate with the element CauseAndDetails
        // with map.get(name) we reach CauseAndDetails object
        // and its methods
        return map.get(name).getCause();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathDetails(final String name) {
        checkOnName(name);
        return map.get(name).getDetails();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNameWritten(final String name) {
        return map.containsKey(name);
    }

    /* Support method. It controls if the name is in the death note */
    private void checkOnName(final String name) {
        Objects.requireNonNull(name);
        if (!isNameWritten(name)) {
            throw new IllegalArgumentException(NO_NAME);
        }
    }

    private final class CauseAndDetails {

        private static final String DEFAULT_DEATH = "Heart attack";

        private String cause;
        private String details;
        private final long time;

        private CauseAndDetails(final String cause, final String details) {
            this.cause = cause;
            this.details = details;
            this.time = System.currentTimeMillis();
        }

        /* Invokes the constructor above with default values */
        private CauseAndDetails() {
            this(DEFAULT_DEATH, "");
        }

        private String getCause() {
            return this.cause;
        }

        private String getDetails() {
            return this.details;
        }

        private void setCause(final String cause) {
            this.cause = cause;
        }

        private void setDetails(final String details) {
            this.details = details;
        }

        private boolean checkTime(final int timeToCheck) {
            final long currentTime = System.currentTimeMillis();
            return currentTime - this.time < timeToCheck;
        }
    }
}
