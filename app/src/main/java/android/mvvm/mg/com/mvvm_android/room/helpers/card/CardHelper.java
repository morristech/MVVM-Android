package android.mvvm.mg.com.mvvm_android.room.helpers.card;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.mvvm.mg.com.mvvm_android.room.helpers.*;
import android.mvvm.mg.com.mvvm_android.room.main.MVVMDatabase;
import android.mvvm.mg.com.mvvm_android.room.models.card.Card;

import java.util.List;
import java.util.concurrent.Executors;

public class CardHelper {

    public static LiveData<List<Card>> getAllCard(final Context context) {
        return MVVMDatabase.getInstance(context).getDB().cardDao().getAll();
    }

    public static void insertCard(final Context context, final Card card, final IOnInsertListener listener) {
        Executors.newSingleThreadExecutor().execute(() -> {
            final long id = MVVMDatabase.getInstance(context).getDB().cardDao().insert(card);
            listener.onInsert(id);
        });
    }

    public static void insertCardList(final Context context, final List<Card> cardList, final IOnInsertAllListener listener) {
        Executors.newSingleThreadExecutor().execute(() -> {
            final long[] ids = MVVMDatabase.getInstance(context).getDB().cardDao().insertAll(cardList);
            listener.onInsertAll(ids);
        });
    }

    public static void update(final Context context, final Card card, final IOnUpdateListener listener) {
        Executors.newSingleThreadExecutor().execute(() -> {
            MVVMDatabase.getInstance(context).getDB().cardDao().update(card);
            listener.onUpdate();
        });
    }

    public static void delete(final Context context, final Card card, final IOnDeleteListener listener) {
        Executors.newSingleThreadExecutor().execute(() -> {
            MVVMDatabase.getInstance(context).getDB().cardDao().delete(card);
            listener.onDelete();
        });
    }

    public static void clearTable(final Context context, final IOnClearTableListener listener) {
        Executors.newSingleThreadExecutor().execute(() -> {
            MVVMDatabase.getInstance(context).getDB().cardDao().cleanTable();
            listener.onClearTable();
        });
    }
}