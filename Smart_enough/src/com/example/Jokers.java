package com.example;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Jokers extends Activity {
	
	Toast help;
	int rightAnswerNumber;
	int i, j;
	Random random = new Random();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jokers);
			
		help = Toast.makeText(getBaseContext(), "bad", Toast.LENGTH_LONG);
		rightAnswerNumber = InGame.getCurrentRightAnswerNumber();
		do{
			i = random.nextInt(4);
			j = random.nextInt(4);
		} while (i==rightAnswerNumber || j==rightAnswerNumber || i==j);
	}

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.audience:
			help.setText("Audience voted mostly for " + InGame.getCurrentRightAnswer());
			help.show();
			finish();
			break;
		case R.id.change:
			help.setText("In process to being fixed");
			help.show();
			finish();
			break;
		case R.id.fifty:
			fiftyButtonWork(i);
			fiftyButtonWork(j);
			finish();
			break;
		}
	}
	
	void fiftyButtonWork(int but){
		switch (but){
			case 0:
				InGame.N1.setEnabled(false);
				break;
			case 1:
				InGame.N2.setEnabled(false);
				break;
			case 2:
				InGame.N3.setEnabled(false);
				break;
			case 3:
				InGame.N4.setEnabled(false);
				break;
		}
	}
}