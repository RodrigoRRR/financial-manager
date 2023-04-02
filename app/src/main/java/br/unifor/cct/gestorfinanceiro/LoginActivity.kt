package br.unifor.cct.gestorfinanceiro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var loginEmail:EditText
    private lateinit var loginPassword:EditText
    private lateinit var loginRegister:TextView
    private lateinit var loginSignIn:Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        loginEmail = findViewById(R.id.login_edittext_email)
        loginPassword = findViewById(R.id.login_edittext_password)

        loginRegister = findViewById(R.id.login_textview_register)
        loginRegister.setOnClickListener(this)

        loginSignIn = findViewById(R.id.login_button_signin)
        loginSignIn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.login_textview_register -> {
                val it = Intent(this, RegisterActivity::class.java)
                startActivity(it)
            }

            R.id.login_button_signin -> {

                var formOk = true

                if (loginEmail.text.isEmpty()) {
                    loginEmail.error = "Preencha este campo"
                    formOk = false
                }


                if (loginPassword.text.isEmpty()) {
                    loginPassword.error = "Preencha este campo"
                    formOk = false
                }

                if(formOk) {

                    val email = loginEmail.text.toString()
                    val password = loginPassword.text.toString()

                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this) { task ->
                                if(task.isSuccessful) {

                                    val it = Intent(this, MainActivity::class.java)
                                    startActivity(it)
                                    finish()
                                } else {

                                    Toast.makeText(this,
                                            "Email ou senha invalidos",
                                            Toast.LENGTH_SHORT)
                                            .show()
                                }
                            }
                }
            }
        }
    }
}