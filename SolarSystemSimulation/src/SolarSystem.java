import java.awt.*;
import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Random;

class SolarSystem extends JPanel {

    private static final long serialVersionUID = 1L; 
    private ArrayList<Planet> planets;
    private Sun sun;
    private double originalWidth;
    private double originalHeight;
    private ArrayList<Point> stars; // List to hold star positions
    private int numStars = 100; // Number of stars to generate
    
    public SolarSystem(int width, int height) {
        this.planets = new ArrayList<>();
        this.originalWidth = width;
        this.originalHeight = height;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);  // Set background color to black
        generateStars(width, height); // Generate stars
        
        // Add component listener for resizing
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                SolarSystem.this.componentResized(e);
            }
        });
    }
    
    private void generateStars(int width, int height) {
        stars = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < numStars; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            stars.add(new Point(x, y)); // Add random star positions
        }
    }

    public void addPlanet(Planet p) {
        planets.add(p);
    }

    public void addSun(Sun s) {
        this.sun = s;
    }

    public void movePlanets() {
        double G = 0.3; // Increased gravitational constant for faster response
        double dt = 0.2; // Smaller time step for stability

        for (Planet planet : planets) {
            // Calculate the distance vector from the sun to the planet
            double r_x = sun.getX() - planet.getX();
            double r_y = sun.getY() - planet.getY();
            double r = Math.sqrt(r_x * r_x + r_y * r_y);

            // Prevent division by zero
            if (r == 0) {
                continue;
            }

            // Calculate the gravitational force (F = G * m1 * m2 / r^2)
            double force = G * (sun.getMass() * planet.getMass()) / (r * r);

            // Calculate the acceleration (a = F / m)
            double a_x = force * (r_x / (planet.getMass() * r)); // Direction towards the sun
            double a_y = force * (r_y / (planet.getMass() * r));

            // Update velocity using acceleration
            planet.setVelocity(planet.getVelocityX() + a_x * dt, planet.getVelocityY() + a_y * dt);

            // Update position using the new velocity
            planet.setPosition(planet.getX() + planet.getVelocityX() * dt, planet.getY() + planet.getVelocityY() * dt);
            
            // Update the trail with the current position
            planet.addToTrail(); // Add current position to the trail
        }

        // Repaint to show new positions
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw stars
        g.setColor(Color.WHITE); // Set star color to white
        for (Point star : stars) {
            g.fillOval(star.x, star.y, 3, 3); // Draw each star
        }
        
        // Draw the Sun
        if (sun != null) {
            g.setColor(Color.YELLOW);
            g.fillOval((int) (sun.getX() - sun.getRadius()), (int) (sun.getY() - sun.getRadius()), 
                    (int) (2 * sun.getRadius()), (int) (2 * sun.getRadius()));
            
            // Draw the Sun's name
            g.setColor(Color.WHITE);
            g.drawString(sun.getName(), (int) (sun.getX() + sun.getRadius()), (int) (sun.getY() - sun.getRadius()));
        }

        // Draw planets and their trails
        for (Planet p : planets) {
            // Draw the trails
            g.setColor(Color.LIGHT_GRAY); // Set a lighter color for the trail
            ArrayList<Point> trail = p.getTrail();
            for (int i = 1; i < trail.size(); i++) {
                Point prev = trail.get(i - 1);
                Point current = trail.get(i);
                g.drawLine(prev.x, prev.y, current.x, current.y); // Draw the line segment
                
                if (trail.size() > 10000) { // Keep the last 100 points
                    trail.remove(0); // Remove the oldest point
                }
            }
            
            // Draw the planets
            g.setColor(p.getColor());
            g.fillOval((int) (p.getX() - p.getRadius()), (int) (p.getY() - p.getRadius()), 
                    (int) (2 * p.getRadius()), (int) (2 * p.getRadius()));
            
            // Draw the planet name
            g.setColor(Color.WHITE); // Set the color for the planet name
            g.drawString(p.getName(), (int) (p.getX() + p.getRadius()), (int) (p.getY() - p.getRadius())); // Position the name
        }
    }

    // Handle resizing and repositioning planets and the Sun
    public void componentResized(ComponentEvent e) {
        Dimension newSize = e.getComponent().getSize();
        setPreferredSize(newSize);

        // Adjust the sun's position to the center
        if (sun != null) {
            sun.moveTo(newSize.width / 2, newSize.height / 2); // Center the sun in the new window
        }

        // Adjust planet positions based on new dimensions
        double scaleX = (double)newSize.width / originalWidth; // Scaling factor for width
        double scaleY = (double)newSize.height / originalHeight; // Scaling factor for height

        for (Planet p : planets) {
            double newX = (p.getX() - (originalWidth / 2)) * scaleX + (newSize.width / 2);
            double newY = (p.getY() - (originalHeight / 2)) * scaleY + (newSize.height / 2);
            p.setPosition(newX, newY); // Update the planet's position
        }

        // Update the original dimensions to the new size
        originalWidth = newSize.width;
        originalHeight = newSize.height;

        revalidate(); // Revalidate the layout
        repaint(); // Repaint the panel to reflect changes
    }
}
