package com.example.selfmadehtmldisp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.*
import com.example.selfmadehtmldisp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val webSettings: WebSettings = binding.questionView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true

        val htmlUrl = "file:///android_asset/Text/examframe.html"
        binding.questionView.loadUrl(htmlUrl)
        binding.selectView1.loadUrl(htmlUrl)
        binding.selectView2.loadUrl(htmlUrl)
        binding.selectView3.loadUrl(htmlUrl)

        binding.questionView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                // HTMLの読み込み完了時の処理
                Log.d("WebView", "HTMLの読み込み完了")
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                // HTMLの読み込みエラー時の処理
                Log.e("WebView", "HTMLの読み込みエラー: $error")
            }
        }

        val questionStr = "<style>body { background-color: blue; }</style>" + "（　）内に入る適切な語を選べ。<br>ああああああああああは（　）。<img src='file:///android_asset/img/flower_png8.png'>"
        binding.questionView.loadDataWithBaseURL(htmlUrl, questionStr, "text/html", "UTF-8", null)

        val selectStr1 = "<style>body { background-color: yellow; }</style>" + "<div style='display: flex; align-items: center; justify-content: center; height: 75vh;'>選択肢1</div>"
        binding.selectView1.loadDataWithBaseURL(htmlUrl, selectStr1, "text/html", "UTF-8", null)

        val selectStr2 = "<style>body { background-color: green; }</style>" + "<div style='display: flex; align-items: center; justify-content: center; height: 75vh;'>選択肢2</div>"
        binding.selectView2.loadDataWithBaseURL(htmlUrl, selectStr2, "text/html", "UTF-8", null)

        val selectStr3 = "<style>body { background-color: white; }</style>" + "<div style='display: flex; align-items: center; justify-content: center; height: 75vh;'>選択肢3</div>"
        binding.selectView3.loadDataWithBaseURL(htmlUrl, selectStr3, "text/html", "UTF-8", null)
    }

}
