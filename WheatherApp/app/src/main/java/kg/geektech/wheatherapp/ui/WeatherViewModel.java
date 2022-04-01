package kg.geektech.wheatherapp.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import kg.geektech.wheatherapp.App;
import kg.geektech.wheatherapp.common.Resource;
import kg.geektech.wheatherapp.data.models.MainResponse;

public class WeatherViewModel extends ViewModel {
    public LiveData<Resource<MainResponse>> liveData;
    public void getWeather() {
        liveData = App.repository.getCharacters();
    }

}
