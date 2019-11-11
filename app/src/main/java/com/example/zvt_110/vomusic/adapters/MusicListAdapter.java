package com.example.zvt_110.vomusic.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zvt_110.vomusic.R;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    private Context mContext;
    private View mItemView;
    private RecyclerView mRv;

    public MusicListAdapter(Context mContext,RecyclerView recyclerView) {
        this.mContext = mContext;
        mRv=recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mItemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_music, viewGroup, false);
        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            setRecyclerViewHeight();
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    private void setRecyclerViewHeight() {
        RecyclerView.LayoutParams itemViemLp = (RecyclerView.LayoutParams) mItemView.getLayoutParams();
        int itemCount = getItemCount();
        int recyclerViewHeight = itemViemLp.height * itemCount;
        LinearLayout.LayoutParams rvLp= (LinearLayout.LayoutParams) mRv.getLayoutParams();
        rvLp.height=recyclerViewHeight;
        mRv.setLayoutParams(rvLp);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView ivIcon;
        TextView tvName, tvAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAuthor = itemView.findViewById(R.id.tv_author);
        }
    }

}
