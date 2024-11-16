package org.example.jurassicpark;

import static org.example.jurassicpark.IslaService.*;

public class ParkService{

    Park InitPark(){
        IslaService.InitIslas();
        Park park = new Park();
        park.islas[0]= IslaNublar;
        park.islas[1]= IslaSorna;
        park.islas[2]= IslAquatica;
        park.islas[3]= Infirmerie;
        park.combiendino=0;
        park.revenus=0;
        park.depenses=0;
        park.budget=0;
        for (int i = 0; i<50; i++){
            park.idlibres[i]=true;
        }
        return park;
    }
    void UpdatePark(Park park){
        park.combiendino = IslaNublar.CombienDino + IslaSorna.CombienDino + IslAquatica.CombienDino + Infirmerie.CombienDino;
        // un dino à l'infiremerie coûte 500€ mais ne rapporte rien en contrepartie
        park.revenus = IslaNublar.argent + IslaSorna.argent + IslAquatica.argent;
        park.depenses = IslaNublar.CombienDino*100 + IslaSorna.CombienDino*40 + IslAquatica.CombienDino*80 + Infirmerie.CombienDino*500;
        park.budget = park.revenus - park.depenses;
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