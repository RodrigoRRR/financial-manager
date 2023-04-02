package br.unifor.cct.gestorfinanceiro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var registerFinancial:Button
    private lateinit var graphButton:Button
    private lateinit var receita1View:TextView
    private lateinit var receita2View:TextView
    private lateinit var receita3View:TextView
    private lateinit var receita4View:TextView
    private lateinit var receita5View:TextView
    private lateinit var despesa1View:TextView
    private lateinit var despesa2View:TextView
    private lateinit var despesa3View:TextView
    private lateinit var despesa4View:TextView
    private lateinit var despesa5View:TextView

    lateinit var receitaRef: DatabaseReference
    lateinit var despesaRef: DatabaseReference

    private lateinit var auth: FirebaseAuth
    val database = Firebase.database


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        val user_id = auth.getCurrentUser()?.getUid();
        user_id?.let { it1 -> receitaRef = FirebaseDatabase.getInstance().getReference("financy").child(it1).child("Receita")
                              despesaRef = FirebaseDatabase.getInstance().getReference("financy").child(it1).child("Despesa")}

        receita1View = findViewById(R.id.textview_receita1)
        receita2View = findViewById(R.id.textview_receita2)
        receita3View = findViewById(R.id.textview_receita3)
        receita4View = findViewById(R.id.textview_receita4)
        receita5View = findViewById(R.id.textview_receita5)
        despesa1View = findViewById(R.id.textview_despesa1)
        despesa2View = findViewById(R.id.textview_despesa2)
        despesa3View = findViewById(R.id.textview_despesa3)
        despesa4View = findViewById(R.id.textview_despesa4)
        despesa5View = findViewById(R.id.textview_despesa5)
        registerFinancial = findViewById(R.id.main_cadastrar_button)
        registerFinancial.setOnClickListener(this)
        graphButton = findViewById(R.id.main_graph_button)
        graphButton.setOnClickListener(this)

        var receitas = arrayListOf<Receita>()
        var lenght = 0

        receitaRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                receitas = arrayListOf<Receita>()
                for (receitaSnapshot in dataSnapshot.children) {
                    val receita = receitaSnapshot.getValue(Receita::class.java)
                    receitas.add(receita!!)
                }
                lenght = receitas.size
                if (lenght >= 1)
                    receita1View.text = "Nome: " + receitas[lenght - 1].financial_name + " | " + "Valor: " + receitas[lenght - 1].value + " | " + "Dia: " + receitas[lenght - 1].data
                if (lenght >= 2)
                    receita2View.text = "Nome: " + receitas[lenght - 2].financial_name + " | " + "Valor: " + receitas[lenght - 2].value + " | " + "Dia: " + receitas[lenght - 2].data
                if (lenght >= 3)
                    receita3View.text = "Nome: " + receitas[lenght - 3].financial_name + " | " + "Valor: " + receitas[lenght - 3].value + " | " + "Dia: " + receitas[lenght - 3].data
                if (lenght >= 4)
                    receita4View.text = "Nome: " + receitas[lenght - 4].financial_name + " | " + "Valor: " + receitas[lenght - 4].value + " | " + "Dia: " + receitas[lenght - 4].data
                if (lenght >= 5)
                    receita5View.text = "Nome: " + receitas[lenght - 5].financial_name + " | " + "Valor: " + receitas[lenght - 5].value + " | " + "Dia: " + receitas[lenght - 5].data
                //receitasView.text = teste
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        var despesas = arrayListOf<Receita>()

        despesaRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                despesas = arrayListOf<Receita>()
                for (receitaSnapshot in dataSnapshot.children) {
                    val despesa = receitaSnapshot.getValue(Receita::class.java)
                    despesas.add(despesa!!)
                }
                lenght = despesas.size
                if (lenght >= 1)
                    despesa1View.text = "Nome: " + despesas[lenght - 1].financial_name + " | " + "Valor: " + despesas[lenght - 1].value + " | " + "Dia: " + despesas[lenght - 1].data
                if (lenght >= 2)
                    despesa2View.text = "Nome: " + despesas[lenght - 2].financial_name + " | " + "Valor: " + despesas[lenght - 2].value + " | " + "Dia: " + despesas[lenght - 2].data
                if (lenght >= 3)
                    despesa3View.text = "Nome: " + despesas[lenght - 3].financial_name + " | " + "Valor: " + despesas[lenght - 3].value + " | " + "Dia: " + despesas[lenght - 3].data
                if (lenght >= 4)
                    despesa4View.text = "Nome: " + despesas[lenght - 4].financial_name + " | " + "Valor: " + despesas[lenght - 4].value + " | " + "Dia: " + despesas[lenght - 4].data
                if (lenght >= 5)
                    despesa5View.text = "Nome: " + despesas[lenght - 5].financial_name + " | " + "Valor: " + despesas[lenght - 5].value + " | " + "Dia: " + despesas[lenght - 5].data
                //receitasView.text = teste
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.main_cadastrar_button -> {
                val it = Intent(this, financialRegister::class.java)
                startActivity(it)
            }
        }

        when (v?.id) {
            R.id.main_graph_button -> {
                val it = Intent(this, Graph::class.java)
                startActivity(it)
            }
        }
    }
}