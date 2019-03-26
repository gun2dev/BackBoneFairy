package gun2.dev.backbonefairy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gun2.dev.backbonefairy.R;
import gun2.dev.backbonefairy.db.utils.RealmController;
import io.realm.Realm;

public class FairyTweenAnimFragment extends Fragment {

    private static final String TAG = "FairyTweenAnimFragment";
    /**
     * rsc
     */
    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_fairy_tween_anim, container, false);
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
