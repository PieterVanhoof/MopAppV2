package ehb.be.mopappv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import ehb.be.mopappv2.Model.Joke;
import ehb.be.mopappv2.Model.JokeDatabase;
import ehb.be.mopappv2.utillities.JokeAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvJokes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //welke container, recycler view
        rvJokes = findViewById(R.id.rv_jokes);
        //welke data uit DAO
        List<Joke> items = JokeDatabase.getInstance(getApplicationContext()).getJokeDAO().selectAllJoke();
        //adapter, hoe moet alles opgevuld worden
        JokeAdapter jokeAdapter = new JokeAdapter(items,getApplicationContext());
        //welke layout
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        //alles weergeven
        rvJokes.setAdapter(jokeAdapter);
        rvJokes.setLayoutManager(gridLayoutManager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mi_add :
                Intent intent = new Intent(getApplicationContext(),AddJokeActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
