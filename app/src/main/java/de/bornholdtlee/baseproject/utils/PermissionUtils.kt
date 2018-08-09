package de.bornholdtlee.baseproject.utils

import android.Manifest
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import de.bornholdtlee.baseproject.base.BaseActivity
import de.bornholdtlee.baseproject.base.BaseApplication

class PermissionUtils(application: BaseApplication) : BaseUtils(application) {

    fun requestPermission(activity: BaseActivity, permission: PERMISSION, listener: PermissionListener? = null, onSameThread: Boolean = false) {
        val builder = Dexter.withActivity(activity)
                .withPermission(permission.permissionString)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse) {
                        listener?.onPermissionGranted(response)
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse) {
                        listener?.onPermissionDenied(response)
                    }

                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest, token: PermissionToken) {
                        token.continuePermissionRequest()
                        listener?.onPermissionRationaleShouldBeShown(permission, token)

                    }
                })
        if (onSameThread) {
            builder.onSameThread()
        }
        builder.check()
    }

    enum class PERMISSION(val permissionString: String) {
        LOCATION(Manifest.permission.ACCESS_FINE_LOCATION),
        CAMERA(Manifest.permission.CAMERA)
    }
}