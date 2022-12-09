# UofT Timetable Manager

This is a Java app that allows users to plan for courses whilst catching as many issues as possible,
so they don't enroll in a course they can't take by mistake. This project was done as part of the CSC207H1 course project offered by UofT. No further updates for this project are planned at this time.

## Requirements and Dependencies

This project uses **Java 18.**
I'm unsure if it is backwards compatible.
If you seem to be getting errors, ensure that your JDK is Java 18.

Maven manages these dependencies, so you don't need to worry about installing external packages.

## Sources

The information for all courses is sourced from this link: [Here](https://timetable.iit.artsci.utoronto.ca/api/20229/courses?org=&section=F,S,Y)

## Running the project

Please read the section below before running this for the first time. This is not a compiled program, so you must use IntelliJ to run this.

- Run `MainLoop` in `src/main/java/org/` for the command-line version of this program.

- Run `RunGUIDisplay` in the same path above for the GUI version of this program.

## Important information regarding running this program for the  first time

Firstly, you must mark the folder in blue in this screenshot as sources root (this makes the folder blue).

![image](https://user-images.githubusercontent.com/93059453/206634131-8298f316-61a5-4263-a401-650849e4a646.png)

Then:

After running your program once, you must stop the program and edit your run
configurations such that the
working directory is set
to `$MODULE_WORKING_DIR$`.
Then, you may subsequently run the program like normal.
Otherwise, exceptions regarding files
not being found may arise.
Moreover, the program won't be able to properly load courses if you don't.

## Screenshots

![](.README_images/6a1de7c7.png)
