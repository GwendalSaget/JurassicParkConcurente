package org.example.jurassicpark;


import java.sql.SQLException;
import java.util.Scanner;

import static org.example.jurassicpark.DinoService.*;

public class Main {
    static final String URL = "jdbc:sqlite:C:/Users/gwend/OneDrive/Documents/UAX/Concurrente/JurassicPark/src/main/java/org/example/jurassicpark/databasedino.db";

    public static String eleccion(int ano, Scanner scanner) {
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
        Database.clearTable();
        System.out.println("Bienvenido a Jurassic Park !!! (Imaginale la musica de la pelicula)");
        System.out.println("  *                               *     _\n" +
                "       /\\     *            ___.       /  `)\n" +
                "   *  //\\\\    /\\          ///\\\\      / /\n" +
                "     ///\\\\\\  //\\\\/\\      ////\\\\\\    / /     /\\\n" +
                "    ////\\\\\\\\///\\\\/\\\\.-~~-.///\\\\\\\\  / /     //\\\\\n" +
                "   /////\\\\\\\\///\\\\/         `\\\\\\\\\\\\/ /     ///\\\\\n" +
                "  //////\\\\\\\\// /            `\\\\\\\\/ /     ////\\\\\n" +
                " ///////\\\\\\\\\\//               `~` /\\    /////\\\\\n" +
                "////////\\\\\\\\\\/      ,_____,   ,-~ \\\\\\__//////\\\\\\\n" +
                "////////\\\\\\\\/  /~|  |/////|  |\\\\\\\\\\\\\\\\@//jro/\\\\\n" +
                "//<           / /|__|/////|__|///////~|~/////\\\\\n" +
                "\n" +
                "~~~     ~~   ` ~   ..   ~  ~    .     ~` `   '.\n" +
                "~ _  -  -~.    .'   .`  ~ .,    '.    ~~ .  '.");
        System.out.println("Debo assumir, este pequeño dibujo es copiado pegado\n Para empezar, haz un clic en <<J>>");

        Scanner scanner = new Scanner(System.in);
        String inicio = scanner.nextLine().toUpperCase();

        if (inicio.equals("J")) {
            int ano = 1;
            System.out.println("Perfecto ! Que placer conocerte !!! Vas a empezar con 3 dinosaurios, un carnivorio, un herbivoro y un monstro aquatico.");
            System.out.println("Despues de cada año que va a estar simulado, tendre la oportunidad de observar como evolua Jurassic Park !\n Pero podras tambien ver cada evento importante del año !");
            System.out.println("...\n...\n...\n...\n...\n...\n...\nLet'sssssss goooooo !!! Año 1 :\n");

            Park park = new ParkService().InitPark();
            Dino Dino1 = CreateDino(0, park);
            Dino Dino2 = CreateDino(1, park);
            Dino Dino3 = CreateDino(2, park);
            int jour = 0;

            // Simulation des années
            while (true) {
                for (jour = 1; jour < 366; jour++) {
                    // Mise à jour des dinosaures et de l'environnement
                    DinoService.UpdateAllDino(park);
                    DinoService.oeuf(park);
                    ParkService.UpdatePark(park);
                }

                ano++;  // Incrémente l'année
                if (eleccion(ano, scanner).equals("Q")) {
                    System.out.println("Muchas gracias ! Hasta luego !!!");
                    break;  // Fin de la simulation
                } else {
                    System.out.println("Año " + ano + " :\n");
                }
            }
        } else {
            System.out.println("Opción no válida.");
        }

        scanner.close();
    }
}
