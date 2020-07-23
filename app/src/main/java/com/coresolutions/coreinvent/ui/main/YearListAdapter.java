package com.coresolutions.coreinvent.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.data.pojos.Year;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class YearListAdapter extends RecyclerView.Adapter<YearListAdapter.YearViewHolder> {


    public interface OnClickListener {
        void OnClickListener(Year years, int post);
    }

    private final LayoutInflater layoutInflater;
    private Context mcontext;
    private List<Year> mYears;
    private OnClickListener onClickListener;

    public YearListAdapter(Context context, OnClickListener clickListener) {
        layoutInflater = LayoutInflater.from(context);
        mcontext = context;
        this.onClickListener = clickListener;
    }

    public YearListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        mcontext = context;
        this.onClickListener = null;
    }

    @NonNull
    @Override
    public YearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.year_item, parent, false);
        YearViewHolder viewHolder = new YearViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull YearViewHolder holder, int position) {
        if (mYears != null) {
            Year years = mYears.get(position);
            holder.setData(years, position);
            holder.setListeners();
        }

    }

    @Override
    public int getItemCount() {
        if (mYears != null) {
            return mYears.size();
        } else return 0;
    }

    public void setYears(List<Year> years) {
        mYears = years;
        notifyDataSetChanged();
    }

    public void setSelected(int selected) {
        notifyDataSetChanged();
    }


    public class YearViewHolder extends RecyclerView.ViewHolder {
        private TextView year_text;
        private MaterialCardView cardView;
        private int mPosition;

        public YearViewHolder(@NonNull View itemView) {
            super(itemView);
            year_text = itemView.findViewById(R.id.year_text);
            cardView = itemView.findViewById(R.id.cardView);


        }

        public void setData(Year years, int position) {
            year_text.setText(years.getName());
            mPosition = position;
        }

        public void setListeners() {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.OnClickListener(mYears.get(mPosition), mPosition);
                    }
                }
            });
        }

    }
}
