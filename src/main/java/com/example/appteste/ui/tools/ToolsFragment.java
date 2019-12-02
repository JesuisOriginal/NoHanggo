package com.example.appteste.ui.tools;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.appteste.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_tools, container, false);

        final Button sendToFirestoreBtn = root.findViewById(R.id.sendToFirestore);
        final EditText itemName = root.findViewById(R.id.itemNameField);
        final EditText itemLocal = root.findViewById(R.id.itemLocalField);
        final EditText itemPrice = root.findViewById(R.id.itemPriceField);
        final EditText itemQtd = root.findViewById(R.id.itemQtdField);
        final Activity context = getActivity();

        sendToFirestoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                Map<String, Object> item = new HashMap<>();
                item.put("itemName", itemName.getText().toString());
                item.put("itemLocal", itemLocal.getText().toString());
                item.put("itemPrice",  Double.parseDouble(itemPrice.getText().toString()));
                item.put("itemQtd", Integer.parseInt(itemQtd.getText().toString()));

                db.collection("itens").document(itemName.getText().toString())
                        .set(item)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully written!");
                                Toast.makeText(context, "Mamao com Batata", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing document", e);
                            }
                        });

            }
        });

        return root;
    }
}