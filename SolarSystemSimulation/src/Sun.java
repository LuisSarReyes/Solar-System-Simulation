public class Sun {
    private String name;
    private double radius;
    private double mass;
    private double x, y;

    public Sun(String name, double radius, double mass, double x, double y) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.x = 400;  // Center of the Solar System (fixed position for simplicity)
        this.y = 300;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getRadius() { return radius; }
    public double getMass() { return mass; }

    public String getName() {  // Getter for the name field
        return name;
    }

    // Method to move the Sun to a new position
    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
