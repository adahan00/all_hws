package kg.geektech.wheatherapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import kg.geektech.wheatherapp.R;
import kg.geektech.wheatherapp.data.models.MainResponse;
import kg.geektech.wheatherapp.databinding.FragmentWeatherBinding;


public class WeatherFragment extends Fragment {
    private WeatherViewModel viewModel;
    private FragmentWeatherBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        viewModel.getWeather();
        viewModel.liveData.observe(getViewLifecycleOwner(), mainResponseResource -> {
            switch (mainResponseResource.status) {
                case SUCCESS: {
                    loadData(mainResponseResource.data);
                    binding.progress.setVisibility(View.GONE);
                    break;
                }
                case ERROR: {
                    binding.progress.setVisibility(View.GONE);
                    break;
                }
                case LOADING: {
                    binding.progress.setVisibility(View.VISIBLE);

                    break;
                }
            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();
        Calendar uh = Calendar.getInstance();
        int timeOfDay = uh.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 12) {
            binding.lightOutside.setImageResource(R.drawable.city_night);
        } else if (timeOfDay >= 12 && timeOfDay < 24) {
            binding.lightOutside.setImageResource(R.drawable.city_day);
        }
    }

    private void loadData(MainResponse response) {
        String urlImg = "https://openweathermap.org/img/wn/" + response.getWeather().get(0).getIcon() + ".png";
        String humidity = String.valueOf(response.getMain().getHumidity())+ "%";
        String barometer = String.valueOf(response.getMain().getPressure())+ "mBar";
        String mainWeather = response.getWeather().get(0).getMain();
        String tempNow = String.valueOf((int) Math.round(response.getMain().getTemp()));

        Glide.with(requireActivity()).load(urlImg).into(binding.weatherImg);
        
        binding.windPower.setText(String.valueOf((int) Math.round(response.getWind().getSpeed()))+" km/h");
        binding.humidityNumber.setText(humidity);
        binding.temp.setText(tempNow);
        binding.pressurePower.setText(barometer);
        binding.weather.setText(mainWeather);
        binding.countryCity.setText(response.getName());
        binding.sunriseTime.setText(getDate(response.getSys().getSunrise(), "hh:mm") + " AM");
        binding.sunsetTime.setText(getDate(response.getSys().getSunset(), "hh:mm") + " PM");
        binding.dayTime.setText(getDate(response.getDt(), "hh:mm"));
//        binding.date.setText(getDate(response.getDt() , "E, dd MM yyyy"));


        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("E, dd MM yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        binding.date.setText(dateText);
    }
    public static String getDate(Integer milliSeconds, String dateFormat) {

        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}