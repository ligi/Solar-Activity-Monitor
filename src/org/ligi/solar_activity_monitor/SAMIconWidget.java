package org.ligi.solar_activity_monitor;
import org.ligi.solar_activity_monitor.R;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

/**
 * 
 * 2012 Marcus -ligi- Bueschleb
 *
 */
public class SAMIconWidget extends AppWidgetProvider {

	private RemoteViews remoteViews;
	private ComponentName watchWidget;
	private AppWidgetManager appWidgetManager;

	@Override
	public void onUpdate( Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds )
	{
		remoteViews = new RemoteViews( context.getPackageName(), R.layout.widget_icon );
		watchWidget = new ComponentName( context, SAMIconWidget.class );
		this.appWidgetManager=appWidgetManager;
		new UpdateTask().execute();
	}

	class UpdateTask extends BaseUpdateTask {
		@Override
		protected void onPostExecute(Integer result) {


			if ((result==null)||(result<0))
				remoteViews.setImageViewResource( R.id.kp_number_ico, android.R.drawable.ic_menu_close_clear_cancel);
			else {
				if (result<4)
					remoteViews.setImageViewResource( R.id.kp_number_ico, R.drawable.quiet);
				else if (result==4)
					remoteViews.setImageViewResource( R.id.kp_number_ico, R.drawable.raised);
				else if (result>4)
					remoteViews.setImageViewResource( R.id.kp_number_ico, R.drawable.storm);
			}	


			appWidgetManager.updateAppWidget( watchWidget, remoteViews );
			super.onPostExecute(result);
		}
	}

}
