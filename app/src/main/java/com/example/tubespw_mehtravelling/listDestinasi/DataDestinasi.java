package com.example.tubespw_mehtravelling.listDestinasi;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DataDestinasi {
    private String namaDestinasi;
    private String alamatDestinasi;
    private String deskripsiDestinasi;
    private String imgurl;

    public DataDestinasi(String namaDestinasi, String alamatDestinasi, String deskripsiDestinasi, String imgurl) {
        this.namaDestinasi = namaDestinasi;
        this.alamatDestinasi = alamatDestinasi;
        this.deskripsiDestinasi = deskripsiDestinasi;
        this.imgurl = imgurl;
    }

    public String getNamaDestinasi() {
        return namaDestinasi;
    }

    public void setNamaDestinasi(String namaDestinasi) {
        this.namaDestinasi = namaDestinasi;
    }

    public String getAlamatDestinasi() {
        return alamatDestinasi;
    }

    public void setAlamatDestinasi(String alamatDestinasi) {
        this.alamatDestinasi = alamatDestinasi;
    }

    public String getDeskripsiDestinasi() {
        return deskripsiDestinasi;
    }

    public void setDeskripsiDestinasi(String deskripsiDestinasi) {
        this.deskripsiDestinasi = deskripsiDestinasi;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions())
                .into(view);
    }
}
