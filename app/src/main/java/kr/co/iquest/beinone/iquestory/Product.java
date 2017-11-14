package kr.co.iquest.beinone.iquestory;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BeINone on 2017-11-12.
 */

public class Product implements Parcelable {

    private String name;
    private int price;
    private int numCustomer;
    private double incrementRate;
    private double decrementRate;
    private Popularity popularity;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Product(Parcel parcel) {
        name = parcel.readString();
        price = parcel.readInt();
        numCustomer = parcel.readInt();
        incrementRate = parcel.readDouble();
        decrementRate = parcel.readDouble();
        popularity = parcel.readParcelable(Popularity.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(price);
        parcel.writeInt(numCustomer);
        parcel.writeDouble(incrementRate);
        parcel.writeDouble(decrementRate);
        parcel.writeParcelable(popularity, 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumCustomer() {
        return numCustomer;
    }

    public void setNumCustomer(int numCustomer) {
        this.numCustomer = numCustomer;
    }

    public double getIncrementRate() {
        return incrementRate;
    }

    public void setIncrementRate(double incrementRate) {
        this.incrementRate = incrementRate;
    }

    public double getDecrementRate() {
        return decrementRate;
    }

    public void setDecrementRate(double decrementRate) {
        this.decrementRate = decrementRate;
    }

    public Popularity getPopularity() {
        return popularity;
    }

    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }

    private Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel parcel) {
            return new Product(parcel);
        }

        @Override
        public Product[] newArray(int i) {
            return new Product[i];
        }
    };
}
