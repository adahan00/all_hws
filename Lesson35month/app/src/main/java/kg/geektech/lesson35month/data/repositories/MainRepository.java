package kg.geektech.lesson35month.data.repositories;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import kg.geektech.lesson35month.common.Resource;
import kg.geektech.lesson35month.data.models.MainResponse;
import kg.geektech.lesson35month.data.remote.RickAndMortyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository{

    private RickAndMortyApi api;
@Inject
    public MainRepository(RickAndMortyApi api) {
        this.api = api;

    }
    public MutableLiveData<Resource<MainResponse>> getCharacters(){
        MutableLiveData<Resource<MainResponse>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getChararcters().enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {

                if (response.isSuccessful() && response.body() != null){
                    liveData.setValue(Resource.success(response.body()));
                }else{
                    liveData.setValue(Resource.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));

            }
        });
        return liveData;
    }
}
