package lightningparser;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class LightningParser {

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {  
      Scanner scanner = new Scanner(System.in);
      
      String file_name; 
      String[] values;
      String line;
      
      String time;
      String latitude;
      String longitude;
      String signal_strength;
      String semi_major_axis;
      String semi_minor_axis; 
      String ellipse_angle;
//      String extra1;
//      String extra2;
//      String extra3;
      
      int year;
      int month;
      int day;
      int hour;
      int mins;
      int secs;
      double fraction;
      long unixTimeMs;

      // Asks user for filename
      // System.out.println("Enter filename: ");
      // file_name = scanner.nextLine();
      
      if (args != null && args.length > 0) {
         file_name = args[0];
         System.err.println("Parsing lightning file: " + args[0]);
      } else {
         System.err.println("No input file specified! " + Integer.toString(args.length));
         System.exit(1);
         return;
      }

      // This will be deleted later. Testing purposes.
      int count = 0;

      try {
         File readFile = new File(file_name);
         Scanner inFile = new Scanner(readFile);
         
         // loops through the data of the file
         // DELETE COUNT
         while (inFile.hasNextLine()) {
            line = inFile.nextLine();
            System.err.println("READ: " + line);
            values = removeEmpty(line.split(" "));
            
            time = values[0] + " " + values[1];

            // Parses time string into specified time fields
            year = Integer.parseInt(time.split(" ")[0].split("-")[0]);
            month = Integer.parseInt(time.split(" ")[0].split("-")[1]);
            day =  Integer.parseInt(time.split(" ")[0].split("-")[2]);     
            hour = Integer.parseInt(time.split(" ")[1].split(":")[0]);
            mins = Integer.parseInt(time.split(" ")[1].split(":")[1]);
            secs = Integer.parseInt(time.split(" ")[1].split(":")[2]
                    .split("\\.")[0]);
            fraction = Double.parseDouble("0." + time.split(" ")[1].split(":")[2]
                    .split("\\.")[1]);
            
            // Creates a date instance
            var myDate = new GregorianCalendar(year, month - 1, day, hour, mins, secs);
            System.err.printf("%d-%d-%d %d:%d:%d\n", year, month, day, hour, mins, secs);
            System.err.printf("%d\n", (int)(fraction * 1000));
            unixTimeMs = myDate.getTimeInMillis() + (int)(fraction * 1000);
            
            latitude = values[2];
            longitude = values[3];
            signal_strength = values[4];
            semi_major_axis = values[5];
            semi_minor_axis = values[6];
            ellipse_angle = values[7];
//            extra1 = values[8];
//            extra2 = values[9];
//            extra3 = values[10];            


            // Formats variables into string
            line = String.format("Lightning,dummy=1 latitude=%s,longitude=%s,"
                    + "signal_strength=%s,semi_major_axis=%s,"
                    + "semi_minor_axis=%s,ellipse_angle=%s %d",
//                    + "extra1=%s,extra2=%s,extra3=%s"
                    latitude, longitude, signal_strength, semi_major_axis,
                    semi_minor_axis, ellipse_angle, unixTimeMs
//                    + "extra1, extra2, extra3"
            );
            System.out.println(line);
         }
                  
         inFile.close();
         
      } catch (IOException ioe) {
         System.err.println("Error opening file " + file_name + "\n" + ioe.toString());
      }   
   }
   private static String[] removeEmpty(String[] ary) {
      return Arrays.stream(ary).filter(e -> e.trim().length() > 0).toArray(String[]::new);
   }
}
