package com.example.devoir_maison;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

class RecyclerItemToucheHelper extends ItemTouchHelper.SimpleCallback {
    private RecyclerItemTouchHelperListener listener;
    public RecyclerItemToucheHelper(int dragDirs, int swipeDirs,RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener=listener;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if(listener != null){
            listener.onSwiped(viewHolder,direction,viewHolder.getAdapterPosition());
        }
    }
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder){
        final View foregroundView = ((favorisAdapter.MyViewHolder) viewHolder).ViewForeground;
        //  getDefaultUIUtil().clearView(foregroundView);
    }
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView , RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((favorisAdapter.MyViewHolder) viewHolder).ViewForeground;



        // getDefaultUIUtil().onDraw(c,recyclerView,foregroundView,dX,dY,actionState,isCurrentlyActive);
    }
    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((favorisAdapter.MyViewHolder) viewHolder).ViewForeground;
        // getDefaultUIUtil().onDrawOver(c,recyclerView,foregroundView,dX,dY,actionState,isCurrentlyActive);
    }
}
