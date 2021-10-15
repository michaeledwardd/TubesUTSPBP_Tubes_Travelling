package com.example.tubespw_mehtravelling.listDestinasi;

import java.util.ArrayList;

public class DaftarDestinasi {
    public ArrayList<DataDestinasi> DataDestinasi;

    public DaftarDestinasi()
    {
        DataDestinasi = new ArrayList<>();
        DataDestinasi.add(Malioboro);
        DataDestinasi.add(Kuta);
    }


    public static final DataDestinasi Malioboro = new DataDestinasi("Malioboro","Yogyakarta","Tempat wisata Yogyakarta","https://upload.wikimedia.org/wikipedia/commons/f/f0/Malioboro_Street%2C_Yogyakarta.JPG");
    public static final DataDestinasi Kuta = new DataDestinasi("Kuta","Bali","Pantai khas Bali","https://upload.wikimedia.org/wikipedia/commons/f/fe/Kuta_Beach_%286924448550%29.jpg");

}
