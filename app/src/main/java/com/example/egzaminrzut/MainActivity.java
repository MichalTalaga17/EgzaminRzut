package com.example.egzaminrzut;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button start_btn = findViewById(R.id.start_btn);
        Button reset_btn = findViewById(R.id.reset_btn);
        TextView this_score = findViewById(R.id.this_score);
        TextView main_score = findViewById(R.id.score);
        ImageView img1 = findViewById(R.id.img1);
        ImageView img2 = findViewById(R.id.img2);
        ImageView img3 = findViewById(R.id.img3);
        ImageView img4 = findViewById(R.id.img4);
        ImageView img5 = findViewById(R.id.img5);

        ImageView[] img_spaces = {img1, img2, img3, img4, img5};

        start_btn.setOnClickListener(v -> start(img_spaces, this_score, main_score));
        reset_btn.setOnClickListener(v -> reset(this_score, main_score, img_spaces));
    }
    int main_score_val = 0;
    private void start(ImageView[] img_spaces, TextView this_score, TextView main_score) {
        int[] random_numbers = new int[5];
        int[] images = {
                R.drawable.k1, R.drawable.k2, R.drawable.k3,
                R.drawable.k4, R.drawable.k5, R.drawable.k6
        };

        Random random = new Random();
        for (int i = 0; i < random_numbers.length; i++) {
            random_numbers[i] = random.nextInt(6);
            img_spaces[i].setImageResource(images[random_numbers[i]]);
        }

        int score = 0;
        int[] counts = new int[6];

        for (int number : random_numbers) {
            counts[number]++;
        }

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] >= 2) {
                score += (i + 1) * counts[i];
            }
        }

        main_score_val += score;
        this_score.setText("Wynik tego losowania: " + score);
        main_score.setText("Wynik gry: " + main_score_val);
    }
    private void reset(TextView this_score, TextView main_score, ImageView[] img_spaces) {
        main_score_val = 0;
        this_score.setText("Wynik tego losowania: 0");
        main_score.setText("Wynik gry: 0");
        for (ImageView img : img_spaces) {
            img.setImageResource(R.drawable.question);
        }

    }


}