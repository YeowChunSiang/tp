package meditrack.ui.modal;

import java.util.Map;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

import meditrack.commons.core.Index;
import meditrack.logic.Logic;
import meditrack.logic.commands.DeleteSupplyCommand;
import meditrack.logic.commands.exceptions.CommandException;
import meditrack.logic.parser.CommandType;
import meditrack.logic.parser.Parser;
import meditrack.logic.parser.exceptions.ParseException;
import meditrack.model.ModelManager;
import meditrack.model.Supply;

/** Confirm then delete supply via logic. */
public class DeleteSupplyModal {

    /**
     * @param model for parser validation
     * @param logic executes the delete command
     * @param supply row to delete
     * @param oneBasedIndex table index (1-based)
     * @param owner parent window
     */
    public static void show(ModelManager model, Logic logic, Supply supply,
                            int oneBasedIndex, Window owner) {
        Parser parser = new Parser(model);
        try {
            parser.validate(CommandType.DELETE_SUPPLY,
                    Map.of("index", String.valueOf(oneBasedIndex)));
        } catch (ParseException e) {
            Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage());
            error.initOwner(owner);
            error.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Supply");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Are you sure you want to delete \"" + supply.getName() + "\"?");
        alert.initOwner(owner);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                logic.executeCommand(new DeleteSupplyCommand(Index.fromOneBased(oneBasedIndex)));
            } catch (CommandException e) {
                Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage());
                error.initOwner(owner);
                error.showAndWait();
            }
        }
    }
}
