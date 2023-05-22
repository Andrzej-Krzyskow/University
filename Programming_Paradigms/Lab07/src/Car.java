public abstract class Car {

    private String color;
    double maxSpeed;
    boolean isFunctional;

    double fuelLevelPercentage;

    public Car(String color, double maxSpeed, boolean isFunctional, double fuelLevel) {
        this.color = color;
        this.maxSpeed = maxSpeed;
        this.isFunctional = isFunctional;
        this.fuelLevelPercentage = fuelLevel;
    }

    public abstract void honk();

    public abstract void pullOutTheEngine();

    public abstract boolean repair();

    public abstract void tankUpFull();


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public boolean isFunctional() {
        return isFunctional;
    }

    public void setFunctional(boolean functional) {
        isFunctional = functional;
    }

    public double getFuelLevelPercentage() {
        return fuelLevelPercentage;
    }
}
