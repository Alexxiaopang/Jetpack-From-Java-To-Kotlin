package com.example.jetpack_kotlin.sample_03_viewmodel.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack_koltin.R
import com.example.jetpack_kotlin.common_data.bean.Moment
import com.example.jetpack_kotlin.common_ui.adapter.MomentAdapter
import com.example.jetpack_kotlin.sample_03_viewmodel.ui.state.ListViewModel
import com.kunminx.architecture.ui.BaseActivity

/**
 * @author Flywith24
 * @date   2020/5/31
 * time   22:02
 * description
 */
class ViewModelListActivity : BaseActivity(R.layout.activity_list_viewmodel), MomentAdapter.OnItemClickListener {

    /**
     * activity-ktx 扩展函数
     */
    private val mListViewModel by viewModels<ListViewModel>()

    /**
     * 不推荐使用 Kotlin Synthetics
     * 可以使用 ViewBinding 和 功能更强大的 DataBinding 来替换 findViewById
     *
     * 本示例尽量只演示单个组件的使用，因此没有使用 ViewBinding 或 DataBinding
     *
     * 详情参考 https://juejin.im/post/5e8ef0bc518825736b749705#heading-17
     */
    private lateinit var mRecyclerView: RecyclerView

    private val mAdapter by lazy { MomentAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRecyclerView = findViewById(R.id.rv)

        mRecyclerView.adapter = mAdapter

        mListViewModel.getListMutableLiveData().observe(this) { list ->
            mAdapter.submitList(list)
        }

        mListViewModel.requestList()
    }

    override fun onItemClick(moment: Moment) {
    }
}