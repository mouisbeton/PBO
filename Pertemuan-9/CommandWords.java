import java.util.ArrayList;
import java.util.List;

class CommandWords
{
    private List<String> validCommands;

    public CommandWords()
    {
        validCommands = new ArrayList<>();
        validCommands.add("go");
        validCommands.add("quit");
        validCommands.add("help");
        validCommands.add("look");
    }

    public boolean isCommand(String aString)
    {
        if (aString == null) {
            return false;
        }
        return validCommands.contains(aString.toLowerCase());
    }

    public void printCommands()
    {
        System.out.println("Available commands: " + validCommands.toString());
    }
}
