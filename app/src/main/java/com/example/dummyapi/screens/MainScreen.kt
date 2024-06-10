package com.example.dummyapi.screens


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.dummyapi.presentation.ProductViewModel
import com.example.dummyapi.products.Product
import com.example.dummyapi.ui.theme.Hello
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainScreen(
    navController: NavHostController, viewModel: ProductViewModel,
) {

    val productList = viewModel.products.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel.EmptyBanner) {
        viewModel.EmptyBanner.collectLatest { show ->
            if (show) {
                Toast.makeText(
                    context,
                    "Error in the connection",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }
    if (productList.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(productList.size) { index ->
                ProductItem(
                    productList[index],
                    onClick = { title, description, price ->
                        navController.navigate("Detail/$title/$description/$price")
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    onClick: (String, String, String) -> Unit,
) {
    val onlineImage = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(product.thumbnail)
            .size(Size.ORIGINAL).build()
    ).state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick(product.title, product.description, product.price) }
            .padding(10.dp)
            .height(450.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
                .padding(2.dp),
            elevation = CardDefaults.cardElevation(22.dp)
            , colors = CardDefaults.cardColors(Hello)

        ) {

            if (onlineImage is AsyncImagePainter.State.Error) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            if (onlineImage is AsyncImagePainter.State.Success) {
                Image(
                    painter = onlineImage.painter,
                    contentDescription = "",
                    modifier = Modifier
                        .size(450.dp),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
//                Spacer(modifier = Modifier.width(6.dp))
            }

//            Text(
//                text = product.title,
//                fontSize = 26.sp,
//                textAlign = TextAlign.Center, maxLines = 1,
//                fontWeight = FontWeight.W600, modifier = Modifier
//            )
//            Spacer(modifier = Modifier.height(6.dp))
//            Text(
//                text = "${product.category}",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Medium, modifier = Modifier.padding(start = 130.dp),
//                maxLines = 2
//            )
//            Spacer(modifier = Modifier.height(6.dp))
//            Text(
//                text = "$${product.price}",
//                fontSize = 18.sp,
//                textAlign = TextAlign.Center,
//                fontWeight = FontWeight.Normal, modifier = Modifier.padding(start = 130.dp)
//            )
//            Spacer(modifier = Modifier.height(6.dp))


        }
    }
}

