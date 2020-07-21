package ir.developer_boy.mnews.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ir.developer_boy.mnews.R;
import ir.developer_boy.mnews.data.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<News> newsList = new ArrayList<>();

    public NewsAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.bindNews(newsList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_news_image;
        private TextView tv_news_title;
        private TextView tv_news_date;
        private View videoIndicator;

        public NewsViewHolder(View itemView) {
            super(itemView);
            iv_news_image = itemView.findViewById(R.id.iv_news_image);
            tv_news_title = itemView.findViewById(R.id.tv_news_title);
            tv_news_date = itemView.findViewById(R.id.tv_news_date);
            videoIndicator = itemView.findViewById(R.id.iv_news_video_Indicator);
        }

        public void bindNews(News news) {
            Picasso.get().load(news.getImage()).into(iv_news_image);
            tv_news_title.setText(news.getTitle());
            tv_news_date.setText(news.getDate());
            videoIndicator.setVisibility(news.isVideoNews() ? View.VISIBLE : View.GONE);
        }
    }
}
