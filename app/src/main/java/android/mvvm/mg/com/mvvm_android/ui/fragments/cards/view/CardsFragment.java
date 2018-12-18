package android.mvvm.mg.com.mvvm_android.ui.fragments.cards.view;

import android.arch.lifecycle.LifecycleOwner;
import android.mvvm.mg.com.mvvm_android.R;
import android.mvvm.mg.com.mvvm_android.core.listeners.IEmptyViewHandler;
import android.mvvm.mg.com.mvvm_android.core.models.room.card.Card;
import android.mvvm.mg.com.mvvm_android.databinding.FragmentCardsBinding;
import android.mvvm.mg.com.mvvm_android.ui.fragments.base.BaseFragment;
import android.mvvm.mg.com.mvvm_android.ui.fragments.base.IBaseRequestListener;
import android.mvvm.mg.com.mvvm_android.ui.fragments.cards.viewModel.CardsViewModel;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class CardsFragment extends BaseFragment<CardsViewModel, FragmentCardsBinding> implements IEmptyViewHandler {

    @Override
    protected int getLayout() {
        return R.layout.fragment_cards;
    }

    @Override
    protected Class<CardsViewModel> getViewModelClass() {
        return CardsViewModel.class;
    }

    @Override
    protected void initBinding(final FragmentCardsBinding binding, final CardsViewModel viewModel) {
        binding.setViewModel(viewModel);
        binding.setHandler(this);
    }

    @Override
    public int getTitleRes() {
        return R.string.cards_title;
    }

    @Override
    public void subscribes(final LifecycleOwner owner) {
        mViewModel.<String>getAction(Action.SHOW_TOAST).observe(owner, text -> Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show());
        mViewModel.loadData().observe(owner, cardList -> mViewModel.initRecycleViewData(cardList));
        makeRequest(mViewModel.getCards(), new IBaseRequestListener<Card>() {
            @Override
            public void onSuccessList(final List<Card> cardList) {
                mViewModel.insertAll(cardList);
            }
        });
    }

    @Override
    public void onClick(final View view) {
        Toast.makeText(getContext(), "Card Empty view click", Toast.LENGTH_SHORT).show();
    }
}
