package gun2.dev.backbonefairy.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gun2.dev.backbonefairy.R;
import gun2.dev.backbonefairy.test.TestActivity;

public class CountFragment extends Fragment {
    private static final String TAG = "CountFragment";

    /**
     * rsc
     */
    private View mView;
    private TextView mCountViewTextView;

    /**
     * other
     */
    private static final int TIME_LIMIT = 20;
    private int currentTime;

    /**
     * callback
     */
    public interface Callback {
        void countListener();
    }

    private Callback mCallback = new Callback() {
        @Override
        public void countListener() {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_count, container, false);
        mCountViewTextView = (TextView) mView.findViewById(R.id.textview_fragment_countview);


        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Callback) mCallback = (Callback) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void start() {
        currentTime = TIME_LIMIT;

        Thread thread;

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    while (currentTime > 0) {
                        Thread.sleep(1000);
                        currentTime--;
                        mCountViewTextView.setText(String.valueOf(currentTime));
                        mCallback.countListener();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
