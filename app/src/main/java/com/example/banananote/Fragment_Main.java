package com.example.banananote;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class Fragment_Main extends Fragment {

    RecyclerView recyclerView;
    NoteAdapter adapter;

    static boolean checked= false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,1);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NoteAdapter();

        adapter.addItem(new Note("가나다라마바","2020-07-20","테스트 메모 1"));
        adapter.addItem(new Note("가나다라마바사아자차카타파하","2020-07-20","테스트 메모 2, 수능완성 유니티 게임만들기"));
        adapter.addItem(new Note("가나다라마","2020-07-20","테스트 메모 3, 가낟라ㅏ"));
        adapter.addItem(new Note("test4","2020-07-20","테스트 메모 4,ㅓㄻㄴ아렁ㅁ니러아ㅣㅁ러ㅣ어ㅏ"));
        adapter.addItem(new Note("test5","2020-07-20","테스트 메모 5걎ㄷ갣ㅈㄱㄷ쟈ㅐㄱㅈ뎌갲댝ㅈ뎌갸ㅐㅈㄷ겨ㅑㅐㅈㄷ겾ㄷ겾갲ㄷ겨ㅑㄷㅈ겨ㅐㅈ뎌갲"));
        adapter.addItem(new Note("test6","2020-07-20","테스트 메모 6 jrtkweltj tkwejktlejqwl ejkwlq tjkleqwj tklewjkqt ljqweklt jkwqltj eklwtjkqwtjklweqtjwekltjkwelqtjqwekltjql"));

        adapter.addItem(new Note("test1","2020-07-20","테스트 메모 1"));
        adapter.addItem(new Note("test2","2020-07-20","테스트 메모 2, 수능완성 유니티 게임만들기"));
        adapter.addItem(new Note("test3","2020-07-20","테스트 메모 3, 가낟라ㅏ"));
        adapter.addItem(new Note("test4","2020-07-20","테스트 메모 4,ㅓㄻㄴ아렁ㅁ니러아ㅣㅁ러ㅣ어ㅏ"));
        adapter.addItem(new Note("test5","2020-07-20","테스트 메모 5걎ㄷ갣ㅈㄱㄷ쟈ㅐㄱㅈ뎌갲댝ㅈ뎌갸ㅐㅈㄷ겨ㅑㅐㅈㄷ겾ㄷ겾갲ㄷ겨ㅑㄷㅈ겨ㅐㅈ뎌갲"));
        adapter.addItem(new Note("test6","2020-07-20","테스트 메모 6 jrtkweltj tkwejktlejqwl ejkwlq tjkleqwj tklewjkqt ljqweklt jkwqltj eklwtjkqwtjklweqtjwekltjkwelqtjqwekltjql"));

        adapter.addItem(new Note("test1","2020-07-20","테스트 메모 1"));
        adapter.addItem(new Note("test2","2020-07-20","테스트 메모 2, 수능완성 유니티 게임만들기"));
        adapter.addItem(new Note("test3","2020-07-20","테스트 메모 3, 가낟라ㅏ"));
        adapter.addItem(new Note("test4","2020-07-20","테스트 메모 4,ㅓㄻㄴ아렁ㅁ니러아ㅣㅁ러ㅣ어ㅏ"));
        adapter.addItem(new Note("test5","2020-07-20","테스트 메모 5걎ㄷ갣ㅈㄱㄷ쟈ㅐㄱㅈ뎌갲댝ㅈ뎌갸ㅐㅈㄷ겨ㅑㅐㅈㄷ겾ㄷ겾갲ㄷ겨ㅑㄷㅈ겨ㅐㅈ뎌갲"));
        adapter.addItem(new Note("test6","2020-07-20","테스트 메모 6 jrtkweltj tkwejktlejqwl ejkwlq tjkleqwj tklewjkqt ljqweklt jkwqltj eklwtjkqwtjklweqtjwekltjkwelqtjqwekltjql"));

        adapter.addItem(new Note("test1","2020-07-20","테스트 메모 1"));
        adapter.addItem(new Note("test2","2020-07-20","테스트 메모 2, 수능완성 유니티 게임만들기"));
        adapter.addItem(new Note("test3","2020-07-20","테스트 메모 3, 가낟라ㅏ"));
        adapter.addItem(new Note("test4","2020-07-20","테스트 메모 4,ㅓㄻㄴ아렁ㅁ니러아ㅣㅁ러ㅣ어ㅏ"));
        adapter.addItem(new Note("test5","2020-07-20","테스트 메모 5걎ㄷ갣ㅈㄱㄷ쟈ㅐㄱㅈ뎌갲댝ㅈ뎌갸ㅐㅈㄷ겨ㅑㅐㅈㄷ겾ㄷ겾갲ㄷ겨ㅑㄷㅈ겨ㅐㅈ뎌갲"));
        adapter.addItem(new Note("test6","2020-07-20","테스트 메모 6 jrtkweltj tkwejktlejqwl ejkwlq tjkleqwj tklewjkqt ljqweklt jkwqltj eklwtjkqwtjklweqtjwekltjkwelqtjqwekltjql"));


        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new OnNoteItemClickListener() {
            @Override
            public void onItemClick(NoteAdapter.ViewHolder holder, View view, int position) {
                Note item = adapter.getItem(position);
                Toast.makeText(getContext(), "아이템 선택됨: " + item.getTitle(), Toast.LENGTH_LONG).show();
                /*CheckBox checkBox;
                checkBox = view.findViewById(R.id.main_checkbox);
                if(!checkBox.isChecked()) checkBox.setVisibility(View.VISIBLE);*/


                //checked = true;
            }
        });


        //return inflater.inflate(R.layout.fragment_main, container, false);

        //checkbox , LongClick

        
        return v;
    }
}
