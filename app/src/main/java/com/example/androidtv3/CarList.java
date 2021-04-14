package com.example.androidtv3;

import android.annotation.SuppressLint;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class CarList {
    static List<Car> list;
    static int number = 3;

    @SuppressLint("UseCompatLoadingForDrawables")
    public static List<Car> buildListCard(Context context, int count){
        String[] brands = {"McLaren", "Mitsubishi", "Honda"};
        String[] models = {"P1", "Lancer Evo X", "Prelude V"};
        String[] desc = {"Supersamochód segmentu F produkowany przez brytyjską markę McLaren w latach 2013 – 2015.",
                "Sportowy samochód osobowy firmy Mitsubishi." +
                "Pierwsza generacja Mitsubishi Lancera pojawiła się na rynku w 1973 roku, " +
                        "a w Europie w 1974 roku. Firma, zachęcona sukcesami większego brata Lancera" +
                        " – Galanta VR-4 na trasach rajdowych, w roku 1990 zdecydowała się na " +
                        "produkcję zupełnie nowego auta. W kwietniu 2014 roku producent ogłosił " +
                        "zakończenie produkcji, które ma przypaść na rok 2015.",
                "Honda Prelude V zadebiutowała w 1996 roku. Auto produkowano do 2001 roku po czym " +
                        "produkcję zakończono ze względu na przerwanie prac nad F1, po ukazaniu się " +
                        "w Japonii czwartej generacji Integry. Sprzedano 58 118 sztuk piątej " +
                        "generacji Prelude"};
        int[] prices = {2_390_000, 35_000, 4_200};
        int[] ids = new int[3];
        ids[0] = R.drawable.img01;
        ids[1] = R.drawable.img02;
        ids[2] = R.drawable.img03;

        if(count > number)
            return null;
        list = new ArrayList<>();
        for(int idx = 0; idx < number; ++idx){
            Car car = new Car();
            car.brand = brands[idx];
            car.model = models[idx];
            car.description = desc[idx];
            car.image = context.getResources().getDrawable(ids[idx]);
            car.image_id = ids[idx];
            car.price = prices[idx];
            list.add(car);
        }
        return list;
    }
}

