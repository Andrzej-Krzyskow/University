public class Dog {

    // identity
    private final String name;

    // States
    private int limbs;
    private int age;
    private String breed;

    public Dog(int limbs, int age, String breed, String name) {
        this.limbs = limbs;
        this.age = age;
        this.breed = breed;
        this.name = name;
    }

    // behaviour
    public void bark() {
        System.out.println("Bark bark");
    }

    public void eat() {
        System.out.println("chomp chomp");
    }
}
