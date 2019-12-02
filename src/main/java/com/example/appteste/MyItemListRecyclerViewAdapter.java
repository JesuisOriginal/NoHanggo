package com.example.appteste;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appteste.ItemListFragment.OnListFragmentInteractionListener;
import com.example.appteste.dummy.ProductsList.Product;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Product} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemListRecyclerViewAdapter extends RecyclerView.Adapter<MyItemListRecyclerViewAdapter.ViewHolder> {

    private final List<Product> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyItemListRecyclerViewAdapter(List<Product> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_itemlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Product mProduct = mValues.get(position);

//        holder.mItem = mValues.get(position);
//        RecyclerView recyclerView = holder
        holder.mNameView.setText(mValues.get(position).name);
        holder.mValueView.setText(mValues.get(position).value.toString());
        holder.mQtdView.setText(mValues.get(position).qtd.toString());
        holder.mLocalView.setText(mValues.get(position).local);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mValueView;
        public final TextView mQtdView;
        public final TextView mLocalView;
        public Product mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.name);
            mValueView = (TextView) view.findViewById(R.id.price);
            mQtdView = (TextView) view.findViewById(R.id.qtd);
            mLocalView = view.findViewById(R.id.local);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mQtdView.getText() + "'";
        }
    }
}
