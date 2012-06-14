package org.ligi.solar_activity_monitor;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * 
 * 2012 Marcus -ligi- Bueschleb
 *
 */
public class ConfigureActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int mAppWidgetId=0;
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
		    mAppWidgetId = extras.getInt(
		            AppWidgetManager.EXTRA_APPWIDGET_ID, 
		            AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		
		if (mAppWidgetId!=0) {
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
			
			RemoteViews views = new RemoteViews(this.getPackageName(),
					R.layout.widget_icon);
			appWidgetManager.updateAppWidget(mAppWidgetId, views);
					
					
			Intent resultValue = new Intent();
			resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
			setResult(RESULT_OK, resultValue);
			finish();
			return;
		}
		
		setContentView(R.layout.main);
	}

		

}
