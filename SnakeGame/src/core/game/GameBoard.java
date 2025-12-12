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
        addSnake(17, 7);
        addSnake(54, 34);
        addSnake(62, 19);
        addSnake(64, 60);
        addSnake(87, 24);
        addSnake(93, 73);
        addSnake(95, 75);
        addSnake(99, 9);
    }
    
    private void loadLadders() {
        addLadder(3, 22);
        addLadder(5, 8);
        addLadder(11, 28);
        addLadder(20, 38);
        addLadder(27, 83);
        addLadder(36, 44);
        addLadder(51, 67);
        addLadder(71, 91);
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
