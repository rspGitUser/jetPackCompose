package com.test.jetpackcompose.presentation.screens.userList

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.test.jetpackcompose.data.model.userlist.Data
import com.test.jetpackcompose.presentation.navigation.UserListDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun UserList(viewModel: UserListViewModel = hiltViewModel(), NavigationToDetail: () -> Unit) {


    //val state by viewModel.userListState.collectAsStateWithLifecycle()
    val state by viewModel.userListState.collectAsState()
    val userListData by viewModel.userListData.collectAsState()
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize()) {
        scope.launch {

            viewModel.getUserList()
            delay(1000)
            when (state) {
                is UserListState.Init -> {
                    Log.i("UserListState=>", "Inicializando...")
                }

                is UserListState.Success -> {

                    NavigationToDetail()


                }

                is UserListState.Loading -> {
                    Log.i("UserListState=>", "Loading...")
                }

                is UserListState.Error -> {
                    Log.i("UserListState=>", "Error...")
                }

            }
        }


    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserListDetailScreen(userListViewModel: UserListViewModel = hiltViewModel()) {
    userListViewModel.getUserList()
    val userListData by userListViewModel.userListData.collectAsState()

    Scaffold(
        topBar = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Row() {
                    Text(text = "Contacts", fontWeight = FontWeight.Bold)
                }
            }
        }, content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 25.dp)
            ) {

                LazyColumn(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    items(userListData) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            //Mostrar imagen
                            AsyncImage(
                                model = it.avatar,
                                contentDescription = it.avatar,
                                modifier = Modifier
                                    .clip(
                                        CircleShape
                                    )
                                    .size(50.dp),
                                contentScale = ContentScale.Crop,
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = """
                        ${it.first_name} ${it.last_name}
                    """.trimIndent(), fontWeight = FontWeight.Bold
                                )

                                Text(text = it.email)

                                //mostrar foto, nombre completo y correo electrónico de cada usuario).
                            }
                        }
                        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
                    }


                }
            }
        }, bottomBar = {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )
        }
    )


}