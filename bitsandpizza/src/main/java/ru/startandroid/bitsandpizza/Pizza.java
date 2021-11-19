package ru.startandroid.bitsandpizza;

public class Pizza {

    private String name;
    private int imageResourceId;

    public static final Pizza[] pizzas = { new Pizza("Diavollo", R.drawable.diavolo),
            new Pizza("Margarita", R.drawable.funghi),
            new Pizza("Pepperoni", R.drawable.pepperoni)
    };

    private Pizza(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
