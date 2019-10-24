package com.example.appteste.ui.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.appteste.MainActivity;
import com.example.appteste.MenuActivity;
import com.example.appteste.R;
import com.example.appteste.ScrollingActivity;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);


//        final TextView textView = root.findViewById(R.id.text_gallery);
        final ImageButton lapaBtn = root.findViewById(R.id.btnLapa);
        final ImageButton gordaBtn = root.findViewById(R.id.gordaBtn);

//        galleryViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        final Activity context = getActivity();
        lapaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                Toast.makeText(context,"Laposo.", Toast.LENGTH_SHORT).show();
            }
        });

        gordaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Otaku Fudido que gosta de SAO", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}