package pe.idat.creacionescarrasco;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pe.idat.creacionescarrasco.Interface.MetodosApi;
import pe.idat.creacionescarrasco.config.VariablesGlobales;
import pe.idat.creacionescarrasco.databinding.ActivityCrearCuentaBinding;
import pe.idat.creacionescarrasco.model.CrearCuentaRequest;
import pe.idat.creacionescarrasco.model.LoginResponse;
import pe.idat.creacionescarrasco.model.WorkPosition;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearCuentaActivity extends AppCompatActivity {

    private ActivityCrearCuentaBinding binding;
    private Button btnIrInicio;
    private Button btnCrearCuenta;
    private EditText inputFechaNacimiento;
    private DatePickerDialog.OnDateSetListener setListener;
    private TextView textprueba;

    Spinner spinner;
    ArrayList<String> listaWorkPosition = new ArrayList<String>();
    ArrayList<String> listaWorkPositionID = new ArrayList<String>();
    ArrayList<String> listaRoles = new ArrayList<String>();

    String WorkPositionIDobtenido = "";

    ArrayAdapter adaptador = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCrearCuentaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CrearCuentaRequest CuentaACrear = new CrearCuentaRequest();
        listaRoles.add("employed");

        // --- SPINNER ---
        spinner = binding.spinnerPosicionesTrabajo;
        adaptador = new ArrayAdapter<String>(CrearCuentaActivity.this, android.R.layout.simple_spinner_item, listaWorkPosition);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptador);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Al seleccionar una opción en el Spinner, se obtiene una orden "i"
                String workPositionID = listaWorkPositionID.get(i); //Obtener valor del Array que tiene los ID con el orden "i"
                WorkPositionIDobtenido = workPositionID; //Setear el valor para usarlo despues
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // ---------------

        // ------ OBTENER VALORES DE WORK POSITION -----
        MetodosApi metodosApi = RetrofitClient.getRetrofitInstance().create(MetodosApi.class);
        Call<List<WorkPosition>> call = metodosApi.obtenerPosicionesTrabajo("Bearer " + VariablesGlobales.getToken());

        call.enqueue(new Callback<List<WorkPosition>>() {
            @Override
            public void onResponse(Call<List<WorkPosition>> call, Response<List<WorkPosition>> response) {
                // Mapear el response a una lista de tipo WorkPosition
                List<WorkPosition> lista = response.body();
                // Iterar cada objeto WorkPosition
                for(WorkPosition x:lista){
                    listaWorkPosition.add(x.getName()); //Agregar el nombre de cada objeto WorkPosition a la lista
                    listaWorkPositionID.add(x.get_id()); //Agregar el id de cada objeto WorkPosition a la lista
                }
                adaptador.notifyDataSetChanged(); //Notificar cambios al adapter
            }

            @Override
            public void onFailure(Call<List<WorkPosition>> call, Throwable t) {
                String textoerror = t.getMessage();
                Toast toasterror = Toast.makeText(getApplicationContext(), textoerror, Toast.LENGTH_SHORT);
                toasterror.show();
            }
        });
        // -----------------------

        // --- SETEAR BOTONES ---
        btnIrInicio = binding.btnRegresarInicio;
        btnCrearCuenta = binding.btnCrearNuevaCuenta;

        // --- SETEAR VALORES FECHAS ---
        inputFechaNacimiento = binding.txtInputFechaNacimiento;
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        inputFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        CrearCuentaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String fecha = "";
                        if(day < 10 && month < 10){
                            fecha = year + "-0" + month + "-0" + day;
                            binding.txtInputFechaNacimiento.setText(fecha);
                        }
                        else{
                            if(day < 10) {
                                fecha = year + "-" + month + "-0" + day;
                                binding.txtInputFechaNacimiento.setText(fecha);
                            }
                            else if(month < 10){
                                fecha = year + "-0" + month + "-" + day;
                                binding.txtInputFechaNacimiento.setText(fecha);
                            }
                            else{
                                fecha = year + "-" + month + "-" + day;
                                binding.txtInputFechaNacimiento.setText(fecha);
                            }
                        }
                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearCuentaRequest crearCuentaRequest = new CrearCuentaRequest();

                crearCuentaRequest.setWork_position(WorkPositionIDobtenido);
                crearCuentaRequest.setNames(binding.txtInputNombreCuenta.getText().toString());
                crearCuentaRequest.setLastnames(binding.txtInputApellidoCuenta.getText().toString());
                crearCuentaRequest.setDni(binding.txtInputDNICuenta.getText().toString());

                // ---- Setear Sexo ----
                String valorSexo = "";
                if(binding.rbMasculino.isChecked()){
                    valorSexo = "M";
                }
                if(binding.rbFemenino.isChecked()){
                    valorSexo = "F";
                }
                crearCuentaRequest.setSex(valorSexo);
                // ----------------------

                // ----- Setear Fecha Nacimiento -----
                crearCuentaRequest.setBirth_date(binding.txtInputFechaNacimiento.getText().toString());
                // -----------------------------------

                // ---- Setear Numero Celular ----
                String numeroDeInput = binding.txtInputTelefonoCuenta.getText().toString();
                String numeroTelefonoNuevo = numeroDeInput.replaceAll("...", "$0 ").trim();
                String numeroTelefonoFinal = "+51 " + numeroTelefonoNuevo;
                crearCuentaRequest.setPhone_number(numeroTelefonoFinal);
                // -------------------------------

                int valorSalarioInput = Integer.parseInt(binding.txtInputSalarioCuenta.getText().toString());
                crearCuentaRequest.setSalary(valorSalarioInput);
                crearCuentaRequest.setEmail(binding.txtInputEmailCuenta.getText().toString());
                crearCuentaRequest.setPassword(binding.txtInputContraCuenta.getText().toString());

                // ------ Obtener Valores Roles / Setear Valores Roles ------
                if(binding.cbAdminCuenta.isChecked()){
                    if(!listaRoles.contains("admin")){
                        listaRoles.add("admin");
                    }
                }
                else{
                    if(listaRoles.contains("admin")){
                        listaRoles.remove("admin");
                    }
                }

                if (binding.cbSupervisorCuenta.isChecked()){
                    if(!listaRoles.contains("supervisor")){
                        listaRoles.add("supervisor");
                    }
                }
                else{
                    if(listaRoles.contains("supervisor")){
                        listaRoles.remove("supervisor");
                    }
                }
                crearCuentaRequest.setRoles(listaRoles);
                // ---------------------------------------------------------

                crearNuevaCuenta(crearCuentaRequest);
            }
        });

        btnIrInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresarInicio();
            }
        });

    }
    private void crearNuevaCuenta(CrearCuentaRequest crearCuentaRequest) {
        MetodosApi metodosApi = RetrofitClient.getRetrofitInstance().create(MetodosApi.class);
        Call<LoginResponse> call = metodosApi.creacionCuenta("Bearer " + VariablesGlobales.getToken(), crearCuentaRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.code() == 400){
                    Toast toasterrorinput1 = Toast.makeText(getApplicationContext(),
                            "Los datos no tienen la forma esperada, ingrese nuevamente", Toast.LENGTH_SHORT);
                    toasterrorinput1.show();
                }
                else if (response.code() == 401){
                    Toast toasterrorinput2 = Toast.makeText(getApplicationContext(),
                            "El Token no es válido, expiró o no se envió", Toast.LENGTH_SHORT);
                    toasterrorinput2.show();
                }

                else{
                    Toast toastsuccess = Toast.makeText(getApplicationContext(),
                            "¡La cuenta se creó correctamente!", Toast.LENGTH_SHORT);
                    toastsuccess.show();
                    binding.txtInputNombreCuenta.setText("");
                    binding.txtInputApellidoCuenta.setText("");
                    binding.txtInputDNICuenta.setText("");
                    binding.rbgrupoSexo.clearCheck();
                    binding.txtInputFechaNacimiento.setText("");
                    binding.txtInputTelefonoCuenta.setText("");
                    binding.txtInputSalarioCuenta.setText("");
                    binding.txtInputEmailCuenta.setText("");
                    binding.txtInputContraCuenta.setText("");
                    binding.cbAdminCuenta.setChecked(false);
                    binding.cbSupervisorCuenta.setChecked(false);

                    Log.d("testid", response.body().getUser().get_id());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String texto = t.getMessage();
                Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void regresarInicio() {
        Intent intentRegresarInicio = new Intent(this, AdminActivity.class);
        startActivity(intentRegresarInicio);
    }
}