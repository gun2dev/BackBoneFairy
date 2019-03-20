package gun2.dev.backbonefairy.db.utils;

import android.content.Context;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

public class RealmController implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

    }

    /**
     * DB삭제
     */
    public static void deleteDb(Context context) {
        Realm.init(context);
        Realm.deleteRealm(baseRealmConfig());
    }

    /**
     * Realm설정
     */
    //삭제
    public static RealmConfiguration getRealmConfig() {
        return new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
    }

    //Realm DB config 설정
    public static RealmConfiguration baseRealmConfig() {
        RealmConfiguration myConfig = new RealmConfiguration.Builder()
                .name("backbonefairy.realm")
                .schemaVersion(1)
                .build();
        return myConfig;
    }
}
