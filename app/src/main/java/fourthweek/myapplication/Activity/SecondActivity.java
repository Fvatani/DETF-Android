package fourthweek.myapplication.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fourthweek.myapplication.Adapter.QuestionAdapter;
import fourthweek.myapplication.Model.Question;
import fourthweek.myapplication.Model.StackOverflowModel;
import fourthweek.myapplication.R;
import fourthweek.myapplication.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SecondActivity extends AppCompatActivity {

    private List<Question> questions = new ArrayList<>();
    private RecyclerView recyclerView;
    private QuestionAdapter adapter;
    private String Tags;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        adapter = new QuestionAdapter(questions,SecondActivity.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(SecondActivity.this));
        recyclerView.setAdapter(adapter);
        setData();

        Button button = (Button) findViewById(R.id.buttonSearch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              setCustomData();

            }

        });
    }

    private void setData()
    {

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.stackexchange.com/2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        Call<StackOverflowModel> serviceSlackModels = service.getStackModel();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();

        serviceSlackModels.enqueue(new Callback<StackOverflowModel>() {
            @Override
            public void onResponse(Call<StackOverflowModel> call, Response<StackOverflowModel> response) {

                StackOverflowModel model = response.body();

                for(int i=0 ; i<10 ; i++){

                    int size;
                    size=model.getItems().get(i).getTags().size();
                    for(int j=0 ; j<size ; j++)
                    {
                       Tags=model.getItems().get(i).getTags().get(j).toString();
                    }

                    questions.add(new Question(model.getItems().get(i).getOwner().getProfileImage(),model.getItems().get(i).getTitle(),Tags, model.getItems().get(i).getIsAnswered(), model.getItems().get(i).getScore(),model.getItems().get(i).getLink(),model.getItems().get(i).getLink(),model.getItems().get(i).getOwner().getDisplayName()));
                    adapter.notifyDataSetChanged();

                }

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<StackOverflowModel> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(SecondActivity.this,"on Failure",Toast.LENGTH_LONG).show();


            }
        });

    }




    private void setCustomData()
    {
        final TextView words = (TextView) findViewById(R.id.editSearch);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.stackexchange.com/2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        Call<StackOverflowModel> serviceSlackModels = service.getStackModel();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();

        serviceSlackModels.enqueue(new Callback<StackOverflowModel>() {
            @Override
            public void onResponse(Call<StackOverflowModel> call, Response<StackOverflowModel> response) {

                StackOverflowModel model = response.body();
                Toast.makeText(SecondActivity.this,"search...",Toast.LENGTH_LONG).show();


                for(int i=0 ; i<10 ; i++){

                    int size;
                    Boolean find;find=false;
                    size=model.getItems().get(i).getTags().size();
                    for(int j=0 ; j<size ; j++)
                    {
                        Tags=model.getItems().get(i).getTags().get(j).toString();
                        if(Tags.toString().equals(words.toString())) {
                            find=true;
                        }
                    }

                    if(find.equals(false)) {
                        // ((QuestionAdapter)recyclerView.getAdapter()).removeItem(i);
                        questions.remove(new Question(model.getItems().get(i).getOwner().getProfileImage(), model.getItems().get(i).getTitle(), Tags, model.getItems().get(i).getIsAnswered(), model.getItems().get(i).getScore(), model.getItems().get(i).getLink(), model.getItems().get(i).getLink(), model.getItems().get(i).getOwner().getDisplayName()));
                        adapter.notifyItemRemoved(i);
                    }
                }

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<StackOverflowModel> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(SecondActivity.this,"on Failure",Toast.LENGTH_LONG).show();


            }
        });
    }

}
