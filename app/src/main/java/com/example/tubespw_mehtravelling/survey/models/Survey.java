package com.example.tubespw_mehtravelling.survey.models;

public class Survey {
    private Long id;
    private String namaPengguna;
    private String namaDestinasi;
    private int penilaian;
    private String alasan;

    public Survey(String namaPengguna, String namaDestinasi, int penilaian, String alasan) {
        this.namaPengguna = namaPengguna;
        this.namaDestinasi = namaDestinasi;
        this.penilaian = penilaian;
        this.alasan = alasan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getNamaDestinasi() {
        return namaDestinasi;
    }

    public void setNamaDestinasi(String namaDestinasi) {
        this.namaDestinasi = namaDestinasi;
    }

    public int getPenilaian() {
        return penilaian;
    }

    public void setPenilaian(int penilaian) {
        this.penilaian = penilaian;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }
}
