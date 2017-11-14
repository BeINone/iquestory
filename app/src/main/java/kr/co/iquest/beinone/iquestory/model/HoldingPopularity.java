package kr.co.iquest.beinone.iquestory.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

/**
 * Created by BeINone on 2017-11-12.
 */

public class HoldingPopularity implements Popularity {

    public HoldingPopularity() {

    }

    public HoldingPopularity(Parcel parcel) {

    }

    @Override
    public void changeNumCustomer(Product product) {
        Random random = new Random(System.currentTimeMillis());
        int numCustomer = random.nextInt(200);
        boolean isIncreasing = random.nextBoolean();
        if (isIncreasing) {
            product.increaseNumCustomer(numCustomer);
        } else {
            product.decreaseNumCustomer(numCustomer);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    private Parcelable.Creator<Popularity> CREATOR = new Creator<Popularity>() {
        @Override
        public Popularity createFromParcel(Parcel parcel) {
            return new HoldingPopularity(parcel);
        }

        @Override
        public Popularity[] newArray(int i) {
            return new Popularity[i];
        }
    };
}
