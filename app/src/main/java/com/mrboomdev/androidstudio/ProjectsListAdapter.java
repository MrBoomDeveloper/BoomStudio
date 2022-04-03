package com.mrboomdev.androidstudio;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Map;

public class ProjectsListAdapter extends RecyclerView.Adapter<ProjectsListAdapter.ViewHolder> {
    private final List<Map> data;
    private final Context context;

    public ProjectsListAdapter (List<Map> data, Context context){
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ProjectsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsListAdapter.ViewHolder holder, int position) {
        holder.name.setText(this.data.get(position).get("name").toString());
        holder.path.setText(this.data.get(position).get("path").toString());
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView name;
        private final TextView path;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.name = view.findViewById(R.id.name);
            this.path = view.findViewById(R.id.path);
            ImageView options = view.findViewById(R.id.options);
            options.setOnClickListener(v -> {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.inflate(R.menu.project_options);
                popupMenu.setOnMenuItemClickListener(item -> {
                    if(item.getItemId() == R.id.install)
                        Log.d("debug", "install");
                    if(item.getItemId() == R.id.share)
                        Log.d("debug", "share");
                    if(item.getItemId() == R.id.copy)
                        Log.d("debug", "copy");
                    if(item.getItemId() == R.id.delete)
                        Log.d("debug", "delete");
                    return true;
                });
                popupMenu.show();
            });
        }

        @Override
        public void onClick(@NonNull View view) {
            Toast.makeText(view.getContext(), getLayoutPosition(), Toast.LENGTH_SHORT).show();
        }
    }
}
