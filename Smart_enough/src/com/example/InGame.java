package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InGame extends Activity {
	
	public static Activity thisActivity;
	int i=0;
	Toast status;
	static int correct_answer;
	int linescount=0;
	static int rightAnswersCounter;
	ArrayList<Integer> invalidNumbers = new ArrayList<Integer>();
	static Button N1;
	static Button N2;
	static Button N3;
	static Button N4;
	TextView Question, StreakCounter;
	static String all_info[];
	String line = null;
	AssetManager am;
	static int questionIndex;
	boolean correct = true;
	static String[] answers = new String[4];
	Random random = new Random();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ingame);
		N1 = (Button) findViewById (R.id.A);
		N2 = (Button) findViewById (R.id.B);	
		N3 = (Button) findViewById (R.id.C);
		N4 = (Button) findViewById (R.id.D);
		Question = (TextView) findViewById(R.id.Question);
		StreakCounter = (TextView) findViewById(R.id.StreakCounter);
		StreakCounter.setText("Show your knowledge");
		rightAnswersCounter = 0;
		status = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT);
		status.setGravity(Gravity.TOP|Gravity.LEFT, 0, 45);
		try {
			countLines();
		} catch (IOException e) {
			e.printStackTrace();
		}
		all_info = new String[linescount];		
		readFile(all_info);
		init();
		thisActivity  = this;
	}
	
	protected void init(){
		i = 0;
		correct_answer = 5;
		
		// zima sledvashtiqt vupros s otgovorite kum nego
		getNextQuestion();
		
		// razburkva otgovorite
		shuffleArray(answers);
		
		//izkarva otgovorite vurhu butonite
		setButtonText();
		
		
	}
	
	private void getNextQuestion(){
		while (true){
			questionIndex = random.nextInt(linescount/5) *5;
			if (invalidNumbers.contains(questionIndex)){
				if (invalidNumbers.size() >= linescount/5){
					Intent myEndGameIntent = new Intent(InGame.this, EndGame.class);
					InGame.this.startActivity(myEndGameIntent);
					finish();
					break;
				}else continue;
			}else{
				Question.setText(all_info[questionIndex]);
				invalidNumbers.add(questionIndex);
				for(i=0;i<4;i++){
					answers[i] = all_info[questionIndex + i + 1];
				}
				break;
			}
		}	
	}
	
	private void shuffleArray(String[] answers2){
	    for (int i = answers2.length - 1; i > 0; i--){
	      int index = random.nextInt(i + 1);
	      String a = answers2[index];
	      answers2[index] = answers2[i];
	      answers2[i] = a;
	    }
	 }

	private void setButtonText(){
		for(i=0;i<4;i++){
			if (answers[i].contains("+")){
				answers[i] = answers[i].substring(1);
				correct_answer = i;
			}
			switch (i){
				case 0:
					N1.setText(answers[i]);
				case 1:
					N2.setText(answers[i]);
				case 2:
					N3.setText(answers[i]);
				case 3:
					N4.setText(answers[i]);
			}
			N1.setEnabled(true);
			N2.setEnabled(true);
			N3.setEnabled(true);
			N4.setEnabled(true);
		}
	}
	
	private void readFile(String[] lines){
		i = 0;
		try {
			am = getAssets();
			InputStream is = am.open("file.txt");
			InputStreamReader inputStreamReader = new InputStreamReader(is);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			while ((line=bufferedReader.readLine()) != null && i < linescount){
				lines[i] = line;
				i++;
			}
			bufferedReader.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void countLines() throws IOException {
		am = getAssets();
		InputStream is = am.open("file.txt");
		InputStreamReader inputStreamReader = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		linescount = 0;
		while (reader.readLine() != null) linescount++;
		reader.close();
	}
	
	@Override
	public void onBackPressed() {
		Intent myMenuIntent = new Intent(InGame.this, MenuEndGame.class);
		InGame.this.startActivity(myMenuIntent);
		return;
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
	        case R.id.A:
	        	if (correct_answer == 0){
	        		status.show();
	        		init();
	        		rightAnswersCounter++;
	        		StreakCounter.setText("Streak of " + String.valueOf(rightAnswersCounter));
	        	}else{
	        		goToAnsweredWrong();
	        	}
	        	break;
	        case R.id.B:
	        	if (correct_answer == 1){
	        		status.show();
	        		init();
	        		rightAnswersCounter++;
	        		StreakCounter.setText("Streak of " + String.valueOf(rightAnswersCounter));
	        	}else{
	        		goToAnsweredWrong();
	        	}
	        	break;
	        case R.id.C:
	        	if (correct_answer == 2){
	        		status.show();
	        		init();
	        		rightAnswersCounter++;
	        		StreakCounter.setText("Streak of " + String.valueOf(rightAnswersCounter));
	        	}else{
	        		goToAnsweredWrong();
	        	}
	        	break;
	        case R.id.D:
	        	if (correct_answer == 3){
	        		status.show();
	        		init();
	        		rightAnswersCounter++;
	        		StreakCounter.setText("Streak of " + String.valueOf(rightAnswersCounter));
	        	}else{
	        		goToAnsweredWrong();
	        	}
	        	break;
	        case R.id.Jokers:
	        	Intent myJokerIntent = new Intent(InGame.this, Jokers.class);
				InGame.this.startActivity(myJokerIntent);
	        	break;
	        case R.id.Menu:
	        	Intent myMenuIntent = new Intent(InGame.this, MenuEndGame.class);
				InGame.this.startActivity(myMenuIntent);
				break;
		}
	}
	
	void goToAnsweredWrong(){
		Intent myAnsweredWrongIntent = new Intent(InGame.this, AnsweredWrong.class);
		InGame.this.startActivity(myAnsweredWrongIntent);
		finish();
	}

	public static int getRightAnswersCounter() {
		return rightAnswersCounter;
	}
	
	public static String getCurrentQuestion(){
		return all_info[questionIndex];		
	}
	
	public static String getCurrentRightAnswer(){
		return answers[correct_answer];
	}
	
	public static int getCurrentRightAnswerNumber(){
		return correct_answer;
	}
	
}
