package it.unibo.mvc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;
//import it.unibo.mvc.view.DrawNumberStandardOutputView;
//import it.unibo.mvc.view.DrawNumberSwingView;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private static final String PATH = "it.unibo.mvc.view.DrawNumber";

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args)
        throws 
        ClassNotFoundException, 
        NoSuchMethodException,
        InvocationTargetException, // Needs to be imported
        InstantiationException,
        IllegalAccessException,
        IllegalArgumentException {

        /* Old code
        // Model
        final var model = new DrawNumberImpl();
        // Control
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        // View
        app.addView(new DrawNumberSwingView());
        app.addView(new DrawNumberSwingView());
        app.addView(new DrawNumberStandardOutputView());*/

        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);

        final List<String> nameOfViews = new ArrayList<>();
        nameOfViews.add(PATH + "StandardOutputView");
        nameOfViews.add(PATH + "SwingView");
        for (final String name : nameOfViews) {
            final Class<?> specialClass = Class.forName(name);
            final Constructor<?> specialConstructor = specialClass.getConstructor();

            for (int i = 0; i < 3; i++) {
                final DrawNumberView view = (DrawNumberView) specialConstructor.newInstance();
                app.addView(view);
            }
        }
    }
}
