package org.ligi.solar_activity_monitor;

import org.ligi.solar_activity_monitor.R;

import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

/**
 * 
 * 2012 Marcus -ligi- Bueschleb
 *
 */
public class SAMIconWidget extends SAMBaseWidget {

	@Override
	public void update(Integer val,RemoteViews remoteViews) {
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

	}

	@Override
	public ComponentName getComponent(Context ctx) {
		return new ComponentName( ctx, SAMIconWidget.class );
	}

	@Override
	public int getLayout() {
		return R.layout.widget_icon;
	}
	
}
