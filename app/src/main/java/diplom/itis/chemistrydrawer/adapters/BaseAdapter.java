package diplom.itis.chemistrydrawer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import diplom.itis.chemistrydrawer.R;
import diplom.itis.chemistrydrawer.models.CreateExperimentsModel;
import diplom.itis.chemistrydrawer.models.ExperimentsModel;
import diplom.itis.chemistrydrawer.models.TaskModel;

/**
 * Created by denis_000 on 06.11.2016.
 */
public class BaseAdapter extends RecyclerView.Adapter {

    public static final int TASK_TYPE = 1;
    public static final int EXPERIMENT_TYPE = 2;
    public static final int CREATE_EXPERIMENT_TYPE = 3;

    private View.OnClickListener mOnClickListener;
    protected List<Object> mList;
    private Context mContext;

    public BaseAdapter(Context context, List list, View.OnClickListener onClickListener) {
        mList = list;
        mContext = context;
        mOnClickListener = onClickListener;
    }

    public BaseAdapter(Context context, View.OnClickListener onClickListener) {
        mList = new ArrayList<>();
        mContext = context;
        mOnClickListener = onClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position) instanceof TaskModel) {
            return TASK_TYPE;
        }
        if (mList.get(position) instanceof ExperimentsModel) {
            return EXPERIMENT_TYPE;
        }
        if (mList.get(position) instanceof CreateExperimentsModel) {
            return CREATE_EXPERIMENT_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TASK_TYPE:
                View v = LayoutInflater.from(mContext)
                        .inflate(R.layout.row_task, parent, false);
                return new TaskHolder(v);
            case EXPERIMENT_TYPE:
                v = LayoutInflater.from(mContext)
                        .inflate(R.layout.row_experiments, parent, false);
                return new ExperimentHolder(v);
            case CREATE_EXPERIMENT_TYPE:
                v = LayoutInflater.from(mContext)
                        .inflate(R.layout.row_experiments, parent, false);
                return new ExperimentHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object item = mList.get(position);
        if (item instanceof TaskModel) {
            final TaskModel taskModel = (TaskModel) item;
            final TaskHolder taskHolder = (TaskHolder) holder;
            taskHolder.title.setText(taskModel.title);
            taskHolder.description.setText(taskModel.description);
            taskHolder.setOnClickListener(mOnClickListener, taskModel);
            return;
        }
        if (item instanceof ExperimentsModel) {
            final ExperimentsModel experimentsModel = (ExperimentsModel) item;
            final ExperimentHolder experimentHolder = (ExperimentHolder) holder;
            experimentHolder.title.setText(experimentsModel.title);
            experimentHolder.description.setText(experimentsModel.description);
            if (experimentsModel.status.equals(ExperimentsModel.STATUS_OK)) {
                experimentHolder.iconStatus.setImageResource(R.drawable.ic_done_black_24px);
            } else if (experimentsModel.status.equals(ExperimentsModel.STATUS_ERROR)) {
                experimentHolder.iconStatus.setImageResource(R.drawable.ic_highlight_off_black_24px);
            }
            experimentHolder.setOnClickListener(mOnClickListener, experimentsModel);
            return;
        }
        if (item instanceof CreateExperimentsModel) {
            final CreateExperimentsModel experimentsModel = (CreateExperimentsModel) item;
            final ExperimentHolder experimentHolder = (ExperimentHolder) holder;
            experimentHolder.title.setText(experimentsModel.name);
            experimentHolder.description.setText(experimentsModel.description);
            experimentHolder.iconStatus.setImageResource(R.drawable.ic_done_black_24px);
            experimentHolder.setOnClickListener(mOnClickListener, experimentsModel);
            return;
        }
        
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////// PUBLIC METHODS //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////


    public void removeItems(List items, RecyclerView recyclerView) {
        if (items == null) return;
        for (Object item : items) {
            int pos = mList.indexOf(item);

            if (pos == -1) continue;

            final Object item2remove = item;
            notifyItemRemoved(pos);
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    int pos = mList.indexOf(item2remove);
                    mList.remove(pos);
                }
            });
        }
    }


    public void removeItems2(List items) {
        if (items == null) return;
        for (Object item : items) {
            int pos = mList.indexOf(item);
            if (pos > -1) {
                int length = mList.size();
                if (pos < length) {
                    mList.remove(pos);
                    notifyItemRangeChanged(pos, length - 1 - pos);
                    notifyItemRemoved(length - 1);
                } else {
                    mList.remove(pos);
                    notifyItemRemoved(pos);
                }
            }
        }
    }

    public void removeItems(List items) {
        if (items == null) return;
        for (Object item : items) {
            int pos = mList.indexOf(item);
            if (pos > -1) {
                mList.remove(pos);
                notifyItemRemoved(pos);
            }
        }
    }

    public void removeItem(Object item) {
        int position = mList.indexOf(item);
        if (position > -1) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addItems(List items) {
        for (Object item : items) {
            addItem(item);
        }
    }

    public void addItems(int startPos, List items) {
        for (Object item : items) {
            addItem(startPos++, item);
        }
    }

    public void changeItem(Object item) {
        int itemPos = mList.indexOf(item);
        if (itemPos == -1) return;
        notifyItemChanged(itemPos);
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    public boolean hasItem(Object object) {
        return mList.indexOf(object) != -1;
    }

    public void clearFrom(int pos) {
        if (pos >= mList.size()) return;

        int itemsToRemoveCount = mList.size() - pos;

        List itemsToRemove = new ArrayList();

        for (int i = pos; i < mList.size(); i++) {
            itemsToRemove.add(mList.get(i));
        }
        mList.removeAll(itemsToRemove);
        notifyItemRangeRemoved(pos, itemsToRemoveCount);
    }

    public void addItem(int position, Object item) {
        mList.add(position, item);
        notifyItemInserted(position);
    }

    public void addItem(Object item) {
        mList.add(item);
        notifyItemInserted(mList.size() - 1);
    }

    public int size() {
        return mList.size();
    }

    public void modifyItem(int i, Object newItem) {
        if (i >= mList.size()) return;

        mList.remove(i);
        mList.add(i, newItem);
        notifyItemChanged(i);
    }

    public int indexOf(Object object) {
        return mList.indexOf(object);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// VIEW HOLDERS //////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static class TaskHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView title;
        TextView description;
        RelativeLayout tapLayout;

        public TaskHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            tapLayout = (RelativeLayout) itemView.findViewById(R.id.taplayout);
        }

        public void setOnClickListener(View.OnClickListener onClickListener, Object tag) {
            tapLayout.setOnClickListener(onClickListener);
            tapLayout.setTag(tag);
        }
    }

    public static class ExperimentHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView title;
        TextView description;
        ImageView iconStatus;
        RelativeLayout tapLayout;

        public ExperimentHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            iconStatus = (ImageView) itemView.findViewById(R.id.icon_status);
            tapLayout = (RelativeLayout) itemView.findViewById(R.id.taplayout);
        }

        public void setOnClickListener(View.OnClickListener onClickListener, Object tag) {
            tapLayout.setOnClickListener(onClickListener);
            tapLayout.setTag(tag);
        }
    }
}
