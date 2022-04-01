package kg.geektech.lesson35month.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import kg.geektech.lesson35month.data.remote.RickAndMortyApi;
import kg.geektech.lesson35month.data.repositories.MainRepository;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Provides
    public static OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }
    @Provides
    public static Retrofit provideRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
    @Provides
    @Singleton
    public static RickAndMortyApi provideApi (Retrofit retrofit){
        return retrofit.create(RickAndMortyApi.class);
    }
    @Provides
    @Singleton
    public static MainRepository provideMainRepository(RickAndMortyApi api){
        return new MainRepository(api);
    }
}
