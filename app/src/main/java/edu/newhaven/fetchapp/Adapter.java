package edu.newhaven.fetchapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<ItemViewHolder> {


    private List<ItemModel> item_Model_List;

    public Adapter() {

        item_Model_List = new ArrayList<>();
    }

    public void setItem_Model_List(List<ItemModel> item_Model_List){
        this.item_Model_List = item_Model_List;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cell, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemModel model = item_Model_List.get(position);

        holder.id.setText(model.id);

    }

    @Override
    public int getItemCount() {
        return item_Model_List.size();
    }
}

class ItemViewHolder extends RecyclerView.ViewHolder {

    TextView id;

    public ItemViewHolder(View view){
        super(view);

        id =(TextView) view.findViewById(R.id.text_id);

    }

}

