package br.com.karloskelvin.crudrealm.util;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.regex.Pattern;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Karlos Kelvin on 06/01/16.
 * Desenvolvedor de Sistemas - UFCA
 * Analista e Desenvolvedor de Sistemas.
 */
public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration configuration = new RealmConfiguration.Builder(this)
                .name("crudrealm.realm")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(configuration);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this)
                                .withLimit(1000)
                                .databaseNamePattern(Pattern.compile(".+\\.realm"))
                                .build())
                        .build());
    }
}
