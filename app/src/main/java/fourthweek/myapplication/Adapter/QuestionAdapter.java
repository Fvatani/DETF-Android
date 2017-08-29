package fourthweek.myapplication.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fourthweek.myapplication.Activity.ThirdActivity;
import fourthweek.myapplication.Model.Question;
import fourthweek.myapplication.R;


public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>
{

    List<Question> questions;
    Context mContext;

    public QuestionAdapter(List<Question> questions, Context mContext)
    {
        this.questions = questions;
        this.mContext = mContext;
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list_row, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, final int position)
    {
        final Question question = questions.get(position);
        holder.txtTitle.setText("Title: "+question.getTitle()+"\n");
        holder.txtTag.setText("Tags: " + question.getTags() +"\n");

        if(question.getsolve().equals(true)) {
            holder.iconImage.setImageResource(R.drawable.images);
        }
        else if(question.getsolve().equals(false))
        {
            holder.iconImage.setImageResource(R.drawable.images2);
        }

        Picasso.with(mContext).load(questions.get(position).getImageUrl().toString()).into(holder.proImage);

        holder.Layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i;
                i = new Intent(mContext, ThirdActivity.class);
                String Link , imageURl,score,title,name;

                title=question.getTitle();
                imageURl=question.getImageUrl();
                Link=question.getLink();
                score=question.getscore().toString();
                name=question.getName();



                i.putExtra("Title", title);
                i.putExtra("imageURL",  imageURl);
                i.putExtra("link",  Link);
                i.putExtra("score",  score);
                i.putExtra("name", name);


                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return questions.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txtTitle;
        public TextView txtTag;
        public ImageView proImage;
        public ImageView iconImage;
        public LinearLayout Layout;

        public QuestionViewHolder(View itemView)
        {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtTag = (TextView) itemView.findViewById(R.id.txtTag);

            proImage=(ImageView)itemView.findViewById(R.id.ProImage) ;
            iconImage=(ImageView)itemView.findViewById(R.id.IconImage) ;


            Layout = (LinearLayout) itemView.findViewById(R.id.rltv);
        }
    }


    public void removeItem(int position){
        questions.remove(position);
        notifyItemRemoved(position);
    }

}
