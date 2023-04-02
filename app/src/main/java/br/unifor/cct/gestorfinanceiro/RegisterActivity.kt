package br.unifor.cct.gestorfinanceiro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerName: EditText
    private lateinit var registerEmail: EditText
    private lateinit var registerPhone: EditText
    private lateinit var registerPassword: EditText
    private lateinit var registerSave: Button

    private lateinit var auth: FirebaseAuth
    val database = Firebase.database
    val myRef = database.getReference("users")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        registerName = findViewById(R.id.register_edittext_nome)
        registerEmail = findViewById(R.id.register_edittext_email)
        registerPhone = findViewById(R.id.register_edittext_phone)
        registerPassword = findViewById(R.id.register_edittext_password)
        registerSave = findViewById(R.id.register_button_signup)
        registerSave.setOnClickListener {

            var formOk = true

            if (registerName.text.isEmpty()) {
                registerName.error = "Preencha este campo"
                formOk = false
            }

            if (registerEmail.text.isEmpty()) {
                registerEmail.error = "Preencha este campo"
                formOk = false
            }

            if (registerPhone.text.isEmpty()) {
                registerPhone.error = "Preencha este campo"
                formOk = false
            }

            if (registerPassword.text.isEmpty()) {
                registerPassword.error = "Preencha este campo"
                formOk = false
            }

            if (formOk) {

                val name = registerName.text.toString()
                val email = registerEmail.text.toString()
                val phone = registerPhone.text.toString()
                val password = registerPassword.text.toString()

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user_id = auth.getCurrentUser()?.getUid();
                            user_id?.let { it1 ->   myRef.child(it1).child("nome").setValue(name)
                                                    myRef.child(it1).child("email").setValue(email)
                                                    myRef.child(it1).child("phone").setValue(phone) }
                            Toast.makeText(RegisterActivity@this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                            finish()

                        } else {
                            Toast.makeText(RegisterActivity@this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show()
                        }


                    }

            }
        }
    }

}