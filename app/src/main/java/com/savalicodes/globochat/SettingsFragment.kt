package com.savalicodes.globochat

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.*


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        //create instance of class datastore
        val dataStore = DataStore()
        // Enable PreferenceDataStore for entire hierarchy and disables the SharedPreferences
//        preferenceManager.preferenceDataStore = dataStore enables for all

        val accSettingsPref = findPreference<Preference>(getString(R.string.key_account_settings))

        accSettingsPref?.setOnPreferenceClickListener {

            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_frag) as NavHostFragment
            val navController = navHostFragment.navController
            val action = SettingsFragmentDirections.actionSettingsToAccSettings()
            navController.navigate(action)
            true
        }

        // Read Preference values in a Fragment
        // Step 1: Get reference to the SharedPreferences (XML File)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        // Step 2: Get the 'value' using the 'key'
        val autoReplyTime = sharedPreferences.getString(getString(R.string.key_auto_reply_time), "")
        Log.i("SettingsFragment", "Auto Reply Time: $autoReplyTime")

        val autoDownload = sharedPreferences.getBoolean(getString(R.string.key_auto_download), false)
        Log.i("SettingsFragment", "Auto Download: $autoDownload")

        val statusPref = findPreference<EditTextPreference>(getString(R.string.key_status))
        statusPref?.setOnPreferenceChangeListener { preference, newValue ->

            val newStatus = newValue as String
            if (newStatus.contains("bad")) {
                Toast.makeText(context, "Inappropriate Status. Please maintain community guidelines.",
                    Toast.LENGTH_SHORT).show()

                false   // false: reject the new value.
            } else {
                true     // true: accept the new value.
            }
        }

        val notificationPref = findPreference<SwitchPreferenceCompat>(getString(R.string.key_new_msg_notif))
        notificationPref?.summaryProvider = Preference.SummaryProvider<SwitchPreferenceCompat> { switchPref ->

            if (switchPref?.isChecked!!)
                "Status: ON"
            else
                "Status: OFF"
        }
        //calls dataStore for this specific pref
        notificationPref?.preferenceDataStore = dataStore
    }
    class DataStore : PreferenceDataStore () {
        //        Override Methods only per your needs.
//        DO NOT override methods that you don't need.
//         After overriding, remove the super call (could through UnsupportedOperationException)
        override fun getBoolean(key: String?, defValue: Boolean): Boolean {
            if(key == "key_new_msg_notif"){
//                retrieve data from local DB or cloud
                Log.i("DataStore", "getBooleanExecuted for $key")
            }
            return defValue
        }

        override fun putBoolean(key: String?, value: Boolean) {
            if(key == "key_new_msg_notif"){
//                save data to local DB or cloud
                Log.i("DataStore", "putBooleanExecuted for $key with new value: $value")
            }
        }
    }
}
