package com.c3.vero.c3_4_settings;


import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.widget.Toast;

public class sundy_demo extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        final CheckBoxPreference cp=(CheckBoxPreference)findPreference("clientmode");
        cp.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (!((CheckBoxPreference)preference).isChecked()){
                    Toast.makeText(getApplicationContext(),"被选中了",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"没有被选中",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

    }
}
