import java.util.Arrays;

public class CronParser {
    public static void main(String[] args) {
        if (args.length==0) {
            System.out.println("Please provide a cron string as an argument");
            return;
        }

        String cronString=args[0];
        String[] fields=cronString.split(" ");

        if (fields.length<6) {
            System.out.println("Invalid cron string, It should have 5 time fields and a command");
            return;
        }

        System.out.println("minute       " + parseField(fields[0], 0, 59));
        System.out.println("hour         " + parseField(fields[1], 0, 23));
        System.out.println("day of month " + parseField(fields[2], 1, 31));
        System.out.println("month        " + parseField(fields[3], 1, 12));
        System.out.println("day of week  " + parseField(fields[4], 0, 6));

        String command = String.join(" ", Arrays.copyOfRange(fields, 5, fields.length));
        System.out.println("command      " + command);
    }

    private static String parseField(String field, int min, int max) {
        StringBuilder res = new StringBuilder();

        if(field.equals("*")) {
            for (int i=min;i<=max;i++) {
                res.append(i).append(" ");
            }
        }else if(field.contains(",")) {
            String[] parts = field.split(",");
            for (String part:parts) {
                res.append(parsePart(part,min,max)).append(" ");
            }
        } else {
            res.append(parsePart(field,min,max)).append(" ");
        }

        return res.toString().trim();
    }

    private static String parsePart(String part, int min, int max) {
        StringBuilder res = new StringBuilder();

        if (part.contains("/")) {
            String[] stepParts = part.split("/");
            int step = Integer.parseInt(stepParts[1]);
            String range = stepParts[0];

            if (range.equals("*")) {
                for (int i=min;i<=max;i+=step) {
                    res.append(i).append(" ");
                }
            } else if (range.contains("-")) {
                String[] edges = range.split("-");
                int start = Integer.parseInt(edges[0]);
                int end = Integer.parseInt(edges[1]);

                for (int i=start;i<=end;i+=step) {
                    res.append(i).append(" ");
                }
            }
        } else if (part.contains("-")) {
            String[] edges=part.split("-");
            int start = Integer.parseInt(edges[0]);
            int end = Integer.parseInt(edges[1]);

            for (int i=start;i<=end;i++) {
                res.append(i).append(" ");
            }
        } else {
            res.append(part);
        }

        return res.toString().trim();
    }
}
