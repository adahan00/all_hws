package kg.geektech.newsapplite;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import kg.geektech.newsapplite.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    private Uri uri;
    private Prefs prefs;
    private FragmentProfileBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new Prefs(requireContext());
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        return binding.getRoot();


    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        uri = result.getData().getData();
                        prefs.saveImageFromProfile(String.valueOf(uri));
                        binding.ivAvatarka.setImageURI(uri);
                    }
                }
            });

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.etName.setText(prefs.getName());
        ifNull();

        binding.ivAvatarka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setAction(Intent.ACTION_PICK);
                in.setType("image/*");
                activityResultLauncher.launch(in);
            }
        });


    }

    private void ifNull() {
        binding.checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.etName.getText().toString().isEmpty()){
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .playOn(binding.etName);
                } else {
                    onStop();
                    prefs.saveName(binding.etName.getText().toString());
                    binding.etName.setText(prefs.getName());
                }
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        if (prefs.getImageFromProfile() != null)
            uri = Uri.parse(prefs.getImageFromProfile());
        Glide.with(requireContext()).load(uri).circleCrop().into(binding.ivAvatarka);

    }

    @Override
    public void onStop() {
        super.onStop();
        prefs.saveName(binding.etName.getText().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        Glide.with(requireContext()).load(uri).circleCrop().into(binding.ivAvatarka);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.profile_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.clear) {
        }
        return super.onOptionsItemSelected(item);
    }
}