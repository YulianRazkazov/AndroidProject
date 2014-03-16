package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class XmlMainActivity extends Activity {
	
	int i=0, correct_answer,linescount=0, rightAnswersCounter=0;
	ArrayList<Integer> invalidNumbers = new ArrayList<Integer>();
	Button N1, N2, N3, N4;
	TextView Image, AnswerCounter;
	String all_info[];
	String line = null;
	AssetManager am;
	int questionIndex;
	boolean correct = true;
	String[] answers = new String[4];
	Random random = new Random();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xml_main);
		N1 = (Button) findViewById (R.id.A);
		N2 = (Button) findViewById (R.id.B);	
		N3 = (Button) findViewById (R.id.C);
		N4 = (Button) findViewById (R.id.D);
		Image = (TextView) findViewById(R.id.Image);
		AnswerCounter = (TextView) findViewById(R.id.AnswerCounter);
		AnswerCounter.setText(String.valueOf(rightAnswersCounter));
		try {
			countLines();
		} catch (IOException e) {
			e.printStackTrace();
		}
		all_info = new String[linescount];		
		readFile(all_info);
		init();
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
				if (invalidNumbers.size() < linescount/5) continue;
				else Image.setText("Game over !"); break;
			}else{
				Image.setText(all_info[questionIndex]);
				invalidNumbers.add(questionIndex);
				for(i=0;i<4;i++){
					answers[i] = all_info[questionIndex + i + 1];
				}
				break;
			}
		}	
	}
	
	private void setButtonText(){
		for(i=0;i<4;i++){
			if (answers[i].contains("+")){
				answers[i] = answers[i].substring(1);
				correct_answer = i;
			}
			System.out.println(correct_answer);
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
		}
	}
	
	private void shuffleArray(String[] answers2){
	    //Random rnd = new Random();
	    for (int i = answers2.length - 1; i > 0; i--){
	      int index = random.nextInt(i + 1);
	      String a = answers2[index];
	      answers2[index] = answers2[i];
	      answers2[i] = a;
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
	
	public void onClick(View v) {
		switch(v.getId()) {
	        case R.id.A:
	        	if (correct_answer == 0){
	        		init();
	        		rightAnswersCounter++;
	        		AnswerCounter.setText(String.valueOf(rightAnswersCounter));
	        	}else{
	        		Image.setText("Wrong");
	        		rightAnswersCounter = 0;
	        		AnswerCounter.setText(String.valueOf(rightAnswersCounter));
	        	}
	        	break;
	        case R.id.B:
	        	if (correct_answer == 1){
	        		init();
	        		rightAnswersCounter++;
	        		AnswerCounter.setText(String.valueOf(rightAnswersCounter));
	        	}else{
	        		Image.setText("Wrong");
	        		rightAnswersCounter = 0;
	        		AnswerCounter.setText(String.valueOf(rightAnswersCounter));
	        	}
	        	break;
	        case R.id.C:
	        	if (correct_answer == 2){
	        		init();
	        		rightAnswersCounter++;
	        		AnswerCounter.setText(String.valueOf(rightAnswersCounter));
	        	}else{
	        		Image.setText("Wrong");
	        		rightAnswersCounter = 0;
	        		AnswerCounter.setText(String.valueOf(rightAnswersCounter));
	        	}
	        	break;
	        case R.id.D:
	        	if (correct_answer == 3){
	        		init();
	        		rightAnswersCounter++;
	        		AnswerCounter.setText(String.valueOf(rightAnswersCounter));
	        	}else{
	        		Image.setText("Wrong");
	        		rightAnswersCounter = 0;
	        		AnswerCounter.setText(String.valueOf(rightAnswersCounter));
	        	}
	        	break;
		}
	}
}
