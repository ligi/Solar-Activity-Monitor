package org.ligi.solar_activity_monitor;

import org.ligi.solar_activity_monitor.R;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Color;
import android.widget.RemoteViews;

/**
 * 
 * 2012 Marcus -ligi- Bueschleb
 *
 */
public class SAMTextWidget extends AppWidgetProvider {

	private RemoteViews remoteViews;
	private ComponentName watchWidget;
	private AppWidgetManager appWidgetManager;

	@Override
	public void onUpdate( Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds ) {
		remoteViews = new RemoteViews( context.getPackageName(), R.layout.widget_text );

		watchWidget = new ComponentName( context, SAMTextWidget.class );
		this.appWidgetManager=appWidgetManager;

		new UpdateTask().execute();

	}

	class UpdateTask extends BaseUpdateTask {

		@Override
		protected void onPostExecute(Integer result) {

			if ((result==null)||(result<0))
				remoteViews.setTextViewText( R.id.kp_number_tv, "-");
			else {
				remoteViews.setTextViewText( R.id.kp_number_tv, "" + result);
				if (result<4)
					remoteViews.setTextColor(R.id.kp_number_tv, Color.GREEN);
				else if (result==4)
					remoteViews.setTextColor(R.id.kp_number_tv, Color.YELLOW);
				else if (result>4)
					remoteViews.setTextColor(R.id.kp_number_tv, Color.RED);
			}

			appWidgetManager.updateAppWidget( watchWidget, remoteViews );
			super.onPostExecute(result);
		}
	}
}
