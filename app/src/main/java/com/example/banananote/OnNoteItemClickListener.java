package com.example.banananote;

import android.view.View;

public interface OnNoteItemClickListener {
    //position 값 아이템 구분 인덱스
    public void onItemClick(NoteAdapter.ViewHolder holder, View view, int position);

}
