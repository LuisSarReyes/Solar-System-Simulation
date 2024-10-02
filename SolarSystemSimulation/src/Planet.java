import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

class Planet {
    private String name;
    private double radius;
    private double mass;
    private double x, y;
    private double velX, velY;
    private Color color;
    private ArrayList<Point> trail; // To store trail points

    public Planet(String name, double radius, double mass, double x, double y, double velX, double velY, Color color) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
        this.color = color;
        this.trail = new ArrayList<>(); // Initialize the trail list
    }

    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
        // Add current position to trail
        trail.add(new Point((int)x, (int)y)); // Store the current position
    }

    public void addToTrail() {
        trail.add(new Point((int)x, (int)y)); // Add current position to trail
        if (trail.size() > 10000) { // Keep the last 10000 points
            trail.remove(0); // Remove the oldest point
        }
    }

    public ArrayList<Point> getTrail() {
        return trail;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getVelocityX() { return velX; }
    public double getVelocityY() { return velY; }
    public double getRadius() { return radius; }
    public Color getColor() { return color; }
    public String getName() { return name; } // Getter for name
    public double getMass() { return mass; } // Getter for mass

    public void setVelocity(double velX, double velY) {
        this.velX = velX; // Set the X velocity
        this.velY = velY; // Set the Y velocity
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
