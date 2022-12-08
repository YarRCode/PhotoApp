package com.testrpackage.testapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    String nameImange[];
    String urlOfImage[];
    String authorName[];
    Double resol[];
    LayoutInflater inflater;
    int doubleToInt;

    public CustomBaseAdapter(Context cnt, String[] urlIm,String[] nameIm,String[] auth,Double[] resolv) {
        this.context = cnt;
        this.nameImange = nameIm;
        this.urlOfImage = urlIm;
        this.resol= resolv;
        this.authorName = auth;

        inflater = LayoutInflater.from(cnt);
    }

    @Override
    public int getCount() {
        return nameImange.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_custom_list_view, null);

        TextView imageNameView = (TextView) view.findViewById(R.id.nameImage);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView authNameView = (TextView) view.findViewById(R.id.authorName);

        doubleToInt = (int) ((int) 300*resol[i]);

        Picasso.get().load(urlOfImage[i]).resize(doubleToInt, 300).onlyScaleDown().into(imageView);

        imageNameView.setText(nameImange[i]);
        authNameView.setText(authorName[i]);

        return view;
    }


}
