package org.ligi.solar_activity_monitor;

import org.ligi.solar_activity_monitor.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

/**
 * 
 * 2012 Marcus -ligi- Bueschleb
 *
 */
public abstract class SAMBaseWidget extends AppWidgetProvider {

	public RemoteViews remoteViews;
	public ComponentName watchWidget;
	public AppWidgetManager appWidgetManager;
	public Context ctx;
	/*private Editor edit;
	private SharedPreferences sp;
	*/
	@Override
	public void onUpdate( Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds ) {
		this.ctx=context;
		remoteViews = new RemoteViews( context.getPackageName(), getLayout() );
		watchWidget = getComponent();
		this.appWidgetManager=appWidgetManager;
		new UpdateTask().execute();
		

        
		String url = "http://www.n3kl.org/sun/images/noaa_kp_3d.gif";
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(url));

		
		//Intent intent = new Intent(context, InfoDialog.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);
        /*
        sp = context.getSharedPreferences("updates",Context.MODE_PRIVATE);
        edit = sp.edit();*/
        
	}

	class UpdateTask extends BaseUpdateTask {
		@Override
		protected void onPostExecute(Integer result) {
			update(result);
			super.onPostExecute(result);
		}
	}

	public abstract ComponentName getComponent();
	public abstract void update(Integer val);
	public abstract int getLayout();
}
