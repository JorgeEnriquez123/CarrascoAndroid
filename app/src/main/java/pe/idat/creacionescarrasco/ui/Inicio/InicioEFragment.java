package pe.idat.creacionescarrasco.ui.Inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import pe.idat.creacionescarrasco.config.VariablesGlobales;
import pe.idat.creacionescarrasco.databinding.FragmentInicioEmpleadoBinding;

public class InicioEFragment extends Fragment {

    private FragmentInicioEmpleadoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InicioEViewModel homeViewModel =
                new ViewModelProvider(this).get(InicioEViewModel.class);

        binding = FragmentInicioEmpleadoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textoEmployed = binding.txtTexto;
        String nombre = VariablesGlobales.getUsuarioDeLaSesion().getNames();
        textoEmployed.setText("Hola "+ nombre + "!! bienvenido al registro de asistencia de la empresa \"Creaciones Carrasco\"");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}