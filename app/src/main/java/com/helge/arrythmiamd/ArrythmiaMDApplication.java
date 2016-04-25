package com.helge.arrythmiamd;

import com.parse.Parse;

/**
 * Created by emil on 19/04/16.
 */
public class ArrythmiaMDApplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(getApplicationContext());
        Parse.initialize(this, "7wop8ncD1stVBTcbPaCgDa1ZRHeiwkZulGYuV0nE", "RY7qjP9NJDwV81dEh3ZHVrMfy3ZU7qP9fKJ9QZOQ");
    }
}
