package pe.idat.creacionescarrasco.ui.Cuenta;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import pe.idat.creacionescarrasco.config.VariablesGlobales;

public class CuentaEViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CuentaEViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(VariablesGlobales.getUsuarioDeLaSesion().getNames().toString());
    }

    public LiveData<String> getText() {
        return mText;
    }
}