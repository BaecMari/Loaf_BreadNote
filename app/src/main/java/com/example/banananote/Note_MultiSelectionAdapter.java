package com.example.banananote;

import android.animation.ValueAnimator;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Note_MultiSelectionAdapter  extends RecyclerView.Adapter<Note_MultiSelectionAdapter.ViewHolder> {

    private List<Note> items;
    private Fragment fragment;

    static Boolean Edit_Activation;

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

    public Note_MultiSelectionAdapter(Fragment fragment, List<Note> itemModels) {
        this.items = itemModels;
        this.fragment = fragment;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag_main_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note item = items.get(position);
        //holder.setItem(item);
        initializeViews(item,holder,position);
    }

    private void initializeViews(final Note item, final ViewHolder holder, int position) {
        holder.textView_Title.setText(item.getTitle());
        holder.textView_CreateDate.setText(item.getCreateDate());
        holder.textView_Memo.setText(item.getMemo());
        holder.Multi_checkBox.setChecked(item.isSelected());
        holder.Multi_checkBox.setTag(position);
        holder.Multi_checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;
                int ClickedPosition = (Integer) checkBox.getTag();
                items.get(ClickedPosition).setSelected(checkBox.isChecked());
                notifyDataSetChanged();
            }
        });

        /*ValueAnimator valueAnimator = ValueAnimator.ofInt(15);
        valueAnimator.setDuration(400);
        holder.Main_CardView = holder.itemView.findViewById(R.id.Main_CardView);
        ViewGroup.MarginLayoutParams layoutParams =
                (ViewGroup.MarginLayoutParams) holder.Main_CardView.getLayoutParams();

        holder.Main_CardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(holder.Multi_checkBox.getVisibility() == View.INVISIBLE)
                    ((MainActivity)MainActivity.context_main).restart();

                return true;
            }
        });

        Edit_Activation = ((MainActivity)MainActivity.context_main).Edit_Activation;

        if(Edit_Activation) {

            if(!holder.Multi_checkBox.isChecked()) {
                holder.Multi_checkBox.setVisibility(View.VISIBLE);

                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        layoutParams.setMargins((Integer) valueAnimator.getAnimatedValue(),
                                (Integer) valueAnimator.getAnimatedValue(),
                                (Integer) valueAnimator.getAnimatedValue(),
                                (Integer) valueAnimator.getAnimatedValue());
                        holder.Main_CardView.requestLayout();
                    }
                });
                valueAnimator.start();
            } else {
                holder.Multi_checkBox.setVisibility(View.INVISIBLE);
            }
        }*/
    }

    public List<Note> getSelectedItem() {
        List<Note> itemModelList = new ArrayList<>();
        int i;
        for (i = 0; i< items.size(); i++) {
            Note item = items.get(i);

            if(item.isSelected()) {
                itemModelList.add(item);
            }
        }
        return itemModelList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView_Title;
        TextView textView_CreateDate;
        TextView textView_Memo;
        CheckBox Multi_checkBox;

        CardView Main_CardView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView_Title = itemView.findViewById(R.id.NoteTitle);
            textView_CreateDate = itemView.findViewById(R.id.CreateDate);
            textView_Memo = itemView.findViewById(R.id.Memo);
            Multi_checkBox = itemView.findViewById(R.id.main_checkbox);

            ValueAnimator valueAnimator = ValueAnimator.ofInt(15);
            valueAnimator.setDuration(400);
            Main_CardView = itemView.findViewById(R.id.Main_CardView);
            ViewGroup.MarginLayoutParams layoutParams =
                    (ViewGroup.MarginLayoutParams) Main_CardView.getLayoutParams();
            if(!Multi_checkBox.isChecked()) {
                Multi_checkBox.setVisibility(View.VISIBLE);

                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        layoutParams.setMargins((Integer) valueAnimator.getAnimatedValue(),
                                (Integer) valueAnimator.getAnimatedValue(),
                                (Integer) valueAnimator.getAnimatedValue(),
                                (Integer) valueAnimator.getAnimatedValue());
                        Main_CardView.requestLayout();
                    }
                });
                valueAnimator.start();
            } else {
                Multi_checkBox.setVisibility(View.INVISIBLE);
            }

            /*Main_CardView = itemView.findViewById(R.id.Main_CardView);
            ViewGroup.MarginLayoutParams layoutParams =
                    (ViewGroup.MarginLayoutParams) Main_CardView.getLayoutParams();*/

            /*ValueAnimator valueAnimator = ValueAnimator.ofInt(15);
            valueAnimator.setDuration(400);*/

            /*itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    if(Multi_checkBox.getVisibility() == View.INVISIBLE)
                        ((MainActivity)MainActivity.context_main).restart();

                    return true;
                }
            });*/

            /*Edit_Activation = ((MainActivity)MainActivity.context_main).Edit_Activation;

                if(Edit_Activation) {
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int position = getAdapterPosition();

                            if(Multi_checkBox.isChecked()) {
                                Multi_checkBox.setChecked(false);
                            } else {
                                Multi_checkBox.setChecked(true);

                            }
                        }
                    });

                    if(!Multi_checkBox.isChecked()) {
                        Multi_checkBox.setVisibility(View.VISIBLE);

                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                layoutParams.setMargins((Integer) valueAnimator.getAnimatedValue(),
                                        (Integer) valueAnimator.getAnimatedValue(),
                                        (Integer) valueAnimator.getAnimatedValue(),
                                        (Integer) valueAnimator.getAnimatedValue());
                                Main_CardView.requestLayout();
                            }
                        });
                        valueAnimator.start();
                    } else {
                        Multi_checkBox.setVisibility(View.INVISIBLE);
                    }
            }*/
        }

        /*public void setItem(Note item) {
            textView_Title.setText(item.getTitle());
            textView_CreateDate.setText(item.getCreateDate());
            textView_Memo.setText(item.getMemo());
        }*/
    }
}
