package com.shaunkawano.androidcolorblendingsample

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

class BlendedColorPagerAdapter : PagerAdapter() {

  companion object {
    const val COUNT = 2
  }

  override fun isViewFromObject(view: View?, obj: Any?) = view == obj

  override fun getCount() = COUNT

  override fun getPageTitle(position: Int) = if (position == 0) "FROM" else "TO"

  override fun instantiateItem(container: ViewGroup, position: Int): View = View.inflate(
      container.context, R.layout.view_empty, container)

  override fun destroyItem(container: ViewGroup?, position: Int, obj: Any?) {
    container?.removeView(obj as View)
  }
}
