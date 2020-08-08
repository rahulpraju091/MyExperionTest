package com.work.experion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.work.experion.interfaces.AppInterfaceAPI
import com.work.experion.viewModel.FeatureListViewModel
import kotlinx.android.synthetic.main.fragment_feature_list.view.*

class FeatureListFragment : Fragment() {

    companion object {
        val TAG: String = FeatureListFragment::class.java.simpleName.toString()
    }

    private lateinit var viewModel: FeatureListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_feature_list, container, false)
        view.swipe_refresh_layout.setColorSchemeColors(context?.let {
            ContextCompat.getColor(
                it,
                R.color.swipe_color1
            )
        }!!,
            context?.let { ContextCompat.getColor(it, R.color.swipe_color2) }!!,
            context?.let { ContextCompat.getColor(it, R.color.swipe_color3) }!!
        )
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = AppInterfaceAPI()
        val featureListRepository = FeatureListRepository(api)
        val featureListVMFactory = FeatureListVMFactory(featureListRepository)
        featureListVMFactory.let {
            viewModel = ViewModelProvider(this,it).get(FeatureListViewModel::class.java)
        }
    }

}