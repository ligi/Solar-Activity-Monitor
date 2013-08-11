package org.ligi.solar_activity_monitor;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.RemoteViews;

/**
 * the base widget where the text and the icon widget inherit from
 */
public abstract class SAMBaseWidget extends AppWidgetProvider {

    private Context context;
    private static final int LAST_GOOD_TIMEOUT = 60 * 60 * 1000;//1h

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        start(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        start(context);
    }

    private void start(Context context) {
        this.context = context;
        long last_timestamp = getSharedPrefs().getLong("last_fetch", -1);

        if (last_timestamp == -1 || Math.abs(System.currentTimeMillis() - last_timestamp) > LAST_GOOD_TIMEOUT) { // 1h
            new UpdateTask(context).execute();
        }
    }

    private SharedPreferences getSharedPrefs() {
        return context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    private class UpdateTask extends BaseUpdateTask {

        private Context ctx;

        public UpdateTask(Context ctx) {
            this.ctx = ctx;
        }


        @Override
        protected void onPostExecute(Integer result) {

            if ((result == null) || result < 0) {
                getSharedPrefs().edit().putLong("last_fetch", -1).commit();
            } else {
                getSharedPrefs().edit().putLong("last_fetch", System.currentTimeMillis()).commit();
            }

            ComponentName watchWidget = getComponent(ctx);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(ctx);

            RemoteViews remoteViews = new RemoteViews(ctx.getPackageName(), getLayout());
            update(result, remoteViews);


            String url = "http://www.n3kl.org/sun/images/noaa_kp_3d.gif";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));

            PendingIntent pendingIntent = PendingIntent.getActivity(ctx, 0, intent, 0);
            remoteViews.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);

            appWidgetManager.updateAppWidget(watchWidget, remoteViews);
            super.onPostExecute(result);
        }
    }

    public abstract ComponentName getComponent(Context ctx);

    public abstract void update(Integer val, RemoteViews rv);

    public abstract int getLayout();
}
