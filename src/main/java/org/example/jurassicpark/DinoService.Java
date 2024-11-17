package org.example.jurassicpark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.example.jurassicpark.IslaService.*;


public class DinoService {

    public static Dino CreateDino(int espece, Park park){
        Dino dino = new Dino();
        dino.id = ParkService.GetIDLibre(park);
        dino.espece = espece;
        //0 = carnivores 1 = herbivores 2 = monstre marin 3 = Indominus-Rex
        if (dino.espece == 0) {
            dino.isla = 0;
            IslaNublar.CombienDino++;
        }
        else if (dino.espece == 1){
            dino.isla = 1;
            IslaSorna.CombienDino++;
        }
        else if (dino.espece == 2){
            dino.isla = 2;
            IslAquatica.CombienDino++;
        }
        else{
            dino.isla = 0;
            IslaNublar.CombienDino++;
        }
        dino.age = 0;
        dino.temperature = 35;
        dino.salud = 1;
        dino.argent = SimuArgent(dino);
        park.idlibres[dino.id] = false;
        InsertDino(dino.id, dino.isla, dino.espece, dino.age, dino.argent, dino.temperature, dino.salud);
        return dino;
    }
    // regarde et "calcule" si un dino est malade, à la fin de l'année tout le monde est en bonne santé par défaut
    public static void SimuTemp (Dino dino, Park park){
        // si un dino est à l'infirmerie son pourcentage de rester malade ou de mourir augmente
        int temp = (int) (Math.random() * 300);
        if (dino.isla == 3) {
            temp = (int) (Math.random() * 30);
        }
        //mort
        if (temp <= 5 && dino.isla == 3 && park.diaAno != 365) {
            dino.salud = 0;
            dino.temperature = 0;
            DinoDeath(dino, park);
        }
        //malade
        else if (temp <= 10 && park.diaAno != 365) {
            dino.salud = 2;
            dino.temperature = 40;
            //GetIslaByID(dino.isla).CombienDino--;
            if (dino.isla != 3) {
                dino.isla = 3;
                Infirmerie.CombienDino++;
                String ES2;
                if (dino.espece == 0){
                    ES2 = "carnivorio";
                }
                else if (dino.espece == 1){
                    ES2 = "herbivoro";
                }
                else if (dino.espece == 2){
                    ES2 = "monstro aquatico";
                }
                else{
                    ES2 = "Indominus-Rex";
                }
                System.out.println("El dinosaurio de ID : " + dino.id + " y de tipo : " + ES2 +"ha sido enfermo el dia" + park.diaAno);
            }
        }
        //en détenteeeee
        else {
            dino.salud = 1;
            dino.isla = DinogetIslabySpecie(dino);
            dino.temperature = 35;
        }
    }
    public static int SimuArgent(Dino dino){
        int visit = (int)(Math.random()*100);
        //0 = carnivores 1 = herbivores 2 = monstre marin 3 = Indominus-Rex
        if (dino.espece == 0) {
            visit += 1000;
        }
        else if (dino.espece == 1){
            visit += 500;
        }
        else if (dino.espece == 2){
            visit += 750;
        }
        else{
            visit += 2500;
        }
        //billets à 30 euros
        int arg = 30*visit;
        //pour les bébés dinosaures, plus de visites → plus d'argent
        if (dino.age < 5) {
            arg = arg*3;
        }
        return arg;
    }

public static void UpdateDino(Dino dino, Park park) {
    if (dino.age == 30) {
        DinoDeath(dino, park);
    }
    else {
        SimuTemp(dino, park);
        if (dino.isla != 3) {
            dino.argent = SimuArgent(dino);
            GetIslaByID(dino.isla).argent += dino.argent;
        }
        updateDbDino(dino.id, dino.isla, dino.espece, dino.age, dino.argent, dino.temperature, dino.salud);
    }
    }

public static void oeuf(Park park){
    for (int i = 0; i<=2; i++){
        Isla TempIsla = IslaService.GetIslaByID(i);
        if (TempIsla.CombienDino < TempIsla.capacity){
            int probOeuf = (int)(Math.random()*200);
            if (probOeuf <= 5){
                //faible proba de faire un indominus
                if (i==0 && probOeuf <= 1 ){
                    i = 3;
                }
                String ES3;
                if (i == 0){
                    ES3 = "carnivorio";
                }
                else if (i == 1){
                    ES3 = "herbivoro";
                }
                else if (i == 2){
                    ES3 = "monstro aquatico";
                }
                else{
                    ES3 = "Indominus-Rex";
                }
                System.out.println("Felicidades, un dinosaurio de tipo : " + ES3 + "nacio en el dia " + park.diaAno);
                Dino huevo = CreateDino(i, park);
            }
        }
    }
}

public static void DinoDeath(Dino dino, Park park){
    GetIslaByID(dino.isla).CombienDino--;
    String ES;
    if (dino.espece == 0){
        ES = "carnivorio";
    }
    else if (dino.espece == 1){
        ES = "herbivoro";
    }
    else if (dino.espece == 2){
        ES = "monstro aquatico";
    }
    else{
        ES = "Indominus-Rex";
    }
    System.out.println("El dinosaurio de ID : " + dino.id + " y de tipo : " + ES + " ha muerto en el dia : " + park.diaAno);
    park.idlibres[dino.id] = true;
    deleteDino(dino.id);
}

public static void InsertDino(int id, int isla, int espece, int age, int argent, int temperature, int salud) {
    try {
        String sql = "INSERT INTO hechizo (id, isla, espece, age, argent, temperature, salud) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection(Main.URL); // Utilisation de Main.URL
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setInt(2, isla);
            stmt.setInt(3, espece);
            stmt.setInt(4, age);
            stmt.setInt(5, argent);
            stmt.setInt(6, temperature);
            stmt.setInt(7, salud);
            stmt.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public static void deleteDino(int id) {
    String sqlSelect = "SELECT * FROM dino WHERE id = ?";
    String sqlDelete = "DELETE FROM dino WHERE id = ?";

    try (Connection conn = Database.getConnection(Main.URL)) {

        try (PreparedStatement selectStmt = conn.prepareStatement(sqlSelect)) {
            selectStmt.setInt(1, id);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {

                try (PreparedStatement deleteStmt = conn.prepareStatement(sqlDelete)) {
                    deleteStmt.setInt(1, id);
                    deleteStmt.executeUpdate();
                }
            } else {
                System.out.println("No se encontro dino con ese ID !");
            }
        }
    } catch (SQLException e) {
        System.err.println("Error de supresion de dino : " + e.getMessage());
        e.printStackTrace();
    }
}

public static void updateDbDino(int id, int isla, int espece, int age, int argent, int temperature, int salud) {
    String sqlUpdate = "UPDATE dino SET isla = ?, espece = ?, age = ?, argent = ?, temperature = ?, salud = ? WHERE id = ?";

    try (Connection conn = Database.getConnection(Main.URL);
         PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {

        stmt.setInt(1, isla);
        stmt.setInt(2, espece);
        stmt.setInt(3, age);
        stmt.setInt(4, argent);
        stmt.setInt(5, temperature);
        stmt.setInt(6, salud);
        stmt.setInt(7, id);

        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Dino actualizado con éxito!");
        } else {
            System.out.println("No se encontró ningún dino con ese ID!");
        }
    } catch (SQLException e) {
        System.err.println("Error al actualizar el dino: " + e.getMessage());
        e.printStackTrace();
    }
}

public static void UpdateAllDino(Park park) {
    try (Connection conn = Database.getConnection(Main.URL);
         PreparedStatement stmt = conn.prepareStatement("SELECT * FROM dino")) {
        ResultSet rs = stmt.executeQuery();
        Dino dino = new Dino();
        while (rs.next()) {
            dino.id = rs.getInt("id");
            dino.isla = rs.getInt("isla");
            dino.espece = rs.getInt("espece");
            dino.age = rs.getInt("age");
            if (park.diaAno == 365){
                dino.age++;
            }
            dino.argent = rs.getInt("argent");
            dino.temperature = rs.getInt("temperature");
            dino.salud = rs.getInt("salud");
            UpdateDino(dino, new Park());
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


static int DinogetIslabySpecie(Dino dino){
        if (dino.espece == 0) {
            return 0;
        }
        else if (dino.espece == 1){
            return 1;
        }
        else if (dino.espece == 2){
            return 2;
        }
        else{
            return 0;
        }
}
}