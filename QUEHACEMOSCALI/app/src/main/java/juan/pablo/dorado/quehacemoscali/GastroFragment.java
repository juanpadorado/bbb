package juan.pablo.dorado.quehacemoscali;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Arrays;
import java.util.Vector;

/**
 * Created by Juan Pablo on 08/06/2015.
 */
public class GastroFragment extends Fragment {

    ListView lista;

    public GastroFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_container, container, false);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lista = (ListView) getView().findViewById(R.id.listViewBu);
        lista.setAdapter(new GastroAdapter(getActivity(), listaElementos()));
    }

    public Vector<String> listaElementos() {
        String[] elementos;
        Vector<String> VectorElementos;
        elementos = new String[]{"Elemento 1", "Elemento 2", "Elemento 3", "Elemento 4", "Elemento 5"};

        VectorElementos = new Vector<String>(Arrays.asList(elementos));

        return VectorElementos;
    }
}
