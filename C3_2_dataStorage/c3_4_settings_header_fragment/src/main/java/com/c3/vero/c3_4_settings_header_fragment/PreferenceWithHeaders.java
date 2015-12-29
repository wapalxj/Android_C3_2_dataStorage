package com.c3.vero.c3_4_settings_header_fragment;

import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.List;

public class PreferenceWithHeaders extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add a button to the header list.
        if (hasHeaders()) {
            Button button = new Button(this);
            button.setText("Some action");
            setListFooter(button);
        }

    }

    /**
     * Populate the activity with the top-level headers.
     */
    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.preference_headers, target);
    }

    /**
     * This fragment shows the preferences for the first header.
     */
    public static class Prefs1Fragment extends PreferenceFragment {
        Preference pre;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            PreferenceManager.setDefaultValues(getActivity(),
                    R.xml.default_values, false);
            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.fragmented_preferences);
            pre=findPreference("checkbox_preference");
            pre.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    CheckBoxPreference cb=(CheckBoxPreference)preference;
                    if (cb.isChecked()){
                        Toast.makeText(getActivity(), "选中了!!!!!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "并没有选中!!!!!", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
        }

    }

    /**
     * This fragment contains a second-level set of preference that you
     * can get to by tapping an item in the first preferences fragment.
     */
    public static class Prefs1FragmentInner extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Can retrieve arguments from preference XML.
            Log.i("args", "Arguments: " + getArguments());

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.settings);

        }
    }

    /**
     * This fragment shows the preferences for the second header.
     */
    public static class Prefs2Fragment extends PreferenceFragment {
        Preference pre;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Can retrieve arguments from headers XML.
            Log.i("args", "Arguments: " + getArguments());
            //获取设置的extra
            String settings = getArguments().getString("someKey");
            Toast.makeText(getActivity(), settings, Toast.LENGTH_SHORT).show();
            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.fragmented_preferences222);


            pre=findPreference("checkbox_preference");
            pre.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    CheckBoxPreference cb = (CheckBoxPreference) preference;
                    if (cb.isChecked()) {
                        Toast.makeText(getActivity(), "选中了!!!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "并没有选中!!!!!", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
        }
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return true;
    }
}