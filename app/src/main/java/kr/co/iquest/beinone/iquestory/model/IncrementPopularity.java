package kr.co.iquest.beinone.iquestory.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

/**
 * Created by BeINone on 2017-11-12.
 */

public class IncrementPopularity implements Popularity {

    public IncrementPopularity() {

    }

    public IncrementPopularity(Parcel parcel) {

    }

    @Override
    public void changeNumCustomer(Product product) {
        Random random = new Random(System.currentTimeMillis());
        int increasingNumCustomer = random.nextInt(500);

        product.increaseNumCustomer(increasingNumCustomer);
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
            return new IncrementPopularity(parcel);
        }

        @Override
        public Popularity[] newArray(int i) {
            return new Popularity[i];
        }
    };
}
