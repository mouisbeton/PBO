import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Field
{
    private static final Random rand = new Random();

    private int depth;
    private int width;
    private Object[][] field;

    public Field(int depth, int width) {
        this.depth = depth;
        this.width = width;
        field = new Object[depth][width];
    }

    public void clear() {
        for(int row = 0; row < depth; row++) {
            for(int col = 0; col < width; col++) {
                field[row][col] = null;
            }
        }
    }

    public void clear(Location location) {
        field[location.getRow()][location.getCol()] = null;
    }

    public void place(Object obj, Location location) {
        field[location.getRow()][location.getCol()] = obj;
    }

    public Object getObjectAt(Location location) {
        return field[location.getRow()][location.getCol()];
    }

    public Location freeAdjacentLocation(Location location) {
        List<Location> free = getFreeAdjacentLocations(location);
        if(free.isEmpty()) return null;
        return free.get(rand.nextInt(free.size()));
    }

    public List<Location> getFreeAdjacentLocations(Location location) {
        List<Location> free = new ArrayList<>();
        for(Location loc : adjacentLocations(location)) {
            if(getObjectAt(loc) == null) {
                free.add(loc);
            }
        }
        return free;
    }

    public List<Location> adjacentLocations(Location location) {
        List<Location> locations = new ArrayList<>();

        int row = location.getRow();
        int col = location.getCol();

        for(int ro = -1; ro <= 1; ro++) {
            int nextRow = row + ro;
            if(nextRow >= 0 && nextRow < depth) {
                for(int co = -1; co <= 1; co++) {
                    int nextCol = col + co;
                    if(nextCol >= 0 && nextCol < width && (ro != 0 || co != 0)) {
                        locations.add(new Location(nextRow, nextCol));
                    }
                }
            }
        }

        Collections.shuffle(locations, rand);
        return locations;
    }

    public int getDepth() {
        return depth;
    }

    public int getWidth() {
        return width;
    }
}
