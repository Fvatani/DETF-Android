package fourthweek.myapplication.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import fourthweek.myapplication.R;


public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");
        String imageURl = intent.getStringExtra("imageURL");
        final  String link = intent.getStringExtra("link");
        String score = intent.getStringExtra("score");
        String name = intent.getStringExtra("name");


        CircleImageView image= (CircleImageView) findViewById(R.id.profileImage);
        Picasso.with(ThirdActivity.this).load(imageURl).into(image);


        TextView Title = (TextView) findViewById(R.id.textTitle);
        Title.setText("Title : " + title +"\n");

        TextView Score = (TextView) findViewById(R.id.textScore);
        Score.setText("Score : " + score +"\n");

        TextView Name = (TextView) findViewById(R.id.textName);
        Name.setText("Name : " + name +"\n");


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                startActivity(intent);

            }


        });

    }
}
