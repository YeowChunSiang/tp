package meditrack.model;

/**
 * Represents the current user session in the application.
 */
public class Session {
    private static Session instance;
    private Role currentRole;

    private Session() {
    }

    /**
     * Retrieves the single instance of the Session.
     */
    /** Returns the singleton session object. */
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    /** Sets the role for the logged-in user. */
    public void setRole(Role role) {
        this.currentRole = role;
    }

    /** Returns the current role, or null if not logged in. */
    public Role getRole() {
        return currentRole;
    }

    /** Clears the session (e.g. on logout). */
    public void clear() {
        this.currentRole = null;
    }
}