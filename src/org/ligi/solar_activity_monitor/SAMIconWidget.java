package org.ligi.solar_activity_monitor;

import org.ligi.solar_activity_monitor.R;

import android.content.ComponentName;

/**
 * 
 * 2012 Marcus -ligi- Bueschleb
 *
 */
public class SAMIconWidget extends SAMBaseWidget {

	@Override
	public void update(Integer val) {
		if ((val==null)||(val<0))
			remoteViews.setImageViewResource( R.id.kp_number_ico, android.R.drawable.ic_menu_close_clear_cancel);
		else {
			if (val<4)
				remoteViews.setImageViewResource( R.id.kp_number_ico, R.drawable.quiet);
			else if (val==4)
				remoteViews.setImageViewResource( R.id.kp_number_ico, R.drawable.raised);
			else if (val>4)
				remoteViews.setImageViewResource( R.id.kp_number_ico, R.drawable.storm);
		}	

			/*
		Date now=new Date(System.currentTimeMillis());
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
        edit.putString("updates",sp.getString("updates","")+dt1.format(now)+"->"+ result +";");
        edit.commit();
			 */
		appWidgetManager.updateAppWidget( watchWidget, remoteViews );
	}

	@Override
	public ComponentName getComponent() {
		return new ComponentName( ctx, SAMIconWidget.class );
	}

	@Override
	public int getLayout() {
		return R.layout.widget_icon;
	}

	
}
