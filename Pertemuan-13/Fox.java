import java.util.Iterator;
import java.util.List;

public class Fox extends Animal
{
    private static final int BREEDING_AGE = 10;
    private static final int MAX_AGE = 150;
    private static final double BREEDING_PROBABILITY = 0.08;
    private static final int MAX_LITTER_SIZE = 2;
    private static final int RABBIT_FOOD_VALUE = 9;

    private int age;
    private int foodLevel;

    public Fox(Field field, Location location) {
        super(field, location);
        age = 0;
        foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
    }

    @Override
    public void act(List<Animal> newFoxes) {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newFoxes);
            Location newLocation = findFood();
            if(newLocation == null) {
                newLocation = field.freeAdjacentLocation(location);
            }
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

    private void incrementHunger() {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }

    private Location findFood() {
        List<Location> adjacent = field.adjacentLocations(location);
        for(Iterator<Location> it = adjacent.iterator(); it.hasNext(); ) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isAlive()) {
                    rabbit.setDead();
                    foodLevel = RABBIT_FOOD_VALUE;
                    return where;
                }
            }
        }
        return null;
    }

    private void giveBirth(List<Animal> newFoxes) {
        List<Location> free = field.getFreeAdjacentLocations(location);
        int births = breed();
        for(int b = 0; b < births && !free.isEmpty(); b++) {
            Location loc = free.remove(0);
            newFoxes.add(new Fox(field, loc));
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
