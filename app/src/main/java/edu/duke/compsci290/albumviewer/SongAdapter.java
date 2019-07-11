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

/**
 * Created by nazligungor on 29.01.2018.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mSongName;

        public ViewHolder(View itemView) {

            super(itemView);

            this.mSongName = itemView.findViewById(R.id.song_name_text_view);

        }
    }

    String[] mSongs;
    Context mContext;
    String mAlbum;
    String mArtist;

    public SongAdapter(final Context context, String album, String artist) {

        mContext = context;
        mAlbum = album;
        mArtist = artist;
        String albumName = mAlbum.toLowerCase().replaceAll("\\W+", "");
        int id = mContext.getResources().getIdentifier(albumName, "array", mContext.getPackageName());
        mSongs = mContext.getResources().getStringArray(id);

    }

    @Override
    public int getItemCount(){
        return mSongs.length;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = mInflater.inflate(R.layout.song_holder, parent, false);

        final SongAdapter.ViewHolder songHolder = new SongAdapter.ViewHolder(row);
        return songHolder;
    }


    @Override
    public void onBindViewHolder(SongAdapter.ViewHolder holder, int position){
        
        holder.mSongName.setText(mSongs[position]);
        //Log.i("test",mSongs[position]);
    }
}
