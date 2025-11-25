import java.util.ArrayList;
import java.util.List;

public class Simulator
{
    private Field field;
    private List<Animal> animals;
    private SimulatorView view;

    public Simulator() {
        field = new Field(20, 20);
        animals = new ArrayList<>();
        view = new SimulatorView(field);
        populate();
    }

    private void populate() {
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                double rand = Math.random();
                Location loc = new Location(row, col);
                if(rand <= 0.05) {
                    Rabbit rabbit = new Rabbit(field, loc);
                    animals.add(rabbit);
                }
                else if(rand <= 0.07) {
                    Fox fox = new Fox(field, loc);
                    animals.add(fox);
                }
            }
        }
    }

    public void simulate(int steps) {
        for(int step = 1; step <= steps; step++) {
            simulateOneStep(step);
        }
    }

    private void simulateOneStep(int step) {
        List<Animal> newAnimals = new ArrayList<>();
        List<Animal> current = new ArrayList<>(animals);
        for(Animal animal : current) {
            if(animal.isAlive()) {
                animal.act(newAnimals);
            } else {
                animals.remove(animal);
            }
        }
        animals.addAll(newAnimals);
        view.showStatus(step, animals);
    }

    public static void main(String[] args) {
        Simulator sim = new Simulator();
        sim.simulate(20);
    }
}
