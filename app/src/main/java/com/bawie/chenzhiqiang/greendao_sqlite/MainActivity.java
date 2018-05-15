package com.bawie.chenzhiqiang.greendao_sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawie.chenzhiqiang.greendao.gen.DaoMaster;
import com.bawie.chenzhiqiang.greendao.gen.DaoSession;
import com.bawie.chenzhiqiang.greendao.gen.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/*https://blog.csdn.net/u014752325/article/details/53996232*/


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.userPwd)
    EditText userPwd;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.delete)
    Button delete;
    @BindView(R.id.update)
    Button update;
    @BindView(R.id.select)
    Button select;
    @BindView(R.id.recyle)
    RecyclerView recyle;
    private UserDao userDao;
    private User user;
    private List<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        ButterKnife.bind (this);

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper (MyApplication.getContext (), "harry-db", null);
        DaoMaster daoMaster = new DaoMaster (devOpenHelper.getWritableDatabase ());
        DaoSession daoSession = daoMaster.newSession ();
        userDao = daoSession.getUserDao ();

        /*userName.setText (user.getName ());
        userPwd.setText (user.getPwd ());*/

        add.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                insertuser ();
                Toast.makeText (MainActivity.this, "4444444444444444444444", Toast.LENGTH_SHORT).show ();
            }
        });

        select.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //查询全部
                list = userDao.queryBuilder ().list ();

                RecycleAdapter adapter = new RecycleAdapter (MainActivity.this, list);

                adapter.notifyDataSetChanged ();

                recyle.setLayoutManager (new LinearLayoutManager (MainActivity.this));

                recyle.setAdapter (adapter);
            }
        });

        update.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String name = userName.getText ().toString ();

                User user = userDao.queryBuilder ().where (UserDao.Properties.Name.eq (name)).build ().unique ();
                Toast.makeText (MainActivity.this, ""+name, Toast.LENGTH_SHORT).show ();
                if(user != null){
                    user.setName ("hahah");

                    userDao.update (user);
                }

                Toast.makeText (MainActivity.this,"修改成功",Toast.LENGTH_SHORT).show ();
            }

        });

        delete.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String name = userName.getText ().toString ();

//                userDao.deleteByKey ();
                userDao.queryBuilder ().where (UserDao.Properties.Name.eq (name)).buildDelete ().executeDeleteWithoutDetachingEntities ();

                Toast.makeText (MainActivity.this,"删除成功",Toast.LENGTH_SHORT).show ();
            }
        });
    }

    private void insertuser() {
        String name = userName.getText ().toString ();
        String pwd = userPwd.getText ().toString ();

        user = new User ( null,name, pwd);

        userDao.insert (user);

        Toast.makeText (MainActivity.this, "4111111111111444444444444", Toast.LENGTH_SHORT).show ();


    }
}
