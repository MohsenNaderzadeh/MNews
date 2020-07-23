package ir.developer_boy.mnews.home.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import ir.developer_boy.mnews.R;
import ir.developer_boy.mnews.data.News;
import ir.developer_boy.mnews.details.NewsDetailActivity;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<News> newsList = new ArrayList<>();
    private int PendingPosition;

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

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateNews(News news) {
        this.newsList.set(PendingPosition, news);
        notifyItemChanged(PendingPosition);
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

        public void bindNews(final News news) {
            Picasso.get().load(news.getImage()).into(iv_news_image);
            tv_news_title.setText(news.getTitle());
            tv_news_date.setText(news.getDate());
            boolean newsindicator = news.isVideoNews();
            videoIndicator.setVisibility(news.isVideoNews() ? View.VISIBLE : View.GONE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PendingPosition = getAdapterPosition();
                    Intent intent = new Intent(itemView.getContext(), NewsDetailActivity.class);
                    intent.putExtra(NewsDetailActivity.EXTRA_KEY_NEWS, news);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
