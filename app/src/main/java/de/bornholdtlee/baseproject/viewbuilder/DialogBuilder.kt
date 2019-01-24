package de.bornholdtlee.baseproject.viewbuilder

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import de.bornholdtlee.baseproject.R

class DialogBuilder {

    fun showSimpleDialog(builder: Builder) {
        val dialog = AlertDialog.Builder(builder.context, R.style.dialogStyle)
                .setTitle(builder.titleId)
                .setMessage(builder.messageId)
                .setPositiveButton(builder.positivBtnId, builder.positivListner)
        if (builder.negativBtnId != NO_BUTTON) {
            dialog.setNegativeButton(builder.negativBtnId, builder.negativListner)
        }
        if (builder.neutralBtnId != NO_BUTTON) {
            dialog.setNeutralButton(builder.neutralBtnId, builder.neutralListner)
        }
        dialog.show()
    }

    class Builder internal constructor(val context: Context) {
        var titleId: Int = 0
            set(titleId) {
                field = this.titleId
            }
        var messageId: Int = 0
            set(messageId) {
                field = this.messageId
            }
        var positivBtnId: Int = NO_BUTTON
            set(positivBtnId) {
                field = this.positivBtnId
            }
        var negativBtnId: Int = NO_BUTTON
            set(negativBtnId) {
                field = this.negativBtnId
            }
        var neutralBtnId: Int = NO_BUTTON
            set(neutralBtnId) {
                field = this.neutralBtnId
            }
        var positivListner: DialogInterface.OnClickListener? = null
            set(positivListner) {
                field = this.positivListner
            }
        var negativListner: DialogInterface.OnClickListener? = null
            set(negativListner) {
                field = this.negativListner
            }
        var neutralListner: DialogInterface.OnClickListener? = null
            set(neutralListner) {
                field = this.neutralListner
            }
    }

    companion object {

        const val NO_BUTTON : Int = 0
    }

}

