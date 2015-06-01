package juan.pablo.dorado.quehacemoscali;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

/**
 * Created by Juan Pablo on 19/05/2015.
 */
public class MyAdapter extends BaseAdapter {

    private final Activity actividad;
    private final Vector<String> lista;

    public MyAdapter(Activity actividad, Vector<String> lista) {
        super();
        this.actividad = actividad;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.elementAt(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = actividad.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_element_list, null, true);
        TextView textView = (TextView) view.findViewById(R.id.titulo);
        textView.setText(lista.elementAt(position));
        ImageView imageView = (ImageView) view.findViewById(R.id.icono);

        switch (Math.round((float) Math.random() * 5)) {
            case 0:
                imageView.setImageResource(R.mipmap.ic_launcher);
                break;
            case 1:
                imageView.setImageResource(R.mipmap.ic_launcher);
                break;
            case 2:
                imageView.setImageResource(R.mipmap.ic_launcher);
                break;
            case 3:
                imageView.setImageResource(R.mipmap.ic_launcher);
                break;
            case 4:
                imageView.setImageResource(R.mipmap.ic_launcher);
                break;
            default:
                imageView.setImageResource(R.mipmap.ic_launcher);
                break;
        }

        return view;
    }
}
