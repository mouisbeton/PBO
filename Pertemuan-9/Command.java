class Command
{
    private String commandWord;
    private String secondWord;

    public Command(String firstWord, String secondWord)
    {
        this.commandWord = firstWord;
        this.secondWord = secondWord;
    }

    public String getCommandWord()
    {
        return commandWord;
    }

    public String getSecondWord()
    {
        return secondWord;
    }

    public boolean isUnknown()
    {
        return commandWord == null || commandWord.isEmpty();
    }

    public boolean hasSecondWord()
    {
        return secondWord != null && !secondWord.isEmpty();
    }

    public void printCommand()
    {
        System.out.println("Command: " + commandWord + " | Parameter: " + secondWord);
    }
}
