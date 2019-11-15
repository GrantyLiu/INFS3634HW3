package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FavouriteCatDatabase {

    public static HashMap<String, Cat> favouriteCatsMap;

    static{
        favouriteCatsMap = new HashMap<>();
    }

    public static ArrayList<Cat> getFavouriteCats(){
        ArrayList<Cat> favouriteCatsArray = new ArrayList<>();
        for (Map.Entry<String, Cat> favouriteCat :
                favouriteCatsMap.entrySet()) {
            favouriteCatsArray.add(favouriteCat.getValue());
        }
        return favouriteCatsArray;
    }

    public static void addToFavourites(Cat newCat){
        String catId = newCat.getId();
        if (!favouriteCatsMap.containsKey(catId)){
            favouriteCatsMap.put(catId, newCat);
            System.out.println(newCat.getName() + " added to favourites.");
        } else {
            System.out.println("Cat already a favourite.");
        }
    }

    public static void removeFromFavourite(Cat newCat){
        String catId = newCat.getId();
        if (favouriteCatsMap.containsKey(catId)){
            favouriteCatsMap.remove(catId);
            System.out.println(newCat.getName() + " removed from favourites.");
        } else {
            System.out.println("Cat already not favourited.");
        }

    }

}