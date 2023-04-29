package pe.idat.creacionescarrasco.Interface;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import pe.idat.creacionescarrasco.model.CrearCuentaRequest;
import pe.idat.creacionescarrasco.model.HourRegisterResponse;
import pe.idat.creacionescarrasco.model.LoginRequest;
import pe.idat.creacionescarrasco.model.LoginResponse;
import pe.idat.creacionescarrasco.model.WorkPosition;
import pe.idat.creacionescarrasco.model.WorkPositionRequest;
import pe.idat.creacionescarrasco.model.registro.AsistenciasUser;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MetodosApi {

    @POST("auth/login")
    Call<LoginResponse> iniciarSesion(@Body LoginRequest loginRequest);

    @GET("hour-register/reg-entrada/{id}/{date}/{hour}")
    Call<AsistenciasUser> regentrada (@Header("Authorization") String auth, @Path("id") String id, @Path("date") String date, @Path("hour") String hour);

    @GET("hour-register/reg-ini-alm/{id}/{date}/{hour}")
    Call<AsistenciasUser> reginitalm (@Header("Authorization") String auth, @Path("id") String id, @Path("date") String date, @Path("hour") String hour);

    @GET("hour-register/reg-fin-alm/{id}/{date}/{hour}")
    Call<AsistenciasUser> regfinalm (@Header("Authorization") String auth, @Path("id") String id, @Path("date") String date, @Path("hour") String hour);

    @GET("hour-register/reg-salida/{id}/{date}/{hour}")
    Call<AsistenciasUser> regsalida (@Header("Authorization") String auth, @Path("id") String id, @Path("date") String date, @Path("hour") String hour);

    @GET("hour-register/{id}/{date}")
    Call<AsistenciasUser> llamarvistaregistro(@Path("id") String id, @Path("date") String date, @Header("Authorization") String auth);

    @POST("work-position")
    Call<WorkPositionRequest> creacionPosicionTrabajo(@Header("Authorization") String auth, @Body WorkPositionRequest workPositionRequest);

    @GET("work-position?all=")
    Call<List<WorkPosition>> obtenerPosicionesTrabajo(@Header("Authorization") String auth);

    @POST("auth/register")
    Call<LoginResponse> creacionCuenta(@Header("Authorization") String auth, @Body CrearCuentaRequest crearCuentaRequest);
    //Se esta usando el LoginResponse porque el response tiene la misma estructura de esa clase

    @GET("hour-register")
    Call<List<HourRegisterResponse>> obtenerHorasEmpleados(@Header("Authorization") String auth, @Query("userNamesComplex") String userNamesComplex, @Query("workPositionID") String idWorkPosition);
}
