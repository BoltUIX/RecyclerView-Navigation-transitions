package com.androidx.androidjetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidx.androidjetpack.databinding.RecyclerViewBinding
import com.androidx.androidjetpack.databinding.RecyclerViewDesignBinding
import com.google.android.material.transition.MaterialElevationScale


// Fragment
class RecyclerViewFragment : BaseFragment<RecyclerViewBinding>() {

    private var recyclerItemModels  =   ArrayList<MyModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }


        arrayList()
        binding.cardsRecyclerView.apply {
            layoutManager      = LinearLayoutManager(context)
            hasFixedSize()
            binding.cardsRecyclerView.adapter = DashboardAdapter(event = { rowEvenBinding ->

                if (findNavController().currentDestination!!.id == R.id.nav_recycler_view) {
                    val extras: FragmentNavigator.Extras = FragmentNavigatorExtras(
                        rowEvenBinding.cardView to rowEvenBinding.cardView.transitionName
                    )
                    findNavController().navigate(
                        RecyclerViewFragmentDirections.actionNavRecyclerViewToNavDetailView(rowEvenBinding.cardView.transitionName,""+rowEvenBinding.titleText.text),
                        extras
                    )

                    exitTransition = MaterialElevationScale(false).apply {
                        duration = 100
                    }
                    reenterTransition = MaterialElevationScale(true).apply {
                        duration = 100
                    }
                }
            },recyclerItemModels)
        }
    }

    private fun arrayList() {
        recyclerItemModels.clear()
        recyclerItemModels.add(MyModel("App bars: bottom"))
        recyclerItemModels.add(MyModel("App bars: top"))
        recyclerItemModels.add(MyModel("Bottom navigation"))
        recyclerItemModels.add(MyModel("Buttons"))
        recyclerItemModels.add(MyModel("Buttons: floating action button"))
        recyclerItemModels.add(MyModel("Cards"))
        recyclerItemModels.add(MyModel("Chips"))
        recyclerItemModels.add(MyModel("Dialogs"))
        recyclerItemModels.add(MyModel("Menus"))
        recyclerItemModels.add(MyModel("Navigation drawer"))
        recyclerItemModels.add(MyModel("Date Pickers"))
        recyclerItemModels.add(MyModel("Time Pickers"))
        recyclerItemModels.add(MyModel("Progress indicators"))
        recyclerItemModels.add(MyModel("Radio buttons"))
        recyclerItemModels.add(MyModel("Checkboxes"))
        recyclerItemModels.add(MyModel("Switches"))
        recyclerItemModels.add(MyModel("Sheets: bottom"))
        recyclerItemModels.add(MyModel("Sliders"))
        recyclerItemModels.add(MyModel("Snackbars"))
        recyclerItemModels.add(MyModel("Tabs"))
        recyclerItemModels.add(MyModel("Text fields"))
        recyclerItemModels.add(MyModel("Shapeable Image View"))
        recyclerItemModels.add(MyModel("Font"))
        recyclerItemModels.add(MyModel("Elevation & Shadow"))
        recyclerItemModels.add(MyModel("Shape Theming"))
        recyclerItemModels.add(MyModel("Transition"))
        recyclerItemModels.add(MyModel("Color"))
        recyclerItemModels.add(MyModel("Data tables"))
        recyclerItemModels.add(MyModel("Dividers"))
        recyclerItemModels.add(MyModel("Image lists"))
        recyclerItemModels.add(MyModel("Navigation rail"))
        recyclerItemModels.add(MyModel("Sheets: side"))
        recyclerItemModels.add(MyModel("Lists"))
        recyclerItemModels.add(MyModel("Backdrop"))
        recyclerItemModels.add(MyModel("Banners"))
        recyclerItemModels.add(MyModel("Tooltips"))
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?, bundle: Bundle?) =
        RecyclerViewBinding.inflate(inflater, container, false)
}

// Adapter
class DashboardAdapterViewHolder(val bindingDesign: RecyclerViewDesignBinding) : RecyclerView.ViewHolder(bindingDesign.root)
class DashboardAdapter(
    private val event: (RecyclerViewDesignBinding) -> Unit,
    private val recyclerViewViewBindModelList: List<MyModel>) :
    RecyclerView.Adapter<DashboardAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardAdapterViewHolder {
        return DashboardAdapterViewHolder(
            RecyclerViewDesignBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: DashboardAdapterViewHolder, position: Int) {
        val currentItem = recyclerViewViewBindModelList[position]
        with(holder) {


            bindingDesign.cardView.apply {
                transitionName = "Title $position"
            }

            //bindingDesign.titleText.text = currentItem.label
            bindingDesign.titleText.text = "Title $position"

        }

        holder.itemView.setOnClickListener {
            event(holder.bindingDesign)
        }
    }

    // Return the size of your data set (invoked by the layout manager)
    override fun getItemCount(): Int {
        return recyclerViewViewBindModelList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}


// Data class
data class MyModel(val label: String)
