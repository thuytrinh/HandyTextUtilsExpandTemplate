package thuy.androidplayground

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.main.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main)

    // Find out about [safety] when using this app and learn [why we collect data].
    // Tap 'Go!' to agree to the [Service Terms] and [Privacy Policy].
    // [Learn more about Privacy].
    val template = getString(R.string.termsOfService)
        .replace("%1\$s", "^1")
        .replace("%2\$s", "^2")
        .replace("%3\$s", "^3")
        .replace("%4\$s", "^4")
        .replace("%5\$s", "^5")
    termsOfServiceView.text = TextUtils.expandTemplate(
        template,
        getString(R.string.safety).asClickable { it.toast() },
        getString(R.string.whyWeCollectData).asClickable { it.toast() },
        getString(R.string.serviceTerms).asClickable { it.toast() },
        getString(R.string.privacyPolicy).asClickable { it.toast() },
        getString(R.string.learnMoreAboutPrivacy).asClickable { it.toast() }
    )

    // To make the links actually clickable.
    termsOfServiceView.movementMethod = LinkMovementMethod.getInstance()
  }

  fun CharSequence.asClickable(whenClick: (CharSequence) -> Unit): SpannableString {
    val spannable = SpannableString(this)
    spannable.setSpan(object : ClickableSpan() {
      override fun onClick(widget: View?) = whenClick(this@asClickable)

      override fun updateDrawState(ds: TextPaint?) {
        super.updateDrawState(ds)
        ds?.color = Color.BLACK
      }
    }, 0, length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    return spannable
  }

  private fun CharSequence.toast() {
    Toast.makeText(applicationContext, this@toast, Toast.LENGTH_SHORT).show()
  }
}
