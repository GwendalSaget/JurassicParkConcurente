package org.example.jurassicpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Main {
    static final String URL = "jdbc:sqlite:C:/src/main/java/org/example/jurassicpark/databasedino.db";

    public static void main(String[] args) {
//créer un système pour retrouver les dinos par l'id dans les iles, dans le create dino, il faudra penser à bien ajouter l'id correspondant à la liste des ids de l'ile
// database
//créer la simulation avec combien de dino on a sur mon ile et gérer l'argent...
        Park park = new ParkService().InitPark();
    }

}
