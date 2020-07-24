package com.example.banananote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> implements OnNoteItemClickListener {

    ArrayList<Note> items = new ArrayList<>();
    OnNoteItemClickListener listener; //뷰 클릭시 여부

    TextView CreateDate;

    public void addItem(Note item) {
        items.add(item);
    }

    public void setItems(ArrayList<Note> items) {
        this.items = items;
    }

    public Note getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Note item) {
        items.set(position,item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.frag_main_item,parent,false);

        return new ViewHolder(itemView, this);
    }

    public void setOnItemClickListener(OnNoteItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_Title;
        TextView textView_CreateDate;
        TextView textView_Memo;

        public ViewHolder(View itemView, final OnNoteItemClickListener listener) {
            super(itemView);

            textView_Title = itemView.findViewById(R.id.NoteTitle);
            textView_CreateDate = itemView.findViewById(R.id.CreateDate);
            textView_Memo = itemView.findViewById(R.id.Memo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(Note item) {
            textView_Title.setText(item.getTitle());
            textView_CreateDate.setText(item.getCreateDate());
            textView_Memo.setText(item.getMemo());
        }
    }
}
