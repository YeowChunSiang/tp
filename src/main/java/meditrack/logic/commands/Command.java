package meditrack.logic.commands;

import meditrack.logic.commands.exceptions.CommandException;
import meditrack.model.Model;
import meditrack.model.Role;

/**
 * Abstract base class for all commands in the application.
 * Each concrete command must implement execute() and getRequiredRole().
 */
public abstract class Command {

    /**
     * Runs the command against the model and returns a result message.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    /**
     * Returns the role needed to run this command, or null if any role can run it.
     */
    public abstract Role getRequiredRole();
}