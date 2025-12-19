package com.greatminds.androidcodingchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.greatminds.androidcodingchallenge.ui.ArticlesViewModel
import com.greatminds.androidcodingchallenge.ui.compose.ArticlesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  val vm: ArticlesViewModel by viewModels<ArticlesViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          ArticlesScreen(
            viewModel = vm
          )
        }
    }
}
