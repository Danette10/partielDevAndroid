package fr.dansebag.partiel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class UsersAdapter extends BaseAdapter {

    private List<Users> users;
    private Context context;

    public UsersAdapter(List<Users> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.users.size();
    }

    @Override
    public Object getItem(int i) {
        return this.users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(this.context);
            view = inflater.inflate(R.layout.row, null);
        }

        ManageSharedPref manageSharedPref = new ManageSharedPref();


        TextView tv_username = view.findViewById(R.id.tv_username);
        TextView tv_score = view.findViewById(R.id.tv_score);

        tv_username.setText(this.users.get(i).getUsername());
        tv_score.setText(String.valueOf(this.users.get(i).getScore()));

        return view;
    }
}
