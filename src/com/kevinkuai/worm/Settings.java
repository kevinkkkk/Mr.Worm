package com.kevinkuai.worm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.kevinkuai.framework.FileIO;

public class Settings {
	
	public static boolean soundEnabled = true;
	public static int[] highscores = new int[]{100,80,50,30,10};
	
	public static void load(FileIO files){
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					files.readFile(".worm")));
			soundEnabled = Boolean.parseBoolean(in.readLine());
			for (int i = 0; i < 5; i++){
				highscores[i] = Integer.parseInt(in.readLine());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}catch (NumberFormatException e){
			
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				
			}
		}
		
		
	}

	public static void save(FileIO files){
		BufferedWriter out = null;
		try {
			out = new BufferedWriter (new OutputStreamWriter(
					files.writeFile(".worm")));
			out.write(Boolean.toString(soundEnabled));
			out.write("\n");
			for (int i=0; i<5; i++){
				out.write(Integer.toString(highscores[i]));
				out.write("\n");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (out!=null)
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					
				}
			
		}
		
	}
	
	public static void addScore(int score){
		for (int i=0; i<5; i++){
			if (highscores[i]<score){
				for (int j=4; j>i; j--){
					highscores[j]=highscores[j-1];
				}
				highscores[i]=score;
				break;
			}
		}
	}
}
