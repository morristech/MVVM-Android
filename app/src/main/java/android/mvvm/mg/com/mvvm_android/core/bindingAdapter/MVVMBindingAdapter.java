package android.mvvm.mg.com.mvvm_android.core.bindingAdapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.mvvm.mg.com.mvvm_android.R;
import android.mvvm.mg.com.mvvm_android.core.glide.GlideApp;
import android.mvvm.mg.com.mvvm_android.core.models.TransactionData;
import android.mvvm.mg.com.mvvm_android.core.models.room.card.Card;
import android.mvvm.mg.com.mvvm_android.core.utils.MVVMUtils;
import android.mvvm.mg.com.mvvm_android.ui.adapters.card.CardAdapter;
import android.mvvm.mg.com.mvvm_android.ui.adapters.transaction.TransactionAdapter;
import android.mvvm.mg.com.mvvm_android.ui.fragments.base.IBaseEmptyViewListener;
import android.mvvm.mg.com.mvvm_android.ui.fragments.base.IBaseOnItemClickListener;
import android.support.v7.widget.AppCompatCheckBox;
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

import dmutils.com.dmutils.animation.DMTranslateViewAnimation;

public class MVVMBindingAdapter {

    @BindingAdapter("setError")
    public static void setError(final MaterialEditText editText, final String error) {
        editText.setError(error);
    }

    @BindingAdapter("setVisibleView")
    public static void setVisibleView(final View view, final boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("setFadAnim")
    public static void setFadAnim(final View view, final boolean isVisible) {
        boolean isFirstTime = false;
        if (view.getTag() == null) {
            isFirstTime = true;
        }
        view.setTag("");

        if (isFirstTime) {
            view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        } else {
            if (isVisible) {
                view.setVisibility(View.VISIBLE);
                final ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
                anim.setDuration(300);
                anim.start();
            } else {
                view.animate()
                        .alpha(0.0f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                view.setVisibility(View.GONE);
                            }
                        });
            }
        }
    }

    @BindingAdapter("setEmptyViewVisibleAnim")
    public static void setEmptyViewVisibleAnim(final View view, final boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        if (isVisible) {
            DMTranslateViewAnimation.upView((Activity) view.getContext(), view, 500, () -> {

            });
        }
    }

    @BindingAdapter("setImageUrl")
    public static void setImageUrl(final ImageView imageView, final String path) {
        GlideApp.with(imageView.getContext())
                .load(path)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.placeholder_profile_photo)
//                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
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
    public static void initRecycleViewCardList(final RecyclerView recyclerView, final List<Card> cardList, final IBaseOnItemClickListener<Card> listener) {
        final Context context = recyclerView.getContext();
        if (recyclerView.getAdapter() == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(new CardAdapter(cardList != null ? cardList : new ArrayList<>(), listener));
            listener.onVisible(cardList == null || cardList.size() == 0);
        } else {
            ((CardAdapter) recyclerView.getAdapter()).replaceList(cardList);
            listener.onVisible(cardList == null || cardList.size() == 0);
        }
    }

    @BindingAdapter(value = {"initRecycleViewTransactionList", "listener"})
    public static void initRecycleViewTransactionList(final RecyclerView recyclerView, final TransactionData transactionData, final IBaseEmptyViewListener listener) {
        if (transactionData != null) {
            if (transactionData.getNextPage() == 0) {
                recyclerView.clearOnScrollListeners();
            }

            if (recyclerView.getAdapter() == null) {
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(new TransactionAdapter(transactionData.getTransactionList()));
                listener.onVisible(transactionData.getTransactionList() == null || transactionData.getTransactionList().size() == 0);
            } else {
                ((TransactionAdapter) recyclerView.getAdapter()).addList(transactionData.getTransactionList());
            }
        } else {
            listener.onVisible(true);
        }
    }

    @BindingAdapter(value = {"color1", "color2"})
    public static void makeBackground(final View view, final String color1, final String color2) {
        view.setBackground(MVVMUtils.changeDrawableColor(view.getContext(), color1, color2));
    }

    @BindingAdapter("android:checked")
    public static void setChecked(final AppCompatCheckBox checkBox, final Boolean isChecked) {
        checkBox.setChecked(isChecked != null ? isChecked : false);
    }
}