package pe.idat.creacionescarrasco.uiA.Lista;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.idat.creacionescarrasco.Interface.MetodosApi;
import pe.idat.creacionescarrasco.RetrofitClient;
import pe.idat.creacionescarrasco.config.VariablesGlobales;
import pe.idat.creacionescarrasco.databinding.FragmentListaABinding;
import pe.idat.creacionescarrasco.model.HourRegisterResponse;
import pe.idat.creacionescarrasco.model.WorkPosition;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaAFragment extends Fragment {

    private FragmentListaABinding binding;
    private Button btnBuscar;
    private EditText inputNombre;

    // ---- WORK POSITION ----
    Spinner spinner;
    ArrayList<String> listaWorkPosition = new ArrayList<String>();
    ArrayList<String> listaWorkPositionID = new ArrayList<String>();
    ArrayAdapter adaptador = null;
    String WorkPositionIDobtenido = "";

    // ---- LISTA EMPLEADOS ----
    private List<HourRegisterResponse> listaEmpleados;
    private RecyclerView recyclerView;
    private AdaptadorLista listaAdaptador;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ListaAViewModel listaAViewModel =
                new ViewModelProvider(this).get(ListaAViewModel.class);

        binding = FragmentListaABinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.recyclerId;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // ---- VALOR EN BLANCO PARA EL SPINNER ----
        listaWorkPosition.add("- Seleccione un Puesto de Trabajo -");
        listaWorkPositionID.add("nada");

        // --- SPINNER ---
        spinner = binding.spinnerBuscarWorkPosition;
        adaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listaWorkPosition);
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
                Toast toasterror = Toast.makeText(getContext(), textoerror, Toast.LENGTH_SHORT);
                toasterror.show();
            }
        });
        // -----------------------

        // ---- ASIGNAR BOTONES ----
        btnBuscar = binding.btnBuscarEmpleado;
        inputNombre = binding.inputBuscadorNombre;

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (WorkPositionIDobtenido.equals("nada")) {
                    Toast.makeText(getContext(), "Buscando...", Toast.LENGTH_SHORT).show();
                    mostrarPorNombre();
                }
                else{
                    Toast.makeText(getContext(), "Buscando...", Toast.LENGTH_SHORT).show();
                    mostrarConAmbosFiltros();
                }
            }
        });


        return root;
    }

    private void mostrarConAmbosFiltros() {
        String nombreInput = inputNombre.getText().toString();
        MetodosApi metodosApi = RetrofitClient.getRetrofitInstance().create(MetodosApi.class);
        Call<List<HourRegisterResponse>> call = metodosApi.obtenerHorasEmpleados("Bearer " + VariablesGlobales.getToken(),
                nombreInput, WorkPositionIDobtenido);

        call.enqueue(new Callback<List<HourRegisterResponse>>() {
            @Override
            public void onResponse(Call<List<HourRegisterResponse>> call, Response<List<HourRegisterResponse>> response) {
                if(response.isSuccessful()){
                    listaEmpleados = response.body();
                    listaAdaptador = new AdaptadorLista(listaEmpleados);
                    recyclerView.setAdapter(listaAdaptador);
                }
            }

            @Override
            public void onFailure(Call<List<HourRegisterResponse>> call, Throwable t) {
                String textoerror = t.getMessage();
                Toast toasterror = Toast.makeText(getContext(), textoerror, Toast.LENGTH_SHORT);
                toasterror.show();
            }
        });
    }

    private void mostrarPorWorkPosition() {
        MetodosApi metodosApi = RetrofitClient.getRetrofitInstance().create(MetodosApi.class);
        Call<List<HourRegisterResponse>> call = metodosApi.obtenerHorasEmpleados("Bearer " + VariablesGlobales.getToken(),
                null, WorkPositionIDobtenido);

        call.enqueue(new Callback<List<HourRegisterResponse>>() {
            @Override
            public void onResponse(Call<List<HourRegisterResponse>> call, Response<List<HourRegisterResponse>> response) {
                if(response.isSuccessful()){
                    listaEmpleados = response.body();
                    listaAdaptador = new AdaptadorLista(listaEmpleados);
                    recyclerView.setAdapter(listaAdaptador);
                }
            }

            @Override
            public void onFailure(Call<List<HourRegisterResponse>> call, Throwable t) {
                String textoerror = t.getMessage();
                Toast toasterror = Toast.makeText(getContext(), textoerror, Toast.LENGTH_SHORT);
                toasterror.show();
            }
        });
    }

    private void mostrarPorNombre() {
        String nombreInput = inputNombre.getText().toString();
        MetodosApi metodosApi = RetrofitClient.getRetrofitInstance().create(MetodosApi.class);
        Call<List<HourRegisterResponse>> call = metodosApi.obtenerHorasEmpleados("Bearer " + VariablesGlobales.getToken(),
                nombreInput, null);

        call.enqueue(new Callback<List<HourRegisterResponse>>() {
            @Override
            public void onResponse(Call<List<HourRegisterResponse>> call, Response<List<HourRegisterResponse>> response) {
                if(response.isSuccessful()){
                    listaEmpleados = response.body();
                    listaAdaptador = new AdaptadorLista(listaEmpleados);
                    recyclerView.setAdapter(listaAdaptador);
                }
            }

            @Override
            public void onFailure(Call<List<HourRegisterResponse>> call, Throwable t) {
                String textoerror = t.getMessage();
                Toast toasterror = Toast.makeText(getContext(), textoerror, Toast.LENGTH_SHORT);
                toasterror.show();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}