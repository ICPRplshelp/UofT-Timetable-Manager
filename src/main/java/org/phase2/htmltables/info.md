# Usage guidelines

The only public class in this
package is `TableOrganizer`.

This class is the class that is
publicly capable of generating
an `HTML` version of a student's
timetable using the method
`generateHTMLTable()`.
Please read its JavaDoc, as it
contains information about how
to use it.

As a warning, an exception is
thrown if:
- There's a conflict
- Any class that starts or ends
on an XX:30 is present

Note that there are no distance checks.
You'll have to add it yourself or scrap
the idea.

All classes here are in the **presenter**
layer.
