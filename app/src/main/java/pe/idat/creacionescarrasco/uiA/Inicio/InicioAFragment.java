package pe.idat.creacionescarrasco.uiA.Inicio;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import pe.idat.creacionescarrasco.CrearCuentaActivity;
import pe.idat.creacionescarrasco.CrearPosicionTrabajoActivty;
import pe.idat.creacionescarrasco.MainActivity;
import pe.idat.creacionescarrasco.R;
import pe.idat.creacionescarrasco.config.VariablesGlobales;
import pe.idat.creacionescarrasco.databinding.FragmentCuentaABinding;
import pe.idat.creacionescarrasco.databinding.FragmentInicioABinding;
import pe.idat.creacionescarrasco.uiA.Cuenta.CuentaAViewModel;

public class InicioAFragment extends Fragment {

    private FragmentInicioABinding binding;
    private Button btnCrearPosicionTrabajo;
    private Button btnCrearCuenta;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InicioAViewModel inicioAViewModel =
                new ViewModelProvider(this).get(InicioAViewModel.class);

        binding = FragmentInicioABinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //final TextView textView = binding.textInicio;
        //inicioAViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        final TextView textoEmployed = binding.txtTexto;
        String nombre = VariablesGlobales.getUsuarioDeLaSesion().getNames();
        textoEmployed.setText("Hola "+ nombre + "!! bienvenido al Control de asistencia de la empresa \"Creaciones Carrasco\"");

        btnCrearCuenta = binding.btnIrFormularioCrearCuenta;
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irFormularioCrearCuenta();
            }
        });

        btnCrearPosicionTrabajo = binding.btnIrFormularioPosicionTrabajo;
        btnCrearPosicionTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irFormularioPosicionTrabajo();
            }
        });


        return root;
    }

    private void irFormularioCrearCuenta() {
        Intent intentFormularioCrearCuenta = new Intent(getContext(), CrearCuentaActivity.class);
        startActivity(intentFormularioCrearCuenta);
    }

    private void irFormularioPosicionTrabajo() {
        Intent intentFormularioPosicionTrabajo = new Intent(getContext(), CrearPosicionTrabajoActivty.class);
        startActivity((intentFormularioPosicionTrabajo));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}