package org.example.mainloophelpers.controllerspresenters;

/**
 * A class representing the command list for all UIs.
 * Layer: presenter
 */
public class UICommandList {

    public String getStudent() {
        return """
                STUDENT COMMANDS - THE SLASH IS OPTIONAL
                            
                /add <course code with -F/-S/-Y suffix>
                Adds a course to your planned course list, which the session you're planning for is F/W 2022-2023. Fails if course DNE.
                Aliases: /a
                     
                /addmeeting <course code with -F/-S/-Y suffix> <meeting (LEC0101)>
                Adds a meeting to an existing course. Fails if meeting or course DNE.
                Aliases: /am

                /addh <course code without -F/-S/-Y suffix>
                Adds a course to your passed course list. Used to determine prerequisites, corequisites, and exclusions. Fails if course DNE or was not offered after F/W 2019-2020.
                Aliases: /ah
                           
                /remove <course code with -F/-S/-Y suffix>
                Removes a course to your planned course list. Fails if it was never there in the first place.
                Aliases: /r
                            
                /removeh <course code without -F/-S/-Y suffix>
                Removes a course from your passed course list. Fails if it was never there in the first place.
                Aliases: /rh
                            
                /view
                View the courses you have chosen so far.
                Aliases: /v
                            
                /getHTMLTT <term: F|S (default)>
                Generates an HTML timetable of your planned courses.
                Paste this to some HTML visualizer.
                Aliases: /tt
                """;
    }

    public String getAdmin() {
        return """
                ADMIN COMMANDS - THE SLASH IS OPTIONAL
                ALL COMMANDS WILL FAIL IF YOU ARE NOT AN ADMIN.
                            
                /ban <username> <date dd/MM/yyyy>
                Bans a user. Fails if username DNE or date format is unreadable.
                            
                /delete <username>
                Deletes a user. Fails if username DNE.
                            
                /new <username> <password>
                Creates a new admin account. Fails if user already exists.
                            
                /promote <username>
                Promotes an existing user to admin. Fails if username DNE.
                """;
    }

    public String getCourseSearch() {
        return """
                SEARCH COMMANDS - THE SLASH IS OPTIONAL
                            
                /search <keyword>
                Prints out all courses offered in F/W 2022-2023 that starts with the keyword.
                            
                /searchmeetings <course code with -F/-S/-Y suffix>
                Prints out all meetings (LEC, TUT, PRAs) of the course code. Fails if the course DNE or is not specific enough.
                            
                /ss <keyword>
                Prints out all courses offered in F/W 2022-2023 that will not result in issues in taking
                them (follows prerequisites, corequisites, exclusions, and so on)
                """;
    }

    public String getStandard() {
        return """
                STANDARD COMMANDS - THE SLASH IS OPTIONAL
                            
                /history
                Prints the history log of your logins.
                            
                """;
    }

    public String getGlobalCommands() {
        return """
                GLOBAL COMMANDS - REMEMBER THEM!
                            
                /switchto <mode: admin (ad) | search (se/sh/sea) | standard (std) | student (stu)>
                Switches windows, which is the set of commands that work.
                Aliases: /switch, /sw; words in brackets are aliases to view modes
                            
                /logout
                Logs you out from the program and brings you back to the login screen.
                            
                /exit
                Exits the program.
                """;
    }

    // for each of the four methods above, run and print them
    public void printStandard() {
        System.out.println(getStandard());
    }

    public void printAdmin() {
        System.out.println(getAdmin());
    }

    public void printGlobal() {
        System.out.println(getGlobalCommands());
    }

    public void printSearch() {
        System.out.println(getCourseSearch());
    }

    public void printStudent() {
        System.out.println(getStudent());
    }

    public void printSwitchFailure() {
        System.out.println("Invalid view mode specified, so no switching" + " will be done.");
    }
}
