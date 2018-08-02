package com.kottodat.pppoppppop;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;

import com.kottodat.pppoppppop.databinding.RowResultItemBinding;

import java.util.ArrayList;

/**
 * Created by hanago on 2018. 8. 2..
 */


/**
 * 게임 메인화면에 출력하는데 사용 할 리사이클러 아답터
 */
class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>
{
    Context mContext;
    ArrayList<RoundItem> mArrRoundResultItem;


    /// 생성자
    public MyRecyclerViewAdapter( Context context, ArrayList<RoundItem> arrRoundResultItem )
    {
        mContext = context;
        mArrRoundResultItem = arrRoundResultItem;
    }


    /// 리스트뷰에 View객체 한칸이 새롭게 생성 될 떄 호출됨
    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType )
    {
        LayoutInflater inflater = LayoutInflater.from( mContext );
        RowResultItemBinding binding = DataBindingUtil.inflate( inflater, R.layout.row_result_item, parent, true );

        MyViewHolder holder = new MyViewHolder( binding );

        return holder;
    }


    /// 리스트뷰에 실제로 뷰를 출력하기 직전에 호출됨
    @Override
    public void onBindViewHolder( MyViewHolder holder, int position )
    {
        RoundItem item = mArrRoundResultItem.get( position );
        RowResultItemBinding binding = holder.mBiding ;

        binding.roundInputTv.setText( item.input );
        binding.roundResultTv.setText( item.result );
    }

    /// 리스트뷰가 출력 할 아이템의 갯수를 리턴함
    @Override
    public int getItemCount()
    {
        return mArrRoundResultItem.size();
    }






    class MyViewHolder extends RecyclerView.ViewHolder
    {
        RowResultItemBinding mBiding;
        public MyViewHolder( RowResultItemBinding binding )
        {
            super( binding.getRoot() );

            mBiding = binding;
        }
    }

}

