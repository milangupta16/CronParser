# CronParser
Parses a cron string and expands each field to show the times at which it will run.

Here we are considering the standard cron format with five time fields (minute, hour, day of month, month, and day of week) plus a command.

The cron string for this application required a single argument.

For Example:
Input:
*/15 0 1,15 * 1-5 /usr/bin/find

Output:

minute       0 15 30 45
hour         0
day of month 1 15
month        1 2 3 4 5 6 7 8 9 10 11 12
day of week  1 2 3 4 5
command      /usr/bin/find

Requirements:

Java 8 or higher
JDK installed on your machine

How to Set Up: 

1.Clone or Download this Repository.
2.Compile the Application:
              javac CronParser.java
3.Run the Application:
              java CronParser "*/15 0 1,15 * 1-5 /usr/bin/find"


