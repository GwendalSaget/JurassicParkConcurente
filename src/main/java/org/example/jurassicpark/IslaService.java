package org.example.jurassicpark;

public class IslaService {
    public static Isla IslaNublar = new Isla();
    public static Isla IslaSorna = new Isla();
    public static Isla IslAquatica = new Isla();
    public static Isla Infirmerie = new Isla();

    public static void InitIslas()
    {
        IslaNublar.name = "Isla Nublar";
        IslaNublar.id = 0;
        IslaNublar.CombienDino = 0;
        IslaNublar.argent = 0;
        IslaNublar.capacity = 5;

        IslaSorna.name = "Isla Sorna";
        IslaSorna.id = 1;
        IslaSorna.CombienDino = 0;
        IslaSorna.argent = 0;
        IslaSorna.capacity = 25;

        IslAquatica.name = "IslAquatica";
        IslAquatica.id = 2;
        IslAquatica.CombienDino = 0;
        IslAquatica.argent = 0;
        IslAquatica.capacity = 20;

        Infirmerie.name = "Infirmerie";
        Infirmerie.id = 3;
        Infirmerie.CombienDino = 0;
        Infirmerie.argent = 0;
        Infirmerie.capacity = 50;
    }

    public static Isla GetIslaByID(int id){
        if (id == 0){
            return IslaNublar;
        }
        if (id == 1){
            return IslaSorna;
        }
        if (id == 2){
            return IslAquatica;
        }
        if (id == 3){
            return Infirmerie;
        }
        return null;
    }
}