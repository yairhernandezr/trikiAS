package com.example.triki1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView TxGanador;
    Integer[] botones;
    int[] tablero = new int[]{
            0, 0, 0,
            0, 0, 0,
            0, 0, 0
    };

    int estado = 0;
    int fichaPuesta = 0;
    int turno = -1;
    int[] posGanadora = new int[]{-1,-1,-1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TxGanador = (TextView) findViewById(R.id.TxGanador);
        TxGanador.setVisibility(View.INVISIBLE);

        botones = new Integer[]{
                R.id.Bt1, R.id.Bt2, R.id.Bt3,
                R.id.Bt4, R.id.Bt5, R.id.Bt6,
                R.id.Bt7, R.id.Bt8, R.id.Bt9,
        };
    }

    public void terminarPartida(){
        int fichaVictoria = R.drawable.circuloganador;
        if(estado == 1 || estado == -1){
            if(estado == 1){
                TxGanador.setVisibility(View.VISIBLE);
                TxGanador.setTextColor(Color.GREEN);
            }
            else{
                TxGanador.setVisibility(View.VISIBLE);
                TxGanador.setText("HAS PERDIDO");
                TxGanador.setTextColor(Color.GREEN);
                fichaVictoria = R.drawable.equisganador;
            }
            for(int i = 0; i < posGanadora.length; i++){
                Button b = findViewById(botones[posGanadora[i]]);
                b.setBackgroundResource(fichaVictoria);
            }
        }
        else if(estado ==2){
            TxGanador.setVisibility(View.VISIBLE);
            TxGanador.setText("HAS EMPATADO");
            TxGanador.setTextColor(Color.GREEN);
        }
    }


    public void ponerFicha(View v){
        if(estado == 0){
            turno = 1;
            int numBoton = Arrays.asList(botones).indexOf(v.getId());
            if(tablero[numBoton] == 0){
                v.setBackgroundResource(R.drawable.circulo);
                tablero[numBoton] = 1;
                fichaPuesta += 1;
                estado = comprobarEstado();
                terminarPartida();
                if(estado == 0){
                    automatico();
                    turno = -1;
                    fichaPuesta += 1;
                    estado = comprobarEstado();
                    terminarPartida();
                }
            }
        }
    }

    public void automatico(){
        Random ran = new Random();
        int pos = ran.nextInt(tablero.length);
        while(tablero[pos] != 0){
            pos = ran.nextInt(tablero.length);
        }
        Button b = (Button) findViewById(botones[pos]);
        b.setBackgroundResource(R.drawable.equis);
        tablero[pos] = -1;
    }

    public int comprobarEstado(){
        int nuevoEstado = 0;
        if(Math.abs(tablero[0]+tablero[1]+tablero[2]) == 3){
            posGanadora = new int[]{0,1,2};
            nuevoEstado = 1*turno;
        }
        else if(Math.abs(tablero[3]+tablero[4]+tablero[5]) == 3){
            posGanadora = new int[]{3,4,5};
            nuevoEstado = 1*turno;
        }
        else if(Math.abs(tablero[6]+tablero[7]+tablero[8]) == 3){
            posGanadora = new int[]{6,7,8};
            nuevoEstado = 1*turno;
        }
        else if(Math.abs(tablero[0]+tablero[3]+tablero[6]) == 3){
            posGanadora = new int[]{0,3,6};
            nuevoEstado = 1*turno;
        }
        else if(Math.abs(tablero[1]+tablero[4]+tablero[7]) == 3){
            posGanadora = new int[]{1,4,7};
            nuevoEstado = 1*turno;
        }
        else if(Math.abs(tablero[2]+tablero[5]+tablero[8]) == 3){
            posGanadora = new int[]{2,5,8};
            nuevoEstado = 1*turno;
        }
        else if(Math.abs(tablero[0]+tablero[4]+tablero[8]) == 3){
            posGanadora = new int[]{0,4,8};
            nuevoEstado = 1*turno;
        }
        else if(Math.abs(tablero[2]+tablero[4]+tablero[6]) == 3){
            posGanadora = new int[]{2,4,6};
            nuevoEstado = 1*turno;
        }
        else if(fichaPuesta == 9){
            nuevoEstado = 2;
        }
        return nuevoEstado;
    }
}