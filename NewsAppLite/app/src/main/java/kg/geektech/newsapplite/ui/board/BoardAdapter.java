package kg.geektech.newsapplite.ui.board;

import android.app.Activity;
import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import kg.geektech.newsapplite.R;
import kg.geektech.newsapplite.databinding.ItemBoardBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

private ItemBoardBinding binding;

    private String[] titles = new String[]{"салам", "привет", "hello"};
    private String[] desc = new String[]{"кандай", "как дела", "how are you"};
    private int [] img1 = new int[] {R.raw.plane,R.raw.robot,R.raw.video};

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding= ItemBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull ItemBoardBinding itemView) {
         super(itemView.getRoot());
        }

        public void bind(int position) {
           binding.textTitle.setText(titles[position]);
           binding.textDesc.setText(desc[position]);
            binding.animationView.setAnimation(img1[position]);


            if (position == 2) {
                binding.start.setVisibility(View.VISIBLE);
            } else binding.start.setVisibility(View.INVISIBLE);
            binding.start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavController navController = Navigation.findNavController((Activity) view.getContext(), R.id.nav_host_fragment_activity_main);
                    navController.navigateUp();
                }
            });
        }
    }
}

