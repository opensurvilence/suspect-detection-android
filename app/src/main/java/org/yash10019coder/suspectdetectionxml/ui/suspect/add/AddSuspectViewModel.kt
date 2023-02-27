package org.yash10019coder.suspectdetectionxml.ui.suspect.add

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class AddSuspectViewModel : ViewModel() {
    val name = ObservableField("")
    val age = ObservableField(-1)
    val place = ObservableField("")
    val location = ObservableField("")
    val timeUnixTimestamp = ObservableField(-1L)

    fun addSuspect() {

    }
}
