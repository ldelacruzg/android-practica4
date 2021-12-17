package app.smty.practica4.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.smty.practica4.Models.Evaluador;
import app.smty.practica4.R;

public class EvaluadorAdapter extends RecyclerView.Adapter<EvaluadorAdapter.ViewHolder> {
    List<Evaluador> evaluadorList;
    int layout;
    OnItemClickListener itemClickListener;

    public EvaluadorAdapter(List<Evaluador> evaluadorList, int layout, OnItemClickListener itemClickListener) {
        this.evaluadorList = evaluadorList;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(evaluadorList.get(position), this.itemClickListener);
    }

    @Override
    public int getItemCount() {
        return evaluadorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewArea;
        ImageView imageViewFoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewArea = itemView.findViewById(R.id.textViewArea);
            imageViewFoto = itemView.findViewById(R.id.imageViewFoto);
        }

        public void bind(Evaluador evaluador, OnItemClickListener listener) {
            this.textViewName.setText(evaluador.getNombres());
            this.textViewArea.setText(evaluador.getArea());
            Picasso.get()
                    .load(evaluador.getImgjpg())
                    .error(R.drawable.unknown)
                    .into(this.imageViewFoto);

            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(evaluador, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Evaluador evaluador, int position);
    }
}
