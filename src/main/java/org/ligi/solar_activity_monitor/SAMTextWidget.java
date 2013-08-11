package org.ligi.solar_activity_monitor;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Color;
import android.widget.RemoteViews;

/**
 * widget showing the recent kp value ( number ) in a stateful color
 */
public class SAMTextWidget extends SAMBaseWidget {

    public void update(Integer val, RemoteViews remoteViews) {

        remoteViews.setTextColor(R.id.kp_number_tv, getColorByKpValue(val));

        if ((val == null) || (val < 0)) {
            remoteViews.setTextViewText(R.id.kp_number_tv, "-");
        } else {
            remoteViews.setTextViewText(R.id.kp_number_tv, String.valueOf(val));
        }
    }

    private int getColorByKpValue(Integer val) {
        if ((val == null) || (val < 0)) {
            return Color.GRAY;
        }

        if (val < 4) {
            return Color.GREEN;
        }

        if (val == 4) {
            return Color.YELLOW;
        }

        return Color.RED; // >4
    }

    @Override
    public ComponentName getComponent(Context ctx) {
        return new ComponentName(ctx, SAMTextWidget.class);
    }

    @Override
    public int getLayout() {
        return R.layout.widget_text;
    }

}
