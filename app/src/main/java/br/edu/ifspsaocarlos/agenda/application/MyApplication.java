package br.edu.ifspsaocarlos.agenda.application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ediel on 16/12/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("agenda.realm").schemaVersion(1).build();
        Realm.setDefaultConfiguration(config);
    }
}
