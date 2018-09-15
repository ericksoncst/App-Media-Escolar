package net.br.mobiledesign.mediaescolarmvc.controller;

import android.content.ContentValues;
import android.content.Context;

import net.br.mobiledesign.mediaescolarmvc.datamodel.MediaEscolarDataModel;
import net.br.mobiledesign.mediaescolarmvc.datasource.DataSource;
import net.br.mobiledesign.mediaescolarmvc.model.MediaEscolar;

import java.util.ArrayList;
import java.util.List;

// EXTENDER DATASOURCE PARA ESTA CLASSE
public class MediaEscolarController extends DataSource {

    ContentValues dados;

    public MediaEscolarController(Context context) {
        super(context);
    }

    public double calcularMedia(MediaEscolar obj) {
        return (obj.getNotaProva() + obj.getNotaTrabalho()) / 2;
    }

    public String resultadoFinal(double media) {

        // ternario = if
        return media >= 7 ? "Aprovado" : "Reprovado";
    }

    //BOA PRÁTICA DE PROGRAMAÇÃO. CRIAR METODO BOOLEAN PARA SABER SE HOUVE SUCESSO NA HORA DE SALVAR OS DADOS
    public boolean salvar(MediaEscolar obj){

        boolean sucesso = true;

        dados = new ContentValues();

        dados.put(MediaEscolarDataModel.getMateria(), obj.getMateria());
        dados.put(MediaEscolarDataModel.getBimestre(), obj.getBimestre());
        dados.put(MediaEscolarDataModel.getSituacao(), obj.getSituacao());
        dados.put(MediaEscolarDataModel.getNotaProva(), obj.getNotaProva());
        dados.put(MediaEscolarDataModel.getNotaTrabalho(), obj.getNotaTrabalho());
        dados.put(MediaEscolarDataModel.getMediaFinal(), obj.getMediaFinal());

        sucesso = insert(MediaEscolarDataModel.getTABELA(), dados);

        return sucesso;
    }

    public boolean deletar(MediaEscolar obj){

        boolean sucesso = true;

        sucesso = deletar(MediaEscolarDataModel.getTABELA(),obj.getId());


        return sucesso;
    }

    public boolean alterar(MediaEscolar obj){

        boolean sucesso = true;

        dados = new ContentValues();

        dados.put(MediaEscolarDataModel.getId(), obj.getId());
        dados.put(MediaEscolarDataModel.getMateria(), obj.getMateria());
        dados.put(MediaEscolarDataModel.getBimestre(), obj.getBimestre());
        dados.put(MediaEscolarDataModel.getSituacao(), obj.getSituacao());
        dados.put(MediaEscolarDataModel.getNotaProva(), obj.getNotaProva());
        dados.put(MediaEscolarDataModel.getNotaTrabalho(), obj.getNotaTrabalho());
        dados.put(MediaEscolarDataModel.getMediaFinal(), obj.getMediaFinal());

        sucesso = alterar(MediaEscolarDataModel.getTABELA(), dados);

        return sucesso;
    }

    public List<MediaEscolar> listar(){
        return getAllMediaEscolar();
    }

    public ArrayList<MediaEscolar> getResultadoFinal(){

        return getAllResultadoFinal();
    }

}
