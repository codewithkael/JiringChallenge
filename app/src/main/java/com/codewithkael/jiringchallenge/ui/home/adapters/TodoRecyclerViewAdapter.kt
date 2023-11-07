package com.codewithkael.jiringchallenge.ui.home.adapters

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codewithkael.jiringchallenge.R
import com.codewithkael.jiringchallenge.databinding.ItemTodoRecyclerViewBinding
import com.codewithkael.jiringchallenge.data.remote.models.TodoModel

@SuppressLint("NotifyDataSetChanged", "SetTextI18n")
class TodoRecyclerViewAdapter : RecyclerView.Adapter<TodoRecyclerViewAdapter.TodoViewHolder>() {

    private var todoList = listOf<TodoModel>()
    fun setTodoList(list: List<TodoModel>) {
        this.todoList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ItemTodoRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            ), parent.context.resources
        )
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    inner class TodoViewHolder(
        private val view: ItemTodoRecyclerViewBinding,
        private val resource: Resources
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: TodoModel) {
            view.apply {
                titleTv.text = item.title
                counterTv.text = "${item.id}."
                if (item.completed) {
                    doneTv.text = "done"
                    doneTv.setTextColor(resource.getColor(R.color.green, null))
                } else {
                    doneTv.text = "undone"
                    doneTv.setTextColor(resource.getColor(R.color.red, null))
                }
            }
        }
    }

}