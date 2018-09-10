package com.example.coen.mad_w1_higher_lower

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random

class MainActivity : AppCompatActivity() {

    private var score: Int = 0
    private var highScore: Int = 0
    private var dice: Int = 0
    private var throws = ArrayList<Int>()

    private var dices = arrayOf(R.drawable.dice01, R.drawable.dice02, R.drawable.dice03,
            R.drawable.dice04, R.drawable.dice05, R.drawable.dice06)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setScore(score)
        setHighScore(highScore)
        throwDice()
        fab_higher.setOnClickListener { higherClick() }
        fab_lower.setOnClickListener { lowerClick() }
        rv_results.layoutManager = LinearLayoutManager(this)
    }

    private fun throwDice(): Int {
        dice = Random().nextInt(7 - 1) + 1
        iv_dice.setImageResource(dices[dice-1])
        addThrow()
        return dice
    }

    private fun setScore(value: Int) {
        tv_score.text = resources.getString(R.string.score, value)
    }

    private fun setHighScore(value: Int) {
        highScore = value
        tv_highscore.text = resources.getString(R.string.highscore, value)
    }

    private fun higherClick() {
        val prevThrow = dice
        throwDice()

        if (dice > prevThrow) {
            winAction()
        } else {
            loseAction()
        }
    }

    private fun lowerClick() {
        val prevThrow = dice
        throwDice()

        if (dice < prevThrow) {
            winAction()
        } else {
            loseAction()
        }
    }

    private fun winAction() {
        setScore(++score)
        if (score > highScore) {
            setHighScore(score)
        }

        snackbarMsg("You win")
    }

    private fun loseAction() {
        score = 0
        setScore(0)
        snackbarMsg("You lose")
    }


    private fun addThrow() {
        println("add throw: " + dice)
        throws.add(dice)
        rv_results.adapter = ListAdapter(throws, this)
    }

    private fun snackbarMsg(value: String) {
        Snackbar.make(fab_lower, value, Snackbar.LENGTH_SHORT).show()
    }
}
