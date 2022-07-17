
# Supposed bad design cases I decided to keep
This is a list of all "bad design" cases in the project that I've intentionally kept in.

## Requisite Checker
The class `RequisiteChecker` has two purposes: to check prerequisites and corequisites, and to check exclusions. Each are managed in two different classes. You'll notice this class has two methods: `check()` and `checkExclusions()` - coding it like that is way quicker than making a factory, as these are the only two methods that will be needed - no more is planned.

## Course-related entities
You may notice there are some unchecked casts over there, and that the entities pertaining to `Course` seem to be constructing themselves based on JSON data rather than another class constructing it for them. Taking this approach is way less frustrating than having another class construct it. Also, `Course`-related entities are immutable and can never be changed, and seems to be compatible with the course data we have.

# Plans

Text below lists what the program should be able to do in phase 1, and what the program should be able to do in phase 2.

## What should this program be able to do?
- Allow students to record courses they have already passed before the current session (this may include summer courses they are concurrently taking
- Allow students to plan for courses offered in the upcoming session
    - For planned courses, do prerequisite, corequisite, and exclusion checks on them. Also look for other issues that may come up should students enroll in these courses.
- Allow students to choose the lecture sections for each course
    - If a lecture section is unselected, the program will warn them about it, but will not break the program.
    - The program should give warnings if a course conflicts, and if two back-to-back courses are too far from each other.

Here's an sample command prompt UI:

```
You have completed:
CSC110Y1, CSC111H1, STA130H1, MAT137Y1

Your planned courses

Fall (6.5 FCEs): [WARNING: Overload]
CSC236H1 L0201 T0202
CSC258H1 L0201 [WEAK WARNING: Long travel dist. with subsequent crs]
STA247H1 L???? T0201  
MAT224H1 L0101 T0201 [WARNING: Conflicts with BIO120H1 L0101]  
BIO120H1 L0101 P???? [WARNING: Conflicts with MAT224H1 L0101]
CSC198H1 L0101 [CRITICAL: Year 1 only course]
STA130H1 L0101 [CRITICAL: Year 1 only course]

Winter (420969.5 FCEs): [WARNING: Overload]
MAT244H1 L0101 T0101
MAT246H1 L0101 T0101
MAT247H1 L0101 T???? [CRITICAL: Missing coreqs]
MAT267H1 L0101 T0101 [CRITICAL: Missing coreqs]

Year:
MAT235Y1 [CRITICAL: Exclusion found]
MAT237Y1
```


## What should be planned in the future
- Automatic timetable generator (using the brute force approach)
- Allow students to rate courses (optional)
    - Note that `Course` data is immutable, so all the rating data will have to be placed somewhere else.

