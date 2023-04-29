package pe.idat.creacionescarrasco.ui.Inicio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InicioEViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public InicioEViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Info Inicio");
    }

    public LiveData<String> getText() {
        return mText;
    }
}