package pe.idat.creacionescarrasco.uiA.Lista;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pe.idat.creacionescarrasco.databinding.ItemListEmpleadosBinding;
import pe.idat.creacionescarrasco.model.HourRegisterResponse;

public class AdaptadorLista extends RecyclerView.Adapter<AdaptadorLista.ViewHolder> {

    private List<HourRegisterResponse> listaDatos;

    public AdaptadorLista(List<HourRegisterResponse> listaDatos) {
        this.listaDatos = listaDatos;
    }

    @NonNull
    @Override
    public AdaptadorLista.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemListEmpleadosBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final HourRegisterResponse itemHourRegisterResponse = listaDatos.get(position);
        String nombreCompletoEmpleado = itemHourRegisterResponse.getUser().getNames()
                + " " + itemHourRegisterResponse.getUser().getLastnames();
        String horarioEmpleado = itemHourRegisterResponse.getUser().getWork_position().getWork_start_time()
                + " - " + itemHourRegisterResponse.getUser().getWork_position().getWork_end_time();

        int minutosfaltantes = itemHourRegisterResponse.getMissing_minutes();
        String minutosfaltantesTexto = String.valueOf(minutosfaltantes);

        holder.binding.txtlistaFecha.setText(itemHourRegisterResponse.getDate());
        holder.binding.txtlistaEmpleado.setText(nombreCompletoEmpleado);
        holder.binding.txtlistaCargo.setText(itemHourRegisterResponse.getUser().getWork_position().getName());
        holder.binding.txtlistaHorario.setText(horarioEmpleado);
        holder.binding.txtlistaHoraInicio.setText(itemHourRegisterResponse.getStart_time());
        holder.binding.txtlistaHoraSalida.setText(itemHourRegisterResponse.getEnd_time());
        holder.binding.txtlistaInicioAlmuerzo.setText(itemHourRegisterResponse.getLunch_start_time());
        holder.binding.txtlistaTerminoAlmuerzo.setText(itemHourRegisterResponse.getLunch_end_time());
        holder.binding.txtlistaMinutosFaltantes.setText(minutosfaltantesTexto);
    }

    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemListEmpleadosBinding binding;

        public ViewHolder(@NonNull ItemListEmpleadosBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
