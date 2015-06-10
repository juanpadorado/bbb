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
 * Created by Juan Pablo on 08/06/2015.
 */
public class ControlAdapter extends BaseAdapter {

    private final Activity actividad;
    private final Vector<String> listaTit;
    private final Vector<String> listaSubT;
    int i=0;

    public ControlAdapter(Activity actividad, Vector<String> lista, Vector<String> listaSubT) {
        super();
        this.actividad = actividad;
        this.listaTit = lista;
        this.listaSubT = listaSubT;
    }

    @Override
    public int getCount() {
        return listaTit.size();
    }

    @Override
    public Object getItem(int position) {
        return listaTit.elementAt(position);
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
        TextView textView2 = (TextView) view.findViewById(R.id.subtitulo);
        textView.setText(listaTit.elementAt(position));
        textView2.setText(listaSubT.elementAt(position));
        ImageView imageView = (ImageView) view.findViewById(R.id.icono);

        switch (i) {
            case 0:
                imageView.setImageResource(R.drawable.logo_jala);
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

        i++;

        return view;
    }
}
