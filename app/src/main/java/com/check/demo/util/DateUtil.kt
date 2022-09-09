package com.check.demo.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    @SuppressLint("SimpleDateFormat")
    val simpleFormat = SimpleDateFormat("dd/MM/yyyy")
    fun formatDate(date: Long): String {
        return simpleFormat.format(Date(date))
    }
}
