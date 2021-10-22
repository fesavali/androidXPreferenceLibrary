package com.savalicodes.globochat

import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.preference.MultiSelectListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat


class AccountSettingsFragment : PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.account_settings, rootKey)
//        define all preference objects
    val publicInfoRef = MultiSelectListPreference(context)
        publicInfoRef.entries = resources.getStringArray(R.array.entries_public_info)
        publicInfoRef.entryValues = resources.getStringArray(R.array.values_public_info)
        publicInfoRef.key = resources.getString(R.string.key_public_info)
        publicInfoRef.title = resources.getString(R.string.title_public_info)
        publicInfoRef.isIconSpaceReserved = false

        val logOutPref = Preference(context)
        logOutPref.key = getString(R.string.key_log_out)
        logOutPref.title = getString(R.string.title_log_out)
        logOutPref.isIconSpaceReserved = false

        val deleteAccPref = Preference(context)
        deleteAccPref.key = getString(R.string.key_delete_account)
        deleteAccPref.summary = getString(R.string.summary_delete_account)
        deleteAccPref.title = getString(R.string.title_delete_account)
        deleteAccPref.icon = ResourcesCompat.getDrawable(resources, android.R.drawable.ic_menu_delete, null)

        val privacyCategory = PreferenceCategory(context)
        privacyCategory.title = getString(R.string.title_privacy)
        privacyCategory.isIconSpaceReserved = false

        val securityCategory = PreferenceCategory(context)
        securityCategory.title = getString(R.string.title_security)
        securityCategory.isIconSpaceReserved = false

        val prefScreen = preferenceManager.createPreferenceScreen(context)

//        Add all preference objects in hierachy
        prefScreen.addPreference(privacyCategory)
        prefScreen.addPreference(securityCategory)

        privacyCategory.addPreference(publicInfoRef)

        securityCategory.addPreference(logOutPref)
        securityCategory.addPreference(deleteAccPref)

//        set the preference screen
        preferenceScreen = prefScreen
    }
}
