package com.example.myapplicationcompose.flashcards.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplicationcompose.R
import com.example.myapplicationcompose.flashcards.data.Glossary
import com.example.myapplicationcompose.flashcards.viewModel.FlashcardsViewModel
import com.example.myapplicationcompose.ui.theme.Pink80
import com.example.myapplicationcompose.ui.theme.Purple80
import com.example.myapplicationcompose.ui.theme.PurpleGrey80

@Composable
fun AddingFileScreen(
    viewModel: FlashcardsViewModel,
    addFileOnClick : (String) -> Unit,
) {
    val glossaryName by viewModel.glossaryName.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
    ) {
        Column {
            TextField(
                value = glossaryName,
                onValueChange = { newName ->
                    viewModel.updateGlossaryName(newName)
                },
                label = { Text("Nazwa") },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(20.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            GlossaryScreen(viewModel = viewModel)

        }
        IconButton(
            onClick = {
                viewModel.updateGlossaryEntry(viewModel.term.value, viewModel.definition.value)
                addFileOnClick(glossaryName)},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(80.dp)
                .background(Color.DarkGray, RoundedCornerShape(100.dp))
                .border(BorderStroke(2.dp, colorResource(id = R.color.blue)), RoundedCornerShape(100.dp)),
        ) {
            Image(
                painter = painterResource(id = R.drawable.icons8_done_52),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.blue))
            )
        }
    }
}

//@Preview
//@Composable
//fun PreviewAddingFileScreen(){
//    AddingFileScreen()
//}