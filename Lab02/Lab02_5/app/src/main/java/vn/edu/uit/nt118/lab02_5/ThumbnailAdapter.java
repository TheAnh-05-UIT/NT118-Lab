package vn.edu.uit.nt118.lab02_5;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ThumbnailAdapter
        extends ArrayAdapter<Thumbnail> {

    public ThumbnailAdapter(Context context) {
        super(context, 0, Thumbnail.values());
    }

    // Hiển thị thumbnail được chọn
    @NonNull
    @Override
    public View getView(
            int position,
            @Nullable View convertView,
            @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater
                    .from(getContext())
                    .inflate(
                            R.layout.item_selected_thumbnail,
                            parent,
                            false
                    );
        }

        TextView tvName = (TextView) convertView;

        Thumbnail thumbnail = getItem(position);

        if (thumbnail != null) {
            tvName.setText(thumbnail.getName());
        }

        return convertView;
    }

    // Hiển thị tên và ảnh trong dialog
    @Override
    public View getDropDownView(
            int position,
            @Nullable View convertView,
            @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater
                    .from(getContext())
                    .inflate(
                            R.layout.item_thumbnail,
                            parent,
                            false
                    );
        }

        TextView tvName =
                convertView.findViewById(
                        R.id.tvThumbnailName
                );

        ImageView imgThumbnail =
                convertView.findViewById(
                        R.id.imgThumbnail
                );

        Thumbnail thumbnail = getItem(position);

        if (thumbnail != null) {
            tvName.setText(thumbnail.getName());

            imgThumbnail.setImageResource(
                    thumbnail.getImg()
            );
        }

        return convertView;
    }
}