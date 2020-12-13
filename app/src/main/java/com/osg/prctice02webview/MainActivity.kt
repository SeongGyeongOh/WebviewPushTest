package com.osg.prctice02webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.installations.InstallationTokenResult
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    lateinit var webView: WebView
    private lateinit var webSettings: WebSettings

    lateinit var firebaseInstall: FirebaseInstallations

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)

        //클릭시 새창이 안뜨게
        webView.webViewClient = WebViewClient()

        webSettings = webView.settings
        //웹뷰에서 자바스크립트를 사용할 수 있도록
        webSettings.javaScriptEnabled = true
        webSettings.setSupportMultipleWindows(true) //새창 띄우기 허용 여부
        webSettings.setSupportZoom(true)
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING

        webView.loadUrl("https://www.naver.com")


        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.i("fail", "FCM 실패")
                return@OnCompleteListener
            }
            val token = task.result
            if (token != null) {
                Log.i("token", token)
            }
        })
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
