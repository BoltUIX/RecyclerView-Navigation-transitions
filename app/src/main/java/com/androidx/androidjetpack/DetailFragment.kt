package com.androidx.androidjetpack

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.androidx.androidjetpack.databinding.DetailFragmentBinding
import com.google.android.material.animation.AnimationUtils
import com.google.android.material.transition.MaterialContainerTransform

class DetailFragment : BaseFragment<DetailFragmentBinding>() {

    private val args: DetailFragmentArgs by navArgs()

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?, bundle: Bundle?) =
        DetailFragmentBinding.inflate(inflater, container, false)

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val transformation = MaterialContainerTransform()
        transformation.interpolator = AnimationUtils.LINEAR_INTERPOLATOR
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment_content_main
            duration = 400
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val key = args.transition  // get from home fragment
        binding.titleText.text = args.title

        with(binding.cardView) {
            transitionName = key // name of the View to uniquely identify it for Transitions.
        }
    }
}
