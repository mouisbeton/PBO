class Game
{
    private Parser parser;
    private Room currentRoom;
    private int moveCount;

    public Game()
    {
        createRooms();
        parser = new Parser();
        moveCount = 0;
    }

    private void createRooms()
    {
        Room outside = new Room("outside the main entrance of the university");
        Room theatre = new Room("in a lecture theatre");
        Room pub = new Room("in the campus pub");
        Room lab = new Room("in a computing lab");
        Room office = new Room("in the computing admin office");

        outside.setExits(theatre, lab, pub, null);
        theatre.setExits(null, null, outside, null);
        pub.setExits(outside, null, null, null);
        lab.setExits(office, null, outside, null);
        office.setExits(null, lab, null, null);

        currentRoom = outside;
    }

    public void play()
    {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }

        System.out.println("Thank you for playing. Good bye.");
        parser.close();
    }

    private void printWelcome()
    {
        System.out.println("\n=== Welcome to Adventure Game ===");
        System.out.println("Type 'help' for available commands.\n");
        printRoomDetails();
    }

    private void printRoomDetails()
    {
        currentRoom.printRoomInfo();
    }

    private boolean processCommand(Command command)
    {
        if (command.isUnknown()) {
            System.out.println("I don't know that command...");
            return false;
        }

        String commandWord = command.getCommandWord();

        switch(commandWord) {
            case "help":
                printHelp();
                break;
            case "go":
                goRoom(command);
                break;
            case "look":
                look();
                break;
            case "quit":
                return quit(command);
            default:
                System.out.println("Unknown command!");
        }

        return false;
    }

    private void printHelp()
    {
        System.out.println("\nYour adventures lead you through the university.");
        System.out.println("Available commands:");
        System.out.println("  - go <direction> : Move in a direction (north, east, south, west)");
        System.out.println("  - look : Observe your current surroundings");
        System.out.println("  - help : Show this help message");
        System.out.println("  - quit : Exit the game\n");
    }

    private void goRoom(Command command)
    {
        if (!command.hasSecondWord()) {
            System.out.println("Go where? Please specify a direction.\n");
            return;
        }

        String direction = command.getSecondWord().toLowerCase();
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no exit in that direction!\n");
        } else {
            currentRoom = nextRoom;
            moveCount++;
            System.out.println();
            printRoomDetails();
            System.out.println();
        }
    }

    private void look()
    {
        System.out.println();
        printRoomDetails();
        System.out.println();
    }

    private boolean quit(Command command)
    {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?\n");
            return false;
        }

        System.out.println("\nYou made " + moveCount + " moves before quitting.");
        return true;
    }

    public static void main(String[] args)
    {
        Game game = new Game();
        game.play();
    }
}
