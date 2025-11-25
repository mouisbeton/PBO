import java.util.List;
import java.util.Random;

public abstract class Animal
{
    protected static final Random rand = new Random();

    private boolean alive;
    protected Field field;
    protected Location location;

    public Animal(Field field, Location location) {
        alive = true;
        this.field = field;
        setLocation(location);
    }

    public boolean isAlive() {
        return alive;
    }

    protected void setDead() {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    public Location getLocation() {
        return location;
    }

    protected void setLocation(Location newLocation) {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    protected Field getField() {
        return field;
    }

    public abstract void act(List<Animal> newAnimals);
}
