package kg.geektech.lesson35month.ui.characters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import kg.geektech.lesson35month.R;
import kg.geektech.lesson35month.base.BaseFragment;
import kg.geektech.lesson35month.common.Resource;
import kg.geektech.lesson35month.data.models.MainResponse;
import kg.geektech.lesson35month.databinding.FragmentCharactersBinding;


public class CharactersFragment extends BaseFragment<FragmentCharactersBinding> {

private FragmentCharactersBinding binding;
private CharactersAdapter adapter;
private CharactersViewModel viewModel;
 public CharactersFragment(){

 }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CharactersAdapter();
        viewModel = new ViewModelProvider(requireActivity()).get(CharactersViewModel.class);

    }



    @Override
    protected void setupViews() {

    }

    @Override
    protected void setupListeners() {

    }

    @Override
    protected void setupObservers() {
        @Override
        public void onViewCreated(@NonNull View View view;
        view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            binding.rvCharacter.setAdapter(adapter);
            viewModel.getCharacters();
            viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<Resource<MainResponse>>() {
                @Override
                public void onChanged(Resource<MainResponse> mainResponseResource) {
                    switch (mainResponseResource.status) {
                        case SUCCESS: {
                            binding.rvCharacter.setVisibility(View.VISIBLE);
                            binding.progress.setVisibility(View.GONE);
                            adapter.setCharacters(mainResponseResource.data.getResults());
                            break;
                        }
                        case ERROR: {
                            binding.rvCharacter.setVisibility(View.GONE);
                            binding.progress.setVisibility(View.GONE);
                            Snackbar.make(binding.getRoot(), mainResponseResource.msg, BaseTransientBottomBar.LENGTH_LONG).show();
                            break;
                        }
                        case LOADING: {
                            binding.rvCharacter.setVisibility(View.VISIBLE);
                            binding.progress.setVisibility(View.VISIBLE);
                            break;
                        }
                    }
                }
            });
        }

    }

    @Override
    protected void callRequests() {

    }

    @Override
    protected FragmentCharactersBinding bind() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_characters, container, false);
    }
}