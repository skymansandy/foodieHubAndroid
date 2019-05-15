package in.codeshuffle.foodiehub.data.db;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.codeshuffle.foodiehub.data.db.model.DaoMaster;
import in.codeshuffle.foodiehub.di.ApplicationContext;
import in.codeshuffle.foodiehub.di.DatabaseInfo;
import in.codeshuffle.foodiehub.util.AppLogger;



@Singleton
public class DbOpenHelper extends DaoMaster.OpenHelper {

    @Inject
    DbOpenHelper(@ApplicationContext Context context, @DatabaseInfo String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        AppLogger.d("DEBUG", "DB_OLD_VERSION : " + oldVersion + ", DB_NEW_VERSION : " + newVersion);
        switch (oldVersion) {
            case 1:
            case 2:
                //db.execSQL("ALTER TABLE " + UserDao.TABLENAME + " ADD COLUMN "
                // + UserDao.Properties.Name.columnName + " TEXT DEFAULT 'DEFAULT_VAL'");
        }
    }
}
