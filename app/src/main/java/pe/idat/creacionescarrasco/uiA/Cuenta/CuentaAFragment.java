package pe.idat.creacionescarrasco.uiA.Cuenta;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import pe.idat.creacionescarrasco.LoginActivity;
import pe.idat.creacionescarrasco.config.VariablesGlobales;
import pe.idat.creacionescarrasco.databinding.FragmentCuentaABinding;

public class CuentaAFragment extends Fragment {
    private FragmentCuentaABinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CuentaAViewModel cuentaAViewModel =
                new ViewModelProvider(this).get(CuentaAViewModel.class);

        binding = FragmentCuentaABinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView usuariotxt = binding.txtusuario;
        final TextView nombretxt = binding.txtNombres;
        final TextView emailtxt = binding.txtEmail;
        final TextView apellidostxt = binding.txtApellido;
        final TextView dnitxt = binding.txtDni;
        final TextView sexotxt = binding.txtSexo;
        final TextView fechatxt = binding.txtFecha;
        final TextView numerotxt = binding.txtNumero;
        final TextView salariotxt = binding.txtSalario;
        final TextView cargotxt = binding.txtCargo;
        final TextView htrabajotxt = binding.txtHTrabajo;
        final Button btn_cerrar_sesion = binding.btnCerrarSesionAdmin;

        usuariotxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getNames());
        nombretxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getNames());
        emailtxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getEmail());
        apellidostxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getLastnames());
        dnitxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getDni());
        usuariotxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getNames());
        fechatxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getBirth_date());
        numerotxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getPhone_number());
        salariotxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getSalary());
        cargotxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getWork_position().getName());
        htrabajotxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getWork_position().getWork_start_time() + " - "
                + VariablesGlobales.getUsuarioDeLaSesion().getWork_position().getWork_end_time());

        btn_cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder AlertDialogPerzonalizado = new AlertDialog.Builder(getContext());
                AlertDialogPerzonalizado.setMessage("¿Seguro que desea cerrar sesión?")
                        .setCancelable(false)
                        .setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                cerrarSession();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog pregunta = AlertDialogPerzonalizado.create();
                pregunta.setTitle("Salir");
                pregunta.show();

            }
        });

        if (VariablesGlobales.getUsuarioDeLaSesion().getSex().toString().equals("M")){
            sexotxt.setText("Masculino");
        }
        else {
            sexotxt.setText("Femenino");
        }
        return root;
    }

    public void cerrarSession() {
        VariablesGlobales.setToken("");
        VariablesGlobales.setUsuarioDeLaSesion(null);
        irAlLogin();
        getActivity().finish();
    }

    private void irAlLogin(){
        Intent intentLogin = new Intent(this.getContext(), LoginActivity.class);
        startActivity(intentLogin);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}