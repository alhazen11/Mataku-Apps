package com.apps.mataku.ui.list.pterigium

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import javax.inject.Inject
import android.support.v4.widget.SwipeRefreshLayout
import android.os.Handler
import com.apps.mataku.Apps
import com.apps.mataku.R
import com.apps.mataku.models.LogPterigium
import com.apps.mataku.ui.list.butawarna.ListPterigiumPresenter
import com.apps.mataku.utils.SQLiteHandler
import com.hmik_up.smarthima.ui.adapter.ListButaWarnaAdapter
import com.hmik_up.smarthima.ui.adapter.ListPterigiumAdapter
import kotlinx.android.synthetic.main.fragment_pterigium.*


class ListPterigiumFregment : Fragment(),ListPterigiumView {
    override fun showGetListPterigium(status: String?, error: Boolean, result: List<LogPterigium>?) {
        if(result!!.size != 0) {
            ui_list.visibility= View.VISIBLE
            ui_swipe.visibility= View.VISIBLE
            ui_kosong.visibility= View.GONE
            (ui_list.adapter as ListPterigiumAdapter).addListPterigium(result)
        }
    }
    fun kosong(){
        ui_list.visibility= View.GONE
        ui_swipe.visibility= View.GONE
        ui_kosong.visibility= View.VISIBLE
    }
    @Inject
    lateinit var presenter: ListPterigiumPresenter
    private var layoutManager = null;
    private lateinit var mContext: Context
    private var db: SQLiteHandler? = null
    private var idUser:String=""
    private lateinit var user: HashMap<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context?.applicationContext as Apps).createListComponent().inject(this)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater!!.inflate(R.layout.fragment_pterigium, container, false)

    companion object {
        fun newInstance(): ListPterigiumFregment = ListPterigiumFregment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        kosong()
        super.onViewCreated(view, savedInstanceState)
        mContext = activity!!.applicationContext
        db = SQLiteHandler(mContext)
        user= db!!.userDetails
        idUser= user.get("uid").toString()
        val layoutManager = LinearLayoutManager(context)
        ui_list.layoutManager = layoutManager
        ui_list.setHasFixedSize(true)
        ui_list.adapter = ListPterigiumAdapter(context)
        presenter.setViewListPterigium(this, createQuery(idUser))
        ui_swipe.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            ui_swipe.setRefreshing(true)

            Handler().postDelayed(Runnable {
                ui_swipe.setRefreshing(false)
                kosong()
                presenter.setViewListPterigium(this, createQuery(idUser))
            }, 1000)
        })


    }
    override fun onDestroyView() {
        super.onDestroyView()
        (context?.applicationContext as Apps).releaseMainComponent()
    }
    private fun createQuery(id_user:String): Map<String, String>{
        return hashMapOf(
            "id" to id_user
        )
    }
}