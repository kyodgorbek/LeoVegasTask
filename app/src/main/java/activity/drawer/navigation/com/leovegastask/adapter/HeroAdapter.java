package activity.drawer.navigation.com.leovegastask.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.List;
import activity.drawer.navigation.com.leovegastask.DetailActivity;
import activity.drawer.navigation.com.leovegastask.Pojo.Hero;
import activity.drawer.navigation.com.leovegastask.R;

public  class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.CustomViewHolder> implements Filterable {

    private Context context;
    private List<Hero> heros;

    public HeroAdapter(Context context, List<Hero> heros) {
        this.context = context;
        this.heros = heros;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hero_list_item, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        final Hero hero = heros.get(position);
        holder.heroName.setText(hero.getName());
        holder.heroName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = hero.getId();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return heros.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView heroName;

        public CustomViewHolder(View view) {
            super(view);
            heroName = view.findViewById(R.id.heroName);
        }
    }
}