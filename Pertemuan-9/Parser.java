import java.util.Scanner;

class Parser
{
    private CommandWords commands;
    private Scanner scanner;

    public Parser()
    {
        commands = new CommandWords();
        scanner = new Scanner(System.in);
    }

    public Command getCommand()
    {
        System.out.print("> ");
        String inputLine = "";

        try {
            if (scanner.hasNextLine()) {
                inputLine = scanner.nextLine();
            }
        } catch (Exception exc) {
            System.out.println("Error reading input: " + exc.getMessage());
            return new Command(null, null);
        }

        String[] words = inputLine.trim().split("\\s+");

        String word1 = words.length > 0 ? words[0] : null;
        String word2 = words.length > 1 ? words[1] : null;

        if (word1 != null && commands.isCommand(word1)) {
            return new Command(word1, word2);
        } else {
            return new Command(null, word2);
        }
    }

    public void close()
    {
        scanner.close();
    }
}
