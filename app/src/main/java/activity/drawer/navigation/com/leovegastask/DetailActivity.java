package activity.drawer.navigation.com.leovegastask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import activity.drawer.navigation.com.leovegastask.Pojo.Data;
import activity.drawer.navigation.com.leovegastask.Pojo.Hero;
import activity.drawer.navigation.com.leovegastask.Pojo.HeroesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView comicsCountTextView;
    private TextView seriesCountTextView;
    private ImageView thumbnailImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nameTextView = findViewById(R.id.detailHeroName);
        descriptionTextView = findViewById(R.id.detailHeroDescription);
        comicsCountTextView = findViewById(R.id.detailHeroComicsCount);
        seriesCountTextView = findViewById(R.id.detailHeroSeriesCount);
        thumbnailImageView = findViewById(R.id.thumbnailImageView);

        int heroId = getIntent().getIntExtra("id", 0);

        HeroesApi heroesApi = HeroesApiClient.getApiService();
        String ts = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        String hash = Hasher.md5(ts + HeroesApiClient.privateKey + HeroesApiClient.publicKey);
        Call<HeroesResponse> callHeroes = heroesApi.getHeroById(heroId, ts, HeroesApiClient.publicKey, hash);

        callHeroes.enqueue(new Callback<HeroesResponse>() {
            @Override
            public void onResponse(Call<HeroesResponse> call, Response<HeroesResponse> response) {
                HeroesResponse heroesResponse = response.body();
                Data data = heroesResponse.getData();
                List<Hero> heroes = data.getResults();
                Hero hero = heroes.get(0);
                nameTextView.setText(hero.getName());
                descriptionTextView.setText(hero.getDescription());
                comicsCountTextView.setText("Comics count: " + hero.getComics().getAvailable());
                seriesCountTextView.setText("Series count: " + hero.getSeries().getAvailable());
                Picasso.get().load(""+ hero.getThumbnail().getPath()).into(thumbnailImageView);
            }

            @Override
            public void onFailure(Call<HeroesResponse> call, Throwable t) {
                Log.e("API", "onFailure", t);
            }
        });
    }
}












