package app.smty.practica4.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.smty.practica4.Models.Evaluador;
import app.smty.practica4.Models.Funcionario;
import app.smty.practica4.R;

public class FuncionarioAdapter extends RecyclerView.Adapter<FuncionarioAdapter.FuncionarioViewHolder> {
    List<Funcionario> funcionarioList;
    int layout;

    public FuncionarioAdapter(List<Funcionario> funcionarioList, int layout) {
        this.funcionarioList = funcionarioList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public FuncionarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent,false);
        return new FuncionarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FuncionarioViewHolder holder, int position) {
        holder.bind(funcionarioList.get(position));
    }

    @Override
    public int getItemCount() {
        return funcionarioList.size();
    }

    public static class FuncionarioViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewRDependencia, textViewCargo, textViewEvalInicio, textViewEvalFin;
        ImageView imageViewFuncionario;

        public FuncionarioViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewNames);
            textViewRDependencia = itemView.findViewById(R.id.textViewRDependencia);
            textViewCargo = itemView.findViewById(R.id.textViewCargo);
            textViewEvalFin = itemView.findViewById(R.id.textViewEvalFin);
            textViewEvalInicio = itemView.findViewById(R.id.textViewEvalInicio);
            imageViewFuncionario = itemView.findViewById(R.id.imageViewFuncionario);
        }

        public void bind(Funcionario funcionario) {
            textViewName.setText(funcionario.getNombres());
            textViewRDependencia.setText(funcionario.getSituacion());
            textViewCargo.setText(funcionario.getCargo());

            if(funcionario.getFechafin() != null){
                textViewEvalFin.setText(funcionario.getFechafin());
            }

            if (funcionario.getFechainicio() != null){
                textViewEvalInicio.setText(funcionario.getFechainicio());
            }

            Picasso.get()
                    .load(funcionario.getImgjpg())
                    .error(R.drawable.unknown)
                    .into(this.imageViewFuncionario);
        }
    }
}
