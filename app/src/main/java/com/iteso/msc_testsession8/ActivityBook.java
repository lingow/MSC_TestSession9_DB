package com.iteso.msc_testsession8;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.iteso.msc_testsession8.beans.Author;
import com.iteso.msc_testsession8.beans.Editorial;
import com.iteso.msc_testsession8.beans.Libro;
import com.iteso.msc_testsession8.database.AuthorStore;
import com.iteso.msc_testsession8.database.DataBaseHandler;
import com.iteso.msc_testsession8.database.EditorialStore;
import com.iteso.msc_testsession8.database.LibroStore;
import com.iteso.msc_testsession8.tools.NothingSelectedSpinnerAdapter;

import java.util.List;

/**
 * Created by oscarvargas on 08/03/15.
 */
public class ActivityBook extends ActionBarActivity {

    protected TextView nombre;
    protected TextView publicacion;
    protected Spinner paises;
    protected Spinner autores;
    protected Spinner editoriales;
    protected Spinner lenguajes;
    protected DataBaseHandler dbHandler;
    protected List<Editorial> arrEditorial;
    protected List<Author> arrAuthors;
    protected Button btnGuardar;
    protected Button btnBorrar;
    protected Libro libro;
    protected boolean isInsert = true;
    protected LibroStore libroStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.libro);

        nombre = (TextView) findViewById(R.id.libro_nombre);
        publicacion = (TextView) findViewById(R.id.libro_publicacion);
        paises = (Spinner) findViewById(R.id.libro_paises);
        autores = (Spinner) findViewById(R.id.libro_autores);
        editoriales = (Spinner) findViewById(R.id.libro_editoriales);
        lenguajes = (Spinner) findViewById(R.id.libro_lenguajes);
        btnGuardar = (Button) findViewById(R.id.libro_btnGuardar);
        btnBorrar = (Button) findViewById(R.id.libro_btnBorrar);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this, R.array.arrPaises, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paises.setAdapter(adapter1);
        }else {
            paises.setAdapter(new NothingSelectedSpinnerAdapter(adapter1,
                    R.xml.spinner_row_nothing_selected, this));
        }

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.arrLenguajes,
                android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            lenguajes.setAdapter(adapter2);
        }else {
            lenguajes.setAdapter(new NothingSelectedSpinnerAdapter(adapter2,
                    R.xml.spinner_row_nothing_selected, this));
        }

        //Database object
        dbHandler = DataBaseHandler.getInstance(this);

        EditorialStore editorialStore = new EditorialStore();
        arrEditorial = editorialStore.getEditorialsWhere(null, "_id ASC",
                dbHandler);
        ArrayAdapter<Editorial> adapterEditorials = new ArrayAdapter<Editorial>(
                this, android.R.layout.simple_spinner_item, arrEditorial);
        adapterEditorials.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            editoriales.setAdapter(adapterEditorials);
        }else {
            editoriales.setAdapter(new NothingSelectedSpinnerAdapter(
                    adapterEditorials, R.xml.spinner_row_nothing_selected, this));
        }

        AuthorStore autorStore = new AuthorStore();
        arrAuthors = autorStore
                .getAuthorsWhere(null, "idAuthor ASC", dbHandler);
        ArrayAdapter<Author> adapterAuthors = new ArrayAdapter<Author>(this,
                android.R.layout.simple_spinner_item, arrAuthors);
        adapterAuthors.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            autores.setAdapter(adapterAuthors);
        }else{
            autores.setAdapter(new NothingSelectedSpinnerAdapter(adapterAuthors,
                    R.xml.spinner_row_nothing_selected, this));
        }

        libroStore = new LibroStore();
    }

    public boolean isValidRecord() {
        if (isEmptyField(nombre) || isEmptyField(publicacion)) {
            return false;
        }
        if (autores.getSelectedItemId() == Spinner.INVALID_POSITION) {
            return false;
        }
        if (editoriales.getSelectedItemId() == Spinner.INVALID_POSITION) {
            return false;
        }
        if (paises.getSelectedItemId() == Spinner.INVALID_POSITION) {
            return false;
        }
        if (lenguajes.getSelectedItemId() == Spinner.INVALID_POSITION) {
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

    public void onClick(View v){
        if(v.getId()==R.id.libro_btnLoad){
            List<Libro> libros = libroStore.getBooksWhere("name = '" + nombre.getText()+"'",null,dbHandler);
            if(libros.isEmpty()){
                Toast.makeText(this,"No se encontro el libro",Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(this,"Se encontraron " + libros.size() + " libros",Toast.LENGTH_SHORT).show();
                fillForm(libros.get(0));
                return;
            }
        }
        if (!isValidRecord()) {
            Toast.makeText(this, getString(R.string.libro_RecordNotValid),
                    Toast.LENGTH_LONG).show();
            return;
        }
        Libro l = fillBook();
        switch(v.getId()){
            case R.id.libro_btnGuardar:
                libroStore.updateBook(l,dbHandler);
                break;
            case R.id.libro_btnNuevo:
                libroStore.addBook(l,dbHandler);
                break;
            case R.id.libro_btnBorrar:
                libroStore.deleteLibro(l.getId(),dbHandler);
                break;
        }
    }

    private void fillForm(Libro libro) {

    }

    public Libro fillBook(){
        Author author = null;
        author = (Author) autores.getSelectedItem();
        libro = new Libro();
        libro.setIdAutor(author.getId());
        libro.setNombre(nombre.getText().toString());
        libro.setAnioPublicacion(Integer.parseInt(publicacion.getText().toString()));
        libro.setLenguaje(lenguajes.getSelectedItem().toString());
        libro.setPais(paises.getSelectedItem().toString());
        libro.setIdEditorial(((Editorial) editoriales.getSelectedItem()).getId());
        return libro;
    }

}
