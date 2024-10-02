import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.Timer;

public class SolarSystemSimulation {

    public static void main(String[] args) {
        createAndShowSimulation();  // Method to create and show the simulation
    }

    private static void createAndShowSimulation() {
        JFrame frame = new JFrame("Solar System Simulation");
        SolarSystem solarSystem = new SolarSystem(1920, 1080);

        // Create and add the Sun
        Sun sun = new Sun("SUN", 50, 50000.0, 400, 300);
        solarSystem.addSun(sun);

        // Create and add planets with adjusted positions and velocities
        Planet mercury = new Planet("MERCURY", 12, 6000, 1200, 500, 0, 6.0, Color.LIGHT_GRAY);
        solarSystem.addPlanet(mercury);

        Planet venus = new Planet("VENUS", 12, 6500, 1300, 500, 0, 5.0, Color.ORANGE);
        solarSystem.addPlanet(venus);

        Planet earth = new Planet("EARTH", 15, 7000, 1400, 500, 0, 4.0, Color.GREEN);
        solarSystem.addPlanet(earth);

        Planet mars = new Planet("MARS", 18, 8000, 1500, 500, 0, 3.0, Color.RED);
        solarSystem.addPlanet(mars);

        Planet jupiter = new Planet("JUPITER", 30, 10000, 1600, 500, 0, 2.5, Color.PINK);
        solarSystem.addPlanet(jupiter);

        Planet saturn = new Planet("SATURN", 24, 49000, 1700, 500, 0, 2.0, Color.DARK_GRAY);
        solarSystem.addPlanet(saturn);

        Planet uranus = new Planet("URANUS", 20.0, 49000, 1800, 500, 0, 1.5, Color.CYAN);
        solarSystem.addPlanet(uranus);

        Planet neptune = new Planet("NEPTUNE", 21.0, 49000, 1900, 500, 0, 1.0, Color.BLUE);
        solarSystem.addPlanet(neptune);

        frame.add(solarSystem);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Animation loop
        new Timer(10, e -> solarSystem.movePlanets()).start();
    }
}
