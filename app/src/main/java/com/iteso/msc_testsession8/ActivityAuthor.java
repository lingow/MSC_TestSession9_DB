package com.iteso.msc_testsession8;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.iteso.msc_testsession8.beans.Author;
import com.iteso.msc_testsession8.database.AuthorStore;
import com.iteso.msc_testsession8.database.DataBaseHandler;
import com.iteso.msc_testsession8.tools.NothingSelectedSpinnerAdapter;

import java.util.Iterator;
import java.util.List;

/**
 * Created by oscarvargas on 08/03/15.
 */
public class ActivityAuthor extends ActionBarActivity implements
        SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    protected EditText id;
    protected EditText nombre;
    protected EditText extra;
    protected Spinner paises;
    protected SearchView searchView;
    protected DataBaseHandler dbHandler;
    protected AuthorStore authorStore;
    protected Author autor;
    protected Button btnGuardar;
    protected Button btnBorrar;
    protected boolean isInsert = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autor);

        id = (EditText) findViewById(R.id.autor_id);
        nombre = (EditText) findViewById(R.id.autor_nombre);
        extra = (EditText) findViewById(R.id.autor_extra);
        paises = (Spinner) findViewById(R.id.autor_pais);
        btnGuardar = (Button) findViewById(R.id.autor_btnGuardar);
        btnBorrar = (Button) findViewById(R.id.autor_btnBorrar);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this, R.array.arrPaises, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paises.setPrompt(getString(R.string.spinner_opcion));
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paises.setAdapter(adapter1);
        } else{
            paises.setAdapter(new NothingSelectedSpinnerAdapter(adapter1,
                    R.xml.spinner_row_nothing_selected, this));
        }

        searchView = (SearchView) findViewById(R.id.autor_searchView);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        btnBorrar.setEnabled(false);

        dbHandler = DataBaseHandler.getInstance(this);
        authorStore = new AuthorStore();
    }

    public void cleanFields() {
        id.setText("");
        nombre.setText("");
        extra.setText("");
        paises.setSelection(0);
        id.requestFocus();
    }

    public boolean isValidRecord() {

        if (isEmptyField(id) || isEmptyField(nombre) || isEmptyField(extra)) {
            return false;
        }
        if (paises.getSelectedItemId() == Spinner.INVALID_POSITION) {
            return false;
        }
        return true;
    }

    public boolean isEmptyField(View v) {

        if (v instanceof EditText) {
            EditText edit = (EditText) v;
            if ((edit.getText().toString().trim().length()) > 0) {
                if (!edit.getText().toString()
                        .equalsIgnoreCase(edit.getHint().toString())) {
                    return false;
                }
            }
        }
        return true;
    }

    public void fillAuthor(){
        autor = new Author();
        autor.setId(Integer.valueOf(id.getText().toString()));
        autor.setNombre(nombre.getText().toString());
        autor.setExtra(extra.getText().toString());
        autor.setPais(paises.getSelectedItem().toString());
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.autor_btnGuardar:
                if (!isValidRecord()) {
                    Toast.makeText(this, getString(R.string.autor_RecordNotValid),
                            Toast.LENGTH_LONG).show();
                } else {
                    fillAuthor();
                    if (isInsert) {
                        long inserted = authorStore.addAuthor(autor, dbHandler);
                        if (inserted == -1) {
                            Toast.makeText(this,
                                    getString(R.string.autor_RecordDuplicated),
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this,
                                    getString(R.string.autor_RecordValid),
                                    Toast.LENGTH_LONG).show();
                        }
                    }else{
                        int updated = authorStore.updateAuthor(autor, dbHandler);
                        if(updated > 0){
                            Toast.makeText(this,
                                    getString(R.string.autor_RecordValid),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    restoreFields();
                }
                break;
            case R.id.autor_btnNuevo:
                restoreFields();
                break;
            case R.id.autor_btnBorrar:

                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle(getString(R.string.app_name));
                alert.setMessage(getString(R.string.autor_EraseAutor));
                alert.setPositiveButton(R.string.autor_EraseAutorPositive,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                authorStore.deleteAuthor(id.getText().toString(), dbHandler);
                                restoreFields();
                            }
                        });
                alert.setNegativeButton(
                        R.string.autor_EraseAutorNegative,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                alert.show();

                break;
        }
    }

    public void restoreFields() {
        isInsert = true;
        btnBorrar.setEnabled(false);
        btnGuardar.setText(getString(R.string.btnGuardar));
        id.setEnabled(true);
        cleanFields();
    }

    @Override
    public boolean onClose() {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String arg0) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String idSubmitted) {
        if (idSubmitted.trim().length() > 0) {
            try {
                Integer idAutor = Integer.valueOf(idSubmitted);

                List<Author> arrAutor = authorStore.getAuthorsWhere(idAutor,
                        null, null, dbHandler);
                autor = null;
                Iterator<Author> oIterator = arrAutor.iterator();
                while (oIterator.hasNext()) {
                    autor = oIterator.next();
                    break;
                }
                if (autor != null) {
                    id.setText(String.valueOf(autor.getId()));
                    nombre.setText(autor.getNombre());
                    extra.setText(autor.getExtra());

                    paises.setSelection(getIndex(paises, autor.getPais()));

                    btnBorrar.setEnabled(true);
                    btnGuardar.setText(getString(R.string.btnActualizar));
                    id.setEnabled(false);
                    isInsert = false;
                } else {

                    restoreFields();

                    Toast.makeText(this,
                            getString(R.string.autor_RecordNotFound),
                            Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                Toast.makeText(this, getString(R.string.autor_IdNotNumeric),
                        Toast.LENGTH_LONG).show();
            }
        }

        return false;
    }

    private int getIndex(Spinner spinner, String myString) {
        int index = 0;

        for (int i = 1; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString()
                    .equalsIgnoreCase(myString)) {
                index = i;
            }
        }
        return index;
    }
}

