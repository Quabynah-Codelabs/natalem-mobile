package io.codelabs.natalem.view

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.transition.TransitionManager
import io.codelabs.natalem.R
import io.codelabs.natalem.core.BaseActivity
import io.codelabs.natalem.databinding.ActivityMainBinding
import io.codelabs.natalem.util.OnDayRetrievedListener
import io.codelabs.natalem.util.calculateDateOfBirth
import io.codelabs.natalem.util.debugLog
import io.codelabs.natalem.view.helper.DatePickerFragment

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment: DatePickerFragment
    private var hasPickedDate: Boolean = false
    var year: Int = 0
    var month: Int = 0
    var day: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        // Initialize date picker fragment
        fragment = DatePickerFragment()
    }

    fun showTimePickerDialog(v: View) {
        if (hasPickedDate) {
            binding.datePickerButton.isEnabled = false
            binding.datePickerButton.text = getString(R.string.computing)

            calculateDateOfBirth(year, month, day, object : OnDayRetrievedListener {
                override fun onDayRetrieved(result: String) {
                    binding.datePickerButton.isEnabled = true
                    hasPickedDate = false
                    binding.datePickerButton.text = getString(R.string.get_started)

                    binding.dateContent.visibility = View.VISIBLE
                    binding.dateContent.text = result

                    /*val snackbar = Snackbar.make(binding.container, result, Snackbar.LENGTH_INDEFINITE)
                    snackbar.setAction("Try again") { snackbar.dismiss() }.show()*/
                }
            })
        } else {
            fragment.show(supportFragmentManager, "datePicker")
        }
    }

    fun onDatePicked(year: Int, month: Int, day: Int) {
        this.year = year
        this.month = month.plus(1)
        this.day = day
        debugLog("Year -> $year, Month -> $month, Day -> $day")
        hasPickedDate = true
        binding.dateContent.text = String.format("You selected : %d/%d/%d", this.day, this.month, this.year)

        // Show date picked
        TransitionManager.beginDelayedTransition(binding.container)
        binding.dateContent.visibility = View.VISIBLE
        binding.datePickerButton.text = getString(R.string.calculate_date)
    }
}
