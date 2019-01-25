package com.enlogy.statusview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by enlogy on 2019/1/24 0024.
 */
public class StatusRelativeLayout extends RelativeLayout implements StatusView {
    private LayoutInflater inflater;
    private int contentViewResId;
    private int loadingViewResId;
    private int emptyViewResId;
    private int errorViewResId;
    private int noNetworkViewResId;
    private int extendViewResId;
    private SparseArray<Integer> contentVisibilityStatusArray;
    private SparseArray<View> views = new SparseArray<>();
    private View contentView;
    private View emptyView;
    private View loadingView;
    private View noNetworkView;
    private View errorView;
    private View extendView;
    private static final int defValue = -1;
    private int viewStatus = defValue;
    private ViewGroup defViewGroup;
    private static final LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

    public StatusRelativeLayout(Context context) {
        this(context, null);
    }

    public StatusRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflater = LayoutInflater.from(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StatusRelativeLayout, defStyleAttr, 0);
        contentViewResId = typedArray.getResourceId(R.styleable.StatusRelativeLayout_rContentView, defValue);
        loadingViewResId = typedArray.getResourceId(R.styleable.StatusRelativeLayout_rLoadingView, defValue);
        emptyViewResId = typedArray.getResourceId(R.styleable.StatusRelativeLayout_rEmptyView, defValue);
        errorViewResId = typedArray.getResourceId(R.styleable.StatusRelativeLayout_rErrorView, defValue);
        noNetworkViewResId = typedArray.getResourceId(R.styleable.StatusRelativeLayout_rNoNetworkView, defValue);
        extendViewResId = typedArray.getResourceId(R.styleable.StatusRelativeLayout_rExtendView, defValue);
        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initDefaultContentView();
        saveAllViewToArray();
        showContent();
    }

    /**
     * Initialization view to record the current state
     */
    private void initDefaultContentView() {
        defViewGroup = this;
        contentVisibilityStatusArray = saveViewVisibleStatus(defViewGroup);
        saveViewsToClick(defViewGroup, views);
    }

    /**
     * Display default content view
     */
    @Override
    public void showContent() {
        if (viewStatus == STATUS_CONTENT) {
            return;
        }
        if (contentViewResId != defValue) {
            removeAllViews();
            if (contentView == null) {
                contentView = inflater.inflate(contentViewResId, this, false);
                if (contentView instanceof ViewGroup) {
                    saveViewsToClick((ViewGroup) contentView, views);
                }
            }
            addView(contentView, params);
        } else {
            removeOldView();
            invalidateViewVisibleFromStatus(defViewGroup, contentVisibilityStatusArray);
        }
        viewStatus = STATUS_CONTENT;
    }

    /**
     * Display empty content view
     */
    @Override
    public void showEmptyContent() {
        if (viewStatus == STATUS_EMPTY) {
            return;
        }
        removeOldView();
        viewStatus = STATUS_EMPTY;
        if (emptyViewResId != defValue) {
            if (emptyView == null) {
                emptyView = inflater.inflate(emptyViewResId, this, false);
                if (emptyView instanceof ViewGroup) {
                    saveViewsToClick((ViewGroup) emptyView, views);
                }
            }
            addView(emptyView, params);
        }
    }

    /**
     * Remove the previous view
     */
    private void removeOldView() {
        switch (viewStatus) {
            case STATUS_CONTENT:
                if (contentViewResId != defValue) {
                    removeView(contentView);
                } else {
                    contentVisibilityStatusArray = saveViewVisibleStatus(defViewGroup);
                    setAllViewGone(defViewGroup);
                }
                break;
            case STATUS_ERROR:
                removeView(errorView);
                break;
            case STATUS_LOADING:
                removeView(loadingView);
                break;
            case STATUS_EXTEND:
                removeView(extendView);
                break;
            case STATUS_NO_NETWORK:
                removeView(noNetworkView);
                break;
            case STATUS_EMPTY:
                removeView(emptyView);
                break;
            default:
                break;
        }
    }

    /**
     * Set all view to gone
     *
     * @param defViewGroup Operated object
     */
    private static void setAllViewGone(ViewGroup defViewGroup) {
        int childCount = defViewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = defViewGroup.getChildAt(i);
            child.setVisibility(GONE);
        }
    }

    /**
     * Display error content view
     */
    @Override
    public void showErrorContent() {
        if (viewStatus == STATUS_ERROR) {
            return;
        }
        removeOldView();
        viewStatus = STATUS_ERROR;
        if (errorViewResId != defValue) {
            if (errorView == null) {
                errorView = inflater.inflate(errorViewResId, this, false);
                if (errorView instanceof ViewGroup) {
                    saveViewsToClick((ViewGroup) errorView, views);
                }
            }
            addView(errorView, params);
        }
    }

    /**
     * Display no network content view
     */
    @Override
    public void showNoNetworkContent() {
        if (viewStatus == STATUS_NO_NETWORK) {
            return;
        }
        removeOldView();
        viewStatus = STATUS_NO_NETWORK;
        if (noNetworkViewResId != defValue) {
            if (noNetworkView == null) {
                noNetworkView = inflater.inflate(noNetworkViewResId, this, false);
                if (noNetworkView instanceof ViewGroup) {
                    saveViewsToClick((ViewGroup) noNetworkView, views);
                }
            }
            addView(noNetworkView, params);
        }
    }

    /**
     * Display loading content view
     */
    @Override
    public void showLoadingContent() {
        if (viewStatus == STATUS_LOADING) {
            return;
        }
        removeOldView();
        viewStatus = STATUS_LOADING;
        if (loadingViewResId != defValue) {
            if (loadingView == null) {
                loadingView = inflater.inflate(loadingViewResId, this, false);
                if (loadingView instanceof ViewGroup) {
                    saveViewsToClick((ViewGroup) loadingView, views);
                }
            }
            addView(loadingView, params);
        }
    }

    /**
     * Display extend content view
     */
    @Override
    public void showExtendContent() {
        if (viewStatus == STATUS_EXTEND) {
            return;
        }
        removeOldView();
        viewStatus = STATUS_EXTEND;
        if (extendViewResId != defValue) {
            if (extendView == null) {
                extendView = inflater.inflate(extendViewResId, this, false);
                if (extendView instanceof ViewGroup) {
                    saveViewsToClick((ViewGroup) extendView, views);
                }
            }
            addView(extendView, params);
        }
    }

    /**
     * Trying to click response listening
     *
     * @param viewId   view id
     * @param listener listener
     */
    @Override
    public void setOnItemClickListener(int viewId, OnClickListener listener) {
        View view = getView(viewId);
        Log.d("TestR", "setOnItemClickListener");
        if (view != null) {
            Log.d("TestR", "view != null");
            view.setOnClickListener(listener);
        }
    }

    /**
     * An view to return the corresponding ID
     *
     * @param viewId view id
     * @return view
     */
    private View getView(int viewId) {
        return views.get(viewId);
    }

    /**
     * Returns the status currently displayed
     *
     * @return status
     */
    @Override
    public int getViewStatus() {
        return viewStatus;
    }

    /**
     * Save the visible state of the current view
     *
     * @param viewGroup Operated object
     * @return Current status array
     */
    private SparseArray<Integer> saveViewVisibleStatus(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        SparseArray<Integer> sparseArray = new SparseArray<>(childCount);
        for (int i = 0; i < childCount; i++) {
            View child = viewGroup.getChildAt(i);
            sparseArray.put(child.getId(), child.getVisibility());
        }
        return sparseArray;
    }

    /**
     * Save an view to click
     *
     * @param viewGroup Operated object
     * @return Current status array
     */
    private void saveViewsToClick(ViewGroup viewGroup, SparseArray<View> sparseArray) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = viewGroup.getChildAt(i);
            sparseArray.put(child.getId(), child);
        }
    }

    /**
     * Update the visible state of the current view
     *
     * @param viewGroup   Operated object
     * @param sparseArray State array
     */
    private void invalidateViewVisibleFromStatus(ViewGroup viewGroup, SparseArray<Integer> sparseArray) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = viewGroup.getChildAt(i);
            int visible = sparseArray.get(child.getId(), defValue);
            if (visible == defValue) {
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(visible);
            }
        }
    }

    /**
     * Save view to Array
     */
    private void saveAllViewToArray() {
        if (contentViewResId != defValue) {
            contentView = inflater.inflate(contentViewResId, this, false);
            if (contentView instanceof ViewGroup) {
                saveViewsToClick((ViewGroup) contentView, views);
            }
        }
        if (emptyViewResId != defValue) {
            emptyView = inflater.inflate(emptyViewResId, this, false);
            if (emptyView instanceof ViewGroup) {
                saveViewsToClick((ViewGroup) emptyView, views);
            }
        }
        if (errorViewResId != defValue) {
            errorView = inflater.inflate(errorViewResId, this, false);
            if (errorView instanceof ViewGroup) {
                saveViewsToClick((ViewGroup) errorView, views);
            }
        }
        if (noNetworkViewResId != defValue) {
            noNetworkView = inflater.inflate(noNetworkViewResId, this, false);
            if (noNetworkView instanceof ViewGroup) {
                saveViewsToClick((ViewGroup) noNetworkView, views);
            }
        }
        if (loadingViewResId != defValue) {
            loadingView = inflater.inflate(loadingViewResId, this, false);
            if (loadingView instanceof ViewGroup) {
                saveViewsToClick((ViewGroup) loadingView, views);
            }
        }
        if (extendViewResId != defValue) {
            extendView = inflater.inflate(extendViewResId, this, false);
            if (extendView instanceof ViewGroup) {
                saveViewsToClick((ViewGroup) extendView, views);
            }
        }
    }
}
