package com.bawie.chenzhiqiang.greendao_sqlite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHodler> {
    private final Context context;
    private final List<User> list;
    private MyViewHodler hodler;

    public RecycleAdapter(Context context, List<User> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate (context, R.layout.layout_item, null);

        hodler = new MyViewHodler (view);

        return hodler;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
         hodler.userName.setText ("用户名:"+list.get (position).getName ());
         hodler.userPad.setText ("密码:"+list.get (position).getPwd ());
    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    class MyViewHodler extends RecyclerView.ViewHolder{

        private final TextView userName;
        private final TextView userPad;

        public MyViewHodler(View itemView) {
            super (itemView);

            userName = itemView.findViewById (R.id.user_name);
            userPad = itemView.findViewById (R.id.user_pwd);
        }

    }
}
