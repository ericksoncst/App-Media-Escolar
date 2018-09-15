package net.br.mobiledesign.mediaescolarmvc.datasource;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import net.br.mobiledesign.mediaescolarmvc.datamodel.MediaEscolarDataModel;
import net.br.mobiledesign.mediaescolarmvc.model.MediaEscolar;

import java.util.ArrayList;
import java.util.List;

public class DataSource extends SQLiteOpenHelper{

    private static final String DB_NAME = "media_escolar.sqlite";
    private static final int DB_VERSION = 1;

    SQLiteDatabase db;

    Cursor cursor;


    // O parametro original (FACTORY) é referente ao cursor,que é utilizado na hora de consumir o bd
    //O contrutor original,vem com os campos (, String DB_NAME, SQLiteDatabase.CursorFactory factory, int DB_VERSION),porém só necessito do Contexto
    public DataSource(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TRY CATCH PARA SEGURANÇA
        try{

            db.execSQL(MediaEscolarDataModel.criarTabela());

        }catch (Exception e){
            Log.e("Media", "db--------> ERRO: "+e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(String tabela, ContentValues dados){

        boolean sucesso = true;

        try {

            sucesso = db.insert(tabela, null,dados) >0;

        }catch (Exception e){

            sucesso = false;
        }

        return true;
    }

    public boolean deletar(String tabela, int id){

        boolean sucesso = true;

        sucesso = db.delete(tabela, "id=?",
                new String[]{Integer.toString(id)}) >0;

        return sucesso;
    }

    public boolean alterar(String tabela, ContentValues dados){

        boolean sucesso = true;

        int id = dados.getAsInteger("id");

        sucesso = db.update(tabela,dados, "id=?",
                new String[]{Integer.toString(id)}) >0;

        return sucesso;
    }

    public List<MediaEscolar> getAllMediaEscolar() {

        MediaEscolar obj;

        // TIPADA
        List<MediaEscolar> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + MediaEscolarDataModel.getTABELA()  + " ORDER BY materia";

        cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            do {

                obj = new MediaEscolar();

                obj.setId(cursor.getInt(cursor.getColumnIndex(MediaEscolarDataModel.getId())));
                obj.setMateria(cursor.getString(cursor.getColumnIndex(MediaEscolarDataModel.getMateria())));
                obj.setSituacao(cursor.getString(cursor.getColumnIndex(MediaEscolarDataModel.getSituacao())));

                lista.add(obj);

            } while (cursor.moveToNext());

        }

        cursor.close();

        return lista;
    }

    public ArrayList<MediaEscolar> getAllResultadoFinal() {

        MediaEscolar obj;

        // TIPADA
        ArrayList<MediaEscolar> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + MediaEscolarDataModel.getTABELA()  + " ORDER BY materia";

        cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            do {

                obj = new MediaEscolar();

                obj.setId(cursor.getInt(cursor.getColumnIndex(MediaEscolarDataModel.getId())));
                obj.setBimestre(cursor.getString(cursor.getColumnIndex(MediaEscolarDataModel.getBimestre())));
                obj.setMediaFinal(Double.parseDouble(cursor.getString(cursor.getColumnIndex(MediaEscolarDataModel.getMediaFinal()))));
                obj.setMateria(cursor.getString(cursor.getColumnIndex(MediaEscolarDataModel.getMateria())));
                obj.setSituacao(cursor.getString(cursor.getColumnIndex(MediaEscolarDataModel.getSituacao())));

                lista.add(obj);

            } while (cursor.moveToNext());

        }

        cursor.close();

        return lista;
    }


}
