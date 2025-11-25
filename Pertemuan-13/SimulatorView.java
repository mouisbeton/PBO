import java.util.List;

public class SimulatorView
{
    private Field field;

    public SimulatorView(Field field)
    {
        this.field = field;
    }

    public void showStatus(int step, List<Animal> animals)
    {
        System.out.println("\n===== STEP " + step + " =====");
        System.out.println("Jumlah hewan: " + animals.size());
        int foxCount = 0;
        int rabbitCount = 0;

        for (Animal a : animals) {
            if (a instanceof Fox) {
                foxCount++;
            } else if (a instanceof Rabbit) {
                rabbitCount++;
            }
        }

        System.out.println("Fox: " + foxCount + ", Rabbit: " + rabbitCount);
        System.out.println("Peta (F=Fox, R=Rabbit, .=kosong):");

        for (int row = 0; row < field.getDepth(); row++) {
            StringBuilder line = new StringBuilder();
            for (int col = 0; col < field.getWidth(); col++) {
                Object obj = field.getObjectAt(new Location(row, col));
                if (obj instanceof Fox) {
                    line.append('F');
                } else if (obj instanceof Rabbit) {
                    line.append('R');
                } else {
                    line.append('.');
                }
            }
            System.out.println(line.toString());
        }
    }
}
