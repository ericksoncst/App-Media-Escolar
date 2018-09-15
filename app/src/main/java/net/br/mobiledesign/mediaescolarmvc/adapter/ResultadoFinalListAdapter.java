package net.br.mobiledesign.mediaescolarmvc.adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.br.mobiledesign.mediaescolarmvc.R;
import net.br.mobiledesign.mediaescolarmvc.controller.MediaEscolarController;
import net.br.mobiledesign.mediaescolarmvc.model.MediaEscolar;

import java.util.ArrayList;

//Herdar Array Adapter e implementar onclick
public class ResultadoFinalListAdapter extends ArrayAdapter<MediaEscolar> implements View.OnClickListener{

    Context context;

    private int ultimaPosicao = -1;

    AlertDialog.Builder builder;
    AlertDialog alert;

    // Objetos e coleções (dataSet)
    ArrayList<MediaEscolar> dados;
    MediaEscolar mediaEscolar;
    MediaEscolarController controller;

    private static class ViewHolder{

        TextView txtBimestre;
        TextView txtMateria;
        TextView txtSituacao;
        ImageView imgConsultar;
        ImageView imgDeletar;
        ImageView imgSalvar;
        ImageView imgEditar;
        ImageView imgLogo;

    }

    //TODO: verificar cast dataSet
    public ResultadoFinalListAdapter(ArrayList<MediaEscolar> dataSet, Context context) {
        super(context, R.layout.lisview_resultado_final, dataSet);

        this.dados = dataSet;

        this.context = context;
    }

    public void atualizarLista(ArrayList<MediaEscolar> novosDados){

        this.dados.clear();
        this.dados.addAll(novosDados);

        notifyDataSetChanged();
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

    @Override
    public void onClick(View view) {

        int posicao = (Integer) view.getTag();

        Object object = getItem(posicao);

        mediaEscolar = (MediaEscolar) object;

        controller = new MediaEscolarController(getContext());

        switch (view.getId()) {

            case R.id.imgLogo:

                // Aprensentar os dados detalhados

                    Snackbar.make(view, "Nota da Prova " + mediaEscolar.getNotaProva(),
                            Snackbar.LENGTH_LONG)
                            .setAction("No action", null).show();

                break;

            case R.id.imgDeletar:

                builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Alerta");
                builder.setMessage("Deseja DELETAR este registro?");
                builder.setCancelable(true);
                builder.setIcon(R.mipmap.ic_launcher);

                builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        controller.deletar(mediaEscolar);

                        notifyDataSetChanged();

                    }
                });

                builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }
                });

                alert = builder.create();
                alert.show();

                break;

            case R.id.imgEditar:

                builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Alerta");
                builder.setMessage("Deseja EDITAR este registro?");
                builder.setCancelable(true);
                builder.setIcon(R.mipmap.ic_launcher);

                builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });

                builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }
                });

                alert = builder.create();
                alert.show();

                break;

            case R.id.imgConsultar:

                builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Alerta");
                builder.setMessage("Deseja CONSULTAR este registro?");
                builder.setCancelable(true);
                builder.setIcon(R.mipmap.ic_launcher);

                builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });

                builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }
                });

                alert = builder.create();
                alert.show();

                break;

            case R.id.imgSalvar:

                builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Alerta");
                builder.setMessage("Deseja SALVAR este registro?");
                builder.setCancelable(true);
                builder.setIcon(R.mipmap.ic_launcher);

                builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });

                builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }
                });

                alert = builder.create();
                alert.show();

                break;
        }
    }

    @Override
    public View getView(int position,
                        View dataSet,
                        @NonNull ViewGroup parent) {

        mediaEscolar = getItem(position);

        ViewHolder linha;

        if (dataSet == null) {

            linha = new ViewHolder();

            LayoutInflater layoutResultadoFinalList = LayoutInflater.from(getContext());

            dataSet = layoutResultadoFinalList.inflate(R.layout.lisview_resultado_final,
                    parent,
                    false);

            linha.txtMateria = dataSet.findViewById(R.id.txtMateria);
            linha.txtBimestre = dataSet.findViewById(R.id.txtBimestre);
            linha.txtSituacao = dataSet.findViewById(R.id.txtResultado);
            linha.imgLogo = dataSet.findViewById(R.id.imgLogo);
            linha.imgConsultar = dataSet.findViewById(R.id.imgConsultar);
            linha.imgDeletar = dataSet.findViewById(R.id.imgDeletar);
            linha.imgEditar = dataSet.findViewById(R.id.imgEditar);
            linha.imgSalvar = dataSet.findViewById(R.id.imgSalvar);

            dataSet.setTag(linha);


        }else {

            linha = (ViewHolder) dataSet.getTag();

        }

        linha.txtMateria.setText(mediaEscolar.getMateria());
        linha.txtBimestre.setText(mediaEscolar.getBimestre());
        linha.txtSituacao.setText(mediaEscolar.getSituacao());

        linha.imgLogo.setOnClickListener(this);
        linha.imgLogo.setTag(position);

        linha.imgConsultar.setOnClickListener(this);
        linha.imgConsultar.setTag(position);

        linha.imgDeletar.setOnClickListener(this);
        linha.imgDeletar.setTag(position);

        linha.imgEditar.setOnClickListener(this);
        linha.imgEditar.setTag(position);

        linha.imgSalvar.setOnClickListener(this);
        linha.imgSalvar.setTag(position);

        return dataSet;
    }
}


