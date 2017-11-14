package kr.co.iquest.beinone.iquestory;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import kr.co.iquest.beinone.iquestory.model.Product;

/**
 * Created by BeINone on 2017-11-14.
 */

public class ProductCardView extends CardView {

    private Product mProduct;

    public ProductCardView(Context context) {
        super(context);
    }

    public ProductCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product product) {
        mProduct = product;
    }
}
