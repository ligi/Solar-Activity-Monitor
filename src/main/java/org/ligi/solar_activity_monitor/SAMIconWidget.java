package org.ligi.solar_activity_monitor;

import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

/**
 * widget showing icon in 4 states quite/raised/storm/error
 */
public class SAMIconWidget extends SAMBaseWidget {

    @Override
    public void update(Integer val, RemoteViews remoteViews) {
        remoteViews.setImageViewResource(R.id.kp_number_ico, getIconResForKpValue(val));
    }

    private int getIconResForKpValue(Integer value) {
        if ((value == null) || (value < 0)) {
            return android.R.drawable.ic_menu_close_clear_cancel;
        }
        if (value < 4) {
            return R.drawable.quiet;
        }

        if (value == 4) {
            return R.drawable.raised;
        }

        return R.drawable.storm; // >4
    }

    @Override
    public ComponentName getComponent(Context ctx) {
        return new ComponentName(ctx, SAMIconWidget.class);
    }

    @Override
    public int getLayout() {
        return R.layout.widget_icon;
    }

}
