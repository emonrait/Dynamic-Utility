package com.emon.raihan.dynamicutility.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


class CustomActivityClear {

    companion object {

        fun doClearActivity(intent: Intent, activity: Activity) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            activity.startActivity(intent)
            activity.finish()

        }

      /*  fun doClearActivityWithSession(activity: Activity, message: String) {

            val dialog = AlertDialog.Builder(activity).setCancelable(false)
            val inflater = LayoutInflater.from(activity)
            val reg_layout: View =
                inflater.inflate(R.layout.success_dialog, null)

            val confirm_button = reg_layout.findViewById<Button>(R.id.confirm_button)
            val custom_image = reg_layout.findViewById<ImageView>(R.id.custom_image)
            val custom_image1 = reg_layout.findViewById<ImageView>(R.id.custom_image1)
            val title_text =
                reg_layout.findViewById<TextView>(R.id.title_text)

            val title_text1 =
                reg_layout.findViewById<TextView>(R.id.title_text1)

            custom_image.visibility = View.GONE
            custom_image1.visibility = View.VISIBLE


            dialog.setView(reg_layout)
            val alertDialog = dialog.create()
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


            //title_text.text = "Your Active Session is Expired! Please login Again"
            title_text.text = message
            title_text1.text = "Warning!"


            confirm_button.setOnClickListener {
                val intent = Intent(activity, Login::class.java)
                CustomActivityClear.doClearActivity(intent, activity)
                //Custom_alert.appClose(activity,"ENG")
                //activity.finishAffinity()
                activity.finish()
            }

            alertDialog.show()

        }

        fun callDialog(activity: Activity, languagecode: String) {
            // For Call
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:" + "16205")
            callIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            //For Mail


            val dialog = AlertDialog.Builder(activity).setCancelable(false)
            val inflater = LayoutInflater.from(activity)
            val reg_layout: View =
                inflater.inflate(R.layout.call_diallog, null)

            val btn_call = reg_layout.findViewById<Button>(R.id.btn_call)
            val ivClose = reg_layout.findViewById<ImageView>(R.id.ivClose)
            val tv_mail_send = reg_layout.findViewById<TextView>(R.id.tv_mail_send)


            val welcome_to = reg_layout.findViewById<TextView>(R.id.welcome_to)
            val bdbl_digital_bank_help_center =
                reg_layout.findViewById<TextView>(R.id.bdbl_digital_bank_help_center)
            val tv_call_message = reg_layout.findViewById<TextView>(R.id.tv_call_message)
            val tv_call_action = reg_layout.findViewById<TextView>(R.id.tv_call_action)
            val or_send_an_email_to = reg_layout.findViewById<TextView>(R.id.or_send_an_email_to)




            dialog.setView(reg_layout)
            val alertDialog = dialog.create()
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            if (TextContants.banglaLanguageCode.equals(languagecode)) {
                welcome_to.setText(R.string.welcome_welcome_bangla)
                bdbl_digital_bank_help_center.setText(R.string.bdbl_digital_bank_help_center_bangla)
                tv_call_message.setText(R.string.you_are_able_to_call_this_number_bangla)
                tv_call_action.setText(R.string._16205_bangla)
                or_send_an_email_to.setText(R.string.or_send_an_email_to_bangla)
                btn_call.setText(R.string.call_now_bangla)
            }



            btn_call.setOnClickListener {
                activity.startActivity(callIntent)
            }

            ivClose.setOnClickListener {
                alertDialog.dismiss()
            }

            tv_mail_send.setOnClickListener {

                val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "contact.center@bankasia-bd.com", null))
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")
                emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                if (emailIntent.resolveActivity(activity.packageManager) != null) {
                    activity.startActivity(Intent.createChooser(emailIntent, "Send email..."))
                }

            }

            alertDialog.show()

        }

        fun atmDialog(activity: Activity, languagecode: String) {

            val dialog = AlertDialog.Builder(activity).setCancelable(false)
            val inflater = LayoutInflater.from(activity)
            val reg_layout: View =
                inflater.inflate(R.layout.atm_diallog, null)


            val ivClose = reg_layout.findViewById<ImageView>(R.id.ivClose)
            val tvWelcome = reg_layout.findViewById<TextView>(R.id.tvWelcome)
            val bdbl_atm_location_info =
                reg_layout.findViewById<TextView>(R.id.bdbl_atm_location_info)
            val tv_atm_message = reg_layout.findViewById<TextView>(R.id.tv_atm_message)


            dialog.setView(reg_layout)
            val alertDialog = dialog.create()
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            if (TextContants.banglaLanguageCode.equals(languagecode)) {
                tvWelcome.setText(R.string.welcome_welcome_bangla)
                bdbl_atm_location_info.setText(R.string.bdbl_atm_location_info_bangla)
                tv_atm_message.setText(R.string.atm_info_message_bangla)
            }



            ivClose.setOnClickListener {
                alertDialog.dismiss()
            }


            alertDialog.show()

        }


        fun logoutExpireTime(intent: Intent, activity: Activity, diffInMs: Long, logoutTime: Int) {

            //Log.e("erra logoutExpireTime","indie****")
            // Log.e("idl exeper **","logout****"+ logoutTime)
            //  Log.e("idl diffInMs-->",diffInMs.toString())

            if (diffInMs > logoutTime) {
                val message = "Your Active Session is Expired! Please login Again."
                /*intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                activity.startActivity(intent)
                activity.finish()*/
                doClearActivityWithSession(activity, message)

            }


        }

        */
    }
}