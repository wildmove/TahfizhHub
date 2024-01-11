package com.example.tahfizhhub.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tahfizhhub.R
import com.example.tahfizhhub.navigasi.DestinasiNavigasi

object DestinasiMenu : DestinasiNavigasi {
    override val route = "menu"
    override val titleRes = "menu"
}

@Composable
fun MenuScreen(
    navigateToMurajaah: () -> Unit,
    navigateToSetoran: () -> Unit,
    modifier: Modifier = Modifier,

) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom)
        ) {
            Image(
                painterResource(id = R.drawable.quran),
                contentDescription = "Gambar Quran",
                modifier = Modifier.weight(1f, fill = true)
            )
            Text(
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(bottom = 25.dp)
                    .fillMaxWidth(),
                text =  buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )   {
                        append("Sudah Murajaah dan Setor Hafalan Hari Ini?")
                    }
                },
            )


            Spacer(modifier = Modifier.size(20.dp))

            Button(
                onClick = navigateToSetoran,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Setor Hafalan",
                    Modifier.padding(vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.size(20.dp))

            Button(
                onClick = navigateToMurajaah,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Murajaah",
                    Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }

}