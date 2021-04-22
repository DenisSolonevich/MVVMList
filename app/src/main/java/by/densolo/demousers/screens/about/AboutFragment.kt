package by.densolo.demousers.screens.about

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import by.densolo.demousers.R
import by.densolo.demousers.databinding.FragmentAboutBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class AboutFragment : Fragment(R.layout.fragment_about) {

    private val binding: FragmentAboutBinding by viewBinding()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.aboutView) {
            settings.setJavaScriptEnabled(true)
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    view?.loadUrl(url!!)
                    return true
                }
            }
            loadUrl("http://bit.ly/CVDenis42")
        }
    }
}