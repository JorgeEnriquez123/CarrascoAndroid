package pe.idat.creacionescarrasco.uiA.Inicio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InicioAViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public InicioAViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Info Inicio Admin");
    }

    public LiveData<String> getText() {
        return mText;
    }
}