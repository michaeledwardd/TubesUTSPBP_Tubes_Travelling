package com.example.tubespw_mehtravelling.listDestinasi;

public class Destinasi {
    private String namadestinasi;
    private String alamat;
    private String deskripsi;
    private String urlImg;


    public Destinasi(String namadestinasi, String alamat, String deskripsi, String urlImg) {
        this.namadestinasi = namadestinasi;
        this.alamat = alamat;
        this.deskripsi=deskripsi;
        this.urlImg=urlImg;
    }

    public String getNamadestinasi() {
        return namadestinasi;
    }


    public void setNamadestinasi(String namadestinasi) {
        this.namadestinasi = namadestinasi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public static Destinasi[] listOfDestinasi = {
            new Destinasi("Malioboro","Yogyakarta","Malioboro merupakan salah satu tempat yang istimewa di Yogyakarta","https://cdn.idntimes.com/content-images/community/2019/07/jalan-malioboro-1-696x464-1f073355a40f21f19ae69dc8240887c9_600x400.jpg" ),
            new Destinasi("Kraton Solo","Solo","Kraton Solo merupakan salah satu tempat yang istimewa di Solo","https://4.bp.blogspot.com/-Dfhw4Xe9tnk/XL46e8_ejwI/AAAAAAAACEM/2_c-4dNmVXwBXQOzUV-yNrXHjcysIurKACLcBGAs/s640/solo.jpg" ),
            new Destinasi("Monas","Jakarta","Monas merupakan salah satu tempat yang istimewa di Jakarta", "https://cdns.klimg.com/dream.co.id/resized/640x320/news/2020/06/18/139659/dibuka-20-juni-monas-hanya-bisa-diakses-dari-2-pintu-200618h.jpg"),
            new Destinasi("Raja Ampat","Sulawesi","Raja Ampat merupakan salah satu tempat yang istimewa di Sulawesi", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fregional.kompas.com%2Fread%2F2021%2F06%2F17%2F061600478%2Fasal-usul-raja-ampat-legenda-tujuh-telur-dan-tuah-keramat-empat-raja%3Fpage%3Dall&psig=AOvVaw1OXXMHNFofvWRC5gTXN0mC&ust=1634304369277000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCLjVzrCAyvMCFQAAAAAdAAAAABAD"),
            new Destinasi("Kuta","Bali","Kuta merupakan salah satu tempat yang istimewa di Bali","https://upload.wikimedia.org/wikipedia/commons/f/fe/Kuta_Beach_%286924448550%29.jpg" ),
            new Destinasi("Labuan Bajo","NTT","Labuan Bajo merupakan salah satu tempat yang istimewa di NTT", "https://img.jakpost.net/c/2017/08/02/2017_08_02_30495_1501654303._large.jpg"),
    };
}

