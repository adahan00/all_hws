package kg.geektech.lesson35month.ui.characters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.lesson35month.common.OnItemClick;
import kg.geektech.lesson35month.data.models.Result;
import kg.geektech.lesson35month.databinding.ItemCharacterBinding;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder> {
    private List<Result> characters = new ArrayList<>();
    private OnItemClick itemClick;

    public void setItemClick(OnItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public void setCharacters(List<Result> characters) {
        this.characters = characters;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCharacterBinding binding = ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CharacterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.onBind(characters.get(position));
        holder.itemView.setOnClickListener(view -> {
        });
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {

        private ItemCharacterBinding binding;
        public CharacterViewHolder(@NonNull ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Result character) {
            binding.tvName.setText(character.getName());
            binding.tvStatus.setText(character.getStatus());
            Glide.with(binding.getRoot())
                    .load(character.getImage())
                    .centerCrop()
                    .into(binding.ivCharacter);
        }
    }
}
