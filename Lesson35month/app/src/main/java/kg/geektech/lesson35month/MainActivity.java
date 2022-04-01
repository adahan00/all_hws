package kg.geektech.lesson35month;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kg.geektech.lesson35month.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);
    }
}