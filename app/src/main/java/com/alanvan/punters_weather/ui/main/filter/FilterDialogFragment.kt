package com.alanvan.punters_weather.ui.main.filter

import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.airbnb.epoxy.EpoxyRecyclerView
import com.alanvan.punters_weather.R
import com.alanvan.punters_weather.ui.main.FilterEpoxyController
import com.alanvan.punters_weather.ui.main.event.FilterEvent
import com.alanvan.punters_weather.ui.main.event.PublishFilterEvents
import com.alanvan.punters_weather.utils.RxUtils

class FilterDialogFragment : DialogFragment(), FilterEpoxyController.Callback {

    private lateinit var toolbar: android.support.v7.widget.Toolbar
    private var viewModel: FilterDialogViewModel? = null
    private val epoxyController = FilterEpoxyController(this)

    companion object {
        fun newInstance(): FilterDialogFragment {
            return FilterDialogFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FilterDialogViewModel::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimationStyle
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_filter, container, false)

        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp)
        toolbar.setNavigationOnClickListener {
            dismiss()
        }
        val recyclerView: EpoxyRecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setController(epoxyController)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.loadCountries()?.map { dataList ->
            epoxyController.setData(dataList)
            epoxyController.requestModelBuild()
        }?.compose(RxUtils.applyIOSchedulers())?.subscribe()
    }

    override fun onItemClick(countryID: String?) {
        PublishFilterEvents.instance.subject.onNext(FilterEvent(countryID))
        dismiss()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}