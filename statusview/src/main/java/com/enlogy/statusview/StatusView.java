package com.enlogy.statusview;

import android.view.View;

/**
 * Created by enlogy on 2019/1/24 0024.
 */
public interface StatusView {
    void showContent();
    void showEmptyContent();
    void showErrorContent();
    void showNoNetworkContent();
    void showLoadingContent();
    void showExtendContent();
    void setOnItemClickListener(int viewId, View.OnClickListener listener);
    int getViewStatus();
    int STATUS_CONTENT    = 0x00;
    int STATUS_LOADING    = 0x01;
    int STATUS_EMPTY      = 0x02;
    int STATUS_ERROR      = 0x03;
    int STATUS_NO_NETWORK = 0x04;
    int STATUS_EXTEND = 0x05;
}
