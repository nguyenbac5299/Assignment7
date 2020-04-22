package com.example.email1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    ArrayList<ItemModel> list;
    ArrayList<ItemModel> favoriteItems = new ArrayList<>();
    ArrayList<String> search = new ArrayList<>();
    Button favorite;
    boolean favorited = false;
    AutoCompleteTextView editSearch;
    ArrayAdapter<String> searchAdapter;
    RecyclerView recyclerView;
    String searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        list.add(new ItemModel("Name1", "CHúc mừng bạn đã trúng thưởng 1 tỷ đồng", "12:00PM", false));
        list.add(new ItemModel("Name2", "CHúc mừng bạn đã trúng thưởng 1 tỷ đồng", "12:00PM", false));
        list.add(new ItemModel("Name3", "CHúc mừng bạn đã trúng thưởng 1 tỷ đồng", "12:00PM", false));
        list.add(new ItemModel("Name4", "CHúc mừng bạn đã trúng thưởng 1 tỷ đồng", "12:00PM", false));
        list.add(new ItemModel("Name5", "CHúc mừng bạn đã trúng thưởng 1 tỷ đồng", "12:00PM", false));
        list.add(new ItemModel("Name6", "CHúc mừng bạn đã trúng thưởng 1 tỷ đồng", "12:00PM", false));
        list.add(new ItemModel("Name7", "CHúc mừng bạn đã trúng thưởng 1 tỷ đồng", "12:00PM", false));
        list.add(new ItemModel("Name8", "CHúc mừng bạn đã trúng thưởng 1 tỷ đồng", "12:00PM", false));
        list.add(new ItemModel("Name9", "CHúc mừng bạn đã trúng thưởng 1 tỷ đồng", "12:00PM", false));
        list.add(new ItemModel("Name10", "CHúc mừng bạn đã trúng thưởng 1 tỷ đồng", "12:00PM", false));

        for (int i = 0; i < list.size(); i++) {
            search.add(list.get(i).getName());
            search.add(list.get(i).getSubject());
        }
        editSearch = findViewById(R.id.edit_search);
        searchAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, search);
        editSearch.setAdapter(searchAdapter);
        editSearch.addTextChangedListener(this);


        recyclerView = findViewById(R.id.recycle_view);
        favorite = findViewById(R.id.button);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final EmailAdapter adapter = new EmailAdapter(list);
        recyclerView.setAdapter(adapter);

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favorited = !favorited;
                favoriteItems.clear();
                if (favorited) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).isFavorite())
                            favoriteItems.add(list.get(i));
                    }
                    EmailAdapter adapter1 = new EmailAdapter(favoriteItems);
                    recyclerView.setAdapter(adapter1);
                } else {
                    EmailAdapter adapter = new EmailAdapter(list);
                    recyclerView.setAdapter(adapter);
                }
            }
        });


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {


    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.d("BacNT", s + "");
        searchText = String.valueOf(editSearch.getText());
        ArrayList<ItemModel> arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (s.toString().equals(list.get(i).getName()) || s.toString().equals(list.get(i).getSubject()))
                arrayList.add(list.get(i));
        }
        if (arrayList.size() == 0) {
            Toast.makeText(this, "Không có kết quả phù hợp", Toast.LENGTH_LONG).show();
        } else {
            EmailAdapter adapter2 = new EmailAdapter(arrayList);
            recyclerView.setAdapter(adapter2);
        }
    }

}
