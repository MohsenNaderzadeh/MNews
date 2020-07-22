package ir.developer_boy.mnews.details;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import ir.developer_boy.mnews.R;
import ir.developer_boy.mnews.data.News;

public class NewsDetailActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_NEWS = "news_key";
    private static final int VIDEO_WIDTH = 640;
    private static final int VIDEO_HEIGHT = 360;
    private News news;
    private JZVideoPlayerStandard news_details_video_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        if (getIntent().getExtras() != null) {
            if (getIntent().getParcelableExtra(EXTRA_KEY_NEWS) != null) {
                news = getIntent().getParcelableExtra(EXTRA_KEY_NEWS);
            } else {
                finish();
            }
        } else {
            finish();
        }
        setUpViews();
    }

    private void setUpViews() {

        Toolbar toolbar = findViewById(R.id.toolbar_details);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbarlayout);
        collapsingToolbarLayout.setExpandedTitleTextColor(ContextCompat.getColorStateList(this, android.R.color.transparent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColorStateList(this, android.R.color.white));
        collapsingToolbarLayout.setTitle(news.getTitle());


        if (news.isVideoNews()) {
            final FrameLayout frameLayout = findViewById(R.id.frame_details_video_container);
            frameLayout.post(new Runnable() {
                @Override
                public void run() {
                    ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
                    layoutParams.height = frameLayout.getWidth() * 254 / 400;
                    frameLayout.setLayoutParams(layoutParams);

                }
            });
            news_details_video_player = findViewById(R.id.news_details_video_player);
            frameLayout.setVisibility(View.VISIBLE);
            news_details_video_player.setUp(news.getVideo(), JZVideoPlayer.SCREEN_WINDOW_NORMAL);
            Picasso.get().load(news.getImage()).into(news_details_video_player.thumbImageView);
            news_details_video_player.fullscreenButton.setVisibility(View.GONE);
        } else {
            ImageView imageView = findViewById(R.id.iv_details_newsdetails);
            imageView.setVisibility(View.VISIBLE);
            Picasso.get().load(news.getImage()).into(imageView);

        }

        TextView tv_details_title = findViewById(R.id.tv_details_title);
        tv_details_title.setText(news.getTitle());

        TextView tv_details_date = findViewById(R.id.tv_details_date);
        tv_details_date.setText(news.getDate());


        TextView tv_details_content = findViewById(R.id.tv_details_content);
        tv_details_content.setText(news.getContent());
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayerStandard.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayerStandard.releaseAllVideos();
    }
}
