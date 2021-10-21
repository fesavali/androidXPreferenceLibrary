package com.savalicodes.globochat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.sriyank.globochat.ChatListFragmentDirections
import com.sriyank.globochat.SettingsFragmentDirections


class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        TODO("Not yet implemented")
        setPreferencesFromResource(R.xml.settings, rootKey)

        val accSettingsPref = findPreference<Preference>(getString(R.string.key_account_settings))

        accSettingsPref?.setOnPreferenceClickListener {
            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_frag) as NavHostFragment
            val navController = navHostFragment.navController
            val action = SettingsFragmentDirections.actionSettingsToAccSettings()
            navController.navigate(action)
            true
        }
//        get preference to the preferences
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
//        get preference value using key
        val autoReplyTime = sharedPreference.getString(getString(R.string.key_auto_reply_time), "")
        Log.i("Settings Screen", "auto replay time: $autoReplyTime")

        val autoDownload = sharedPreference.getBoolean(getString(R.string.key_auto_download), false)
        Log.i("Settings Screen", "Auto download: $autoDownload")

        val statusPref = findPreference<EditTextPreference>("key_status")
//        executed before value has changed
        statusPref?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference, newValue ->
                TODO("Not yet implemented")
                Log.i("Setting fragment", "new status: $newValue")
                val newStatus = newValue as String
                if (newStatus.contains("bad")){
                    Toast.makeText(this, "inappropriate text", Toast.LENGTH_SHORT).show()
                    false
                }else{
                    true
                }

            }
    }
}