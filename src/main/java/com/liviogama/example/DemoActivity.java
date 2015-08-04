package com.liviogama.example;


import com.backelite.droid.bkcardwallet.R;
import com.liviogama.cardwalletview.CardWalletView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends AppCompatActivity {

    private CardWalletView mCardWalletView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        List<CardView> cardsViews = new ArrayList<>();

        cardsViews.add(new CardView(this, R.drawable.classic_card));
        cardsViews.add(new CardView(this, R.drawable.debit_card));
        cardsViews.add(new CardView(this, R.drawable.gold_card));

        mCardWalletView = new CardWalletView(this, cardsViews);
        ((RelativeLayout) findViewById(R.id.activity_layout)).addView(mCardWalletView);
    }

    @Override
    public void onBackPressed() {
        if (mCardWalletView.isPresentingCards()) {
            mCardWalletView.exitPresentingCardMode();
        } else {
            super.onBackPressed();
        }
    }
}