package com.example.androidtv3;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class Car implements Parcelable {
    String brand;
    String model;
    String description;
    Drawable image;
    int image_id;
    int price;

    protected Car(){ }

    protected Car(Parcel in) {
        brand = in.readString();
        model = in.readString();
        description = in.readString();
        image_id = in.readInt();
        price = in.readInt();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand);
        dest.writeString(model);
        dest.writeString(description);
        dest.writeInt(image_id);
        dest.writeInt(price);
    }
}
