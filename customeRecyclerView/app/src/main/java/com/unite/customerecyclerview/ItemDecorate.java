package com.unite.customerecyclerview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ItemDecorate extends RecyclerView.ItemDecoration {
    public ItemDecorate() {
        super();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);


    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int childCount = parent.getChildCount();
        int parentPaddingLeft = parent.getPaddingLeft();
        for (int i = 0; i < childCount; i++) {
            Log.i("jason", "我是调用 : " + i);
            if (i % 5 != 0) continue;
            View child = parent.getChildAt(i);

            int top = child.getTop();
            int left = child.getLeft();
            Rect rect = new Rect();
            rect.set(left, top, left + 50, top + 40);
            c.drawRect(rect, new Paint());
        }
//        int width = parent.getWidth();
//        Rect rect = new Rect();
//        rect.set(0, 0, width, 40);
//        c.drawRect(rect, new Paint());

    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        outRect.set(20, 40, 0, 0);
    }
}
