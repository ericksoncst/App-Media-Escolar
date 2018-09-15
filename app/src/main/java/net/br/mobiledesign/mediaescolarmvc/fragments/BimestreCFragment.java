package net.br.mobiledesign.mediaescolarmvc.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.br.mobiledesign.mediaescolarmvc.R;
import net.br.mobiledesign.mediaescolarmvc.controller.MediaEscolarController;
import net.br.mobiledesign.mediaescolarmvc.model.MediaEscolar;
import net.br.mobiledesign.mediaescolarmvc.view.MainActivity;


public class BimestreCFragment extends Fragment {

    MediaEscolar mediaEscolar;
    MediaEscolarController controller;

    Button btnCalcular;
    EditText etMateria, etNotaProva, etNotaTrabalho, etResultado, etSituacao;

    double notaProva, notaTrabalho, media;

    boolean dadosValidados = true;

    Context context;
    View view;

    public BimestreCFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bimestre_c, container, false);


        etMateria = view.findViewById(R.id.etMateria);
        etNotaProva = view.findViewById(R.id.etNotaProva);
        etNotaTrabalho = view.findViewById(R.id.etNotaTrabalho);
        etResultado = view.findViewById(R.id.etMedia);
        etSituacao = view.findViewById(R.id.etSituacao);
        btnCalcular = view.findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /*Try/Catch para que o erro não pare o App
                (Na falta de campo digitado) e também é uma forma de validação. */
                try {

                    if (etNotaProva.getText().toString().length() > 0) {
                        notaProva = Double.parseDouble(etNotaProva.getText().toString());

                        if (notaProva > 10) {
                            dadosValidados = false;
                            Toast.makeText(context, "Nota inválida!", Toast.LENGTH_SHORT).show();
                            etNotaProva.requestFocus();

                        } else {
                            dadosValidados = true;
                        }

                    } else {
                        etNotaProva.setError("*");
                        etNotaProva.requestFocus();
                        dadosValidados = false;
                    }

                    if (etNotaTrabalho.getText().toString().length() > 0) {
                        notaTrabalho = Double.parseDouble(etNotaTrabalho.getText().toString());


                        if (notaTrabalho > 10) {
                            dadosValidados = false;
                            Toast.makeText(context, "Nota inválida!", Toast.LENGTH_SHORT).show();
                            etNotaTrabalho.requestFocus();

                        } else {
                            dadosValidados = true;
                        }

                    } else {
                        etNotaTrabalho.setError("*");
                        etNotaTrabalho.requestFocus();
                        dadosValidados = false;
                    }

                    if (etMateria.getText().toString().length() == 0) {
                        etMateria.setError("*");
                        etMateria.requestFocus();
                        dadosValidados = false;
                    }

                    // Após Validação
                    if (dadosValidados) {

                        mediaEscolar = new MediaEscolar();
                        controller = new MediaEscolarController(context);

                        mediaEscolar.setMateria(etMateria.getText().toString());
                        mediaEscolar.setNotaProva(Double.parseDouble(etNotaProva.getText().toString()));
                        mediaEscolar.setNotaTrabalho(Double.parseDouble(etNotaTrabalho.getText().toString()));
                        mediaEscolar.setBimestre("3º Bimestre");

                        media = controller.calcularMedia(mediaEscolar);

                        mediaEscolar.setMediaFinal(media);

                        mediaEscolar.setSituacao(controller.resultadoFinal(media));

                        etResultado.setText(MainActivity.formatarValorDecimal(media));

                        etSituacao.setText(mediaEscolar.getSituacao());

                        etNotaProva.setText(MainActivity.formatarValorDecimal(notaProva));
                        etNotaTrabalho.setText(MainActivity.formatarValorDecimal(notaTrabalho));

                        //salvarSharedPreferences(); //CHAMEI METODO APENAS APÓS VALIDAÇÃO

                        if(controller.salvar(mediaEscolar)){
                            // obj salvo com sucesso no DB
                            Toast.makeText(context,"Dados Salvos com Sucesso...",Toast.LENGTH_LONG).show();
                        }else{
                            // falha ao salvar o obj  no DB
                            Toast.makeText(context,"Falha ao salvar os dados do DB...",Toast.LENGTH_LONG).show();
                        }

                    }

                } catch (Exception e) {

                    Toast.makeText(context, "Informe as notas...", Toast.LENGTH_LONG).show();

                }




            }
        });

        return view;
    }


}
