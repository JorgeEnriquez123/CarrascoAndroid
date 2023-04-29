package pe.idat.creacionescarrasco.uiA.Lista;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListaAViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ListaAViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Lista de Asistencias");
    }

    public LiveData<String> getText() {
        return mText;
    }
}