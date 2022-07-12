package com.example.test0_debug.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.test0_debug.MainActivity;
import com.example.test0_debug.R;
import com.example.test0_debug.tools.OtherTools;
import com.example.test0_debug.tools.db.DBHelper;

public class SettintFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "myTAG";
    private EditText limit_edit;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting_fragment, container, false);
        Button limit_button = v.findViewById(R.id.limit_button);
        Button reset=v.findViewById(R.id.db_reset);
        TextView alter_users=v.findViewById(R.id.alter_users);
        alter_users.setOnClickListener(this);
        reset.setOnClickListener(this);
        limit_button.setOnClickListener(this);
        Switch night_theme = v.findViewById(R.id.theme_night);
        night_theme.setOnCheckedChangeListener(this);
        night_theme.setChecked(DBHelper.getThemeNight(context, 1) == 1);
        limit_edit = v.findViewById(R.id.limit_edit);
        limit_edit.setText(String.valueOf(DBHelper.getWordLimit(context, 1)));
        return v;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*如果limit<progress, 重置progress=limit并记入DB
            * */
            case R.id.limit_button:
                Log.d(TAG, "onClick: limit_button");
                int limit= Integer.parseInt(limit_edit.getText().toString());
                int progress=DBHelper.getTodayProgress(context,1);
                if (progress>limit)
                    DBHelper.setTodayProgress(context,limit,1);
                DBHelper.setWordLimit(context,limit, 1);
                break;
            case R.id.db_reset:
                DBHelper.reset(context,1);
                break;
            case R.id.alter_users:
                OtherTools.setLoginStatus(context,false);
                context.startActivity(new Intent(context,MainActivity.class));
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.theme_night) {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                DBHelper.setThemeNight(context, DBHelper.THEME_NIGHT, 1);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                DBHelper.setThemeNight(context, DBHelper.THEME_LIGHT, 1);
            }
        }
    }
}
