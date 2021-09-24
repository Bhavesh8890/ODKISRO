package org.odk.collect.android.fragments.dialogs

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.odk.collect.android.R
import org.odk.collect.android.formmanagement.downloaderror.FormsDownloadErrorActivity
import org.odk.collect.android.formmanagement.downloaderror.FormsDownloadErrorItem
import java.util.ArrayList

class FormsDownloadResultDialog : DialogFragment() {
    companion object {
        const val FAILURES = "FAILURES"
        const val NUMBER_OF_ALL_FORMS = "NUMBER_OF_ALL_FORMS"
    }

    private lateinit var failures: ArrayList<FormsDownloadErrorItem>
    private var numberOfAllForms = 0

    var listener: FormDownloadResultDialogListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FormDownloadResultDialogListener) {
            listener = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        failures = arguments?.getSerializable(FAILURES) as ArrayList<FormsDownloadErrorItem>
        numberOfAllForms = arguments?.getInt(NUMBER_OF_ALL_FORMS)!!

        val builder = MaterialAlertDialogBuilder(requireContext())
            .setMessage(getMessage())
            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                listener?.onCloseDownloadingResult()
            }

        if (failures.isNotEmpty()) {
            builder.setNegativeButton(getString(R.string.show_details)) { _, _ ->
                val intent = Intent(context, FormsDownloadErrorActivity::class.java).apply {
                    putExtra(FormsDownloadErrorActivity.FAILURES, failures)
                }
                startActivity(intent)
                listener?.onCloseDownloadingResult()
            }
        }

        return builder.create()
    }

    private fun getMessage(): String {
        return if (failures.isEmpty()) {
            getString(R.string.all_downloads_succeeded)
        } else {
            getString(R.string.some_downloads_failed, failures.size.toString(), numberOfAllForms.toString())
        }
    }

    interface FormDownloadResultDialogListener {
        fun onCloseDownloadingResult()
    }
}
