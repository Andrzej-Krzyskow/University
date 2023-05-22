public class SportCar extends Car {

    private double spoilerHeight;   // we create additional state
    private boolean sportModeOn;    // we create additional state

    // constructor receives additional info about spoiler's height
    public SportCar(String color, double maxSpeed, boolean isFunctional, double fuelLevel, double spoilerHeight) {
        super(color, maxSpeed, isFunctional, fuelLevel);
        this.spoilerHeight = spoilerHeight;
    }

    // some methods are overrided differently
    @Override
    public void honk() {
        System.out.println("Sport Honk!");
    }

    @Override
    public void pullOutTheEngine() {
        this.setMaxSpeed(0);
    }

    @Override
    public boolean repair() {
        return isFunctional = true;
    }

    @Override
    public void tankUpFull() {
        this.fuelLevelPercentage = 100;
    }


    // we create additional methods
    public double getSpoilerHeight() {
        return spoilerHeight;
    }

    public void setSpoilerHeight(double spoilerHeight) {
        this.spoilerHeight = spoilerHeight;
    }

    public boolean isSportModeOn() {
        return sportModeOn;
    }

    public void turnOnSportMode() {
        this.sportModeOn = true;
    }

    public void turnOffSportMode() {
        this.sportModeOn = false;
    }
    // no more additional methods
}
