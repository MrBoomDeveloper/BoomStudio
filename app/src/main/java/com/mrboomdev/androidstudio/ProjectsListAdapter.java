package com.mrboomdev.androidstudio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Map;

public class ProjectsListAdapter extends RecyclerView.Adapter<ProjectsListAdapter.ViewHolder> {
	private ArrayList<ProjectItem> mProjectsList;

	public class ViewHolder extends RecyclerView.ViewHolder {
		public TextView name;
		public TextView path;
		public ImageView options;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            path = view.findViewById(R.id.path);
            options = view.findViewById(R.id.options);
        }
    }
    
    public ProjectsListAdapter(ArrayList<ProjectItem> list) {
    	mProjectsList = list;
	}

    @Override
    public ProjectsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProjectsListAdapter.ViewHolder holder, int position) {
        ProjectItem currentItem = mProjectsList.get(position);
        holder.mName.setText(currentItem.getName());
        holder.mPath.setText(currentItem.getPath());
    }

    @Override
    public int getItemCount() {
        return mProjectsList.size();
    }
}
