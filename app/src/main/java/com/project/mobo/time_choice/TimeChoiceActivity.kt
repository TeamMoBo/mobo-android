package com.project.mobo.time_choice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.project.mobo.*
import khronos.Dates
import khronos.days
import khronos.plus
import kotlinx.android.synthetic.main.activity_time_choice.*
import kotlinx.android.synthetic.main.toolbar_timechoice.*
import java.util.*

typealias ChooseDate = Pair<Date, MutableSet<String>>

class TimeChoiceActivity : AppCompatActivity() {

    private val chooseDates = List<ChooseDate>(7) {
        Pair(Dates.today + (2 + it).days, mutableSetOf())
    }

    fun makeReserveDates(): List<ReserveDate> {
        val ret = mutableListOf<ReserveDate>()
        for (dates in chooseDates) {
            val date = dates.first
            val set = dates.second

            val reservationDate = date.toPlotString()
            val reservationTime = mutableListOf<Int>()
            for (value in set) {
                reservationTime.add(value.toInt())
            }
            ret.add(
                ReserveDate(reservationDate, reservationTime)
            )
        }
        return ret
    }

    var checked_three: Boolean = false

    private var currentSelectedDatePosition = 0
        set(value) {

            field = value
            clearTimetable()
            loadTimetableFromMemory()
        }
    private lateinit var btnTimeChoices: List<CheckedTextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_choice)

        Log.v("asdfa", chooseDates.toString())
        print(chooseDates)
        initUI()
        initDayUI()
        initChoiceTime()
    }

    private fun initUI() {
        btnTimeChoices = listOf(
            btnTimeChoice10pm,
            btnTimeChoice11pm,
            btnTimeChoice12am,
            btnTimeChoice12pm,
            btnTimeChoice6pm,
            btnTimeChoice1am,
            btnTimeChoice2am,
            btnTimeChoice3pm,
            btnTimeChoice4pm,
            btnTimeChoice5pm,
            btnTimeChoice7pm,
            btnTimeChoice8pm,
            btnTimeChoice9pm,
            btnTimeChoice1pm,
            btnTimeChoice2pm
        )
    }

    private fun initDayUI() {
        txtMonth.text = Dates.today.getMonthOfYear()

        val txtDays = listOf(txtDay1, txtDay2, txtDay3, txtDay4, txtDay5, txtDay6, txtDay7)
        val txtDayNums = listOf(
            txtDay1_num,
            txtDay2_num,
            txtDay3_num,
            txtDay4_num,
            txtDay5_num,
            txtDay6_num,
            txtDay7_num
        )

        chooseDates.forEachIndexed { index, (date, _) ->
            txtDays[index].text = date.getDayOfWeek()
            txtDayNums[index].text = date.getDayNum().toString()
        }

        txtDayNums.forEachIndexed { index, view ->
            view.setOnClickListener {
                currentSelectedDatePosition = index
            }
        }

        btnTimeChoiceBack.setOnClickListener{
            finish()
        }
    }

    private fun initChoiceTime() {
        btnTimeChoices.forEach { view ->
            view.setOnClickListener {
                val checkbox = it as CheckedTextView
                checkbox.isChecked = !checkbox.isChecked
                if (checkbox.isChecked) {
                    chooseDates[currentSelectedDatePosition].second.add(checkbox.tag.toString())
                } else {
                    chooseDates[currentSelectedDatePosition].second.remove(checkbox.tag.toString())
                }
            }
        }

        btnTimeChoiceGo.setOnClickListener {
            if (isValidTimeChoice()) {
                //TODO: 선택한 데이터를 서버에 보내줘야함. chooseDates
//                SharedPreferenceController.setTimeTable(
//                    this@TimeChoiceActivity,
//                    chooseDates[currentSelectedDatePosition].first,
//                    chooseDates[currentSelectedDatePosition].second
//                )
                finish()
            } else {
                Toast.makeText(this, "시간을 선택하세요!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearTimetable() {
        btnTimeChoices.forEach { it.isChecked = false }
    }

    private fun loadTimetableFromMemory() {
        btnTimeChoices
            .filter { chooseDates[currentSelectedDatePosition].second.contains(it.tag.toString()) }
            .forEach { it.isChecked = true }
    }

    private fun isValidTimeChoice(): Boolean {
        return chooseDates.map { it.second.size }.sum() >= MININUM_CHOOSEN_DATE
//        for (i in 0 until chooseDates.size) {
//            if ((chooseDates[i].second.size) >= MININUM_CHOOSEN_ONE) {
//                if ((chooseDates[i].second.size) < MININUM_CHOOSEN_DATE) {
//                    checked_three = false
//                }
//            } else
//                checked_three = true
//        }
//        return checked_three

//        if(chooseDates.size < MININUM_CHOOSEN_DATE){
//            Log.v("Tiem", chooseDates[currentSelectedDatePosition].size.toString())
//            return checked_three
//        }
//        else{
//            return checked_three = true
//        }
        //return checked_three
    }

    private companion object {
        const val MININUM_CHOOSEN_DATE = 3
        const val MININUM_CHOOSEN_ONE = 1
    }
}

//data class SomeResponse(
//    val status: Int,
//    val message: String,
//    val data: SomeData
//)
//
//data class SomeData(
//    val userIdx: Int,
//    val matchingState: Int,
//    val randMovie: List<RandMovie>,
//    val reserveMovie: List<ReserveMovie>,
//    val reserveDate: List<ReserveDate>
//)
//
//data class RandMovie(
//    val movieIdx: Int,
//    val movieImg: String,
//    val movieName: String,
//    val movieScore: Float,
//    val movieRuntime: String,
//    val movieGenre: String
//)
//
//data class ReserveMovie(
//    val movieIdx: Int,
//    val movieImg: String,
//    val movieName: String,
//    val movieScore: Float
//)
//
data class ReserveDate(
    val reservationDate: String,
    val reservationTime: List<Int>
)