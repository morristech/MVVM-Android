package android.mvvm.mg.com.mvvm_android.core.repository.repositoryManager.database.room.main;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.mvvm.mg.com.mvvm_android.core.models.room.card.Card;
import android.mvvm.mg.com.mvvm_android.core.models.room.card.CardDao;

@Database(entities = {Card.class}, version = 1, exportSchema = false)
public abstract class MVVMRoomDatabase extends RoomDatabase {

    public abstract CardDao cardDao();
}
