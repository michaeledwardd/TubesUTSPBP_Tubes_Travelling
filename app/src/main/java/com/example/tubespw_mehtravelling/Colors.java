package com.example.tubespw_mehtravelling;

public class Colors {
    public void setColorTheme(){

        switch (Constant.color){
            case 1:
                Constant.theme = R.style.AppTheme_red;
                break;
            case 2:
                Constant.theme = R.style.AppTheme_blue;
                break;
            case 3:
                Constant.theme = R.style.AppTheme;
                break;
            default:
                Constant.theme = R.style.AppTheme;
                break;
        }
    }
}
