package com.example.assigment_2.view.adapter

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.assigment_2.R
import com.example.assigment_2.databinding.ItunesLayoutBinding
import com.example.assigment_2.model.ItunesInfo
import com.example.assigment_2.model.ItunesResponse
import com.example.assigment_2.model.remote.Network
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ItunesAdapter(
    private var dataSet: MutableList<ItunesInfo>
) :
    RecyclerView.Adapter<ItunesAdapter.ItunesViewHolder>() {

    class ItunesViewHolder(private val binding: ItunesLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(itunesItem: ItunesInfo) {
            binding.apply {
                artistName.text = itunesItem.artistName
                collectionName.text = itunesItem.collectionName
                "${itunesItem.trackPrice} $".also { trackPrice.text = it }
                Picasso.get().load(itunesItem.artworkUrl60)
                    .resize(300, 300)
                    .centerCrop()
                    .into(itunesCoverItem)

                val mediaPlayer = MediaPlayer()
                playMusic.setOnClickListener {
                    playOrStopMusic(itunesItem.previewUrl, playMusic, root.context, mediaPlayer)
                }


            }
        }

        private fun playOrStopMusic(
            itunesMusicUrl: String,
            playButton: ImageButton,
            context: Context,
            mediaPlayer: MediaPlayer
        ) {

            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                playButton.background = AppCompatResources.getDrawable(
                    context,
                    R.drawable.ic_play_button_image
                )
            } else {
                playButton.background = AppCompatResources.getDrawable(
                    context,
                    R.drawable.ic_outline_stop_circle_24
                )
                try {
                    mediaPlayer.setDataSource(itunesMusicUrl)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItunesViewHolder(
            ItunesLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        )

    override fun onBindViewHolder(holder: ItunesViewHolder, position: Int) {
        holder.onBind(
            dataSet[position]
        )

    }

    override fun getItemCount() = dataSet.size

    fun updateDataSet(items: List<ItunesInfo>) {

        //dataSet = items
        val originalSize = dataSet.size - 1
        dataSet.addAll(items)
        //val updated = dataSet.size
        notifyItemRangeInserted(originalSize, 10)
    }

    fun getItunesData(
        context: Context,
        itunesTheme: String,
        startIndex: Int,
    ) {
        Network().api.getNextItunesPage(
            itunesTheme, startIndex
        ).enqueue(
            object : Callback<ItunesResponse> {
                override fun onResponse(
                    call: Call<ItunesResponse>,
                    response: Response<ItunesResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            updateDataSet(it.results)
                        }
                    } else {
                        Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<ItunesResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                }
            }
        )

    }

}