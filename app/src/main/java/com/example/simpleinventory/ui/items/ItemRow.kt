package com.example.simpleinventory.ui.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.simpleinventory.data.model.Item
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ItemRow(item: Item, onClick: (Item) -> Unit) {
    Card(modifier = Modifier
        .padding(8.dp)
        .clickable { onClick(item) }
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Column {
                Text(text = item.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Qty: ${item.quantity}", style = MaterialTheme.typography.bodyMedium)
                Text(text = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault()).format(Date(item.updatedAt)), style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
