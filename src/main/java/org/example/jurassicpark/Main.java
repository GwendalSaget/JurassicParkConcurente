package org.example.jurassicpark;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static final String URL = "jdbc:sqlite:C:/src/main/java/org/example/jurassicpark/databasedino.db";

    public static String eleccion(int ano, Scanner scanner){
        String ok = "";
        while (!ok.equals("Q") && !ok.equals("A")) {
            System.out.println("Si quieres seguir con el año " + ano + " haz un clic en <<A>>, si no haz un clic en <<Q>>:\n");
            String eleccion = scanner.nextLine().toUpperCase();
            if (eleccion.equals("Q")) {
                Database.clearTable();
                ok = "Q";
            } else if (eleccion.equals("A")) {
                ok = "A";
            }
        }
        return ok;
    }

    public static void main(String[] args) throws SQLException {
//créer un système pour retrouver les dinosaures par l'id dans les iles, dans le create dino, il faudra penser à bien ajouter l'id correspondant à la liste des ids de l'ile
// database
//créer la simulation avec combien de dino, on a sur mon ile et gérer l'argent...
        System.out.println("Bienvenido a Jurassic Park !!! (Imaginale la musica de la pelicula)");
        System.out.println("Para empezar, haz un clic en <<J>>");
        Scanner scanner = new Scanner(System.in);
        String inicio = scanner.nextLine().toUpperCase();
        if (inicio.equals("J")) {
            int ano = 1;
            System.out.println("Perfecto ! Que placer conocerte !!! Vas a empezar con 3 dinosaurios, un carnivorio, un herbivoro y un monstro aquatico.");
            System.out.println("Despues de cada año que va a estar simulado, tendre la oportunidad de observar como evolua Jurassic Park !");
            System.out.println("...\n...\n...\n...\n...\n...\n...\nLet'sssssss goooooo !!! Año 1 :\n");
            Park park = new ParkService().InitPark();
            int jour = 0;

            while (true) {
                for (jour = 0; jour < 366; jour++) {
                    ParkService.UpdatePark(park);
                }
                ano++;
                if (eleccion(ano, scanner).equals("Q")){
                    System.out.println("Muchas gracias ! Hasta luego !!!");
                    break;
                }
                else {
                    System.out.println("Año " + ano + " :\n");
                }
            }

        }
    }

}
