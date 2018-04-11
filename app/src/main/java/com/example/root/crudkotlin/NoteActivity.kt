package com.example.root.crudkotlin

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity() {

    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        try {
            var bundle: Bundle = intent.extras
            id = bundle.getInt("MainActId", 0)
            if (id != 0) {
                edtTitle.setText(bundle.getString("MainActTitle"))
                edtContent.setText(bundle.getString("MainActContent"))
            }
        } catch (ex: Exception) {
        }

        btAdd.setOnClickListener {
            var dbManager = NoteDbManager(this)

            var values = ContentValues()
            values.put("Title", edtTitle.text.toString())
            values.put("Content", edtContent.text.toString())

            if (id == 0) {
                if(edtTitle.text.isNotEmpty() and edtContent.text.isNotEmpty()) {
                    val mID = dbManager.insert(values)

                    if (mID > 0) {
                        Toast.makeText(this, "Add note successfully!", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Fail to add note!", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this, "Form can not be null!", Toast.LENGTH_LONG).show()
                }
            } else {
                var selectionArs = arrayOf(id.toString())
                if(edtTitle.text.isNotEmpty() and edtContent.text.isNotEmpty()){
                    val mID = dbManager.update(values, "Id=?", selectionArs)

                    if (mID > 0) {
                        Toast.makeText(this, "Update note successfully!", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Fail to update note!", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this, "Form can not be null!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}