package com.shaunkawano.androidcolorblendingsample

import android.graphics.Color
import android.os.Bundle
import android.support.v4.graphics.ColorUtils
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sample_color_blending.*

class SampleColorBlendingActivity : AppCompatActivity() {

  companion object {

    @JvmField val COLOR_PAIRS = listOf(Color.WHITE to Color.BLACK, Color.YELLOW to Color.RED,
        Color.CYAN to Color.BLUE, Color.DKGRAY to Color.GREEN)
  }

  private var current = 0
  private var from = COLOR_PAIRS[current].first
  private var to = COLOR_PAIRS[current].second

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sample_color_blending)

    pager.adapter = BlendedColorPagerAdapter()
    pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
      override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        val ratio = position + positionOffset
        val blendedColor = ColorUtils.blendARGB(from, to, ratio)
        val inverseBlendedColor = ColorUtils.blendARGB(to, from, ratio)

        bg.setBackgroundColor(blendedColor)
        button.setTextColor(inverseBlendedColor)
        button.setBackgroundColor(blendedColor)
      }

      override fun onPageScrollStateChanged(state: Int) {
      }

      override fun onPageSelected(position: Int) {
      }
    })

    button.setOnClickListener { changeColor() }
    tab.setupWithViewPager(pager)

    changeColor()
  }

  private fun changeColor() {
    current = if (current == COLOR_PAIRS.size - 1) 0 else current.inc()
    from = COLOR_PAIRS[current].first
    to = COLOR_PAIRS[current].second

    val rowRatioColor = ColorUtils.blendARGB(from, to, 0.1f)
    val mediumRatioColor = ColorUtils.blendARGB(from, to, 0.5f)
    val highRatioColor = ColorUtils.blendARGB(from, to, 0.9f)

    pager.currentItem = 0

    bg.setBackgroundColor(from)

    button.setTextColor(to)
    button.setBackgroundColor(from)

    text1.setTextColor(rowRatioColor)
    text2.setTextColor(mediumRatioColor)
    text3.setTextColor(highRatioColor)

    tab.setTabTextColors(mediumRatioColor, mediumRatioColor)
    tab.setSelectedTabIndicatorColor(mediumRatioColor)
  }
}
