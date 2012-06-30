package org.ligi.solar_activity_monitor;

import org.ligi.solar_activity_monitor.R;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Color;
import android.widget.RemoteViews;

/**
 * 
 * 2012 Marcus -ligi- Bueschleb
 *
 */
public class SAMTextWidget extends SAMBaseWidget {

		public void update(Integer val,RemoteViews remoteViews) {
			if ((val==null)||(val<0))
				remoteViews.setTextViewText( R.id.kp_number_tv, " -");
			else {
				remoteViews.setTextViewText( R.id.kp_number_tv, "" + val);
				if (val<4)
					remoteViews.setTextColor(R.id.kp_number_tv, Color.GREEN);
				else if (val==4)
					remoteViews.setTextColor(R.id.kp_number_tv, Color.YELLOW);
				else if (val>4)
					remoteViews.setTextColor(R.id.kp_number_tv, Color.RED);
			}

			
		}

		@Override
		public ComponentName getComponent(Context ctx) {
			return new ComponentName( ctx, SAMTextWidget.class );
		}

		@Override
		public int getLayout() {
			return R.layout.widget_text;
		}

}
