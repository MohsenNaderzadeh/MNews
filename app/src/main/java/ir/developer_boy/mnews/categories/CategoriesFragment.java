package ir.developer_boy.mnews.categories;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ir.developer_boy.mnews.R;
import ir.developer_boy.mnews.base.BaseActivity;
import ir.developer_boy.mnews.base.BaseFragment;

public class CategoriesFragment extends BaseFragment {
    @Override
    public void setUpViews() {
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new CategoryAdapter(getContext()));
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_categories;
    }

    @Override
    public BaseActivity getBaseActivity() {
        return null;
    }
}
