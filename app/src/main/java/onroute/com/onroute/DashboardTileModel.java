package onroute.com.onroute;


import android.os.Parcel;
import android.support.annotation.DrawableRes;
import android.view.View;

import com.google.gson.Gson;

import lombok.Data;


@Data
public class DashboardTileModel extends BaseParcelableModel {
    String localBackgroundImagePath;
    @DrawableRes
    int iconRes;
    String title;
    String description;
    OnClickListener onClickListener;


    public static final Creator CREATOR = new Creator() {
        public DashboardTileModel createFromParcel(Parcel in) {
            String json = in.readString();
            Gson gson = App.get(Gson.class);
            return gson.fromJson(json, DashboardTileModel.class);
        }


        public DashboardTileModel[] newArray(int size) {
            return new DashboardTileModel[size];
        }
    };


    public interface OnClickListener {
        void onClick(View view);
    }
}