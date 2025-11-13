package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

/**
 * Standard Output {@link DrawNumberView} implementation.
 */
public final class DrawNumberStandardOutputView implements DrawNumberView {

    private static final String NEW_GAME = ": touch the frame for a new game!";

    @Override
    public void setController(final DrawNumberController observer) {
        // This view does not need a controller because it is output-only
    }

    @Override
    public void start() {
        // We do not have to do anything because standard output is always visible
    }

    @Override
    public void result(final DrawResult res) {
        switch (res) {
            case YOURS_HIGH, YOURS_LOW:
                System.out.println(res.getDescription()); // NOPMD
                // The exercise requests to print out on the console
                break;
            case YOU_WON, YOU_LOST:
                System.out.println(res.getDescription() + NEW_GAME); // NOPMD
                // The exercise requests to print out on the console
                break;
        }
    }
}
