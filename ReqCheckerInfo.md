# The prerequisite checker

Looks through the prerequisite strings of each course, and makes an attempt to read it.

Most courses format their prerequisite strings well, whilst others do not, which
can confuse this program.

The program does something very similar to what is described in this article:

https://artsci.calendar.utoronto.ca/how-use-calendar

## Prerequisites vs. Exclusions

Prerequisites, corequisites, and recommend preparation are treated the same way.
Exclusions are treated differently due to how ambitiously they are written on the timetable.

While prerequisites are read as described on ArtSci's calendar,
exclusions are read by taking everything that looks like a course
(matches the course regex) and adds it to the exclusion list.

## The flaws of the prerequisite checker

People aren't consistent in typing them, and this program can't always process them naturally,
because tere are no actual guidelines on how prerequisites should be written. Here are some
examples:

```
[(MAT135H1, MAT136H1)/ (MAT135H5, MAT136H5)/ MAT134Y5/ MAT135Y5/ (MATA30H3/ MATA31H3, MATA36H3), MAT138H1/ MAT102H5/ MAT246H1]/ MAT137Y1/ MAT137Y5/ (MATA30H3/ MATA31H3, MATA37H3)/ MAT157Y1/ MAT157Y5, MAT223H1/ MATA22H3/ MATA23H3/ MAT240H1/ MAT240H5
```

is a good prerequisite string as it can be read percisely by the program with no complications.

```
MAT235Y1/ MAT237Y1/ MAT257Y1/ MAT291H1/ MAT292H1/ MAT294H1/ (MAT232H5, MAT368H5)/ (MAT233H5, MAT236H5)/ (MATB41H3, MATB42H3); MAT223H1/ MAT240H1/ MAT185H1/ MAT188H1; CSC209H1/ CSC209H5/ CSCB09H3/ proficiency in C or C++/ APS105H1/ ESC180H1/ CSC180H1
```

this prerequisite string's flaw is the usage of the words `proficiency in C or C++`. The program
can't understand what that means, so it will not treat it as a prerequisite and skip it.

```5.0 MAT credits, including MAT224H1/MAT247H1 and MAT237Y1/MAT257Y1```

The program will only read this as ``(MAT224H1/MAT247H1), (MAT237Y1/MAT257Y1)``. It will not understand
what "5.0 MAT credits" mean, and will thus not enforce it.

```
AST121H1, AST210H1, AST221H1, AST222H1, AST201H5, ASTA02H3, ASTB23H3. Also excluded are CIV100H1, CIV101H1, CIV102H1 and any 100- or higher-series CHM or PHY courses taken previously or concurrently (with the exception of PHY100H1, PHY100H5, PHY101H1, PHY201H1, PHY202H1, PHY205H1, PHY207H1, CHM101H1, CHM209H1; and AP, IB, CAPE, and GCE Transfer Credits)
```

This is an exclusion list. The program will treat every course matching the course regex in this string to be exclusions. That's it. This can become problematic in this instance.

```
1.0 ENG credit or any 4.0 credits
```

A course with this as its prerequisite string is assumed to have no prerequisites, because the program can't understand any of it.

## Takeaways

- This program will only look at courses as a prerequisite.
- This program will not enforce minimum grades.
- For exclusions, the program will look at every course code inside the exclusion tree and add it to the exclusion list. That's it. Most courses won't cause any problems, but courses
  stating "except" in the exclusion list may cause problems. Some examples are AST101H1, AST201H1.
- This program is not obliged to be correct on any prerequisite string that uses English.
