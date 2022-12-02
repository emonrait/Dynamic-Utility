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
import com.emon.raihan.dynamicutility.view.binimoy.BinimoyDashboard
import com.emon.raihan.dynamicutility.view.loan.LoanApplication
import com.emon.raihan.dynamicutility.view.loan.LoanDashboard
import com.emon.raihan.dynamicutility.view.loan.LoanResult
import com.emon.raihan.dynamicutility.view.welcome.Welcome
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

        menuList.add(Menu("EBP", "Transfer", R.drawable.electricity_bill))
        menuList.add(Menu("WASA", "Utility", R.drawable.water_bill))
        menuList.add(Menu("GAS", "Recharge", R.drawable.gas_bill))
        menuList.add(Menu("EDU", "MFS", R.drawable.education_bill))
        menuList.add(Menu("INS", "Online Purchase", R.drawable.insurance_bill))
        menuList.add(Menu("IN", "Tuition Fee", R.drawable.internet_bill))
        menuList.add(Menu("EN", "Crad Services", R.drawable.entertainment_bill))
        menuList.add(Menu("LD", "Payoneer", R.drawable.invoice))
        menuList.add(Menu("W", "Insurance Pay", R.drawable.internet_bill))
        menuList.add(Menu("BINIMOY", "Govt. Fee", R.drawable.binimoy))
        menuList.add(Menu("BR", "A/C Services", R.drawable.invoice))
        menuList.add(Menu("AM", "Call Cenetr", R.drawable.ic_programmer))


        adapter = MenuAdapter(requireActivity(), menuList)
        menuGridView.adapter = adapter

        menuGridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val menu_soft_code =
                    view.findViewById<View>(R.id.menu_soft_code) as TextView
                val menu_name =
                    view.findViewById<View>(R.id.menu_name) as TextView

                when {
                    "EBP" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), ElectricityBillPayment::class.java)
                        startActivity(intent)
                    }
                    "WASA" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), WASABillPayment::class.java)
                        startActivity(intent)
                    }
                    "GAS" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), GASBillPayment::class.java)
                        startActivity(intent)
                    }
                    "AM" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), AboutMe::class.java)
                        startActivity(intent)
                    }
                    "EDU" == menu_soft_code.text.toString() -> {

                        val intent = Intent(requireActivity(), EductaionBillPayment::class.java)
                        startActivity(intent)
                    }
                    "INS" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), InsuranceBillPayment::class.java)
                        startActivity(intent)
                    }
                    "IN" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), InternetBillPayment::class.java)
                        startActivity(intent)
                    }
                    "EN" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), EntertainmentBillPayment::class.java)
                        startActivity(intent)

                    }
                    "BR" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), BillsReport::class.java)
                        startActivity(intent)

                    }
                    "LA" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), LoanApplication::class.java)
                        startActivity(intent)

                    }
                    "LD" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), LoanDashboard::class.java)
                        startActivity(intent)

                    }
                    "LR" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), LoanResult::class.java)
                        startActivity(intent)

                    }
                    "BINIMOY" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), BinimoyDashboard::class.java)
                        startActivity(intent)

                    }
                    "W" == menu_soft_code.text.toString() -> {
                        val intent = Intent(requireActivity(), Welcome::class.java)
                        startActivity(intent)

                    }

                }
            }









        return view
    }

}