package ru.startandroid.p1173simplewidget3;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.DateFormat;
import java.util.Date;

public class ExampleWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ExampleWidgetItemFactory(getApplicationContext(), intent);
    }

    class ExampleWidgetItemFactory implements RemoteViewsFactory {
        private Context context;
        private int appWidgetId;
        private String[] exampleData = {"Real Madrid", "Barcelona", "Atletico Madrid", "Sevilla",
        "Villareal", "Athletic Bilbao", "Real Sociedad", "Betis", "Granada", "Cadiz"};

        ExampleWidgetItemFactory (Context context, Intent intent) {
            this.context = context;
            this.appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        @Override
        public void onCreate() {
            // connect to data source
        }

        @Override
        public void onDataSetChanged() {
            // refresh data
            Date date = new Date();
            String timeFormatted = DateFormat.getTimeInstance(DateFormat.SHORT).format(date);
            exampleData = new String[] {"Real Madrid\n" + timeFormatted, "Barcelona\n" + timeFormatted,
                    "Atletico Madrid\n" + timeFormatted, "Sevilla\n" + timeFormatted};
            SystemClock.sleep(3000);
        }

        @Override
        public void onDestroy() {
            // close data source
        }

        @Override
        public int getCount() {
            return exampleData.length;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.example_widget_item);
            views.setTextViewText(R.id.example_widget_item_text, exampleData[position]);

            Intent fillIntent = new Intent();
            fillIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            views.setOnClickFillInIntent(R.id.example_widget_item_text, fillIntent);

            SystemClock.sleep(500);
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
