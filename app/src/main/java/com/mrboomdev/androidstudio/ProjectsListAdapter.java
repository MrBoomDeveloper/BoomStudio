package com.mrboomdev.androidstudio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ProjectsListAdapter extends RecyclerView.Adapter<ProjectsListAdapter.ProjectsViewHolder> {
    private ArrayList<ProjectItem> mProjectsList;

    public static class ProjectsViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public TextView mPath;

        public ProjectsViewHolder(View view) {
            super(view);
            mName = view.findViewById(R.id.name);
            mPath = view.findViewById(R.id.path);
        }
    }

    public ProjectsListAdapter(ArrayList<ProjectItem> list) {
        mProjectsList = list;
    }

    @Override
    public ProjectsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
        ProjectsViewHolder evh = new ProjectsViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ProjectsViewHolder holder, int position) {
        ProjectItem currentItem = mProjectsList.get(position);
        holder.mName.setText(currentItem.getName());
        holder.mPath.setText(currentItem.getPath());
    }

    @Override
    public int getItemCount() {
        return mProjectsList.size();
    }
}