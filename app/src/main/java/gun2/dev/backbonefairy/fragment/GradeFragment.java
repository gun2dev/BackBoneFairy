package gun2.dev.backbonefairy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import gun2.dev.backbonefairy.R;
import gun2.dev.backbonefairy.db.GradeDB;
import gun2.dev.backbonefairy.db.utils.RealmController;
import gun2.dev.backbonefairy.stat.StaticGradeExpData;
import io.realm.Realm;


public class GradeFragment extends Fragment {

    private static final String TAG = "GradeFragment";
    /**
     * rsc
     */
    private View mView;
    private TextView mCurrentExpTextView;
    private TextView mGradeNameTextView;
    private TextView mGradeExpRateTextView;
    private ProgressBar mExpProgressBar;

    /**
     * other
     */
    private static final int BASE_EXP = 10; //기본 추가 경험치
    private int mBalanceExp = BASE_EXP;  //계산된 추가 경험치 (이 값으로 경험치가 매초 추가됨)
    private static final int EXP_DB_TARGET = 1001;  //DB저장 타겟

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_grade, container, false);
        mCurrentExpTextView = (TextView)  mView.findViewById(R.id.textview_fragment_grade_exp_amount);
        mGradeNameTextView = (TextView) mView.findViewById(R.id.textview_fragmentgrade_gradename);
        mGradeExpRateTextView = (TextView) mView.findViewById(R.id.textview_fragment_grade_exp_rate) ;
        mExpProgressBar = (ProgressBar) mView.findViewById(R.id.progressbar_fragment_grade_wxp);

        //DB에 exp가 없으면 생성
        initExpDB();

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


    /**
     * 경험치 조절
     */
    public void balanceExp() {
        // 경험치 조절

    }

    /**
     * 매 초 호출되는 경험치 추가 메소드
     */
    public void plusExp() {

        // 경험치 추가
        final Realm realm = Realm.getInstance(RealmController.baseRealmConfig());
        GradeDB gradeDB = realm.where(GradeDB.class).equalTo("id", EXP_DB_TARGET).findFirst();

        realm.beginTransaction();
        gradeDB.exp += mBalanceExp;
        Log.d(TAG, "exp : " + gradeDB.exp);
        realm.commitTransaction();

        // 등급 갱신
        int currentExp = 0;
        int needExp = 0;
        String gradeName = "";
        for (int i = 1; i < StaticGradeExpData.GRADE_NEED_EXP.length; i++){
            if (gradeDB.exp >= StaticGradeExpData.GRADE_NEED_EXP[i-1] && gradeDB.exp < StaticGradeExpData.GRADE_NEED_EXP[i]){
                currentExp = gradeDB.exp -  StaticGradeExpData.GRADE_NEED_EXP[i-1];
                needExp = StaticGradeExpData.GRADE_NEED_EXP[i] -  StaticGradeExpData.GRADE_NEED_EXP[i-1];
                gradeName = StaticGradeExpData.GRADE_NAME[i-1];
                break;
            }
        }
        final int expRate = ((currentExp * 100 / needExp));

        mCurrentExpTextView.setText(currentExp + " / " + needExp);
        mGradeNameTextView.setText((gradeName));
        mGradeExpRateTextView.setText(expRate+ "%");
        mExpProgressBar.setProgress(expRate);

        // 등급 경험치 갱신

        try {
            realm.close();
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    /**
     * 경험치 데이터베이스가 없으면 생성
     */
    public void initExpDB() {
        final Realm realm = Realm.getInstance(RealmController.baseRealmConfig());

        GradeDB gradeDB = realm.where(GradeDB.class).equalTo("id", EXP_DB_TARGET).findFirst();

        if (gradeDB == null) {
            realm.beginTransaction();
            gradeDB = realm.createObject(GradeDB.class, EXP_DB_TARGET);
            Log.d(TAG, "경험치 데이터베이스생성");
            realm.commitTransaction();
        }

        try {
            realm.close();
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }
}
