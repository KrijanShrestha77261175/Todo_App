package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.databinding.ItemListBinding;

public class ViewAdapter extends ListAdapter<myList, ViewAdapter.ViewHolder> {

    // Creating a constructor for ViewAdapter
    public ViewAdapter(MainActivity mainActivity) {
        super(CALLBACK);

    }


    private static final DiffUtil.ItemCallback<myList> CALLBACK= new DiffUtil.ItemCallback<myList>() {
        // Called by the DiffUtil when it wants to check whether two items have the same data.
        @Override
        public boolean areItemsTheSame(@NonNull myList oldItem, @NonNull myList newItem) {
            return oldItem.getIdList() == newItem.getIdList();
        }

        // Called by the DiffUtil to decide whether two object represent the same Item
        @Override
        public boolean areContentsTheSame(@NonNull myList oldItem, @NonNull myList newItem) {
            return oldItem.getListTitle().equals(newItem.getListTitle()) &&
                    oldItem.getListDescription().equals(newItem.getListDescription());
        }
    };


    // Class ViewHolder that extends from Recycler view
    public class ViewHolder extends RecyclerView.ViewHolder{
        // Binding for each to do task
         ItemListBinding binding;

         // Creating a constructor of ViewHolder
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemListBinding.bind(itemView);
        }
    }

    // As the view holder is created, the list of tasks to do is inflated as view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    // This method enables the old data to be replaced by new data as scrolling is performed, thus new tasks are displayed
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        myList myLists = getItem(position);
        holder.binding.listTitle.setText(myLists.getListTitle());
        holder.binding.dispTitle.setText(myLists.getListDescription());
    }

    public myList getList(int position){
        return getItem(position);
    }
}




