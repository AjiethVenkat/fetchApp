package edu.newhaven.fetchapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ItemViewHolder> {

    private List<ItemModel> item_Model_List;

    public Adapter() {

        item_Model_List = new ArrayList<>();
    }

    /* Class instance methods */
    public void setItem_Model_List(List<ItemModel> item_Model_List){

        this.item_Model_List = item_Model_List;
        notifyDataSetChanged();

    }

    /* recycler adapter methods */
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cell, parent, false);
        return new ItemViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        ItemModel model = item_Model_List.get(position);

        holder.text_id.setText(model.id);
        holder.text_listId.setText(model.listId);
        holder.text_name.setText(model.name);
    }

    @Override
    public int getItemCount() {
        return item_Model_List.size();
    }

/* Item View Holder class */
 public static class ItemViewHolder extends RecyclerView.ViewHolder {

     TextView text_id;
     TextView text_listId;
     TextView text_name;

     public ItemViewHolder(View view) {
         super(view);

         text_id = (TextView) view.findViewById(R.id.text_id);
         text_listId = (TextView) view.findViewById(R.id.text_listId);
         text_name = (TextView) view.findViewById(R.id.messageTextView);
     }
 }
}
/* End of Line */

