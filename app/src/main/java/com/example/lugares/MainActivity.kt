package com.example.lugares

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.lugares.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        binding.btLogin.setOnClickListener() {
            hacerlogin();
        }
        binding.btRegister.setOnClickListener() {
            hacerRegister();
        }

    }

    private fun hacerRegister() {

        var email = binding.etMail.text.toString()
        var clave = binding.etClave.text.toString()

        auth.createUserWithEmailAndPassword(email, clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("Creando usuario", "Usuario registrado")
                    val user = auth.currentUser
                    if (user != null) {
                        actualiza(user)
                    }
                } else {
                    Log.d("Creando usuario", "Creacion fallida")
                    Toast.makeText(baseContext, "Creacion fallida", Toast.LENGTH_LONG).show()
                    //actualiza(null)
                }

            }
    }

    private fun actualiza(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }
    }

    // autenticado no pida mas inicio de sesion a menos que se haga logout
    public override fun onStart() {
        super.onStart()
        val usuario = auth.currentUser
        actualiza(usuario)
    }

    private fun hacerlogin() {
        var email = binding.etMail.text.toString()
        var clave = binding.etClave.text.toString()

        auth.createUserWithEmailAndPassword(email, clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("Autenticando", "Usuario autenticado")
                    val user = auth.currentUser
                    if (user != null) {
                        actualiza(user)
                    }
                } else {
                    Log.d("Autenticando", "Autenticacion fallida")
                    Toast.makeText(baseContext, "Autenticacion fallida", Toast.LENGTH_LONG).show()
                    //actualiza(null)
                }

            }
    }
}











