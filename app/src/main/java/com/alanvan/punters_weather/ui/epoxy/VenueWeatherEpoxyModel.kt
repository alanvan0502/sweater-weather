package com.alanvan.punters_weather.ui.epoxy

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.alanvan.punters_weather.R

@EpoxyModelClass
abstract class VenueWeatherEpoxyModel : EpoxyModelWithHolder<VenueWeatherEpoxyModel.Holder>() {

    @EpoxyAttribute
    var venueName: String? = null

    @EpoxyAttribute
    var venueCondition: String? = null

    @EpoxyAttribute
    var venueTemperature: String? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotUseInToString, EpoxyAttribute.Option.DoNotHash)
    var onClickListener: View.OnClickListener? = null

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.apply {
            venueNameTv.text = venueName
            venueConditionTv.text = venueCondition
            venueTemperatureTv.text = venueTemperature
            itemView.setOnClickListener(onClickListener)
        }

    }

    override fun getDefaultLayout(): Int {
        return R.layout.venue_weather
    }

    class Holder : BaseEpoxyModel.Holder() {
        val venueNameTv by bind<TextView>(R.id.venue_name)
        val venueConditionTv by bind<TextView>(R.id.venue_condition)
        val venueTemperatureTv by bind<TextView>(R.id.venue_temperature)
    }
}