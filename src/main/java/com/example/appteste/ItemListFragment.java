package com.example.appteste;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appteste.dummy.ProductsList;
import com.example.appteste.dummy.ProductsList.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.appteste.dummy.ProductsList.ITEMS;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private ProductsList productsList = new ProductsList();
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final Context mcContext = getContext();
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemListFragment newInstance(int columnCount) {
        ItemListFragment fragment = new ItemListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_itemlist_list, container, false);


        db.collection("itens")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document:
                                 task.getResult()) {
                                if (!document.get("itemName").equals(null)) {
//                                    Log.d(TAG, document.getId() + " , Qtd => type: " + document.get("itemQtd").getClass().getName());
                                    Log.d(TAG, document.getId() + " valores =>  " + document.getData().values() + "chaves => " + document.getData().keySet());
                                    Product test = (Product) document.getData().get(document.getId());
                                    String name = (String) document.get("itemName");
                                    Double price = Double.parseDouble(document.get("itemPrice").toString());
                                    Long qtd = (long) document.get("itemQtd");
                                    String local = (String) document.get("itemLocal");
                                    if (!ITEMS.contains(new Product(name, price, qtd, local)))
                                    {
                                        ITEMS.add(new Product(name, price, qtd, local));
                                    }

                                }

                            }
                        } else {
                            Log.w(TAG, "Error Getting Documentos.", task.getException());
//                            Toast.makeText(mcContext, "Fail BRUH", Toast.LENGTH_LONG);
                        }
                    }
                });


        // create adapter


//         Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            for (Product p:
                 productsList.getITEMS()) {
//                Log.d(TAG, "Constraints2: " + );
            }
            recyclerView.setAdapter(new MyItemListRecyclerViewAdapter(productsList.getITEMS(), mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Product item);
    }
}
