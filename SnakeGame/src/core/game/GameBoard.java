package core.game;

import java.util.*;

public class GameBoard {
    private static final int BOARD_SIZE = 100;
    private Map<Integer, SpecialTile> specialTiles;
    
    public GameBoard() {
        this.specialTiles = new HashMap<>();
        loadSnakes();
        loadLadders();
    }    
    
    private void loadSnakes() {
        addSnake(99, 2);
        addSnake(53, 28);
        addSnake(89, 52);
    }
    
    private void loadLadders() {
        addLadder(6, 15);
        addLadder(37, 84);
        addLadder(54, 74);
        addLadder(71, 90);
    }
    
    private void addSnake(int from, int to) {
        if (from > 0 && from <= BOARD_SIZE && to > 0 && to < from) {
            specialTiles.put(from, new Snake(from, to));
        }
    }
    
    private void addLadder(int from, int to) {
        if (from > 0 && from <= BOARD_SIZE && to > from && to <= BOARD_SIZE) {
            specialTiles.put(from, new Ladder(from, to));
        }
    }
    
    public boolean hasSpecialTile(int position) {
        return specialTiles.containsKey(position);
    }
    
    public SpecialTile getSpecialTile(int position) {
        return specialTiles.get(position);
    }
    
    public int checkSpecialTile(int position) {
        if (hasSpecialTile(position)) {
            SpecialTile tile = getSpecialTile(position);
            return tile.applyEffect(position);
        }
        return position;
    }
    
    public Collection<SpecialTile> getAllSpecialTiles() {
        return specialTiles.values();
    }
    
    public List<Snake> getSnakes() {
        List<Snake> snakes = new ArrayList<>();
        for (SpecialTile tile : specialTiles.values()) {
            if (tile instanceof Snake) {
                snakes.add((Snake) tile);
            }
        }
        return snakes;
    }
    
    public List<Ladder> getLadders() {
        List<Ladder> ladders = new ArrayList<>();
        for (SpecialTile tile : specialTiles.values()) {
            if (tile instanceof Ladder) {
                ladders.add((Ladder) tile);
            }
        }
        return ladders;
    }
    
    public static int getBoardSize() {
        return BOARD_SIZE;
    }
}
