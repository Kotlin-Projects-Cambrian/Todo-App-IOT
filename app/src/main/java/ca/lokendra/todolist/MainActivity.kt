package ca.lokendra.todolist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var itemsList: MutableList<String>
    private lateinit var itemsAdapter: ArrayAdapter<String>
    private lateinit var listView: ListView
    private lateinit var editList: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        editList = findViewById(R.id.EditList)

        itemsList = mutableListOf()
        itemsAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, itemsList)
        listView.adapter = itemsAdapter

        val addButton = findViewById<Button>(R.id.Add)
        addButton.setOnClickListener {
            val newItem = editList.text.toString()
            if (newItem.isNotEmpty()) {
                itemsList.add(newItem)
                itemsAdapter.notifyDataSetChanged()
                editList.text.clear()
            }
        }

        val deleteButton = findViewById<Button>(R.id.Delete)
        deleteButton.setOnClickListener {
            val checkedItems = listView.checkedItemPositions
            (itemsList.size - 1 downTo 0).forEach { position ->
                if (checkedItems.get(position)) {
                    itemsList.removeAt(position)
                }
            }
            itemsAdapter.notifyDataSetChanged()
            listView.clearChoices()
        }

        val clearButton = findViewById<Button>(R.id.Clear)
        clearButton.setOnClickListener {
            itemsList.clear()
            itemsAdapter.notifyDataSetChanged()
        }

    }
}

