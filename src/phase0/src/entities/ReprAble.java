package entities;

/**
 * This interface represents all Classes that can export
 * a string representation of itself in a way that is
 * unambiguous, such that it can be directly loaded back
 * into the class or another instance of the class.
 */
public interface ReprAble {

    /**
     * Returns this Class in a string representation such that
     * it can be read back.
     *
     * @return What is to be represented in the CSV file.
     */
    String repr();

    /**
     * This method should ALWAYS be implemented such that
     * it REPLACES all existing values in any class
     * that this method is implemented in.
     *
     * @param reprString the String read from the CSV file.
     */
    void setFromRepr(String reprString);
}
