package com.example.practisrmd.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practisrmd.data.Person
import com.example.practisrmd.databinding.ItemDesignBinding

class PersonAdapter(
    private var personList: List<Person>,
    private val onEdit :(Person) -> Unit,
    private val onDelete:(Person) -> Unit
): RecyclerView.Adapter<PersonAdapter.PersonViewHolder> (){


    inner class PersonViewHolder(val binding: ItemDesignBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonViewHolder {
        val binding = ItemDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PersonViewHolder,
        position: Int
    ) {
       val person = personList[position]
        holder.binding.apply {
            textUserName.text = person.name
            textUserEmail.text = person.email

            imageEdit.setOnClickListener { onEdit(person) }

            imageDelete.setOnClickListener { onDelete(person) }

        }
    }

    override fun getItemCount(): Int  = personList.size

    fun updateData(newList: List<Person>){
        personList = newList
        notifyDataSetChanged()
    }


}