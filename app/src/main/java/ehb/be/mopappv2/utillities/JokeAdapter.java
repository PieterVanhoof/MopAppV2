package ehb.be.mopappv2.utillities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ehb.be.mopappv2.Model.Converter;
import ehb.be.mopappv2.Model.Joke;
import ehb.be.mopappv2.Model.JokeDatabase;
import ehb.be.mopappv2.R;

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.JokeViewHolder> {



    public class JokeViewHolder extends RecyclerView.ViewHolder{
        public TextView tvDate;
        public TextView tvJoke;
        public Button btnDelete;

        private View.OnClickListener deleteListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = getAdapterPosition();
                Joke toDelete = jokeItems.get(pos);

                JokeDatabase.getInstance(context).getJokeDAO().deleteJoke(toDelete);
                jokeItems.remove(toDelete);
                notifyDataSetChanged();
            }
        };
        public JokeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvJoke = itemView.findViewById(R.id.tv_card_joke);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnDelete.setOnClickListener(deleteListener);
        }
    }

    private List<Joke> jokeItems;
    private Context context;

    public JokeAdapter(List<Joke> jokeItems,Context context){
        this.jokeItems = jokeItems;
        this.context = context;
    }

    @NonNull
    @Override
    public JokeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.joke_card,viewGroup,false);
        return new JokeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JokeViewHolder jokeViewHolder, int i) {
        Joke joke = jokeItems.get(i);

        String dateAsString = Converter.stringFromDate(joke.getPublishDate());
        jokeViewHolder.tvDate.setText(dateAsString);
        jokeViewHolder.tvJoke.setText(joke.getJokeText());
    }

    @Override
    public int getItemCount() {
        return jokeItems.size();
    }
}
