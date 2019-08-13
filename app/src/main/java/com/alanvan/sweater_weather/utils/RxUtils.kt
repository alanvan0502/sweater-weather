package com.alanvan.sweater_weather.utils

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxUtils {

    companion object {

        fun <T> applyIOSchedulers(): ObservableTransformer<T, T> {
            return ObservableTransformer {
                it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            }
        }

    }

}
