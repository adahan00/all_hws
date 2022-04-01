package kg.geektech.wheatherapp;

import android.app.Application;

import kg.geektech.wheatherapp.data.remote.RetrofitClient;
import kg.geektech.wheatherapp.data.remote.WeatherApi;
import kg.geektech.wheatherapp.repositories.MainRepositories;

public class App extends Application {
    private RetrofitClient retrofitClient;
    private WeatherApi api;
    public static MainRepositories repository;
    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        api = retrofitClient.provideApi();
        repository = new MainRepositories(api);
    }
}
