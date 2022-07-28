package org.example.logincode.uiinput;

public class InputParserClass {

    private final String[] args;

    public InputParserClass(String input) {
        if (input.startsWith("/")) {
            input = input.substring(1);
        }
        this.args = input.split(" ");
    }

    /**
     * Gets the keyword.
     * @return above.
     */
    public String getKeyword() {
        return arrayIndex(args, 0);
    }

    /**
     * Gets the nth argument
     * @param index the index of the arg counting from 1, not 0 (0 is kw)
     * @return above.
     */
    public String getArg(int index) {
        return arrayIndex(args, index);
    }

    /**
     * grabs the nth element from a String array if it
     * exists, or "" if it doesn't
     */
    private String arrayIndex(String[] array, int n) {
        if (array.length > n) {
            return array[n];
        } else {
            return "";
        }
    }

}
