package gstv.dogapi.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize
import gstv.dogapi.domain.Category

@Composable
fun CategoryDropdown(suggestions: List<Category>, onItemCLicked: (Category) -> Unit) {
    val expanded = remember { mutableStateOf(false) }

    val selectedText = remember { mutableStateOf("") }

    val textFieldSize = remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded.value)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown


    Column() {
        OutlinedTextField(
            value = selectedText.value,
            onValueChange = { selectedText.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textFieldSize.value = coordinates.size.toSize()
                },
            label = { Text("Label") },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded.value = !expanded.value })
            }
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.value.width.toDp() })
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText.value = label.name
                    onItemCLicked(label)
                }) {
                    Text(text = label.name)
                }
            }
        }
    }
}