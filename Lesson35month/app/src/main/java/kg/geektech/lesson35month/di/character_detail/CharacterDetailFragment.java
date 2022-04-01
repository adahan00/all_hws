package kg.geektech.lesson35month.di.character_detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.lesson35month.R;
import kg.geektech.lesson35month.base.BaseFragment;
import kg.geektech.lesson35month.databinding.FragmentCharactersBinding;
import kg.geektech.lesson35month.ui.characters.CharactersAdapter;
import kg.geektech.lesson35month.ui.characters.CharactersViewModel;

public class CharacterDetailFragment extends Fragment {

    @AndroidEntryPoint
    public class CharactersFragment extends BaseFragment<FragmentCharactersBinding> {

        private CharactersAdapter adapter;
        private CharactersViewModel viewModel;

        public CharactersFragment() {
            // Required empty public constructor
        }


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            adapter = new CharactersAdapter();
            viewModel = new ViewModelProvider(requireActivity()).get(CharactersViewModel.class);
        }

        @Override
        protected FragmentCharactersBinding bind() {
            return FragmentCharactersBinding.inflate(getLayoutInflater());
        }

        @Override
        protected void setupViews() {
            binding.rvCharacter.setAdapter(adapter);
        }

        @Override
        protected void setupListeners() {

        }

        @Override
        protected void setupObservers() {
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
                            Snackbar.make(binding.getRoot(),
                                    mainResponseResource.msg,
                                    BaseTransientBottomBar.LENGTH_LONG)
                                    .show();
                            break;
                        }
                        case LOADING: {
                            binding.rvCharacter.setVisibility(View.GONE);
                            binding.progress.setVisibility(View.VISIBLE);
                            break;
                        }
                    }
                }
            });
        }

        @Override
        protected void callRequests() {
            viewModel.getCharacters();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_detail, container, false);
    }
}