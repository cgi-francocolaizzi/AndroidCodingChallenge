package com.greatminds.androidcodingchallenge.ui.compose

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.greatminds.androidcodingchallenge.model.Article
import com.greatminds.androidcodingchallenge.ui.ArticlesUiEvent
import com.greatminds.androidcodingchallenge.ui.ArticlesUiState
import com.greatminds.androidcodingchallenge.ui.ArticlesViewModel

@Composable
fun ArticlesScreen(
  viewModel: ArticlesViewModel
) {
  val state = viewModel.uiState.collectAsState(
    initial = ArticlesUiState.Empty
  )
  when (val uiState = state.value) {
    is ArticlesUiState.Success -> {
      LazyColumn(
        modifier = Modifier,
      ) {
        items(items = uiState.items, key = { it.id }) { item ->
          ArticleComposable(
            article = item,
          ) {
            viewModel.onEvent(ArticlesUiEvent.ItemClick(it))
          }
        }
      }
    }

    ArticlesUiState.Loading -> {
      Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        CircularProgressIndicator()
      }
    }

    ArticlesUiState.Empty -> {
      Text("Nonthing Returned")
    }


    is ArticlesUiState.Error -> {
      Text("Error ${uiState.message}")
    }
  }

}

@Composable
fun ArticleComposable(
  modifier: Modifier = Modifier,
  article: Article,
  onClick: (article: Article) -> Unit = { }
) {
  Card(
    modifier = modifier
      .padding(10.dp)
      .clickable { onClick(article) },
    elevation = cardElevation(defaultElevation = 10.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .animateContentSize()
        .height(
          if (article.isExpanded) {
            100.dp
          } else {
            50.dp
          }
        )
        .padding(horizontal = 4.dp)
    ) {
      Text(modifier = Modifier.fillMaxWidth(), text = article.userId)
      Text(modifier = Modifier.fillMaxWidth(), text = article.title, fontStyle = FontStyle.Italic)
      Text(modifier = Modifier.fillMaxWidth(), text = article.body)
    }
  }
}
