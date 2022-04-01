package kg.geektech.lesson35month.ui.characters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.lesson35month.App;
import kg.geektech.lesson35month.common.Resource;
import kg.geektech.lesson35month.data.models.MainResponse;
import kg.geektech.lesson35month.data.repositories.MainRepository;

@HiltViewModel
public class CharactersViewModel extends ViewModel {
    public LiveData<Resource<MainResponse>> liveData;
private MainRepository repository;
    @Inject
    public CharactersViewModel(LiveData<Resource<MainResponse>> liveData, MainRepository repository) {
        this.liveData = liveData;
        this.repository = repository;
    }




    public void getCharacters(){
        liveData = repository.getCharacters();
    }
}
