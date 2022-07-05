package org.example.requisitechecker.usecases.internal;

import java.util.*;

public class BracketDealer {
    private final Set<Character> andSymbols = new HashSet<>(Arrays.asList(',', ';', '&', '+'));
    private final Set<Character> orSymbols = new HashSet<>(List.of('/'));
    private final char[][] bracketPairs = {{'(', ')'}, {'[', ']'}};


    /**
     * (Hugging brackets) -> Hugging brackets
     * [Hugging brackets] -> Hugging brackets
     * ([Hugging brackets]) -> [Hugging brackets]
     * @param s the string to pass in. The brackets must be balanced.
     * @return s with the hugging brackets removed
     */
    public String removeHuggingBrackets(String s){
        for(char[] bp : bracketPairs){
            if (validateHuggingBrackets(s, bp[0], bp[1])){
                s = s.substring(1, s.length() - 1);
                return s;
            }
        }
        return s;  // no changes made otherwise
    }

    /**
     * Does s have any hugging brackets?
     * @param s the string to test
     * @param op opening bracket
     * @param cl closing bracket
     * @return whether s starts with op and ends with cl.
     */
    private boolean validateHuggingBrackets(String s, char op, char cl){
        int depth = 0;
        var sCArray = s.toCharArray();
        for(int i = 0; i < sCArray.length; i++){
            if(sCArray[i] == op) depth++;
            if(sCArray[i] == cl) depth--;
            // the very first one must start with op
            if(i == 0 && depth != 1) return false;
            // depth can never hit zero unless i is at sCArray.length - 1
            if(i != 0 && depth == 0){
                return i == sCArray.length - 1;
            }
        }
        return false;
    }

    /**
     * Removes everything nested in brackets () and [].
     *
     * @param s the string to pass in
     * @return the results
     */
    public String quickRemoveTextInBrackets(String s) {
        if (s == null) return null;
        for (char[] bracketPair : bracketPairs) {
            assert s != null;
            s = removeTextInBrackets(s, bracketPair[0], bracketPair[1]);
        }
        return s;
    }

    /**
     * Removes everything nested in brackets you specify.
     *
     * @param s  to pass in
     * @param op opening bracket
     * @param cl closing bracket
     * @return a copy of s, where all characters nested in brackets are
     * removed.
     * Returns null if the brackets are unbalanced.
     * There is no way to escape brackets.
     */
    private String removeTextInBrackets(String s, char op, char cl) {
        StringBuilder sb = new StringBuilder();
        int bracketLevel = 0;
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (ch == op) bracketLevel += 1;
            else if (ch == cl) bracketLevel -= 1;
            else if (bracketLevel == 0) sb.append(ch);
        }
        if (bracketLevel != 0) return null;
        return sb.toString();
    }

    /**
     * Same as Python's str.split(), but don't split anything if
     * it's nested inside () and [].
     *
     * @param s the string to pass in
     * @param aor whether to split commas or slashes, and its variants.
     * @return the result
     */
    public List<String> quickNestlessSplit(String s, AOR aor) {
        Set<Character> symbols = aor == AOR.AND ? andSymbols : orSymbols;
        Set<Character> op = new HashSet<>();
        Set<Character> cl = new HashSet<>();
        for (char[] brp : bracketPairs) {
            op.add(brp[0]);
            cl.add(brp[1]);
        }
        return nestlessSplit(s, symbols, op, cl);
    }

    private List<String> nestlessSplit(String s, Set<Character> separators, Set<Character> op, Set<Character> cl) {
        // prevents an exception from occurring
        // PRECONDITIONS: length of seperators is at least 1
        int bracketLevel = 0;
        char spR = 'a';
        // this is the worst way to get one element from a set
        for (Character separator : separators) {
            spR = separator;
            break;
        }
        s += spR;
        ArrayList<String> parts = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (separators.contains(c) && bracketLevel == 0) {
                parts.add(current.toString());
                current.setLength(0);
            } else {
                if (op.contains(c)) bracketLevel++;
                else if (cl.contains(c)) bracketLevel--;
                current.append(c);
            }
        }
        if (bracketLevel != 0) return null;
        return parts;
    }

    /**
     * Replaces substrings looking like "and" and "or" with
     * commas and slashes, respectively.
     */
    public String narrowAndOrSymbols(String input){
        return input.replaceAll(" ", "")
                .replace("\n", "")
                .replace("++", "pp")
                .replace("and", ",")
                .replace("or", "/")
                .replace("&", ",")
                .replace("+", ",");
    }

}

