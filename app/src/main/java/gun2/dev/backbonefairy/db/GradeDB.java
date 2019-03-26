package gun2.dev.backbonefairy.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class GradeDB extends RealmObject {
    @PrimaryKey
    public int id;
    public int exp;
}
