package com.example.practisrmd.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practisrmd.R
import com.example.practisrmd.data.Person
import com.example.practisrmd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonAdapter
    private val viewModel: PersonalViewModel  by viewModels()
    private var editPerson: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.Btn.setOnClickListener {
            SaveOrUpdate()
        }


        viewModel.allPerson.observe(this) { persons ->
            adapter.updateData(persons)
        }


    }


    fun SaveOrUpdate(){
        val name = binding.editText.text.toString().trim()
        val email = binding.editText2.text.toString().trim()

        if (name.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Name and Email are required", Toast.LENGTH_SHORT).show()
            return
        }


        if (editPerson == null){
            viewModel.insert(Person(0,name,email))
            Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show()
        }else{
            val updated =editPerson!!.copy(name = name, email = email)
            viewModel.update(updated)
            editPerson = null
            binding.Btn.text = "Add"
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show()
        }


        clearFields()


    }

    private fun clearFields() {
        binding.editText.text.clear()
        binding.editText2.text.clear()
    }

 private fun setupRecyclerView(){
     adapter = PersonAdapter(
         emptyList(),
         onEdit ={person -> startEdit(person)},
         onDelete = {person -> viewModel.delete(person)}
     )


     binding.recyclerView.apply {
         binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

         adapter  = this@MainActivity.adapter

     }
 }


    private fun startEdit(person: Person){
        binding.editText.setText(person.name)
        binding.editText2.setText(person.email)

        editPerson = person
       binding.Btn.text = "Update"
    }


}