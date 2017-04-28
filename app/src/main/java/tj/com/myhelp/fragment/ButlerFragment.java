package tj.com.myhelp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tj.com.myhelp.R;

/**
 * Created by Jun on 17/4/28.
 */

public class ButlerFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_butler, null);
        return view;
    }
}
