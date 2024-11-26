package cl.bootcamp.ejercicioinividual2.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.ColumnInfo
import coil.compose.rememberAsyncImagePainter

@Composable
fun MainTitle(title: String) {
    Text(
        text = title,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}


@Composable
fun FloatButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    ) {
        Icon(
            Icons.Default.Add,
            "Añadir"
        )
    }
}

@Composable
fun MainIconButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = null
            // tint = Color.White
        )
    }
}


@Composable
fun Space(size: Dp = 5.dp) {
    Spacer(modifier = Modifier.height(size))
}

@Composable
fun MainNumTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    )
}

@Composable
fun MainCharTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    )
}

@Composable
fun MainButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick
    ) { Text(text = text) }
}

@Composable
fun ImageComponent(imagen: String) {
    val imagen = rememberAsyncImagePainter(model = imagen)

    Image(
        painter = imagen,
        contentDescription = "Imagen del Perfil",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

@Composable
fun ContactCard(
    name: String,
    telephone: String,
    email: String,
    imageProfile: String,
    dateBirth: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            Text(
                text = name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            ImageComponent(imageProfile)
            Row(
                modifier = Modifier
                    .padding(vertical = 5.dp),
                        horizontalArrangement = Arrangement.Center

            ) {
                Text(
                    text = telephone,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = email,
                    fontSize = 20.sp
                )
            }
            Row(
                modifier = Modifier
                    .padding(vertical = 5.dp),
                        horizontalArrangement = Arrangement.Center
            ) {
//                Text(
//                    text = imageProfile,
//                    fontSize = 20.sp
//                )
//                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = dateBirth,
                    fontSize = 20.sp
                )
            }
//            Nota: el borrado lo deje con desplazamiento lateral SwipeAction
//            y la edicion con un click sobre el registro de la card
//            Row (modifier = Modifier
//                .padding(vertical = 5.dp),
//                horizontalArrangement = Arrangement.Center) {
//                MainIconButton(icon = Icons.Default.Edit,
//                    onClick = { onClick })
//
//                MainIconButton(icon = Icons.Default.Delete,
//                    onClick = { /* Navegar a la pantalla de edición */ })
//            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
