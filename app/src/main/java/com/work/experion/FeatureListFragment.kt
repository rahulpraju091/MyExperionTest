package com.work.experion

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.work.experion.interfaces.APICallbackListener
import com.work.experion.interfaces.AppInterfaceAPI
import com.work.experion.model.CityModel
import com.work.experion.viewModel.FeatureListViewModel
import kotlinx.android.synthetic.main.fragment_feature_list.*
import kotlinx.android.synthetic.main.fragment_feature_list.view.*

/**
 * Shows all city features.
 *
 * This fragment lists all features of a city from API.
 *
 * @param MSG_INTERNET_FAILURE used for showing Internet alert message.
 * @property viewModel is the object of FeatureListViewModel class.
 */
class FeatureListFragment : Fragment() {

    companion object {
        const val MSG_INTERNET_FAILURE: String = "No Internet Connection !"
        fun newInstance(): FeatureListFragment = FeatureListFragment()
    }

    private lateinit var viewModel: FeatureListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_feature_list, container, false)
        /** Loading swipe_refresh_layout refreshing colors from resource */
        view.swipe_refresh_layout.setColorSchemeColors(
            context?.let {
                ContextCompat.getColor(
                    it,
                    R.color.swipeColor1
                )
            }!!,
            context?.let { ContextCompat.getColor(it, R.color.swipeColor2) }!!,
            context?.let { ContextCompat.getColor(it, R.color.swipeColor3) }!!
        )
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val featureDAO = FeatureDAO(requireActivity())
        val api = AppInterfaceAPI()
        val featureListRepository = FeatureListRepository(api, featureDAO)
        val featureListVMFactory = FeatureListVMFactory(featureListRepository)
        featureListVMFactory.let {
            viewModel = ViewModelProvider(this, it).get(FeatureListViewModel::class.java)
        }
        /** swipe_refresh_layout onRefresh listener to refresh the view */
        swipe_refresh_layout.setOnRefreshListener {
            if (isNetworkAvailable()) {
                viewModel.doGetLocationDetails(object : APICallbackListener {
                    override fun onResponseSuccess(response: CityModel) {
                        swipe_refresh_layout.isRefreshing = false
                        featureDAO.truncateAllTables()
                        val insert = featureDAO.insertCityDetailsToDB(response)
                        if (insert > 0)
                            featureDAO.insertFeatureDetails(response.rows)
                    }

                    /** Callback function of null response failure. Calls when features list is empty or null */
                    override fun onResponseFailure() {
                        swipe_refresh_layout.isRefreshing = false
                    }

                    /** Callback function of null response case */
                    override fun onResponseNull() {
                        swipe_refresh_layout.isRefreshing = false
                    }

                })
            } else {
                swipe_refresh_layout.isRefreshing = false
                /** Shows Internet failure toast */
                Toast.makeText(activity, MSG_INTERNET_FAILURE, Toast.LENGTH_SHORT)
                    .show()
            }
        }
        /** Calling ViewModel method to get data from DB. */
        viewModel.doGetDataFromDB()
        /** Observing data changes in title.
         * Set title in toolbar */
        viewModel.title?.observe(viewLifecycleOwner, Observer {
            val toolbar: Toolbar =
                requireActivity().findViewById(R.id.toolbar)
            toolbar.title = it
        })
        /** Observing data changes in features list.
         * pass features list to Adapter class */
        viewModel.featuresList?.observe(viewLifecycleOwner, Observer {
            feature_recycler_view.apply {
                setHasFixedSize(false)
                layoutManager = LinearLayoutManager(activity)
                adapter = FeaturesListAdapter(it)
            }
        })
    }

    /** Method to check whether the Internet connection is available or not.
     * @return true if Internet is available.
     */
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }


}