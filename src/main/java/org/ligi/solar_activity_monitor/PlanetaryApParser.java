package org.ligi.solar_activity_monitor;

/**
 * Parses the AP.txt file from http://www.swpc.noaa.gov/ftpdir/lists/geomag/AK.txt
 * and extacts an int with the most recent Planetrary Ap Value
 */
public class PlanetaryApParser {

    /**
     * @return most recent Planetrary Ap Value from the content of an AP file
     */
    public static int parse(String str) {
        int last_number = -1;
        String[] lines = str.split("\n");
        for (String line : lines) {

            // this is the Line we are looking for
            // example Planetary(estimated Ap) 5 1 2 1 1 1 2 2 1
            if (line.startsWith("Planetary")) {

                // cut away the description
                line = line.substring(line.indexOf(")") + 1);

                // we search for the current value by looking at all numbers
                // and finding the last value that is not -1 ( aka NA )

                String[] numbers = line.split(" ");
                for (String number : numbers) {
                    try {
                        int act_num = Integer.parseInt(number);
                        if (act_num == -1) {
                            // yay we found our number
                            return last_number;

                        } else {
                            // not finished - we have to search further
                            last_number = act_num;
                        }
                    } catch (Exception e) {
                    }
                }

            }

        }
        return -1; // nothing found
    }

}
