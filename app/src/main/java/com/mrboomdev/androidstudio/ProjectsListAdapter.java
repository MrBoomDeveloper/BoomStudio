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
	private OnItemClickListener mListener;
	
	public interface OnItemClickListener {
		void onItemClick(int position);
	}
   
   public void setOnItemClickListener(OnItemClickListener listener) {
   	mListener = listener;
   }

	public static class ProjectsViewHolder extends RecyclerView.ViewHolder {
		public TextView mName;
		public TextView mPath;

		public ProjectsViewHolder(View view, OnItemClickListener listener) {
			super(view);
			mName = view.findViewById(R.id.name);
			mPath = view.findViewById(R.id.path);
			
			view.setOnClickListener(v -> {
				if(listener != null) {
					int position = getAdapterPosition();
					if(position != RecyclerView.NO_POSITION) {
						listener.onItemClick(position);
					}
				}
			});
		}
	}

	public ProjectsListAdapter(ArrayList<ProjectItem> list) {
		mProjectsList = list;
	}

	@Override
	public ProjectsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
		ProjectsViewHolder evh = new ProjectsViewHolder(v, mListener);
		return evh;
	}

	@Override
	public void onBindViewHolder(ProjectsViewHolder holder, int position) {
		ProjectItem currentItem = mProjectsList.get(position);
		holder.mName.setText(currentItem.getName());
		holder.mPath.setText(currentItem.getPath().replace("content://com.android.providers.downloads.documents/tree/raw%3A%2F", "/Downloads").replace("content://com.android.externalstorage.documents/tree/primary", "/sdcard").replace("content://com.android.externalstorage.documents/tree", "").replace("%3A", "/"). replace("%2F", "/"));
	}

	@Override
	public int getItemCount() {
		return mProjectsList.size();
	}
}