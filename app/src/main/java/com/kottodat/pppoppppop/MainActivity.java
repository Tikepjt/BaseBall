package com.kottodat.pppoppppop;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.kottodat.pppoppppop.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    ActivityMainBinding mBinding;
    MyRecyclerViewAdapter mAdapter;
    ArrayList<RoundItem> mArrRoundResultItem;


    int rNumber[];

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        mBinding = DataBindingUtil.setContentView( this, R.layout.activity_main );


        rNumber = new int[3];

//        for ( int i = 0; i < 3; i++ )
        {
            rNumber[0] = Math.abs( (int) System.currentTimeMillis() % 10 );
            rNumber[1] = Math.abs( (int) System.currentTimeMillis() / 100 % 10 );
            rNumber[2] = Math.abs( (int) System.currentTimeMillis() / 1000 % 10 );
//            Math.abs( rNumber[i] );
        }


        Log.d( "=-=-=-", "" + rNumber[0]
                + "" + rNumber[1]
                + "" + rNumber[2]
        );


        /// 리사이클러 아답터에 전달해서 화면에 뿌릴 아이템 리스트 메모리에 생성
        mArrRoundResultItem = new ArrayList<>();

        /// 아이템과 컨텍스트를 전달하여 아답터 생성
        mAdapter = new MyRecyclerViewAdapter( this, mArrRoundResultItem );

        /// 리사이클러뷰에 아답터를 설정해줌
        mBinding.resultRv.setAdapter( mAdapter );
        /// 리사이클러뷰가 뿌려질 형태에 관해 명시해줌
        mBinding.resultRv.setLayoutManager( new LinearLayoutManager( this ) );


        /// 샘플데이터 생성
        RoundItem sample = new RoundItem();
        sample.input = "입력값";
        sample.result = "결과";

        /// 샘플데이터를 ArrayList에 추가함
        mArrRoundResultItem.add( sample );


        /// 아답터에 데이터가 변경되었으니 변경된 내용 적용하여 리스트 새로 갱신하여 출력하라고 명령 전달
        mAdapter.notifyDataSetChanged();


        mBinding.submitBtn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {

                try
                {
                    int strike = 0;
                    int ball = 0;
                    String strInput = mBinding.userInputEdt.getText().toString();

                    int nInput = Integer.parseInt( strInput );
                    int input[] = new int[3];
                    input[0] = nInput / 100;
                    input[1] = nInput % 100 / 10;
                    input[2] = nInput % 10;


                    for ( int i = 0; i < 3; i++ )
                    {
                        if ( input[i] == rNumber[i] )
                        {
                            strike += 1;
                        }
                    }
                    if ( input[0] == rNumber[1] || input[0] == rNumber[2] )
                    {
                        ball += 1;
                    }
                    if ( input[1] == rNumber[0] || input[1] == rNumber[2] )
                    {
                        ball += 1;
                    }
                    if ( input[2] == rNumber[0] || input[2] == rNumber[1] )
                    {
                        ball += 1;
                    }


                    RoundItem item = new RoundItem();
                    item.input = mBinding.userInputEdt.getText().toString();
                    String result = "";

                    if( strike == 3 )
                    {
                        result = "홈런!";
                    }
                    else if ( strike == 0 && ball == 0 )
                    {
                        result = "아웃";
                    }
                    else
                    {
                        if ( strike > 0 )
                        {
                            result += strike + "스트라이크 ";
                        }
                        if ( ball > 0 )
                        {
                            result += ball + "볼";
                        }
                    }

                    item.result = result;
                    mArrRoundResultItem.add( item );
                    mAdapter.notifyDataSetChanged();

                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }

                mBinding.userInputEdt.setText( "" );

            }
        } );


    }


}
