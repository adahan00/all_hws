package kg.geektech.lesson35month.data.remote;

import kg.geektech.lesson35month.data.models.MainResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RickAndMortyApi {
    @GET("character")
    Call<MainResponse> getChararcters();
}
