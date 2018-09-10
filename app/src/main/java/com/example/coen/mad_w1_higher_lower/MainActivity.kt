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
        fab_higher.setOnClickListener { higherClick() }
        fab_lower.setOnClickListener { lowerClick() }
        rv_results.layoutManager = LinearLayoutManager(this)

        // Declare inition scores
        setScore(score)
        setHighScore(highScore)

        // Throuw dice to start the first round
        throwDice()
    }

    /**
     * Throw the dice and set the correct image resource.
     */
    private fun throwDice(): Int {
        val newThrow = Random().nextInt(7-1)+1
        dice = if (newThrow != dice) newThrow else throwDice()
        iv_dice.setImageResource(dices[dice-1])
        addThrow()
        return dice
    }

    /**
     * Set the score text in the view.
     */
    private fun setScore(value: Int) {
        tv_score.text = resources.getString(R.string.score, value)
    }

    /**
     * Set the highscore text in the view
     */
    private fun setHighScore(value: Int) {
        highScore = value
        tv_highscore.text = resources.getString(R.string.highscore, value)
    }

    /**
     * Play round.
     * Triggered on higher button.
     */
    private fun higherClick() {
        // store previous throw and throw the dice.
        val prevThrow = dice
        throwDice()

        // If the new throw is higher than the previous trigger a win
        if (dice > prevThrow) winAction()
        else loseAction()
    }

    /**
     * Play round.
     * Triggered on lower button.
     */
    private fun lowerClick() {
        val prevThrow = dice
        throwDice()

        // If the new throw is lower than the previous trigger a win
        if (dice < prevThrow) winAction()
        else loseAction()
    }

    /**
     * Increase the score and notify the user that the round is won.
     */
    private fun winAction() {
        setScore(++score)
        // Update if new highscore is reached
        if (score > highScore) setHighScore(score)
        snackbarMsg("You win")
    }

    /**
     * Reset the score and notify the user.
     */
    private fun loseAction() {
        score = 0
        setScore(0)
        snackbarMsg("You lose")
    }

    /**
     * Add new throw to the list.
     */
    private fun addThrow() {
        println("add throw: " + dice)
        throws.add(0, dice)
        rv_results.adapter = ListAdapter(throws, this)
    }

    /**
     * Use the snackbar to display messages.
     */
    private fun snackbarMsg(value: String) {
        Snackbar.make(fab_lower, value, Snackbar.LENGTH_SHORT).show()
    }
}
