/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hxqc.business.base.mvvm;

import android.databinding.BindingAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

/**
 */
public class ListDataBinding {

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"items", "page"}, requireAll = false)
    public static <T> void setItems(ListView listView, List<T> items, int page) {
     /*   page = page == 0 ? 1 : page;
        CommonAdapter commonAdapter = null;
        if (listView.getAdapter() != null) {
            if (listView.getAdapter() instanceof CommonAdapter) {
                commonAdapter = ((CommonAdapter) listView.getAdapter());
            } else if (listView.getAdapter() instanceof HeaderViewListAdapter) {
                if (((HeaderViewListAdapter) listView.getAdapter()).getWrappedAdapter() instanceof CommonAdapter) {
                    commonAdapter = ((CommonAdapter) ((HeaderViewListAdapter) listView.getAdapter()).getWrappedAdapter());
                }
            }
        }
        if (commonAdapter != null) {
            commonAdapter.setData(items, page == 1);
        }*/
    }

    @BindingAdapter(value = {"adapter"})
    public static void setAdapter(ListView listView, ListAdapter adapter) {
        listView.setAdapter(adapter);
    }


}
