package id.ishakcahya.gamebatuguntingkertas

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import id.ishakcahya.gamebatuguntingkertas.Game.isDraw
import id.ishakcahya.gamebatuguntingkertas.Game.isWin
import id.ishakcahya.gamebatuguntingkertas.Game.pickComputerOption
import id.ishakcahya.gamebatuguntingkertas.Game.pickNextComputerOption
import id.ishakcahya.gamebatuguntingkertas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        initListeners()
    }

    private fun initListeners() {
        val mediaPlayerGame = MediaPlayer.create(this, R.raw.game_click)
        val mediaPlayerRefresh = MediaPlayer.create(this, R.raw.refresh_click)

        binding.ivBatu.setOnClickListener {
            binding.ivBatu.setBackgroundResource(R.drawable.bg_selected_suit)
            binding.ivGunting.setBackgroundResource(R.drawable.bg_clear)
            binding.ivKertas.setBackgroundResource(R.drawable.bg_clear)
            mediaPlayerGame.start()
            startGame("BATU")
            Log.i("TAG", "Option Pemain 1")
        }

        binding.ivKertas.setOnClickListener {
            binding.ivKertas.setBackgroundResource(R.drawable.bg_selected_suit)
            binding.ivBatu.setBackgroundResource(R.drawable.bg_clear)
            binding.ivGunting.setBackgroundResource(R.drawable.bg_clear)
            mediaPlayerGame.start()
            startGame("KERTAS")
            Log.i("TAG", "Option Pemain 1")
        }

        binding.ivGunting.setOnClickListener {
            binding.ivGunting.setBackgroundResource(R.drawable.bg_selected_suit)
            binding.ivKertas.setBackgroundResource(R.drawable.bg_clear)
            binding.ivBatu.setBackgroundResource(R.drawable.bg_clear)
            mediaPlayerGame.start()
            startGame("GUNTING")
            Log.i("TAG", "Option Pemain 1")
        }

        binding.ivRefresh.setOnClickListener {
            binding.ivBatu.setBackgroundResource(R.drawable.bg_clear)
            binding.ivGunting.setBackgroundResource(R.drawable.bg_clear)
            binding.ivKertas.setBackgroundResource(R.drawable.bg_clear)
            binding.ivBatuCom.setBackgroundResource(R.drawable.bg_clear)
            binding.ivGuntingCom.setBackgroundResource(R.drawable.bg_clear)
            binding.ivKertasCom.setBackgroundResource(R.drawable.bg_clear)
            binding.ivResult.setImageResource(R.drawable.vs)
            mediaPlayerRefresh.start()
        }
    }

    private fun startGame(option: String) {
        val computerOption = pickComputerOption()
        pickNextComputerOption(computerOption)
        Log.i("TAG", "Option Computer")
        when {
            computerOption == "BATU" -> {
                binding.ivBatuCom.setBackgroundResource(R.drawable.bg_selected_suit)
                binding.ivGuntingCom.setBackgroundResource(R.drawable.bg_clear)
                binding.ivKertasCom.setBackgroundResource(R.drawable.bg_clear)
            }
            computerOption == "GUNTING" -> {
                binding.ivGuntingCom.setBackgroundResource(R.drawable.bg_selected_suit)
                binding.ivKertasCom.setBackgroundResource(R.drawable.bg_clear)
                binding.ivBatuCom.setBackgroundResource(R.drawable.bg_clear)
            }
            else -> {
                binding.ivKertasCom.setBackgroundResource(R.drawable.bg_selected_suit)
                binding.ivBatuCom.setBackgroundResource(R.drawable.bg_clear)
                binding.ivGuntingCom.setBackgroundResource(R.drawable.bg_clear)
            }
        }

        when {
            isDraw(option, computerOption) -> binding.ivResult.setImageResource(R.drawable.draw)
            isWin(option, computerOption) -> binding.ivResult.setImageResource(R.drawable.pemain_1_menang)
            else -> binding.ivResult.setImageResource(R.drawable.pemain_2_menang)
        }
    }

}