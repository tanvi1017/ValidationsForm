package com.tanvi.validationsform

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
     lateinit var cancel: Button
     lateinit var proceed: Button
    var etFirstName: EditText? = null
    var etLastName: EditText? = null
    lateinit var etEmail: EditText
    var etPassword: EditText? = null
    var isAllFieldsChecked = false
    var isAllValidEmail =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cancel = findViewById(R.id.btnCancel)
        proceed = findViewById(R.id.btnProceed)
        etFirstName = findViewById(R.id.firstName)
        etLastName = findViewById(R.id.LastName)
        etEmail = findViewById(R.id.email)
        etPassword = findViewById(R.id.Password)
          proceed.setOnClickListener(object : View.OnClickListener{
               override fun onClick(v: View?) {
                isAllFieldsChecked = CheckAllFields()
                isAllValidEmail=isValidEmail(etEmail.text.toString())
                if (isAllFieldsChecked && isAllValidEmail) {
                    val i = Intent(this@MainActivity, MainActivity2::class.java)
                    startActivity(i)
                }
            }
        })
        cancel.setOnClickListener(object : View.OnClickListener{
           override  fun onClick(v: View?) {
                finish()
                System.exit(0)
            }
        })
    }
    private fun isValidEmail(email: CharSequence): Boolean {
        var isValid = true
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        if (!matcher.matches()) {
            isValid = false
            Toast.makeText(this,"Email is Invalid",Toast.LENGTH_SHORT).show()
        }
        return isValid
    }

    private fun CheckAllFields(): Boolean {
        if (etFirstName!!.length() == 0) {
            etFirstName!!.error = "This field is required"
            return false
        }
        if (etLastName!!.length() == 0) {
           etLastName!!.error = "This field is required"
            return false
        }

        if (etEmail!!.length() == 0) {
            etEmail!!.error = "Email is required"
            return false
        }
        if (etPassword!!.length() == 0) {
            etPassword!!.error = "Password is required"
            return false
        } else if (etPassword!!.length() < 8) {
            etPassword!!.error = "Password must be minimum 8 characters"
            return false
        }
        return true
    }
}