package org.example.coursegetter.usecases.internal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class FileOpener {

    /**
     * Reads and returns the text contents of the file passed in.
     * @param pathToFile the file to read.
     *                   If the file does not exist, this method
     *                   returns null.
     *                   Path relative to the directory ABOVE src.
     * @return           The text contents of the file as a String.
     */
    public String readFile(String pathToFile){
        // this is the worst implementation of reading a file.
        // this could have been much shorter.
        String line;
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathToFile));
            while ((line = br.readLine()) != null){
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // System.out.println(sb);
        return sb.toString();
    }
}
