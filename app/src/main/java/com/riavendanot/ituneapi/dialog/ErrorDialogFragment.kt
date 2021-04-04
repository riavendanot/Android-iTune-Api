package com.riavendanot.ituneapi.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.riavendanot.ituneapi.common.constant.StringConstant.EMPTY_STRING
import com.riavendanot.ituneapi.databinding.DialogErrorBinding

class ErrorDialogFragment : DialogFragment() {

    private var binding: DialogErrorBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        binding = DialogErrorBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.closeButton?.setOnClickListener { dismiss() }
        binding?.messageTextView?.text = arguments?.getString(DIALOG_BUNDLE_KEY, EMPTY_STRING)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {
        const val DIALOG_BUNDLE_KEY = "dialog_message_key"
    }
}