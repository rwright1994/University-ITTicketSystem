package _ITTicketSystem.models;

public class TerminalCommand {

    private final String name;
    private final String description;

    public TerminalCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
