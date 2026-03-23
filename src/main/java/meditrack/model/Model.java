package meditrack.model;

import java.util.List;

import javafx.collections.ObservableList;

import meditrack.commons.core.Index;

/**
 * API of the Model component.
 */
public interface Model {

    /**
     * Retrieves the current Session object.
     */
    Session getSession();

    /**
     * Sets the active role in the Session after a successful login.
     */
    void setRole(Role role);

    // ========== Supply methods (Person B) ==========

    /**
     * Adds a new supply to the inventory.
     * The supply name must not already exist (case-insensitive).
     */
    void addSupply(Supply supply);

    /**
     * Replaces the supply at {@code targetIndex} with {@code editedSupply}.
     */
    void editSupply(Index targetIndex, Supply editedSupply);

    /**
     * Deletes and returns the supply at {@code targetIndex}.
     */
    Supply deleteSupply(Index targetIndex);

    /**
     * Returns an observable list of all supplies.
     */
    ObservableList<Supply> getFilteredSupplyList();

    /**
     * Returns supplies expiring within {@code daysThreshold} days, sorted by expiry date ascending.
     */
    List<Supply> getExpiringSupplies(int daysThreshold);

    /**
     * Returns supplies with quantity below {@code quantityThreshold}, sorted by quantity ascending.
     */
    List<Supply> getLowStockSupplies(int quantityThreshold);

    /**
     * Returns a read-only view of the underlying MediTrack data (for Storage serialisation).
     */
    ReadOnlyMediTrack getMediTrack();

    // ========== Personnel methods (Person C will add here) ==========
}