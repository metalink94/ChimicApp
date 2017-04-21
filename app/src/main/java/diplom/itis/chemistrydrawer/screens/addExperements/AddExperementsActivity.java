package diplom.itis.chemistrydrawer.screens.addExperements;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import diplom.itis.chemistrydrawer.R;
import diplom.itis.chemistrydrawer.models.CreateExperimentsModel;
import diplom.itis.chemistrydrawer.screens.graphic.GraphActivity;
import diplom.itis.chemistrydrawer.utils.BaseActivity;

/**
 * Created by Денис on 21.04.2017.
 */

public class AddExperementsActivity extends BaseActivity implements AddExperimentsView, View.OnClickListener {

    @BindView(R.id.et_name)
    EditText mName;
    @BindView(R.id.et_description)
    EditText mDescription;
    @BindView(R.id.add_experiment)
    TextView mBtn;
    @BindView(R.id.linear)
    LinearLayout mLayout;

    private AddExperimentsPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exp);
        ButterKnife.bind(this);
        mBtn.setOnClickListener(this);
        mPresenter = new AddExperimentsPresenter(this);
    }

    @Override
    public void onClick(View aView) {
        switch (aView.getId()) {
            case R.id.add_experiment:
                mPresenter.saveData(mName.getText().toString(), mDescription.getText().toString());
                break;
        }
    }

    @Override
    public void sendModel(CreateExperimentsModel aCreateExperimentsModel) {
        startActivity(new Intent(this, GraphActivity.class).putExtra(GraphActivity.KEY_MODEL, Parcels.wrap(aCreateExperimentsModel)));
    }

    @Override
    public void showError() {
        Snackbar.make(mLayout, getString(R.string.need_all_info), Snackbar.LENGTH_LONG).show();
    }
}
