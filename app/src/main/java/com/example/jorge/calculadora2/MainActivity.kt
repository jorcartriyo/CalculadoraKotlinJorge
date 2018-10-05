package com.example.jorge.calculadora2
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var anterior = ""//numero ya pulsado
    var resultado :Double = 0.0//resultado decimal
    var resultadoHex : Int = 0//resultado hexadecimal
    var signo = ""//signo que se pulsa
    var cambia = false//cuando pulso un signo para que empiece solo por un numero y acumule la cadena anterior
    var acumula = ""//acumula la cadena de números antes de borrarla de la pantalla
    var activo = false// dice si el punto se ha pulsado ya
    var memoria = 0.0;//almacena la memora de la decimal
    var memoriaHex = 0// almacena la memoria de la hexadecimal
    var esist = false;//cuando se ha pulsado un signo impide que se pulse el punto, ya que lo pone como activo
    var acumula2=0.0//variable intermedia para acumular una operación en decimal
    var acumula2Hex=0//variable intermedia para acumular una operación en hexadecimal
    var suma = false//variable para que al realizar operaciones y cambiar de signo me siga acumulando lo que hay
    var resta = false//variable para que al realizar operaciones y cambiar de signo me siga acumulando lo que hay
    var multipli = false//variable para que al realizar operaciones y cambiar de signo me siga acumulando lo que hay
    var divid= false//variable para que al realizar operaciones y cambiar de signo me siga acumulando lo que hay
    //pulsación de números
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun numero (v : View) {
        if (pantallaSigno.text.equals("="))signo=""
        val numero = findViewById<Button>(v.id)
        if (!cambia) {
            anterior = anterior + numero.text.toString()
            pantalla.text = anterior
            activo=false
        } else {
            anterior = ""
            pantalla.text= numero.text.toString()
            anterior = anterior + numero.text.toString()
            cambia = false
            activo=false
        }
        activo= false
    }
    fun cero (v : View) {
        if (!pantalla.text.substring(0).equals("0") && !cambia) {
            anterior = anterior + "0"
            pantalla.text = anterior
        }else {
            pantalla.setText("0")
            anterior="0"
        }
        activo=false
        esist=false
        cambia=false
    }
    //Pulsacion del punto
    fun punto (v : View) {
        if (!activo && !esist){
            anterior = anterior + "."
            pantalla.text = anterior
            activo = true
            esist = true
        }else pantalla.text = pantalla.text
        reinicia()
    }
    //Tecla de borrado
    fun ce (v : View){
        pantalla.text = "0"
        anterior = ""
        signo = ""
        pantallaSigno.text = ""
        acumula = ""
        anterior = ""
        esist = false
        activo = false
        reinicia()
    }
    fun pantalla(){
        if (acumula.toString().substring(acumula.toString().length-2,acumula.toString().length).equals(".0")){
            pantalla.setText(acumula.toString().substring(0,acumula.toString().length-2));
        };else pantalla.setText(acumula)
    }

    //funcion que me pone todos los valores a falso para que no me de error
    fun reinicia(){
        suma=false
        resta=false
        multipli=false
        divid=false
    }
    //resta en decimal
    fun resta(v : View){
        if (!suma && !multipli && !divid) {
            if (acumula.equals("")) {
                acumula = pantalla.text.toString()
                acumula2 = 0.0
            } else {
                acumula2 = acumula.toDouble() - pantalla.text.toString().toDouble()
                acumula = acumula2.toString()
                pantalla()
                acumula2 = 0.0
            }
        }else{
            if(suma){
                acumula2 = acumula.toDouble() + pantalla.text.toString().toDouble()
                acumula = acumula2.toString()
                pantalla()
                acumula2 = 0.0
            }
            if(multipli){
                acumula2 = acumula.toDouble() * pantalla.text.toString().toDouble()
                acumula = acumula2.toString()
                pantalla()
                acumula2 = 0.0
            }
            if(divid){
                acumula2 = acumula.toDouble() / pantalla.text.toString().toDouble()
                acumula = acumula2.toString()
                pantalla()
                acumula2 = 0.0
            }
        }
        pantallaSigno.setText("-")
        signo = "-"
        cambia = true
        esist = false
        activo=true
        suma=false
        resta=true
        multipli=false
        divid=false
    }
    //tengo funciones diferentes para decimal y hexadecimal, porque en decimal trabajo con dobles y en hexadecimal con enteros
    //resta en hexadecimal
    fun restaHex(v : View){
        if(pantalla.text.equals("Error /0")) pantalla.setText("0")
        else if (!suma && !multipli && !divid) {
            if (acumula.equals("")) {
                acumula = pantalla.text.toString()
                acumula2Hex=0
            } else {
                acumula2Hex = acumula.toInt(16) - pantalla.text.toString().toInt(16)
                acumula = acumula2Hex.toString()
                pantalla.setText(acumula.toInt().toString(16).toUpperCase());
                acumula2Hex=0
            }
        }else{
            if(suma){
                acumula2Hex = acumula.toInt(16) + pantalla.text.toString().toInt(16)
                acumula = acumula2Hex.toString()
                pantalla.setText(acumula.toInt().toString(16).toUpperCase());
                acumula2Hex=0
            }
            if(multipli){
                acumula2 = acumula.toDouble() * pantalla.text.toString().toDouble()
                acumula = acumula2Hex.toString()
                pantalla.setText(acumula.toInt().toString(16).toUpperCase());
                acumula2Hex=0
            }
            if(divid){
                acumula2 = acumula.toDouble() / pantalla.text.toString().toDouble()
                acumula = acumula2Hex.toString()
                pantalla.setText(acumula.toInt().toString(16).toUpperCase());
                acumula2Hex=0
            }
        }
        pantallaSigno.setText("-")
        signo = "-"
        cambia = true
        esist = false
        activo=true
        suma=false
        resta=true
        multipli=false
        divid=false
    }
    //suma decimal
    fun suma (v : View){
        if (!resta && !multipli && !divid) {
            if (acumula.equals("")) {
                acumula = pantalla.text.toString()
                acumula2 = 0.0
            } else {
                acumula2 = acumula.toDouble() + pantalla.text.toString().toDouble()
                acumula = acumula2.toString()
                pantalla()
                acumula2 = 0.0
            }
        }else{
            if(resta){
                acumula2 = acumula.toDouble() - pantalla.text.toString().toDouble()
                acumula = acumula2.toString()
                pantalla()
                acumula2 = 0.0
            }
            if(multipli){
                acumula2 = acumula.toDouble() * pantalla.text.toString().toDouble()
                acumula = acumula2.toString()
                pantalla()
                acumula2 = 0.0
            }
            if(divid){
                acumula2 = acumula.toDouble() / pantalla.text.toString().toDouble()
                acumula = acumula2.toString()
                pantalla()
                acumula2 = 0.0
            }
        }
        pantallaSigno.setText("+")
        signo = "+"
        cambia = true
        esist = false
        activo=true
        suma=true
        resta=false
        multipli=false
        divid=false
    }
    //suma hexadecimal
    fun sumaHex(v : View){
        if(pantalla.text.equals("Error /0")) pantalla.setText("0")
        else if (!resta && !multipli && !divid) {
            if (acumula.equals("")) {
                acumula = pantalla.text.toString()
                acumula2Hex=0
            } else {
                acumula2Hex = acumula.toInt(16) + pantalla.text.toString().toInt(16)
                acumula = acumula2Hex.toString()
                pantalla.setText(acumula.toInt().toString(16).toUpperCase());
                acumula2Hex=0
            }
        }else{
            if(resta){
                acumula2Hex = acumula.toInt(16) - pantalla.text.toString().toInt(16)
                acumula = acumula2Hex.toString()
                pantalla.setText(acumula.toInt().toString(16).toUpperCase());
                acumula2Hex=0
            }
            if(multipli){
                acumula2 = acumula.toDouble() * pantalla.text.toString().toDouble()
                acumula = acumula2Hex.toString()
                pantalla.setText(acumula.toInt().toString(16).toUpperCase());
                acumula2Hex=0
            }
            if(divid){
                acumula2 = acumula.toDouble() / pantalla.text.toString().toDouble()
                acumula = acumula2Hex.toString()
                pantalla.setText(acumula.toInt().toString(16).toUpperCase());
                acumula2Hex=0
            }
        }
        pantallaSigno.setText("+")
        signo = "+"
        cambia = true
        esist = false
        activo=true
        suma=true
        resta=false
        multipli=false
        divid=false
    }

    fun multiplica (v : View){
        if (!resta && !suma && !divid) {
            if (acumula.equals("")) {
                acumula = pantalla.text.toString()
                acumula2 = 0.0
            } else {
                acumula2 = acumula.toDouble() * pantalla.text.toString().toDouble()
                acumula = acumula2.toString()
                pantalla()
                acumula2 = 0.0
            }
        }else{
            if(resta){
                acumula2 = acumula.toDouble() - pantalla.text.toString().toDouble()
                acumula = acumula2.toString()
                pantalla()
                acumula2 = 0.0
            }
            if(suma){
                acumula2 = acumula.toDouble() + pantalla.text.toString().toDouble()
                acumula = acumula2.toString()
                pantalla()
                acumula2 = 0.0
            }
            if(divid){
                acumula2 = acumula.toDouble() / pantalla.text.toString().toDouble()
                acumula = acumula2.toString()
                pantalla()
                acumula2 = 0.0
            }
        }
        pantallaSigno.setText("x")
        signo = "*"
        cambia = true
        esist = false
        activo=true
        suma=false
        resta=false
        multipli=true
        divid=false
    }
    fun multiplicaHex(v : View){
        if(pantalla.text.equals("Error /0")) pantalla.setText("0")
        else if (!suma && !resta && !divid) {
            if (acumula.equals("")) {
                acumula = pantalla.text.toString()
                acumula2Hex=0
            } else {
                acumula2Hex = acumula.toInt(16) * pantalla.text.toString().toInt(16)
                acumula = acumula2Hex.toString()
                pantalla.setText(acumula.toInt().toString(16).toUpperCase());
                acumula2Hex=0
            }
        }else{
            if(suma){
                acumula2Hex = acumula.toInt(16) + pantalla.text.toString().toInt(16)
                acumula = acumula2Hex.toString()
                pantalla.setText(acumula.toInt().toString(16).toUpperCase());
                acumula2Hex=0
            }
            if(resta){
                acumula2 = acumula.toDouble() - pantalla.text.toString().toDouble()
                acumula = acumula2Hex.toString()
                pantalla.setText(acumula.toInt().toString(16).toUpperCase());
                acumula2Hex=0
            }
            if(divid){
                acumula2 = acumula.toDouble() / pantalla.text.toString().toDouble()
                acumula = acumula2Hex.toString()
                pantalla.setText(acumula.toInt().toString(16).toUpperCase());
                acumula2Hex=0
            }
        }
        pantallaSigno.setText("x")
        signo = "*"
        cambia = true
        esist = false
        activo=true
        suma=false
        resta=false
        multipli=true
        divid=false
    }
    fun divide (v : View){
        if (!resta && !multipli && !suma) {
            if (acumula.equals("")) {
                acumula = pantalla.text.toString()
                acumula2 = 0.0
            } else {
                acumula2 = acumula.toDouble() / pantalla.text.toString().toDouble()
                acumula = acumula2.toString()
                pantalla()
                acumula2 = 0.0
            }
        }else{
            if(resta){
                acumula2 = acumula.toDouble() - pantalla.text.toString().toDouble()
                acumula = acumula2.toString()
                pantalla()
                acumula2 = 0.0
            }
            if(multipli){
                acumula2 = acumula.toDouble() * pantalla.text.toString().toDouble()
                acumula = acumula2.toString()
                pantalla()
                acumula2 = 0.0
            }
            if(suma){
                acumula2 = acumula.toDouble() + pantalla.text.toString().toDouble()
                acumula = acumula2.toString()
                pantalla()
                acumula2 = 0.0
            }
        }
        pantallaSigno.setText("÷")
        signo = "/"
        cambia = true
        esist = false
        activo=true
        suma=false
        resta=false
        multipli=false
        divid=true
    }
    fun divideHex(v : View){
        if(pantalla.text.equals("Error /0")) pantalla.setText("0")
        else if (!resta && !multipli && !suma) {
            if (acumula.equals("")) {
                acumula = pantalla.text.toString()
                acumula2Hex=0
            } else {
                if (!pantalla.text.equals("0")&& !pantalla.text.equals("Error /0")) {
                    acumula2Hex = acumula.toInt(16) / pantalla.text.toString().toInt(16)
                    acumula = acumula2Hex.toString()
                    pantalla.setText(acumula.toInt().toString(16).toUpperCase());
                    acumula2Hex = 0
                }else pantalla.setText("Error /0")
            }
        }else{
            if(resta){
                acumula2Hex = acumula.toInt(16) - pantalla.text.toString().toInt(16)
                acumula = acumula2Hex.toString()
                pantalla.setText(acumula.toInt().toString(16).toUpperCase());
                acumula2Hex=0
            }
            if(multipli){
                acumula2 = acumula.toDouble() * pantalla.text.toString().toDouble()
                acumula = acumula2Hex.toString()
                pantalla.setText(acumula.toInt().toString(16).toUpperCase());
                acumula2Hex=0
            }
            if(suma){
                acumula2 = acumula.toDouble() + pantalla.text.toString().toDouble()
                acumula = acumula2Hex.toString()
                pantalla.setText(acumula.toInt().toString(16).toUpperCase());
                acumula2Hex=0
            }
        }
        pantallaSigno.setText("÷")
        signo = "/"
        cambia = true
        esist = false
        activo=true
        suma=false
        resta=false
        multipli=false
        divid=true
    }
    //funcion que cambia de signo lo que hay en pantalla
    fun masMenos(v : View){
        if(!pantalla.text.equals("0")) {
           var tex = pantalla.text.toString().toDouble() * (-1)
            if (tex.toString().substring(tex.toString().length - 2, tex.toString().length).equals(".0")) {
                pantalla.setText(tex.toString().substring(0, tex.toString().length - 2))
            }; else pantalla.setText(tex.toString());
        }else pantalla.setText(pantalla.text.toString());
        reinicia()
    }
       //funcion que convierte lo que hay en la pantalla de la calculadora hexadecimal a decimal
    fun Dec(v : View){
            var convierte = java.lang.Long.parseLong(pantalla.text.toString(), 16)
            pantalla.setText(convierte.toString())
            reinicia()
    }
    fun memoriaRet(v:View){
        if (memoria.toString().substring(memoria.toString().length-2,memoria.toString().length).equals(".0")){
            pantalla.setText(memoria.toString().substring(0,memoria.toString().length-2))
        };else pantalla.setText(memoria.toString());
        pantallaSigno.setText("MR")
        reinicia()
    }
    fun memoriaRetHex(v:View){
        pantalla.setText(memoriaHex.toString(16).toString().toUpperCase())
        pantallaSigno.setText("MR")
        reinicia()
    }
    fun memoriaCl(v:View){
        memoria = 0.0
        pantallaSigno.setText("MC")
        reinicia()
    }
    fun memoriaClHex(v:View){
        memoriaHex = 0
        pantallaSigno.setText("MC")
        reinicia()
    }
    fun memoriaMas(v:View){
        memoria = pantalla.text.toString().toDouble()+ memoria
        cambia = true
        pantallaSigno.setText("M+")
        reinicia()
    }
    fun memoriaMasHex(v:View){
        memoriaHex = pantalla.text.toString().toInt(16) + memoriaHex
        cambia = true
        pantallaSigno.setText("M+")
        reinicia()
    }
    fun calcula(v : View){
        if ( acumula != "") {
            when (signo) {
                "+" -> {
                    resultado = acumula.toDouble() + pantalla.text.toString().toDouble()
                    if (resultado.toString().substring(resultado.toString().length-2,resultado.toString().length).equals(".0")){
                        pantalla.setText(resultado.toString().substring(0,resultado.toString().length-2));
                    };else pantalla.setText(resultado.toString());
                    acumula=""
                    anterior = ""
                    pantallaSigno.setText("=")
                }
                "-" -> {
                    resultado = acumula.toDouble() - pantalla.text.toString().toDouble()
                    if (resultado.toString().substring(resultado.toString().length-2,resultado.toString().length).equals(".0")){
                        pantalla.setText(resultado.toString().substring(0,resultado.toString().length-2));
                    };else pantalla.setText(resultado.toString())
                    acumula=""
                    anterior = ""
                    pantallaSigno.setText("=")
                }
                "*" -> {
                    resultado = acumula.toDouble() * pantalla.text.toString().toDouble()
                    if (resultado.toString().substring(resultado.toString().length-2,resultado.toString().length).equals(".0")){
                        pantalla.setText(resultado.toString().substring(0,resultado.toString().length-2));
                    };else pantalla.setText(resultado.toString())
                    acumula=""
                    anterior = ""
                    pantallaSigno.setText("=")
                }
                "/" -> {
                    resultado = acumula.toDouble() / pantalla.text.toString().toDouble()
                    if (resultado.toString().substring(resultado.toString().length-2,resultado.toString().length).equals(".0")){
                        pantalla.setText(resultado.toString().substring(0,resultado.toString().length-2));
                    };else pantalla.setText(resultado.toString())
                    acumula=""
                    anterior = ""
                    pantallaSigno.setText("=")
                }
                else -> {
                    pantalla.setText(resultado.toString());
                    acumula=""
                    anterior = ""
                }
            }
        }else pantalla.setText("0")
        esist = false
        reinicia()
    }
    fun calculaHex(v : View){
        if ( acumula != "" && !pantalla.text.equals("Error /0")) {
            when (signo) {
                "+" -> {
                    resultadoHex = acumula.toInt(16) + pantalla.text.toString().toInt(16)
                    pantalla.setText(resultadoHex.toString(16).toUpperCase());
                    acumula=""
                    anterior = ""
                    pantallaSigno.setText("=")
                }
                "-" -> {
                    resultadoHex = acumula.toInt(16) - pantalla.text.toString().toInt(16)
                    pantalla.setText(resultadoHex.toString(16).toUpperCase());
                    acumula=""
                    anterior = ""
                    pantallaSigno.setText("=")
                }
                "*" -> {
                    resultadoHex = acumula.toInt(16) * pantalla.text.toString().toInt(16)
                    pantalla.setText(resultadoHex.toString(16).toUpperCase());
                    acumula=""
                    anterior = ""
                    pantallaSigno.setText("=")
                }

                "/" -> {
                    if (!pantalla.text.equals("0") && !pantalla.text.equals("Error /0")) {
                        resultadoHex = acumula.toInt(16) / pantalla.text.toString().toInt(16)
                        pantalla.setText(resultadoHex.toString(16).toUpperCase());
                        acumula = ""
                        anterior = ""
                        pantallaSigno.setText("=")
                    }else pantalla.setText("Error /0")
                }
                else -> {
                    pantalla.setText(resultadoHex.toString(16).toUpperCase());
                    acumula=""
                    anterior = ""
                    esist = false
                }
            }
        }else pantalla.setText("0")
        esist = false
        reinicia()
    }
}
