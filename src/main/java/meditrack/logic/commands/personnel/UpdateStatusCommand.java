package meditrack.logic.commands.personnel;

import meditrack.logic.commands.Command;
import meditrack.logic.commands.CommandResult;
import meditrack.logic.commands.exceptions.CommandException;
import meditrack.model.Model;
import meditrack.model.ModelManager;
import meditrack.model.Role;
import meditrack.model.Status;

/** Changes someone's status. Index is 1-based like the UI table. */
public class UpdateStatusCommand extends Command {

    public static final String COMMAND_WORD = "update_status";

    public static final String MESSAGE_SUCCESS = "Updated status of %s to: %s";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Updates the status of a personnel member.\n"
                    + "Parameters: INDEX s/STATUS\n"
                    + "Valid statuses: FIT, LIGHT_DUTIES, UNFIT\n"
                    + "Example: " + COMMAND_WORD + " 1 s/UNFIT";

    private final int oneBasedIndex;
    private final Status newStatus;

    /**
     * @param oneBasedIndex 1-based index as displayed in the UI list
     * @param newStatus     pre-validated new status
     */
    public UpdateStatusCommand(int oneBasedIndex, Status newStatus) {
        this.oneBasedIndex = oneBasedIndex;
        this.newStatus = newStatus;
    }

    /** Updates the person's status in the model. */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        ModelManager manager = (ModelManager) model;
        String name = manager.getFilteredPersonnelList(null)
                .get(oneBasedIndex - 1).getName();
        manager.setPersonnelStatus(oneBasedIndex, newStatus);
        return new CommandResult(String.format(MESSAGE_SUCCESS, name, newStatus));
    }

    /** Medical officer only. */
    @Override
    public Role getRequiredRole() {
        return Role.MEDICAL_OFFICER;
    }

    /** Returns the 1-based index for this command. */
    public int getOneBasedIndex() {
        return oneBasedIndex;
    }

    /** Returns the new status for this command. */
    public Status getNewStatus() {
        return newStatus;
    }
}