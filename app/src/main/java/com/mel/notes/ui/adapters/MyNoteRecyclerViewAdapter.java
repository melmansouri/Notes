package com.mel.notes.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mel.notes.NuevaNotaDialogViewModel;
import com.mel.notes.R;
import com.mel.notes.db.entities.Note;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private List<Note> mValues;
    private Context mContext;
    private NuevaNotaDialogViewModel viewModel;

    public MyNoteRecyclerViewAdapter(Context context) {
        mValues = new ArrayList<>();
        mContext = context;
        viewModel= ViewModelProviders.of((AppCompatActivity)context).get(NuevaNotaDialogViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        //int color= ContextCompat.getColor(holder.mView.getContext(),holder.mItem.getColor());
        //((CardView)holder.mView).setCardBackgroundColor(color);
        holder.txtTitle.setText(holder.mItem.getTitle());
        holder.txtContent.setText(holder.mItem.getContent());

        int resourceDrawable=holder.mItem.isFavorite()?R.drawable.ic_favorite:R.drawable.ic_no_favorite;
        holder.imgFavorite.setImageResource(resourceDrawable);
        holder.txtTitle.setText(holder.mItem.getTitle());
        holder.imgFavorite.setOnClickListener(v -> {
            if (holder.mItem.isFavorite()){
                holder.mItem.setFavorite(false);
                holder.imgFavorite.setImageResource(R.drawable.ic_favorite);
            }else{
                holder.mItem.setFavorite(true);
                holder.imgFavorite.setImageResource(R.drawable.ic_no_favorite);
            }
            viewModel.updateNote(holder.mItem);
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNuevasNotas(List<Note> noteList){
        this.mValues=noteList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView txtTitle;
        TextView txtContent;
        ImageView imgFavorite;
        public Note mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtTitle=view.findViewById(R.id.txtTitle);
            txtContent=view.findViewById(R.id.txtContent);
            imgFavorite=view.findViewById(R.id.imgFavorite);
        }
    }
}
