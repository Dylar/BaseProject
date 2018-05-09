package de.bornholdtlee.defaultproject.utils


import android.bluetooth.BluetoothAdapter

import de.bornholdtlee.defaultproject.exceptions.TechnologyDisabledException
import de.bornholdtlee.defaultproject.exceptions.TechnologyNotExistingException

class BluetoothUtils {

    @Throws(TechnologyNotExistingException::class, TechnologyDisabledException::class)
    fun checkForBluetooth() {
        val adapter = BluetoothAdapter.getDefaultAdapter()

        if (adapter == null) {
            throw TechnologyNotExistingException()
        } else if (!adapter.isEnabled) {
            throw TechnologyDisabledException()
        }
    }
}
