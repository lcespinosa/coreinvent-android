package com.coresolutions.coreinvent.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class AssetListAdapter extends PagedListAdapter<FindAssetPojo, AssetListAdapter.FindAssetPojoViewHolder> {


    public interface OnClickListener {
        void OnClickListener(FindAssetPojo assets, int post);
    }

    private final LayoutInflater layoutInflater;
    private Context mcontext;
    private List<FindAssetPojo> mFindAssetPojos;
    private OnClickListener onClickListener;

    public AssetListAdapter(Context context, OnClickListener clickListener) {
        super(DIFF_CALLBACK);
        layoutInflater = LayoutInflater.from(context);
        mcontext = context;
        this.onClickListener = clickListener;
    }

//    public AssetListAdapter(Context context) {
//        super(DIFF_CALLBACK);
//        layoutInflater = LayoutInflater.from(context);
//        mcontext = context;
//        this.onClickListener = null;
//    }

    @NonNull
    @Override
    public FindAssetPojoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.asset_item, parent, false);
        FindAssetPojoViewHolder viewHolder = new FindAssetPojoViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FindAssetPojoViewHolder holder, int position) {
        if (mFindAssetPojos != null) {
            FindAssetPojo assets = mFindAssetPojos.get(position);
            holder.setData(assets, position);
            holder.setListeners();
        }


    }
//
//    @Override
//    public int getItemCount() {
//        if (mFindAssetPojos != null) {
//            return mFindAssetPojos.size();
//        } else return 0;
//    }

    public void setFindAssetPojos(List<FindAssetPojo> assets) {
        mFindAssetPojos = assets;
        notifyDataSetChanged();
    }

    public void setSelected(int selected) {
        notifyDataSetChanged();
    }

    private static DiffUtil.ItemCallback<FindAssetPojo> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<FindAssetPojo>() {
                @Override
                public boolean areItemsTheSame(FindAssetPojo oldItem, FindAssetPojo newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(FindAssetPojo oldItem, FindAssetPojo newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }
            };


    public class FindAssetPojoViewHolder extends RecyclerView.ViewHolder {
        private TextView type;
        private TextView model;
        private TextView tag;
        private TextView location;
        private ImageView assets_thumbnails;
        private MaterialCardView cardView;
        private int mPosition;

        public FindAssetPojoViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
            model = itemView.findViewById(R.id.model);
            tag = itemView.findViewById(R.id.tag);
            location = itemView.findViewById(R.id.location);
            cardView = itemView.findViewById(R.id.cardView);
            assets_thumbnails = itemView.findViewById(R.id.assets_thumbnails);
        }

        public void setData(FindAssetPojo assets, int position) {
            type.setText(assets.getType().getName());
            tag.setText(assets.getTag().getCode());
            location.setText(assets.getLocalization());
            String brand = (assets.getAssetModel().getBrand() != null) ? assets.getAssetModel().getBrand().getName() : "Indefinida";
            String txt_model = (assets.getAssetModel().getId() != 1) ? assets.getAssetModel().getName() : "Indefinida";
            model.setText(brand + "/" + txt_model);
            String value = assets.getInventoryValue() == null ? "0" : assets.getInventoryValue();
            mPosition = position;
        }

        public void setListeners() {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.OnClickListener(mFindAssetPojos.get(mPosition), mPosition);
                    }
                }
            });
        }

    }
}
