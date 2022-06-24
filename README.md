# DEFINITIONS

A **course** can only tell us the course title,
and has no information about the timings it is offered or whether it is offered in the fall or the winter.
Examples:

- CSC110Y1
- MAT137Y1
- CSC207H1

`[A-Z]{3}[0-4]\d{2}[H|Y][01]`
(Only UTSG courses)

A **course offering** tells us about the course title and also which session it is offered.
If we know the session number (the year it is offered, and we're going to focus on session 20229), then we can get its lecture timings.
Examples:

- CSC110Y1-F
- MAT137Y1-Y
- CSC207H1-Y

`[A-Z]{3}[0-4]\d{2}[H|Y][01]-[F|S|Y]` (Only UTSG courses)

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
- session: identifies the year when the course is offered. For example, 20229, which is F/W 2022-2023. 20235 is Summer 2023. Note that S courses are not given its own code.
- section: F/S/Y. better just to call this variable FSY.
