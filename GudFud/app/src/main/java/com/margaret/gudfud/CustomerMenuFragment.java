package com.margaret.gudfud;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

/**
 * the fragment for the customer's menu
 */
public class CustomerMenuFragment extends Fragment{

    public CustomerMenuFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_menu_customer, container, false);
        View tvView = inflater.inflate(R.layout.menu_item_customer, container, false);

        ListView listView = (ListView) view.findViewById(R.id.listViewCustomer);
        Button button = (Button) view.findViewById(R.id.button);

        ArrayList<MenuItem> list = new ArrayList<>();

        final CustomerMenuListAdapter customerAdapter = new CustomerMenuListAdapter(getActivity(), list);

        ArrayList<String> testIngredients = new ArrayList<>();
        testIngredients.add("milk");
        testIngredients.add("flour");
        testIngredients.add("goat's blood");

        MenuItem testItem1 = new MenuItem("cookies", testIngredients);
        MenuItem testItem2 = new MenuItem("pasta with marmalade", testIngredients);
        customerAdapter.add(testItem1);
        customerAdapter.add(testItem2);

        //source for a fair bit of this: http://kb4dev.com/tutorial/android-listview/android-listview-with-checkbox
        listView.setAdapter(customerAdapter);
        listView.setItemsCanFocus(false);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        final ArrayList<Integer> checkedPositions = new ArrayList<Integer>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, do something...

                    CheckedTextView ctv = (CheckedTextView) view.findViewById(R.id.customerCheckedTextView);
                    if (ctv.isChecked()) {
                        Toast.makeText(getContext(), "now it is unchecked", Toast.LENGTH_SHORT).show();
                        ctv.setChecked(false);
                        checkedPositions.add(position);

                    } else {
                        Toast.makeText(getContext(), "now it is checked", Toast.LENGTH_SHORT).show();
                        ctv.setChecked(true);
                        checkedPositions.add(position);

                    }

            }
        });

        button.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getContext(), "" + checkedPositions, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}