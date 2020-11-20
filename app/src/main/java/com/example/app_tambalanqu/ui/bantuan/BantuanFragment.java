package com.example.app_tambalanqu.ui.bantuan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.app_tambalanqu.Article1;
import com.example.app_tambalanqu.Article2;
import com.example.app_tambalanqu.Article3;
import com.example.app_tambalanqu.Article4;
import com.example.app_tambalanqu.R;

public class BantuanFragment extends Fragment {

    private BantuanViewModel notificationsViewModel;


    CardView cardView, cardView2, cardView3, cardView4;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(BantuanViewModel.class);
        View root = inflater.inflate(R.layout.fragment_bantuan, container, false);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardView = getView().findViewById(R.id.article1);
        cardView2 = getView().findViewById(R.id.article2);
        cardView3 = getView().findViewById(R.id.article3);
        cardView4 = getView().findViewById(R.id.article4);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cardView.getContext(), Article1.class);
                startActivity(intent);
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cardView2.getContext(), Article2.class);
                startActivity(intent);
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cardView3.getContext(), Article3.class);
                startActivity(intent);
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cardView4.getContext(), Article4.class);
                startActivity(intent);
            }
        });
    }
}