package com.liviogama.example;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * bkCardWallet
 *
 * Created by livio on 04/09/2014.
 */
public class CardView extends ImageView {

    public CardView(Context context, int cardIdDrawable) {
        super(context);
        setImageDrawable(context.getResources().getDrawable(cardIdDrawable));
    }

    public CardView(Context context) {
        super(context);
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}