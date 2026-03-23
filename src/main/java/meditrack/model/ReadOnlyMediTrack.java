package meditrack.model;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of the MediTrack data.
 */
public interface ReadOnlyMediTrack {
    /** Returns an unmodifiable observable list of supplies. */
    ObservableList<Supply> getSupplyList();

    /** Returns an unmodifiable observable list of personnel. */
    ObservableList<Personnel> getPersonnelList();
}