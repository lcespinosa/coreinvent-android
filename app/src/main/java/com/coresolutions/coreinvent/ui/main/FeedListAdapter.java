package com.coresolutions.coreinvent.ui.main;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.Constants;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.utils.NetworkState;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

public class FeedListAdapter extends PagedListAdapter<FindAssetPojo, RecyclerView.ViewHolder> {

    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;

    private Context context;
    private NetworkState networkState;
    private AssetListAdapter.OnClickListener onClickListener;

    public FeedListAdapter(Context context, AssetListAdapter.OnClickListener clickListener) {
        super(FindAssetPojo.DIFF_CALLBACK);
        this.context = context;
        this.onClickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_PROGRESS) {
            View itemView = layoutInflater.inflate(R.layout.item_network_state, parent, false);
            NetworkStateItemViewHolder viewHolder = new NetworkStateItemViewHolder(itemView);
            return viewHolder;

        } else {
            View itemView = layoutInflater.inflate(R.layout.asset_item, parent, false);
            AssetItemViewHolder viewHolder = new AssetItemViewHolder(itemView);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AssetItemViewHolder) {
            ((AssetItemViewHolder) holder).bindTo(getItem(position), position);
            ((AssetItemViewHolder) holder).setListeners();
        } else {
            ((NetworkStateItemViewHolder) holder).bindView(networkState);
        }
    }


    private boolean hasExtraRow() {
        if (networkState != null && networkState != NetworkState.LOADED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_ITEM;
        }
    }

    public void setNetworkState(NetworkState newNetworkState) {
        NetworkState previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }


    public class AssetItemViewHolder extends RecyclerView.ViewHolder {

        private TextView type;
        private TextView model;
        private TextView tag;
        private TextView location;
        private ImageView assets_thumbnails;
        private MaterialCardView cardView;
        private int mPosition;

        public AssetItemViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
            model = itemView.findViewById(R.id.model);
            tag = itemView.findViewById(R.id.tag);
            location = itemView.findViewById(R.id.location);
            cardView = itemView.findViewById(R.id.cardView);
            assets_thumbnails = itemView.findViewById(R.id.assets_thumbnails);
        }

        public void bindTo(FindAssetPojo assets, int position) {
            type.setText(assets.getType().getName());
            tag.setText(assets.getTag().getCode());
            location.setText(assets.getLocalization());
            String brand = (assets.getAssetModel().getBrand() != null) ? assets.getAssetModel().getBrand().getName() : "Indefinido";
            String txt_model = (assets.getAssetModel().getId() != 1) ? assets.getAssetModel().getName() : "Indefinido";
            model.setText(brand + "/" + txt_model);
            mPosition = position;
            Picasso.get().load(Constants.SERVER_URL + assets.getUrl_photo()).resize(250, 200).into(assets_thumbnails);
        }

        public void setListeners() {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.OnClickListener(getItem(mPosition), mPosition);
                    }
                }
            });
        }
    }


    public class NetworkStateItemViewHolder extends RecyclerView.ViewHolder {
        private TextView error_msg;
        private ProgressBar progress_bar;


        public NetworkStateItemViewHolder(@NonNull View itemView) {
            super(itemView);
            error_msg = itemView.findViewById(R.id.error_msg);
            progress_bar = itemView.findViewById(R.id.progress_bar);
        }

        public void bindView(NetworkState networkState) {
            if (networkState != null && networkState.getStatus() == NetworkState.Status.RUNNING) {
                progress_bar.setVisibility(View.VISIBLE);
            } else {
                progress_bar.setVisibility(View.GONE);
            }

            if (networkState != null && networkState.getStatus() == NetworkState.Status.FAILED) {
                error_msg.setVisibility(View.VISIBLE);
                error_msg.setText(networkState.getMsg());
            } else {
                error_msg.setVisibility(View.GONE);
            }
        }
    }
}
