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
import ir.developer_boy.mnews.data.Banners;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {

    private List<Banners> bannersList = new ArrayList<>();

    public BannerAdapter(List<Banners> bannersList) {
        this.bannersList = bannersList;
    }

    @Override
    public BannerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false));
    }

    @Override
    public void onBindViewHolder(BannerViewHolder holder, int position) {
        holder.bindBanner(bannersList.get(position));
    }

    @Override
    public int getItemCount() {
        return bannersList.size();
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        private ImageView bannerImageView;
        private TextView bannerSourcetv;
        private TextView bannertitletv;

        public BannerViewHolder(View itemView) {
            super(itemView);
            bannerImageView = itemView.findViewById(R.id.iv_banner_image);
            bannerSourcetv = itemView.findViewById(R.id.tv_banner_source);
            bannertitletv = itemView.findViewById(R.id.tv_banner_title);
        }

        public void bindBanner(Banners banner) {
            Picasso.get().load(banner.getImage()).into(bannerImageView);
            bannerSourcetv.setText(banner.getSource());
            bannertitletv.setText(banner.getTitle());
        }
    }
}
