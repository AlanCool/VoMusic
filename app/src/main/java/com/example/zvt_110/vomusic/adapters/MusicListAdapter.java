package com.example.zvt_110.vomusic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zvt_110.vomusic.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        Glide.with(mContext).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573800274933&di=d38349fee8da86d4c9b4c10978cfdc53&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01d00c58fd8378a8012160f72a806d.jpg%401280w_1l_2o_100sh.jpg").into(viewHolder.ivIcon);
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
