package com.example.ticktack_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.example.ticktack_app.databinding.ActivityMainBinding


const val EXTRA_TIME = "com.example.ticktack_app.TIME"
const val EXTRA_GAME_FIELD = "com.example.ticktack_app.GAME_FIELD"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Ticktackapp)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.toNewGame.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        binding.toContinueGame.setOnClickListener {
            val gameInfo = getInfoAboutLastGame()
            val intent = Intent(this, GameActivity::class.java).apply {
                putExtra(EXTRA_TIME, gameInfo.time)
                putExtra(EXTRA_GAME_FIELD, gameInfo.gameField)
            }
            startActivity(intent)
        }

        binding.toSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }

    private fun getInfoAboutLastGame() : GameInfo {
        with(getSharedPreferences("game", MODE_PRIVATE)){
            val time = getLong("time", 0)
            val gameField = getString("gameField", "")

            return if (gameField != null) {
                GameInfo(time, gameField)
            } else {
                GameInfo(0,"")
            }
        }
    }

    data class GameInfo(val time: Long, val gameField: String)
}
