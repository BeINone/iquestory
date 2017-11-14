package kr.co.iquest.beinone.iquestory;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BeINone on 2017-11-12.
 */

public class DecrementPopularity implements Popularity {

    public DecrementPopularity() {

    }

    public DecrementPopularity(Parcel parcel) {

    }

    @Override
    public void changeNumCustomer(Product product) {

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
            return new DecrementPopularity(parcel);
        }

        @Override
        public Popularity[] newArray(int i) {
            return new Popularity[i];
        }
    };
}
