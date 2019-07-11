package edu.duke.compsci290.albumviewer;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;
import android.content.*;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.drawable.Drawable;

import static android.content.ContentValues.TAG;

/**
 * Created by nazligungor on 28.01.2018.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout mLinearLayout;
        ImageView mImageView;
        TextView mAlbumName;
        TextView mArtist;

        public ViewHolder(View itemView) {

            super(itemView);

            this.mLinearLayout = itemView.findViewById(R.id.album_holder_linear_layout);
            this.mImageView = itemView.findViewById(R.id.album_artwork_image_view);
            this.mAlbumName = itemView.findViewById(R.id.album_name_text_view);
            this.mArtist = itemView.findViewById(R.id.artist_name_text_view);

        }


    }

    String[] mAlbums;
    String[] mArtists;
    Context mContext;
    public AlbumAdapter(final Context context, String[] albums, String[] artists) {
        mAlbums = albums;
        mArtists = artists;
        mContext = context;
    }

    @Override
    public int getItemCount(){
        return mAlbums.length;
    }


    private void openAlbum(String albumName, String artistName) {
        Log.d(TAG, "Opening album " + albumName);
        Intent intent = new Intent(mContext, AlbumActivity.class);
        intent.putExtra("album_name_key", albumName);
        intent.putExtra("artist_name_key", artistName);
        mContext.startActivity(intent);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = mInflater.inflate(R.layout.album_holder, parent, false);
        final ViewHolder albumHolder = new ViewHolder(row);

        albumHolder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlbum(mAlbums[albumHolder.getAdapterPosition()], mArtists[albumHolder.getAdapterPosition()]);
            }
        });
        return albumHolder;

    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        String albumName = mAlbums[position].toLowerCase().replaceAll("\\W+", "");
        int drawableId = mContext.getResources().getIdentifier(albumName, "drawable", mContext.getPackageName());
        Drawable albumArtwork = mContext.getDrawable(drawableId);

       // Drawable albumArtwork = mContext.getDrawable(android.R.drawable.ic_dialog_info);
        holder.mImageView.setImageDrawable(albumArtwork);
        holder.mAlbumName.setText(mAlbums[position]);
        holder.mArtist.setText("By " + mArtists[position]);


    }
}
