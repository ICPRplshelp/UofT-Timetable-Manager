# UofT Timetable Manager - Project Report

The report of this project is placed here.

## Specifications

This is a course planner for all FAS courses in the University of
Toronto. This allows you to search up courses and plan them. The program
will check if the courses you’ve planned do not have any issues with
prerequisites, and so on. Users can use this program to plan on courses
they want to take in the future.

### Running the application

This app uses **Java 18.** It will not work on any version earlier than
that. There are two ways to run this program:

-   The main method in the class `GUIDisplay` for the GUI version of
    this app (example/gui/GUIDisplay), but there’s a class called
    `RunGUIDisplay` that calls the main method of `GUIDisplay`.

-   The main method in the class `MainLoop` for the CLI version of this
    app

Users running this app for the first time may face issues with no
courses being queried up. This can be fixed by modifying the run
configurations of what you plan to run. Moreover, **you should change
the working directory to** `$MODULE_WORKING_DIR$` – anything other than
that has the potential of breaking the program’s ability to look for
proper files.

![image1](https://user-images.githubusercontent.com/93059453/184290358-8b0b09b2-95db-4ed6-b1a0-00d7d4fd1296.png)

![image2](https://user-images.githubusercontent.com/93059453/184290360-22e4c12e-c206-42c7-9815-494c0458168e.png)

### Functionality

There are four tabs in this program.

-   Standard: view the account history.

-   Admin: manage other accounts. An account can grant themselves the
    admin permission by typing `secretadmin` in the CLI on standard
    mode. Everyone can access the admin tab, but the actions will always
    fail if the user isn’t an admin.

-   Search: search for courses. A regular search searches for all course
    codes that starts with what the user inputs. A smart search searches
    for courses that don’t have issues regarding prerequisites,
    corequisites, exclusions, and other related issues based on the
    courses the student has added in the student tab. This tool can also
    bring up the list of meeting codes for any course.

    -   Search all the meeting codes for a course by entering the full
        code with the F/Y/S suffix in the search meetings input box,
        then press the appropriate button.

-   Student: add courses to planned or passed list, and add LEC/TUT/PRAs
    to any course in your planned list. Planned is for courses you’ll
    take in the upcoming session (2022-2023), and passed courses is for
    all courses **you will have passed** before the upcoming session.
    Issues such as prerequisites, conflicts, and so on will be tracked
    here.

    -   Planned requires the F/Y/S code; passed does not. The dash is
        not required but is recommended.

    -   There is also the ability to generate a visual timetable, which
        looks like the one you see on ACORN. Note that if there’s a
        course conflict, or one lecture/tutorial/practical starts or
        ends on a time other than right on the hour, a timetable will
        not be generated.

![image3](https://user-images.githubusercontent.com/93059453/184290369-35d102b6-c012-4334-8250-ac2b19507b8a.png)

Important note: The program will freeze for a bit when you choose search
or student for the first time this program is running. Once that freeze
occurs, the program won’t freeze again, even if you log out and log back
into a different account.

#### Warnings

Here are the list of warnings that may be attached to a course or an
individual meeting (“cell” in a visual timetable):

-   Missing prerequisites (note that checks for these are not perfect.
    It should work for most, but not all courses). They can only account
    for required courses and cannot account for minimum grades or other
    special requirements.

-   Missing corequisites

-   Issue with exclusions

-   You’re entering the year with 4+ credits, and for all LEC meetings
    of that course, the enrollment indicator is R1 or R2, and the group
    of permitted students is all year 1

-   You already or will have already taken this course (If you have
    MAT135H1-F and MAT135H1-S planned, then MAT135H1-S will have the
    warning, but not the F version. It will typically be the later
    course that gets the warning.)

-   Missing LEC, TUT, or PRA

-   Course conflict (if one meeting causes the course to conflict, the
    entire course will have this warning)

-   Previous course located over 650m apart (for at least one meeting).
    The specific cell having the issue will be displayed on the
    timetable. Distance is taxicab, which approximates how long you have
    to walk (by limiting your path to north, south, east, and west), not
    how far the two buildings are actually apart. Checks won’t apply if
    the course being checked or the one before it has an unknown
    location or is online.

    -   Some warnings are attached to courses only while other warnings
        can be attached to individual meetings (e.g. WE 15:00-16:00, a
        cell you would find on a timetable)

These checks are done every time you add, modify, or remove a course.
They are programmed to work as close to how UofT checks prerequisites.

*For prerequisites, when checking prerequisites for S courses, the
checker assumes the student has taken all F courses they planned and all
the courses they’ve passed before then.*

*For corequisites and exclusions, all session combinations overlap with
the exception of F & S. If a student plans to take an F course and one
of its required corequisites is an S course, the student does not meet
the corequisite requirement for the F course. Exclusions are checked in
the same way, for the purpose of previously or concurrently taking a
course. Moreover, concurrent means there is a period of time where
you’re taking both at the same time.*

## Design decisions to uphold clean architecture

Decisions implemented in phase 2:

-   No class should break the dependency rule (over two down or opposite
    direction), and it’s easier to abide by the rule using dependency
    inversion. Note that `ScheduleEntry` is the only entity that has
    an interface, and not `Course` (it would violate the interface
    segregation principle if an interface was extracted from `Course`).
    Due to the internal information from each `Course` object not being
    used, only the course code can be presented to the screen, and
    nothing more specific than that. (1618da)

-   The original controllers (for each major section) were split.
    Originally, the UI part and the controller implementation were in
    the same part, mixing Interface Adapters and Frameworks and Drivers
    in the same file. By separating them and refactoring, the two clean
    architecture layers were separated. (c955d49)

## Design decisions made to uphold SOLID

Included in phase 1:

-   Liskov substitution principle — this design principle is used in the
    requisite checker module. Because course prerequisite strings can
    act as nested structures, multiple classes that inherit from the
    same superclass have been created to help act as subtrees to the
    requisite tree that has to be built for each course. It works
    similarly to abstract syntax trees. The prerequisite checker
    implements the composite design pattern to an extent. `(d55ae6a)`

Exclusive to phase 2:

-   Refactoring the student entity to minimize the need to change the
    class. The student class only contains information on present and
    passed courses. This helps uphold SRP. (`1618da4`)

-   Creating new instances of course searchers. This eliminates the need
    to modify the old course searchers, which actually do different
    things. The newest course searcher ensures that the course being
    retrieved is up to date. The class isn’t large unlike the previous
    course searcher. The old course searcher’s functionality was
    extended, and didn’t have to be modified (this feels like an
    antipattern, but there are tradeoffs). (`6670d5d`)

-   Using the strategy pattern in the warning checker to uphold the SRP
    in the warning checker. This ensures that the warning checker only
    tells its dependencies to check warnings for it, not to check each
    individual warning by itself. (`725aa7f and 7040115`)

-   The interface HTMLTableCell — this interface allows classes
    implementing it to have an HTML representation, which is useful for
    generating the HTML timetable in the GUI. We needed multiple classes
    inheriting from it as we needed a way to represent blank timetable
    cells, course cells, and so on. *(97f1a44)*
    Note: this isn't an abstract class because I don't need any inheritance, 
    other than for the purpose of default values. 
    This was originally called `Cellable`, but the wording was awkward.

## All design patterns

Here are as many design patterns we can catch from our program.

### Dependency inversion

The interface `IScheduleEntry` allows more complex data structures to be
accessible by higher layers. Higher-up layers will only need to depend
on its interface, which will likely never change. This interface
contains enough data to be displayed on a visual timetable. The same
applies to `IGateway`. (8d44079)

### State

*The following is based on the legacy input mode (using the command
line) and is not based on the GUI:*

The `MainLoop` class, where the user runs the program, uses a class that
follows the state design pattern. There's a context class that has a
public method called `input` which passes its arguments to the methods
within the state classes (which would be the `UIInput` classes), and a
`setState` method which attempts to switch the state of the context.

The user may switch between different states, which is essentially the
set of commands that they can execute, similar to tabs in a web page.
This ensures that if two "tabs" get the same command name, they won't
clash.

### Strategy - 1

In `WarningChecker`, the strategy design pattern was used for all
classes that are responsible for checking each individual warning.
Commit hash (I may have misidentified the pattern in the commit name):
(`7040115)`

### Strategy - 2 (Unused code, sorry)

There are two requisite checker classes that do very similar things.
They have the same output, the only difference being performance.

-   We have a `RequisiteChecker`, which is responsible for checking
    requisites. It builds the requisite tree every time the primary
    method of that class is called.

-   We have a `RequisiteCheckerMemoized`, which does exactly what
    memoization means. When it builds a requisite tree, it saves it, so
    the same tree does not need to be built twice if we need the
    requisite tree for another course, again.

Ironically, there are two methods in these classes – one for
prerequisites and corequisites, and one for exclusions. Unfortunately,
due to how different prerequisite/corequisite and exclusion strings are
written, I feel there is a need to make these two methods distinct so no
one gets confused over them, as the needed inputs **are required** to be
in a different format even if they both require `String`. (`1a1c44d`)

### Builder - 1

The 4 controller classes all depend on several use case classes each,
and need to be built when a user first logs in. The 4 builder classes
build and return the respective controllers.

### Builder - 2

For construction of the Course classes, we didn’t exactly use a Builder
design pattern, but used something similar. We wanted to move the long,
clunky, and somewhat irrelevant constructor logic from the actual Course
class and put it elsewhere in the CourseBuilder and
CourseBuilderDirector classes, which cleaned up the Course class.
Although the code may have become more complex with the addition of
additional classes, the code now better follows the Single
Responsibility Principle as the complex construction code is put into
its own class. (4a4a5ad6, e0da0032)

### Factory

There are two factory classes, one for controllers and one for UI
inputs. `ControllerFactory` gets the controllers from the builder
classes, and the UI input factory uses the controllers from
`ControllerFactory` to build and return UI inputs. Both factories have
separate methods for each type of object they are returning, instead of
one method that can return multiple types of objects. It is particularly
useful for `ControllerFactory` because the controller classes have
different functions and don’t inherit from one superclass. (1f0e3ee3)
(f7edb392)

### Singleton

There are multiple singleton classes, such as `StorageManager`,
`SaveStateController`, `UsableCourseSearcher`, and
`UsableCourseSearcherPrev`. Each singleton class has a static field
which stores an instance of itself, and a static method
`getInstance()` which returns that instance. This was useful for
classes which only needed one instance throughout the program, such as
certain classes that do not rely on specific account information.
(79ade8d2) (93ad0474) (a65878a0)

No entity is supposed to be a singleton class.

## Design patterns we decided not to use

Here are the list of design patterns that we may not have used fully,
and our reasons for doing so:

### Dependency injection for many, but not all classes

Many classes use DI, but not all of them. One particular example is
`WarningChecker`:

Doing DI feels like the right thing, but it has so many tradeoffs,
especially considering that some of the classes **contain a lot of
fields.** To properly do DI, we would have to create a builder class
that injects such dependencies, which would require us to remove the
`final` keyword on the methods of the class we wish to apply DI to. This
might present itself as a signal for other people working on the class
(even though they shouldn’t be, but I’m doing this for safety because it
is always possible) that such variables can be modified, when in fact
they should never change once initiated. We also don’t want to fill the
entire class with setters, as it makes the class harder to navigate.

To combat this, we can extract the interface of the whole class we’re
applying DI to in order to limit the number of methods in its public
interface, but then, that breaks the interface segregation principle and
doesn’t prevent others from tampering with the internal structure of the
class.

## Instances of major refactoring

We’ve refactored how the student data is stored, so that it is able to
provide the greatest amount of information whilst minimizing its
dependencies, to the point it should not be dependent on any class. This
means that the new student class can only store Java built-ins.
Moreover, it stores the course codes and lecture sections rather than an
entire course choice object, which composes the Course object, which is
already complicated enough. The phase 1 version was extremely messy
(commit hash: `2f1ff107d0ea84d645f3410205d1faaddbf79ed2`).

The warning checker is now a separate class and does not depend on the
student manager at all. While it depends on some of the fields from the
primary student class, it is not dependent on `Student`.

The logic that builds all the controllers has been moved to its own
separate package, so that the actual controllers we need to not have to
worry about constructing way too many other classes.

Phase 2 is when we learned about dependency inversion, and we were able
to apply that to reduce the need to use completely absurd ways to bypass
clean architecture layers.

## Accessibility

The GUI is designed to be really user-friendly, which is way more
friendly than using the command line (though the option to use a CLI is
still possible). Here are some comments for each principle of universal
design:

### Equitable use

There should not be any issues with this one, given this program’s
intended audience. Unless they are blind, then there is no way they can
use it, unless they use a screen reader. Due to the scale of this
project, it doesn’t seem feasible to implement a complete auditory
version of this application. Note that the command prompt method of this
program is likely more friendly to those who can’t see the screen as
they don’t need to worry about moving the mouse cursor, and they can use
a TTS reader on it more easily.

### Flexibility in use

We have a command prompt version and a GUI version of this program.
Users can choose either according to their preferences.

### Perceptible information

The UI part is a bit confusing, but the buttons should signal what a
user should be pressing. The user does need to infer a bit when clicking
the “add meeting” button. Also, some warnings aren’t that helpful - we
had to condense them so that they don’t take up the entire screen, but
it would also require you to already have understood what they mean.
“\>= 1 prev. course too far” isn’t that helpful to read, but to someone
who understands it, it is very concise - perhaps a hover-over tooltip
should be added.

### Simple and intuitive

Very little guides are given to the program, meaning the user has to
test out the program for themselves to determine what something does.
The chances of them actually getting it are very slim, and the best way
to predict that is to assume that your users will understand less than
what you’ll expect them to understand. While default values are
provided, it may not be sufficient for a user to know what they mean,
especially for those who aren’t technologically fluent or have been
explicitly taught how to use the program.

### Tolerance for error

The program isn’t very forgiving if you mess up an input. By far, typing
CSC110Y1F and CSC110Y1-F for the purposes of adding a course to your
course list should have the exact same functionality. However,
otherwise, if an input was rejected, the program may not be able to
pinpoint exactly where you messed up. Moreover, it can show you the
possible reasons, but it never tells you which one. In the future, we
could extend our program by passing error codes, enums, or error classes
to provide additional error information. Here might be some suggested
error message for an input that requires a specific course code like
CSC110Y1-F:

-   You need the -F/-Y/-S suffix for this

-   You need the -H/-Y suffix, the campus code, and the -F/-S/-Y suffix
    for this

-   St. George FAS courses only — UTM and UTSC courses are not supported

-   This isn't a course code

### Low physical effort

The GUI doesn’t listen when you press the enter key, requiring you to
move your hand to the mouse every time you need something to change.
Also, there is no way to navigate this program using the arrow keys; a
mouse must be used. For the command prompt, I could either not show
commands or require the user to scroll up to get command output, and
that takes a lot of stamina. Between the choice of physical movement and
the need to memorize, I chose the first one. For the CLI part of this
program, we’ve made shortcut commands to reduce the number of keystrokes
needed to use this application (e.g. stu can be used instead of
student). Also, course codes must be in all caps, so one way to improve
the program is to auto-capitalize all course code fields the moment the
submit button is pushed.

### Size and space for approach and use

You can resize the app. You may not have full control over the vertical
size of the elements within the apps. We could fix that but we were
unable to due to the project’s tight deadline.

## What did each person do?

-   **Alex (DeathByThermodynamics)**:

    -   Created the swing forms for the GUI and made them usable
        (`3e6234f and 1fa0756`)

    -   Separated the swing forms into multiple classes
        (`3e6234f and 1fa0756`)

    -   Gateway for storage loading and saving (`8d44079`)

    -   Implemented consecutive and distance checks to see if two
        sections were too far away (`b089598`)

    -   Adjusted singleton designs in Storage Manager to fit with the
        gateway implementation for storage (`16a0032`)

    -   Bug fixing

-   **Hanmin (hanmangokiwi):**

    -   Undid an improper Singleton design pattern usage
        (5bf1232d3961d6884cdc7196a028b9d67ae3ddd8)

    -   Created the GUI using swing, including the Login, Admin, Course
        Search, Standard, and Student UI pages (I can’t provide a single
        commit for this because it has multiple commits, sorry!)

    -   Connected the GUI to the rest of the program to make it
        functional (same as above, I can’t provide a single commit)

    -   Corrected Storage Manager to properly use the singleton design
        pattern (aa8845611bbb46ac848b78d7108d5522e571e25d)

    -   Used a builder class (or at least something similar to a
        builder) to create Course instances
        (e0da0032e65df6deb70c5c2e92879fff6dfa31ce)

    -   Worked on conflict checker
        (7844c106c0b6ecfd00dcc4a915c56afe6fc9ba1d)

    -   Refactored several files to remove java warnings

    -   General bug fixing

    -   Removed redundant code

-   **Vinayak:**

    -   Refactored several files to remove java warnings (dde229530)
        (0c03af2c)

    -   Made Storage Manager singleton and edited UsableCourseSearcher
        and UsableCourseSearcherPrev to be singleton as well (96e0847d)
        (10ce81a3)

    -   Wrote tests for WarningChecker (ca7a4305)

-   **ICPR:**

    -   HTML Timetable generator (`8dddc63`)

    -   Refactoring the entire student class (1618da4)

    -   Code for specifically checking the distance between two
        buildings (code is functional in phase 2 though some of it may
        have been done before phase 1 was due) (`78356fe`)

    -   The command-line interface version of this application
        (`4003412`)

    -   Some of the code that checks for prerequisite, corequisite, and
        exclusion warnings (`0ff06d8` - around that)

    -   Some of the code that checks for course conflicts

    -   All code for checking if a student forgot to add lectures or
        tutorials to a course (`52a5b98`)

    -   Strategy design pattern for `WarningChecker`
        (`725aa7f and 7040115`)

    -   The ability to “smart search” for courses (filter out ones
        student can’t take) (`ba5498c`)

-   **Carol (Caspian-9):**

    -   Builders for all the controller classes (`1699746`)

    -   Builders for use case classes the controllers depend on
        (`1699746`)

    -   Factory for controllers (`1699746`)

    -   Factory for UI inputs (`1f0e3ee`)

    -   Make CourseSearcherAdapter and CourseSearcherAdapterPrev
        singleton (`285c5cc`)

    -   The planned courses list in the student window in the GUI
        (`785a4c0`)

    -   Separate buttons for viewing the fall term and winter term
        timetables (`785a4c0`)

    -   Javadocs for the above classes (`9c7b962`)

-   **Kowshik (re-Koe):**

    -   Check Requisite class (551c08a)

    -   Some of the code that checks for prerequisite, corequisite and
        exclusion warnings (551c08a)

    -   Helped with conflict checker

    -   Tests for Check Requisite (121018b)

    -   Tests for Warning Checker (5140cf3)
