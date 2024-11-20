package org.example.jurassicpark;

import java.sql.SQLException;

import static org.example.jurassicpark.DinoService.*;
import static org.example.jurassicpark.IslaService.*;

public class ParkService{

    Park InitPark() throws SQLException {
        IslaService.InitIslas();
        Park park = new Park();
        park.islas[0]= IslaNublar;
        park.islas[1]= IslaSorna;
        park.islas[2]= IslAquatica;
        park.islas[3]= Infirmerie;
        Database.initDatabase(Database.getConnection(Main.URL));
        //on commence avec 3 dinosaures, 1 par île sauf l'infirmerie, le budget initial est de 10000€
        park.combiendino=3;
        park.revenus=0;
        park.depenses=0;
        park.budget=0;
        park.diaAno=0;
        for (int i = 0; i<50; i++){
            park.idlibres[i]=true;
        }
        return park;
    }
    static void UpdatePark(Park park){
        park.diaAno++;
        park.combiendino = IslaNublar.CombienDino + IslaSorna.CombienDino + IslAquatica.CombienDino;
        park.revenus += IslaNublar.argent + IslaSorna.argent + IslAquatica.argent;
        //un carnivore coûte 600€ de nourriture, un herbivore 290€ et un aquatique 435€, les frais vétérinaires sur dino sont de 1500€.
        park.depenses += IslaNublar.CombienDino*600 + IslaSorna.CombienDino*290 + IslAquatica.CombienDino*435 + Infirmerie.CombienDino*1500;
        park.budget = (park.revenus - park.depenses);
        if (park.diaAno == 365) {
            System.out.println("Bilan del año :\n");
            System.out.println("Numero de dinosaurios : " + park.combiendino + "\n");
            System.out.println("Numero de dinosaurios por isla : Isla Nublar = " + IslaNublar.CombienDino + " - Isla Sorna = " + IslaSorna.CombienDino + " - IslAquatica = " + IslAquatica.CombienDino+ "\n");
            System.out.println("Presupuesto = " + park.budget + " con ingresos de " + park.revenus + " y gastos de " + park.depenses+ "\n");
            park.diaAno = 0;
        }
    }

    //retourne le premier id libre pour un dino
    static int GetIDLibre(Park park){
        int i = 0;
        while (park.idlibres[i]==false && i<50){
            i++;
        }
       return i;
    }
}