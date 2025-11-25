import java.util.HashMap;
import java.util.Map;

class Room
{
    private String description;
    private Map<String, Room> exits;

    public Room(String description)
    {
        this.description = description;
        this.exits = new HashMap<>();
    }

    public void setExits(Room north, Room east, Room south, Room west)
    {
        if (north != null) {
            exits.put("north", north);
        }
        if (east != null) {
            exits.put("east", east);
        }
        if (south != null) {
            exits.put("south", south);
        }
        if (west != null) {
            exits.put("west", west);
        }
    }

    public String getDescription()
    {
        return description;
    }

    public Room getExit(String direction)
    {
        return exits.get(direction.toLowerCase());
    }

    public String getExitString()
    {
        return "Available exits: " + exits.keySet().toString();
    }

    public void printRoomInfo()
    {
        System.out.println("You are " + description);
        System.out.println(getExitString());
    }
}
