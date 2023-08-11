package com.template.lovecompatibilitykg.sharedPreferences.board

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.template.lovecompatibilitykg.R
import com.template.lovecompatibilitykg.databinding.ItemBoardBinding
import com.template.lovecompatibilitykg.sharedPreferences.model.BoardModel

class BoardAdapter(val click: () -> Unit) :
    RecyclerView.Adapter<BoardAdapter.OnBoardingViewHolder>() {
    //BoardAdapter принимает функцию click в качестве параметра, которая будет вызываться,
    //когда пользователь кликает на элемент "доски".
    private val list = listOf(
        BoardModel(
            "Trust", "Trust is vital for strong relationships", R.raw.life
        ), BoardModel(
            "Communication", "Essential for healthy relationship and understanding", R.raw.man
        ), BoardModel(
            "Respect", "Recognition and admiration of others' rights", R.raw.yoga
        ), BoardModel(
            "Emotional Intimacy", "Emotional intimacy: deep emotional connection", R.raw.love
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemBoardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    //onCreateViewHolder: Создает новый экземпляр OnBoardingViewHolder для хранения представления
    //элемента списка.
    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    //onBindViewHolder: Связывает данные из list с представлением
    override fun getItemCount(): Int = list.size

    //getItemCount: Возвращает количество элементов в списке list.
    inner class OnBoardingViewHolder(private val binding: ItemBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(boardModel: BoardModel) {
            with(binding) {
                titleItemBoard.text = boardModel.title
                descriptionItemBoard.text = boardModel.description
                lottieAnimation.setAnimation(boardModel.image)
                buttonItemBoard.isVisible = adapterPosition == list.lastIndex
                buttonItemBoard.setOnClickListener {
                    click()
                }
            }
        }
    }
    //OnBoardingViewHolder, заполняя заголовок, описание и анимацию для каждого элемента списка.
}
