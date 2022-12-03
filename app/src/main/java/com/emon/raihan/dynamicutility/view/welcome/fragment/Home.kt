package com.emon.raihan.dynamicutility.view.welcome.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.TextView
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.adaptar.MenuAdapter
import com.emon.raihan.dynamicutility.model.Menu
import com.emon.raihan.dynamicutility.view.*
import com.emon.raihan.dynamicutility.view.welcome.utility.*
import com.emon.raihan.dynamicutility.view.welcome.utility.InternetBillPayment
import com.emon.raihan.dynamicutility.view.welcome.mfs.MfsTransfer
import com.emon.raihan.dynamicutility.view.welcome.mobilerecharge.MobileRecharge
import java.util.ArrayList


class Home : Fragment() {
    private lateinit var menuList: ArrayList<Menu>
    private lateinit var menuGridView: GridView
    var adapter: MenuAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for requireActivity() fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        menuGridView = view.findViewById(R.id.menuGridView)
        menuList = ArrayList<Menu>()

        menuList.add(Menu("FTR", "Transfer", R.drawable.electricity_bill))
        menuList.add(Menu("UTI", "Utility", R.drawable.water_bill))
        menuList.add(Menu("TOP", "Recharge", R.drawable.gas_bill))
        menuList.add(Menu("MFS", "MFS", R.drawable.education_bill))
        menuList.add(Menu("ONPU", "Online Purchase", R.drawable.insurance_bill))
        menuList.add(Menu("TUF", "Tuition Fee", R.drawable.internet_bill))
        menuList.add(Menu("CAS", "Crad Services", R.drawable.entertainment_bill))
        menuList.add(Menu("PA", "Payoneer", R.drawable.invoice))
        menuList.add(Menu("INSP", "Insurance Pay", R.drawable.internet_bill))
        menuList.add(Menu("GOVF", "Govt. Fee", R.drawable.binimoy))
        menuList.add(Menu("ACS", "A/C Services", R.drawable.invoice))
        menuList.add(Menu("CALL", "Call Cenetr", R.drawable.ic_programmer))


        adapter = MenuAdapter(requireActivity(), menuList)
        menuGridView.adapter = adapter

        menuGridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val menu_soft_code =
                    view.findViewById<View>(R.id.menu_soft_code) as TextView
                val menu_name =
                    view.findViewById<View>(R.id.menu_name) as TextView

                when {
                    "FTR" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), ElectricityBillPayment::class.java)
                        startActivity(intent)
                    }
                    "UTI" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), MainActivity::class.java)
                        startActivity(intent)
                    }
                    "TOP" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), MobileRecharge::class.java)
                        startActivity(intent)
                    }
                    "MFS" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), MfsTransfer::class.java)
                        startActivity(intent)
                    }
                    "ONPU" == menu_soft_code.text.toString() -> {

                        val intent = Intent(requireActivity(), EductaionBillPayment::class.java)
                        startActivity(intent)
                    }
                    "TUF" == menu_soft_code.text.toString() -> {

                        val intent = Intent(requireActivity(), EductaionBillPayment::class.java)
                        startActivity(intent)
                    }
                    "CAS" == menu_soft_code.text.toString() -> {

                        val intent = Intent(requireActivity(), EductaionBillPayment::class.java)
                        startActivity(intent)
                    }
                    "PA" == menu_soft_code.text.toString() -> {

                        val intent = Intent(requireActivity(), EductaionBillPayment::class.java)
                        startActivity(intent)
                    }
                    "INSP" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), InsuranceBillPayment::class.java)
                        startActivity(intent)
                    }
                    "GOVF" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), InternetBillPayment::class.java)
                        startActivity(intent)
                    }
                    "ACS" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), EntertainmentBillPayment::class.java)
                        startActivity(intent)

                    }
                    "CALL" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), BillsReport::class.java)
                        startActivity(intent)

                    }

                }
            }









        return view
    }

}