package com.coresolutions.coreinvent.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class AssetListAdapter extends RecyclerView.Adapter<AssetListAdapter.FindAssetPojoViewHolder> {


    public interface OnClickListener {
        void OnClickListener(FindAssetPojo assets, int post);
    }

    private final LayoutInflater layoutInflater;
    private Context mcontext;
    private List<FindAssetPojo> mFindAssetPojos;
    private OnClickListener onClickListener;

    public AssetListAdapter(Context context, OnClickListener clickListener) {
        layoutInflater = LayoutInflater.from(context);
        mcontext = context;
        this.onClickListener = clickListener;
    }

    public AssetListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        mcontext = context;
        this.onClickListener = null;
    }

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

    @Override
    public int getItemCount() {
        if (mFindAssetPojos != null) {
            return mFindAssetPojos.size();
        } else return 0;
    }

    public void setFindAssetPojos(List<FindAssetPojo> assets) {
        mFindAssetPojos = assets;
        notifyDataSetChanged();
    }

    public void setSelected(int selected) {
        notifyDataSetChanged();
    }


    public class FindAssetPojoViewHolder extends RecyclerView.ViewHolder {
        private TextView type;
        private TextView tag;
        private TextView location;
        private TextView inventory_value;
        private MaterialCardView cardView;
        private int mPosition;

        public FindAssetPojoViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
            tag = itemView.findViewById(R.id.tag);
            location = itemView.findViewById(R.id.location);
            inventory_value = itemView.findViewById(R.id.inventory_value);
            cardView = itemView.findViewById(R.id.cardView);


        }

        public void setData(FindAssetPojo assets, int position) {
            type.setText(assets.getHeadTitle());
            tag.setText(assets.getTag().getCode());
            location.setText(assets.getLocalization());
            String value = assets.getInventoryValue() == null ? "0" : assets.getInventoryValue();
            inventory_value.setText(value + "€");
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
