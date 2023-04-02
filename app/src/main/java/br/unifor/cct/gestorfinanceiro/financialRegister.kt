package br.unifor.cct.gestorfinanceiro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class financialRegister : AppCompatActivity() {

    private lateinit var financialName: EditText
    //private lateinit var financialType: Spinner
    private lateinit var financialValue: EditText
    private lateinit var confirmButton: Button

    private lateinit var auth: FirebaseAuth
    val database = Firebase.database
    val myRef = database.getReference("financy")


    val sdf = SimpleDateFormat("yyyy-M-dd hh:mm:ss")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_financial_register)

        auth = Firebase.auth

        val spinner: Spinner = findViewById(R.id.spinner_financial_type)

        ArrayAdapter.createFromResource(this, R.array.financial_type, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        financialName = findViewById(R.id.financial_register_name_edittext)
        //financialType = findViewById(R.id.spinner_financial_type)
        financialValue = findViewById(R.id.financial_register_value_edittext)
        confirmButton = findViewById(R.id.financial_register_confirm_button)
        confirmButton.setOnClickListener {

            val curDate = sdf.format(Date())
            val fName = financialName.text.toString()
            val fType = spinner.selectedItem.toString()
            val fValue = financialValue.text.toString()

            val user_id = auth.getCurrentUser()?.getUid();
            val send = Receita(curDate, fValue, fName)
            user_id?.let { it1 ->   myRef.child(it1 + "/" + fType + "/" + curDate).setValue(send) }

            Toast.makeText(RegisterActivity@this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show()
            finish()
        }


    }
}