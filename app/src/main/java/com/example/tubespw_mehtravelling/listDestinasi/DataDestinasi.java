package com.example.tubespw_mehtravelling.listDestinasi;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DataDestinasi {
    public String namaDestinasi;
    public String alamatDestinasi;
    public String deskripsiDestinasi;
    public String imgURL;

    public DataDestinasi(){}

    public DataDestinasi(String namaDestinasi, String alamatDestinasi, String deskripsiDestinasi, String imgURL) {
        this.namaDestinasi = namaDestinasi;
        this.alamatDestinasi = alamatDestinasi;
        this.deskripsiDestinasi = deskripsiDestinasi;
        this.imgURL = imgURL;
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

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
    @BindingAdapter("imgProfile")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions())
                .into(view);
    }
}
