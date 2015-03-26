package com.iteso.msc_testsession8;

import java.util.List;

import com.iteso.msc_testsession8.beans.Author;
import com.iteso.msc_testsession8.database.AuthorStore;
import com.iteso.msc_testsession8.database.DataBaseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Fragment que se carga inicialmente con la vista principal, es decir, con el
 * listado inicial
 */
public class FragmentList extends android.app.ListFragment {
	
	protected DataBaseHandler dbHandler;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		dbHandler = DataBaseHandler.getInstance(activity);
	}
	
	@Override
	// Se sobreescribe este m�todo como el que siempre "carga" la informaci�n a
	// mostrar en el fragment
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		AuthorStore autorStore = new AuthorStore();
		List<Author> arrAuthors = autorStore
				.getAuthorsWhere(null, "idAuthor ASC", dbHandler);
		
		ArrayAdapter<Author> adapter = new ArrayAdapter<Author>(getActivity(),
				android.R.layout.simple_list_item_1, arrAuthors);

		setListAdapter(adapter);
	}

	@Override
	// Al hacer tap en algun elemento de la vista, establece el texto del elemento
	// en el nuevo fragment
	public void onListItemClick(ListView l, View v, int position, long id) {
		Author item = (Author) getListAdapter().getItem(position);
		FragmentDetail fragment = (FragmentDetail) getFragmentManager()
				.findFragmentById(R.id.detalleFragment);
		if (fragment != null && fragment.isInLayout()) {
			fragment.setText(item.toString());
		} else {
			Intent intent = new Intent(getActivity().getApplicationContext(),
					ActivityDetail.class);
			intent.putExtra("value", item.toString());
			startActivity(intent);
		}
	}
}
