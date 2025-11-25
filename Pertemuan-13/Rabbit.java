import java.util.List;

public class Rabbit extends Animal
{
    private static final int BREEDING_AGE = 5;
    private static final int MAX_AGE = 40;
    private static final double BREEDING_PROBABILITY = 0.15;
    private static final int MAX_LITTER_SIZE = 4;

    private int age;

    public Rabbit(Field field, Location location) {
        super(field, location);
        age = 0;
    }

    @Override
    public void act(List<Animal> newRabbits) {
        incrementAge();
        if(isAlive()) {
            giveBirth(newRabbits);
            Location newLocation = field.freeAdjacentLocation(location);
            if(newLocation != null) {
                setLocation(newLocation);
            }
        }
    }

    private void incrementAge() {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }

    private void giveBirth(List<Animal> newRabbits) {
        List<Location> free = field.getFreeAdjacentLocations(location);
        int births = breed();
        for(int b = 0; b < births && !free.isEmpty(); b++) {
            Location loc = free.remove(0);
            newRabbits.add(new Rabbit(field, loc));
        }
    }

    private int breed() {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    private boolean canBreed() {
        return age >= BREEDING_AGE;
    }
}
