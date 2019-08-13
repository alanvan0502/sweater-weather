package com.alanvan.sweater_weather.ui.epoxy

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.alanvan.sweater_weather.R

@EpoxyModelClass
abstract class CountryFilterEpoxyModel : EpoxyModelWithHolder<CountryFilterEpoxyModel.Holder>() {

    @EpoxyAttribute
    var countryName: String? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotUseInToString, EpoxyAttribute.Option.DoNotHash)
    var onClickListener: View.OnClickListener? = null

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.apply {
            countryNameTv.text = countryName
            itemView.setOnClickListener(onClickListener)
        }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.country_filter
    }

    class Holder : BaseEpoxyModel.Holder() {
        val countryNameTv by bind<TextView>(R.id.country_name)
    }
}