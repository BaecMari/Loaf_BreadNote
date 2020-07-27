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

import java.util.ArrayList;
import java.util.List;

public class Fragment_Main extends Fragment {

    RecyclerView recyclerView;
    NoteAdapter adapter;
    Note_MultiSelectionAdapter multi_adapter;



    static boolean checked= false;

    public  String[] title;

    public  int i = 0;
    //어댑터 사이즈

    int adapter_size = 0;

    String names[] = {"test1","test2","test3"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        List<Note> list = getList();

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,1);
        recyclerView.setLayoutManager(layoutManager);

        //MainActivity.tag = "multi";

        /*        switch (MainActivity.tag) {
            case "single":
                adapter = new NoteAdapter(this,list);

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
                        //CheckBox checkBox;
                        //checkBox = view.findViewById(R.id.main_checkbox);
                        //if(!checkBox.isChecked()) checkBox.setVisibility(View.VISIBLE);


                        //checked = true;
                    }
                });

                break;
            case "multi":
                multi_adapter = new Note_MultiSelectionAdapter(this,list);

                multi_adapter.addItem(new Note("가나다라마바","2020-07-20","테스트 메모 1"));
                multi_adapter.addItem(new Note("가나다라마바사아자차카타파하","2020-07-20","테스트 메모 2, 수능완성 유니티 게임만들기"));
                multi_adapter.addItem(new Note("가나다라마","2020-07-20","테스트 메모 3, 가낟라ㅏ"));
                multi_adapter.addItem(new Note("test4","2020-07-20","테스트 메모 4,ㅓㄻㄴ아렁ㅁ니러아ㅣㅁ러ㅣ어ㅏ"));
                multi_adapter.addItem(new Note("test5","2020-07-20","테스트 메모 5걎ㄷ갣ㅈㄱㄷ쟈ㅐㄱㅈ뎌갲댝ㅈ뎌갸ㅐㅈㄷ겨ㅑㅐㅈㄷ겾ㄷ겾갲ㄷ겨ㅑㄷㅈ겨ㅐㅈ뎌갲"));
                multi_adapter.addItem(new Note("test6","2020-07-20","테스트 메모 6 jrtkweltj tkwejktlejqwl ejkwlq tjkleqwj tklewjkqt ljqweklt jkwqltj eklwtjkqwtjklweqtjwekltjkwelqtjqwekltjql"));

                multi_adapter.addItem(new Note("test1","2020-07-20","테스트 메모 1"));
                multi_adapter.addItem(new Note("test2","2020-07-20","테스트 메모 2, 수능완성 유니티 게임만들기"));
                multi_adapter.addItem(new Note("test3","2020-07-20","테스트 메모 3, 가낟라ㅏ"));
                multi_adapter.addItem(new Note("test4","2020-07-20","테스트 메모 4,ㅓㄻㄴ아렁ㅁ니러아ㅣㅁ러ㅣ어ㅏ"));
                multi_adapter.addItem(new Note("test5","2020-07-20","테스트 메모 5걎ㄷ갣ㅈㄱㄷ쟈ㅐㄱㅈ뎌갲댝ㅈ뎌갸ㅐㅈㄷ겨ㅑㅐㅈㄷ겾ㄷ겾갲ㄷ겨ㅑㄷㅈ겨ㅐㅈ뎌갲"));
                multi_adapter.addItem(new Note("test6","2020-07-20","테스트 메모 6 jrtkweltj tkwejktlejqwl ejkwlq tjkleqwj tklewjkqt ljqweklt jkwqltj eklwtjkqwtjklweqtjwekltjkwelqtjqwekltjql"));

                multi_adapter.addItem(new Note("test1","2020-07-20","테스트 메모 1"));
                multi_adapter.addItem(new Note("test2","2020-07-20","테스트 메모 2, 수능완성 유니티 게임만들기"));
                multi_adapter.addItem(new Note("test3","2020-07-20","테스트 메모 3, 가낟라ㅏ"));
                multi_adapter.addItem(new Note("test4","2020-07-20","테스트 메모 4,ㅓㄻㄴ아렁ㅁ니러아ㅣㅁ러ㅣ어ㅏ"));
                multi_adapter.addItem(new Note("test5","2020-07-20","테스트 메모 5걎ㄷ갣ㅈㄱㄷ쟈ㅐㄱㅈ뎌갲댝ㅈ뎌갸ㅐㅈㄷ겨ㅑㅐㅈㄷ겾ㄷ겾갲ㄷ겨ㅑㄷㅈ겨ㅐㅈ뎌갲"));
                multi_adapter.addItem(new Note("test6","2020-07-20","테스트 메모 6 jrtkweltj tkwejktlejqwl ejkwlq tjkleqwj tklewjkqt ljqweklt jkwqltj eklwtjkqwtjklweqtjwekltjkwelqtjqwekltjql"));

                multi_adapter.addItem(new Note("test1","2020-07-20","테스트 메모 1"));
                multi_adapter.addItem(new Note("test2","2020-07-20","테스트 메모 2, 수능완성 유니티 게임만들기"));
                multi_adapter.addItem(new Note("test3","2020-07-20","테스트 메모 3, 가낟라ㅏ"));
                multi_adapter.addItem(new Note("test4","2020-07-20","테스트 메모 4,ㅓㄻㄴ아렁ㅁ니러아ㅣㅁ러ㅣ어ㅏ"));
                multi_adapter.addItem(new Note("test5","2020-07-20","테스트 메모 5걎ㄷ갣ㅈㄱㄷ쟈ㅐㄱㅈ뎌갲댝ㅈ뎌갸ㅐㅈㄷ겨ㅑㅐㅈㄷ겾ㄷ겾갲ㄷ겨ㅑㄷㅈ겨ㅐㅈ뎌갲"));
                multi_adapter.addItem(new Note("test6","2020-07-20","테스트 메모 6 jrtkweltj tkwejktlejqwl ejkwlq tjkleqwj tklewjkqt ljqweklt jkwqltj eklwtjkqwtjklweqtjwekltjkwelqtjqwekltjql"));

                recyclerView.setAdapter(multi_adapter);

                break;
        }*/

        /*if(MainActivity.tag == "multi") {
            recyclerView.removeAllViewsInLayout();
            recyclerView.removeAllViews();
        }*/
        //싱글 선택 어댑터
        adapter = new NoteAdapter(this,list);

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnNoteItemClickListener() {
            @Override
            public void onItemClick(NoteAdapter.ViewHolder holder, View view, int position) {
                Note item = adapter.getItem(position);
                Toast.makeText(getContext(), "아이템 선택됨: " + item.getTitle(), Toast.LENGTH_LONG).show();
                //CheckBox checkBox;
                //checkBox = view.findViewById(R.id.main_checkbox);
                //if(!checkBox.isChecked()) checkBox.setVisibility(View.VISIBLE);


                //checked = true;
            }
        });




        //return inflater.inflate(R.layout.fragment_main, container, false);

        //checkbox , LongClick

        
        return v;
    }

    private List<Note> getList() {
        List<Note> list = new ArrayList<>();

        for(int i = 0; i< names.length; i++) {
            Note model = new Note();
            model.setTitle(names[i]);
            model.setCreateDate("test");
            model.setMemo("12341234");
            list.add(model);
        }

        return list;
    }

    public void selectedClick() {

        List list = adapter.getSelectedItem();

        if(list.size() > 0 ) {
            StringBuilder sb = new StringBuilder();
            for(int index = 0; index < list.size(); index++) {
                Note model = (Note) list.get(index);
                sb.append(model.getTitle()).append("\n");
            }
            showToast(sb.toString());

        } else {
            showToast("체크 안됨");
        }
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
