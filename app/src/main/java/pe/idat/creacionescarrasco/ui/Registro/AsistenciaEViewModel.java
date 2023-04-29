package pe.idat.creacionescarrasco.ui.Registro;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AsistenciaEViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AsistenciaEViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Registro de Asistencia");
    }

    public LiveData<String> getText() {
        return mText;
    }
}