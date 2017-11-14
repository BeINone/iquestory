package kr.co.iquest.beinone.iquestory.model;

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
    private OnProductDataChangedListener onProductDataChangedListener;

    public Product(String name, int price, OnProductDataChangedListener listener) {
        this.name = name;
        this.price = price;
        this.popularity = new HoldingPopularity();
        this.onProductDataChangedListener = listener;
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

    public void changeNumCustomer() {
        popularity.changeNumCustomer(this);
    }

    public void increaseNumCustomer(int numCustomer) {
        setNumCustomer(this.numCustomer += numCustomer);
    }

    public void decreaseNumCustomer(int numCustomer) {
        setNumCustomer(this.numCustomer -= numCustomer);
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
        onProductDataChangedListener.onNumCustomerChanged(this.numCustomer);
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
        onProductDataChangedListener.onPopularityChanged(this.popularity);
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

    public interface OnProductDataChangedListener {
        void onNumCustomerChanged(int numCustomer);
        void onPopularityChanged(Popularity popularity);
    }
}
