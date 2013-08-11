package org.ligi.solar_activity_monitor;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * task to fetch the current Planetary Ap Value
 * <p/>
 * 2012 Marcus -ligi- Bueschleb
 */
public class BaseUpdateTask extends AsyncTask<Void, Void, Integer> {

    @Override
    /**
     *  @returns the most recent val or -1 if not forund or null on error
     */
    protected Integer doInBackground(Void... params) {

        for (int attempt = 0; attempt < 3; attempt++)
            // try to read the cached/parsed version
            try {
                String parsed_str = downloadURL2String(new URL("http://mk-android.appspot.com/checkSolar"));
                if (parsed_str != null)
                    return Integer.parseInt(parsed_str);
                Thread.sleep(1000 * attempt);
            } catch (Exception e) {
                // OK we try the other method - was worth a try
            }

        for (int attempt = 0; attempt < 6; attempt++)
            // fallback
            try {
                String act_ak = downloadURL2String(new URL("http://www.swpc.noaa.gov/ftpdir/lists/geomag/AK.txt"));
                if (act_ak != null)
                    return PlanetaryApParser.parse(act_ak);
                Thread.sleep(1000 * attempt * attempt);
            } catch (Exception e) {
            }

        return null;
    }

    /**
     * gets the content of an URL as String
     *
     * @param url
     * @return the String or null if there was an error
     */
    private static String downloadURL2String(URL url) {

        URLConnection con;
        InputStream in = null;
        try {
            con = url.openConnection();
            in = con.getInputStream();
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e3) {
        }

        return sb.toString();

    }
}
