package pe.idat.creacionescarrasco.uiA.Cuenta;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CuentaAViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CuentaAViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Info Cuenta Admin");
    }

    public LiveData<String> getText() {
        return mText;
    }
}