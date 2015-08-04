# CardWalletView

A card wallet, 'Passbook style' on Android

![Image](./Screens/cardwallet.gif "CardWalletView")

- Customize the space between each card with the constructor using offset
- Dismissable by clicking outside or back button optionally

## Usage

```java
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
```

## TODO

- Swipe down gesture on the cards
- Put on maven central