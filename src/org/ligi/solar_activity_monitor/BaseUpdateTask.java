package org.ligi.solar_activity_monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;

/**
 * 
 * 2012 Marcus -ligi- Bueschleb
 *
 */
public class BaseUpdateTask extends AsyncTask<Void, Void, Integer> {

	@Override
	protected Integer doInBackground(Void... params) {
		try {
			String act_ak=downloadURL2String(new URL("http://www.swpc.noaa.gov/ftpdir/lists/geomag/AK.txt"));
			if (act_ak==null)
				return null;
			int last_number=-2;
			String[] lines=act_ak.split("\n");
			for (String line:lines) {

				if (line.startsWith("Planetary")) {

					line=line.substring(line.indexOf(")")+1);
					String[] numbers=line.split(" ");
					for (String number:numbers) {
						try {
							int act_num=Integer.parseInt(number);
							if (act_num==-1) {
								return last_number;

							} else {
								last_number=act_num;
							}
						} catch (Exception e) { }
					}

				}


			}


		} catch (MalformedURLException e) {
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
