package com.liviogama.cardwalletview;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * bkCardWallet
 *
 * Created by livio on 04/09/2014.
 */
public class CardWalletView extends RelativeLayout {

    private List<? extends View> mCardViews;

    private ObjectAnimator mColorFadeDown;

    private ObjectAnimator mColorFadeUp;

    private List<Float> mCardViewOriginalY;

    private boolean mIsPresentingCards;

    private int mCardOffset;

    public CardWalletView(Context context, List<? extends View> cardViews) {
        this(context, cardViews, 60);
    }

    public CardWalletView(Context context, List<? extends View> cardViews, int cardOffset) {
        this(context);
        mCardViews = cardViews;
        mCardOffset = cardOffset;
        initView();
    }

    public CardWalletView(Context context) {
        super(context);
    }

    public CardWalletView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardWalletView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CardWalletView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private static int convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }

    private void initView() {
        inflate(getContext(), R.layout.layout_cards_container, this);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        int padding = convertDpToPixel(20, getContext());
        setPadding(padding, 0, padding, 0);
        final int blackTransparent = getResources().getColor(R.color.black_transparent);
        final int transparent = getResources().getColor(R.color.transparent);
        mColorFadeDown = ObjectAnimator.ofObject(this, "backgroundColor", new ArgbEvaluator(), transparent, blackTransparent);
        mColorFadeUp = ObjectAnimator.ofObject(this, "backgroundColor", new ArgbEvaluator(), blackTransparent, transparent);

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsPresentingCards) {
                    exitPresentingCardMode();
                }
            }
        });
        setClickable(false);

        initCardsWallet();
    }

    private void initCardsWallet() {
        if (mCardViews != null) {
            for (int i = mCardViews.size() - 1; i >= 0; i--) {
                final View cardView = mCardViews.get(i);
                cardView.setTag(i);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mIsPresentingCards) {
                            enterPresentingCardMode();
                        }
                        tidyCards();
                        final ObjectAnimator translationY = ObjectAnimator.ofFloat(cardView, "y", 50);
                        translationY.start();
                    }
                });
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                final int offset = i * convertDpToPixel(mCardOffset, getContext());
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                params.setMargins(0, 0, 0, offset);
                addView(cardView, params);
            }
        }
    }

    public void enterPresentingCardMode() {
        mColorFadeDown.start();
        setClickable(true);
        mIsPresentingCards = true;
    }

    public void exitPresentingCardMode() {
        mColorFadeUp.start();
        tidyCards();
        setClickable(false);
        mIsPresentingCards = false;
    }

    public void tidyCards() {
        if (mCardViewOriginalY == null) {
            mCardViewOriginalY = new ArrayList<>();
            for (View cardView : mCardViews) {
                mCardViewOriginalY.add(cardView.getY());
            }
        }
        for (int i = 0; i < mCardViews.size(); i++) {
            View cardView = mCardViews.get(i);
            final ObjectAnimator translationY = ObjectAnimator.ofFloat(cardView, "y", mCardViewOriginalY.get(i));
            translationY.start();
        }
    }

    public boolean isPresentingCards() {
        return mIsPresentingCards;
    }
}