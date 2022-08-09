# CSC207 Project

The name of this project is to be added.

## Dependencies

This project uses **Java 18.**
I'm unsure if it is backwards compatible.
If you seem to be getting errors, ensure that your JDK is Java 18.

## Running the project

Run MainLoop in java/org

For the version with the GUI, run GUIDisplay in java/org/phase2/gui/GUIDisplay

If running Main does not work, you
may need to edit your run
configurations such that the
working directory is set
to `$MODULE_WORKING_DIR$`.
Otherwise, exceptions regarding files
not being found may arise.

Much of the user input is similar to Phase 1, although there have been a few changes.

Most of the work in Phase 2 was implementing the GUI and implementing design patterns and cleaning up code.

## Definitions

A **course code** can only tell us the faculty and the digits.
Examples:

- CSC110
- MAT137
- CSC207

`[A-Z]{3}[0-4]\d{2}`
(Only UTSG courses)

A **course** can only tell us the course title,
and has no information about the timings it is offered or whether it is offered in the fall or the winter.
Examples:

- CSC110Y1
- MAT137Y1
- CSC207H1

`[A-Z]{3}[0-4]\d{2}[HY][01]`
(Only UTSG courses)

A **course offering** tells us about the course title and also which session it is offered.
If we know the session number (the year it is offered, and we're going to focus on session 20229), then we can get its lecture timings.
Examples:

- CSC110Y1-F
- MAT137Y1-Y
- CSC207H1-Y

`[A-Z]{3}[0-4]\d{2}[HY][01]-[FSY]` (Only UTSG courses)

## Course

- Code: The code identifying the course. It does not tell whether the course is offered in the F/S/Y sections.
- Org: A code identifying the department.
- courseTitle: The title of the course
- webTimetableInstructions: an HTML String giving us the timetable instructions.
- orgName: the name of the department offering the course.
- meetings: the meeting of this course: contains LEC, TUT, and PRA, which are organized in the Meetings class.
- exclusion
- prerequsite
- deliveryInstructions
- session: identifies the year when the course is offered. For example, 20229, which is F/W 2022-2023. 20235 is Summer 2023. Note that S courses are not given its own code, even though, in some course, Quercus pages, individual winter session numbers are given anyway.
- section: F/S/Y. better just to call this variable FSY.

