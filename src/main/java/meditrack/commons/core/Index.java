package meditrack.commons.core;

/**
 * Wraps an index value. Internally stored as zero-based but can be
 * created from the one-based indices shown in the UI tables.
 */
public class Index {

    private final int zeroBasedIndex;

    private Index(int zeroBasedIndex) {
        if (zeroBasedIndex < 0) {
            throw new IllegalArgumentException("Index must be non-negative.");
        }
        this.zeroBasedIndex = zeroBasedIndex;
    }

    /** Creates an index from a 0-based position. */
    public static Index fromZeroBased(int zeroBasedIndex) {
        return new Index(zeroBasedIndex);
    }

    /** Creates an index from a 1-based position (as shown in UI tables). */
    public static Index fromOneBased(int oneBasedIndex) {
        return new Index(oneBasedIndex - 1);
    }

    /** Returns the 0-based index. */
    public int getZeroBased() {
        return zeroBasedIndex;
    }

    /** Returns the 1-based index. */
    public int getOneBased() {
        return zeroBasedIndex + 1;
    }

    /** True if both indices have the same zero-based value. */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Index)) {
            return false;
        }
        return zeroBasedIndex == ((Index) other).zeroBasedIndex;
    }

    /** Hash from the zero-based index. */
    @Override
    public int hashCode() {
        return zeroBasedIndex;
    }

    /** 1-based index as text. */
    @Override
    public String toString() {
        return String.valueOf(getOneBased());
    }
}
