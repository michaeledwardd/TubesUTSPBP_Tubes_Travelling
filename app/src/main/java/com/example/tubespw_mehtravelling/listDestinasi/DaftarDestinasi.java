package com.example.tubespw_mehtravelling.listDestinasi;

import java.util.ArrayList;

public class DaftarDestinasi {
    public ArrayList<DataDestinasi> DataDestinasi;

    public DaftarDestinasi()
    {
        DataDestinasi = new ArrayList<>();
        DataDestinasi.add(Malioboro);
        DataDestinasi.add(KratonSolo);
        DataDestinasi.add(Kuta);
        DataDestinasi.add(Monas);
        DataDestinasi.add(RajaAmpat);
        DataDestinasi.add(LabuanBajo);
    }


    public static final DataDestinasi Malioboro = new DataDestinasi("Malioboro","Yogyakarta","Malioboro merupakan salah satu tempat yang istimewa di Yogyakarta","https://cdn.idntimes.com/content-images/community/2019/07/jalan-malioboro-1-696x464-1f073355a40f21f19ae69dc8240887c9_600x400.jpg" );
    public static final DataDestinasi KratonSolo= new DataDestinasi("Kraton Solo","Solo","Kraton Solo merupakan salah satu tempat yang istimewa di Solo","https://4.bp.blogspot.com/-Dfhw4Xe9tnk/XL46e8_ejwI/AAAAAAAACEM/2_c-4dNmVXwBXQOzUV-yNrXHjcysIurKACLcBGAs/s640/solo.jpg");
    public static final DataDestinasi Kuta = new DataDestinasi("Kuta","Bali","Kuta merupakan salah satu tempat yang istimewa di Bali","https://upload.wikimedia.org/wikipedia/commons/f/fe/Kuta_Beach_%286924448550%29.jpg");
    public static final DataDestinasi Monas= new DataDestinasi("Monas","Jakarta","Monas merupakan salah satu tempat yang istimewa di Jakarta", "https://cdns.klimg.com/dream.co.id/resized/640x320/news/2020/06/18/139659/dibuka-20-juni-monas-hanya-bisa-diakses-dari-2-pintu-200618h.jpg");
    public static final DataDestinasi RajaAmpat= new DataDestinasi("Raja Ampat","Sulawesi","Raja Ampat merupakan salah satu tempat yang istimewa di Sulawesi", "https://c4.wallpaperflare.com/wallpaper/550/377/154/raja-ampat-islands-west-papua-timur-indonesia-beautiful-hd-wallpapers-for-desktop-wallpaper-preview.jpg");
    public static final DataDestinasi LabuanBajo= new DataDestinasi("Labuan Bajo","NTT","Labuan Bajo merupakan salah satu tempat yang istimewa di NTT", "https://img.jakpost.net/c/2017/08/02/2017_08_02_30495_1501654303._large.jpg");
}
