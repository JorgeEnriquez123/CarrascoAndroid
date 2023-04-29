package pe.idat.creacionescarrasco.config;

import pe.idat.creacionescarrasco.model.LoginResponse;
import pe.idat.creacionescarrasco.model.User;
import pe.idat.creacionescarrasco.model.registro.AsistenciasUser;

public class VariablesGlobales {
    public static String Token;

    public static User usuarioDeLaSesion;
    public static AsistenciasUser asistenciasUser;

    public static String getToken() {
        return Token;
    }

    public static void setToken(String token) {
        Token = token;
    }

    public static User getUsuarioDeLaSesion() {
        return usuarioDeLaSesion;
    }

    public static void setUsuarioDeLaSesion(User usuarioDeLaSesion) {
        VariablesGlobales.usuarioDeLaSesion = usuarioDeLaSesion;
    }

    public static AsistenciasUser getAsistenciasUser() {
        return asistenciasUser;
    }

    public static void setAsistenciasUser(AsistenciasUser asistenciasUser) {
        VariablesGlobales.asistenciasUser = asistenciasUser;
    }
}
