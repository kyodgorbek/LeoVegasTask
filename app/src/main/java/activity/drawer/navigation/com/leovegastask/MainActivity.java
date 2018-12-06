package activity.drawer.navigation.com.leovegastask;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import activity.drawer.navigation.com.leovegastask.Pojo.HeroesResponse;
import activity.drawer.navigation.com.leovegastask.Pojo.Data;
import activity.drawer.navigation.com.leovegastask.Pojo.Hero;
import activity.drawer.navigation.com.leovegastask.adapter.HeroAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static activity.drawer.navigation.com.leovegastask.HeroesApiClient.privateKey;
import static activity.drawer.navigation.com.leovegastask.HeroesApiClient.publicKey;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HeroAdapter heroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(eLayoutManager);

        HeroesApi heroesApi = HeroesApiClient.getApiService();
        String ts = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        String hash = Hasher.md5(ts + privateKey + publicKey);
        Call<HeroesResponse> callHeroes = heroesApi.getHeroes(ts, publicKey, hash);

        callHeroes.enqueue(new Callback<HeroesResponse>() {
            @Override
            public void onResponse(Call<HeroesResponse> call, Response<HeroesResponse> response) {
                HeroesResponse heroesResponse = response.body();
                Data data = heroesResponse.getData();
                List<Hero> heroes = data.getResults();
                heroAdapter = new HeroAdapter(getApplicationContext(), heroes);
                recyclerView.setAdapter(heroAdapter);
            }

            @Override
            public void onFailure(Call<HeroesResponse> call, Throwable t) {
                Log.e("API", "onFailure", t);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                HeroesApi leoVegasInterface = HeroesApiClient.getApiService();
                String ts = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
                String hash = Hasher.md5(ts + privateKey + publicKey);
                Call<HeroesResponse> callHeroes = leoVegasInterface.findHeroByName(ts, publicKey, hash, query);

                callHeroes.enqueue(new Callback<HeroesResponse>() {
                    @Override
                    public void onResponse(Call<HeroesResponse> call, Response<HeroesResponse> response) {
                        HeroesResponse leoVegas = response.body();
                        Data data = leoVegas.getData();
                        List <Hero> heroes = data.getResults();
                        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
                        heroAdapter = new HeroAdapter(getApplicationContext(),heroes);
                        RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(eLayoutManager);
                        recyclerView.setAdapter(heroAdapter);
                    }

                    @Override
                    public void onFailure(Call<HeroesResponse> call, Throwable t) {

                    }
                });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                // heroAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}

