package com.example.restaurant2.Pedido;

import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant2.R;

public class AdaptadorPedido extends RecyclerView.Adapter<AdaptadorPedido.ViewHolder> {

    List<Pedido> lp;

    Integer pos;

    public AdaptadorPedido(List<Pedido> lp) {//Devuelve un viewholder(un renglon)
        this.lp = lp;
    }

    @NonNull
    @Override
    public AdaptadorPedido.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pedido, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, final int i) {//objeto, elemento
        Pedido p = lp.get(i);
        viewholder.idpedido.setText(lp.get(i).getIdpedido());
        viewholder.platillonombre.setText(lp.get(i).getPlatillonombre());
    }

    @Override
    public int getItemCount() {
        return lp.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder { //renglon

        TextView idpersona, nombre, apellidopaterno, apellidomaterno, idpedido, platillonombre, precio;



        public ViewHolder(View itemView) {
            super(itemView);//super, las caracteristicas del padre

            idpedido = itemView.findViewById(R.id.idpedido);
            platillonombre = itemView.findViewById(R.id.platillonombre);

        }
    }

}
