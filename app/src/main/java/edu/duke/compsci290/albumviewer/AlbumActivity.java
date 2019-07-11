package edu.duke.compsci290.albumviewer;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;


public class AlbumActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        Intent receivedIntent = this.getIntent();
        String albumName = receivedIntent.getStringExtra("album_name_key");
        String artistName = receivedIntent.getStringExtra("artist_name_key");
        String album = albumName.toLowerCase().replaceAll("\\W+", "");

        int drawableId = this.getResources().getIdentifier(album, "drawable", this.getPackageName());
        Drawable albumArtwork = this.getDrawable(drawableId);

        ImageView mImageView = findViewById(R.id.activity_album_artwork_image_view);
        TextView mAlbumName = findViewById(R.id.activity_album_name_text_view);
        TextView mArtist = findViewById(R.id.activity_artist_name_text_view);

        mImageView.setImageDrawable(albumArtwork);
        mAlbumName.setText(albumName);
        mArtist.setText("By "+ artistName);

        RecyclerView rv = findViewById(R.id.activity_album_recycler_view);
        rv.setAdapter(new SongAdapter(this, albumName,artistName));
        rv.setLayoutManager(new LinearLayoutManager(this));

    }
}
