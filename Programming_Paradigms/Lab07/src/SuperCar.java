public class SuperCar extends SportCar {

    private boolean flyingModeOn = false;

    public SuperCar(String color, double maxSpeed, boolean isFunctional, double fuelLevel, double spoilerHeight) {
        super(color, maxSpeed, isFunctional, fuelLevel, spoilerHeight);
    }

    // overrided method, different from sportCar
    @Override
    public void honk() {
        System.out.println("Super car!!!");;
    }
}