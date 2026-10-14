package com.example.playlistmaker

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val trackArtwork: ImageView = itemView.findViewById(R.id.trackArtwork)
    val trackName: TextView = itemView.findViewById(R.id.trackName)
    val trackArtist: TextView = itemView.findViewById(R.id.trackArtist)
    val trackTime: TextView = itemView.findViewById(R.id.trackTime)

    fun bind(track: Track) {
        trackName.text = track.trackName
        trackArtist.text = track.artistName
        trackTime.text = track.trackTime

        val cornerRadius = itemView.resources.getDimensionPixelSize(
            R.dimen.track_artwork_corner_radius
        )

        Glide.with(itemView)
            .load(track.artworkUrl100)
            .placeholder(R.drawable.ic_track_placeholder)
            .error(R.drawable.ic_track_placeholder)
            .transform(CenterCrop(), RoundedCorners(cornerRadius))
            .into(trackArtwork)
    }
}