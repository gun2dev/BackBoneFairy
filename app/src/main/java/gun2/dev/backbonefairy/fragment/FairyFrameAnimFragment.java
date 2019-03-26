package gun2.dev.backbonefairy.fragment;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import gun2.dev.backbonefairy.R;

public class FairyFrameAnimFragment extends Fragment {
    private static final String TAG = "FairyFrameAnimFragment";
    /**
     * rsc
     */
    private View mView;
    private AnimationDrawable mFrameAnimation;
    private ImageView mAnimFrameImageView;
    private TextView mDialogueTextView;


    /**
     * id
     */
    public static final int FRAME_ANIM_BASE_FAIRY = 1001;  //캐릭터 ID
    private static final String FRAME_ANIM_BASE_FAIRY_RSC_NAME = "frame_anim_base_fairy_"; //리소스 name
    public static final int FRAME_ANIM_ACT_BASE = 1001; //기본동작

    private static final String ACT_NAME_BASE = "base"; //동작 리소스 name

    private int mCurrentFairy;  //현재 Fairy상태

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_fairy_frame_anim, container, false);
        mAnimFrameImageView = (ImageView) mView.findViewById(R.id.imageview_fragmentfairyframenanim_main);
        mDialogueTextView = (TextView) mView.findViewById(R.id.textview_fragmentfairyframeanim_dialogue);
        return mView;
    }

    /**
     * 이미지에 사용할 fairy 설정
     *
     * @param fairyId fairy id
     */
    public void setFairy(int fairyId) {
        mCurrentFairy = fairyId;
    }

    /**
     * id별로 이미지 동작
     *
     * @param moveId 동작 ID
     */
    public void startFrameImage(int moveId) {
        //frame drawble.xml 이름
        String frameRsc = "";
        //동작 접미사 이름
        String actRsc = "";
        switch (mCurrentFairy) {
            case FRAME_ANIM_BASE_FAIRY:
                frameRsc = FRAME_ANIM_BASE_FAIRY_RSC_NAME;
                break;
        }
        switch (moveId) {
            case FRAME_ANIM_ACT_BASE:
                actRsc = ACT_NAME_BASE;
                break;
        }
        Log.d(TAG, frameRsc + actRsc);
        mAnimFrameImageView.setBackgroundResource(
                getResources().getIdentifier(frameRsc + actRsc, "drawable", getContext().getPackageName()));

        //프레임레이아웃 설정
        mFrameAnimation = (AnimationDrawable) mAnimFrameImageView.getBackground();
        mFrameAnimation.start();
    }

    /**
     * 대사 설정
     * @param dialogue 대사
     */
    public void setDialogue(String dialogue){
        mDialogueTextView.setText(dialogue);
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
