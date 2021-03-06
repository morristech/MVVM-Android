package android.mvvm.mg.com.mvvm_android.core.bindingAdapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.mvvm.mg.com.mvvm_android.R;
import android.mvvm.mg.com.mvvm_android.core.base.DMBaseBindingAdapter;
import android.mvvm.mg.com.mvvm_android.core.base.DMBaseIOnItemClickListener;
import android.mvvm.mg.com.mvvm_android.core.base.DmBaseIEmptyViewListener;
import android.mvvm.mg.com.mvvm_android.core.glide.GlideApp;
import android.mvvm.mg.com.mvvm_android.core.models.room.card.Card;
import android.mvvm.mg.com.mvvm_android.core.models.transaction.TransactionData;
import android.mvvm.mg.com.mvvm_android.core.utils.MVVMUtils;
import android.mvvm.mg.com.mvvm_android.ui.fragments.cards.view.CardAdapter;
import android.mvvm.mg.com.mvvm_android.ui.fragments.transaction.view.TransactionAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

public final class MVVMBindingAdapter extends DMBaseBindingAdapter {

    @BindingAdapter("setImageUrl")
    public static void setImageUrl(final ImageView imageView, final String path) {
        GlideApp.with(imageView.getContext())
                .load(path)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.placeholder_profile_photo)
                .into(imageView);
    }

    @BindingAdapter("setError")
    public static void setError(final MaterialEditText editText, final String error) {
        editText.setError(error);
    }

    @BindingAdapter("setCardStatus")
    public static void setCardStatus(final TextView textView, final boolean isDefault) {
        final Context context = textView.getContext();
        final String text;
        if (isDefault) {
            text = context.getResources().getString(R.string.cards_default);
        } else {
            text = context.getResources().getString(R.string.cards_secondary);
        }

        textView.setText(text);
    }

    @BindingAdapter(value = {"initRecycleViewCardList", "listener"})
    public static void initRecycleViewCardList(final RecyclerView recyclerView, final List<Card> cardList, final DMBaseIOnItemClickListener<Card> listener) {
        final Context context = recyclerView.getContext();
        if (recyclerView.getAdapter() == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(new CardAdapter(cardList != null ? cardList : new ArrayList<>(), listener));
        } else {
            ((CardAdapter) recyclerView.getAdapter()).replaceList(cardList);
        }
        listener.onEmptyVisible(cardList == null || cardList.size() == 0);
    }

    @BindingAdapter(value = {"initRecycleViewTransactionList", "listener"})
    public static void initRecycleViewTransactionList(final RecyclerView recyclerView, final TransactionData transactionData, final DmBaseIEmptyViewListener listener) {
        if (transactionData != null) {
            if (transactionData.getNextPage() == 0) {
                recyclerView.clearOnScrollListeners();
            }

            if (recyclerView.getAdapter() == null) {
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(new TransactionAdapter(transactionData.getTransactionList()));
                listener.onEmptyVisible(transactionData.getTransactionList() == null || transactionData.getTransactionList().size() == 0);
            } else {
                ((TransactionAdapter) recyclerView.getAdapter()).addList(transactionData.getTransactionList());
            }
        } else {
            listener.onEmptyVisible(true);
        }
    }

    @BindingAdapter(value = {"color1", "color2"})
    public static void makeBackground(final View view, final String color1, final String color2) {
        view.setBackground(MVVMUtils.changeDrawableColor(view.getContext(), color1, color2));
    }
}