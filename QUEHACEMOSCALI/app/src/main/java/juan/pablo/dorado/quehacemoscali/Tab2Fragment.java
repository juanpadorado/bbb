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
 * Created by Juan Pablo on 03/05/2015.
 */
public class Tab2Fragment extends Fragment {

    ListView lista;

    public Tab2Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.fragment_tab2, container, false);

        return V;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lista = (ListView) getView().findViewById(R.id.listView);
        lista.setAdapter(new MyAdapter(getActivity(), listaElementos()));
    }

    public Vector<String> listaElementos() {
        String[] elementos;
        Vector<String> VectorElementos;
        elementos = new String[]{"Elemento 1", "Elemento 2", "Elemento 3", "Elemento 4", "Elemento 5"};

        VectorElementos = new Vector<String>(Arrays.asList(elementos));

        return VectorElementos;
    }
}
