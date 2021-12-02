package com.example.myfirstquiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionsFragment extends Fragment {

    private int pageNumber;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    private RadioButton button4;
    private Button buttonHelp;
    private Use use;
    private TextView timer;
    private CountDownTimer myTimer;
    private boolean flag = false;
    private static int btnHelp=0;

    private String [][] mas = {{"0", "Какая сумма чисел в игральной кости?", "20", "21", "22", "23", "2"},
            {"1", "Росло четыре осины, на каждой по четыре больших ветки, на каждой большой ветке по четыре маленьких ветки, на каждой маленькой ветке по четыре яблока. Сколько всего яблок?", "256", "64", "16", "0", "4"},
            {"2", "Вы опередили лыжника, который находился на второй позиции. Какое месте теперь Вы занимаете?", "1", "2", "3", "4", "2"},
            {"3", "В названии какой конфеты чувствуется холод?", "Мороженое", "Холодец", "Барбариски", "Леденец", "4"},
            {"4", "Сколько яиц можно съесть натощак?", "3", "2", "1", "0", "3"},
            {"5", "Мальчик заплатил за бутылку с пробкой 11 рублей. Бутылка стоит на 10 рублей больше, чем пробка. Сколько стоит пробка?", "0 копеек", "50 копеек", "1 рубль", "1,5 рубля", "2"},
            {"6", "Стоит богатый дом и бедный. Они горят. Какой дом будет тушить полиция?", "Богатый", "Бедный", "Никакой", "Оба", "3"},
            {"7", "С какой птицы нужно ощипать перья, чтобы получились сразу утро, день, вечер и ночь?", "С голубя", "С курицы", "С утки", "С индюка", "3"},
            {"8", "Сколько будет 2+2*2?", "4", "6", "8", "10", "2"},
            {"9", ") У квадратного стола отпилили два угла по прямой линии. Сколько теперь углов у стола?", "4", "5", "6", "7", "3"},
            {"10", "Что тяжелее килограмм железа или килограмм пуха?", "Железеный пух", "Железо", "Пух", "Одинаково", "4"},
            {"11", "В парке 8 скамеек. Три покрасили. Сколько скамеек стало в парке?", "11", "5", "3", "8", "4"},
            {"12", "В светильнике было 20 лампочек, 5 из них перегорели. Сколько лампочек осталось?", "15", "5", "20", "25", "3"},
            {"13", "Самолет, пароход, воздушный шар, вертолет. Какое слово здесь лишнее?", "Самолет", " Пароход", " Воздушный шар", "Вертолет", "2"},
            {"14", "Половина от половины числа равна половине. Какое это число?", "16", "8", "4", "2", "4"}};

    public QuestionsFragment() {
        // Required empty public constructor
    }

    public static QuestionsFragment newInstance(int page) {
        QuestionsFragment fragment = new QuestionsFragment();
        Bundle args=new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_questions, container, false);
        timer(result);
        TextView question = result.findViewById(R.id.question);
        button1 = result.findViewById(R.id.button1);
        button2 = result.findViewById(R.id.button2);
        button3 = result.findViewById(R.id.button3);
        button4 = result.findViewById(R.id.button4);
        buttonHelp = result.findViewById(R.id.buttonHelp);
        Button buttonFinishTheGame = result.findViewById(R.id.buttonFinishTheGame);
        String que = mas[pageNumber][1];
        String b1 = mas[pageNumber][2];
        String b2 = mas[pageNumber][3];
        String b3 = mas[pageNumber][4];
        String b4 = mas[pageNumber][5];
        question.setText(que);
        button1.setText(b1);
        button2.setText(b2);
        button3.setText(b3);
        button4.setText(b4);
        buttonFinishTheGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FinalActivity.class);
                startActivity(intent);
            }
        });
        buttonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnHelp<3) {
                    int[] qwerty = new int[3];
                    for (int i = 0; i < qwerty.length; i++) {
                        boolean flag = false;
                        int q = (int) ((Math.random() * 4) + 1);
//                    qwerty[i] = q;
                        for (int y = 0; y < qwerty.length; y++) {
                            if (qwerty[y] == q) {
                                i--;
                                flag = true;
                            }
                        }
                        if (!flag) qwerty[i] = q;
                    }
                    int i = 0;
                    for (int z=0; z<qwerty.length; z++){
                        if (Integer.parseInt(mas[pageNumber][6]) != qwerty[z] & i < 2){
                            if (qwerty[z] == 1) button1.setVisibility(View.INVISIBLE);
                            if (qwerty[z] == 2) button2.setVisibility(View.INVISIBLE);
                            if (qwerty[z] == 3) button3.setVisibility(View.INVISIBLE);
                            if (qwerty[z] == 4) button4.setVisibility(View.INVISIBLE);
                            i++;
                        }
                    }
                    btnHelp++;
                }
            }
        });
        RadioGroup radGrp = result.findViewById(R.id.answers);
        radGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                use = new Use();
                int i;
                switch(checkedId){
                    case R.id.button1:
                        i=1;
                        method1(i);
                        break;
                    case R.id.button2:
                        i=2;
                        method1(i);
                        break;
                    case R.id.button3:
                        i=3;
                        method1(i);
                        break;
                    case R.id.button4:
                        i=4;
                        method1(i);
                        break;
                    default:
                        break;
                }
            }
        });
        return result;
    }

    public void timer(View result){
        timer = result.findViewById(R.id.timer);
        myTimer = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(Long.toString(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {

            }

        };
        myTimer.start();
    }

    public void method1(int i){
        if (method2(i) == 1 & !flag) {
            use.setScores(use.getScores()+1);
            flag = true;
        }
        else if (method2(i) == 0 & flag){
            use.setScores(use.getScores()-1);
            flag = false;
        }
    }

    public int method2(int i){
        int q = -1;
        if (Integer.parseInt(mas[pageNumber][0]) == Integer.parseInt(mas[0][0])) {
            if (Integer.parseInt(mas[0][6]) == i) q=1;
            else q=0;
        }
        if (Integer.parseInt(mas[pageNumber][0]) == Integer.parseInt(mas[1][0])) {
            if (Integer.parseInt(mas[1][6]) == i) q=1;
            else q=0;
        }
        if (Integer.parseInt(mas[pageNumber][0]) == Integer.parseInt(mas[2][0])) {
            if (Integer.parseInt(mas[2][6]) == i) q=1;
            else q=0;
        }
        if (Integer.parseInt(mas[pageNumber][0]) == Integer.parseInt(mas[3][0])) {
            if (Integer.parseInt(mas[3][6]) == i) q=1;
            else q=0;
        }
        if (Integer.parseInt(mas[pageNumber][0]) == Integer.parseInt(mas[4][0])) {
            if (Integer.parseInt(mas[4][6]) == i) q=1;
            else q=0;
        }
        if (Integer.parseInt(mas[pageNumber][0]) == Integer.parseInt(mas[4][0])) {
            if (Integer.parseInt(mas[5][6]) == i) q=1;
            else q=0;
        }
        if (Integer.parseInt(mas[pageNumber][0]) == Integer.parseInt(mas[4][0])) {
            if (Integer.parseInt(mas[6][6]) == i) q=1;
            else q=0;
        }
        if (Integer.parseInt(mas[pageNumber][0]) == Integer.parseInt(mas[4][0])) {
            if (Integer.parseInt(mas[7][6]) == i) q=1;
            else q=0;
        }
        if (Integer.parseInt(mas[pageNumber][0]) == Integer.parseInt(mas[4][0])) {
            if (Integer.parseInt(mas[8][6]) == i) q=1;
            else q=0;
        }
        if (Integer.parseInt(mas[pageNumber][0]) == Integer.parseInt(mas[4][0])) {
            if (Integer.parseInt(mas[9][6]) == i) q=1;
            else q=0;
        }
        if (Integer.parseInt(mas[pageNumber][0]) == Integer.parseInt(mas[4][0])) {
            if (Integer.parseInt(mas[10][6]) == i) q=1;
            else q=0;
        }
        if (Integer.parseInt(mas[pageNumber][0]) == Integer.parseInt(mas[4][0])) {
            if (Integer.parseInt(mas[11][6]) == i) q=1;
            else q=0;
        }
        if (Integer.parseInt(mas[pageNumber][0]) == Integer.parseInt(mas[4][0])) {
            if (Integer.parseInt(mas[12][6]) == i) q=1;
            else q=0;
        }
        if (Integer.parseInt(mas[pageNumber][0]) == Integer.parseInt(mas[4][0])) {
            if (Integer.parseInt(mas[13][6]) == i) q=1;
            else q=0;
        }
        if (Integer.parseInt(mas[pageNumber][0]) == Integer.parseInt(mas[4][0])) {
            if (Integer.parseInt(mas[14][6]) == i) q=1;
            else q=0;
        }
        return q;
    }

    public void help(View view){
        button4.setVisibility(view.INVISIBLE);
//        int i = 0;
//        if (Integer.parseInt(mas[pageNumber][6]) != 1 & i < 3){
//            button1.setVisibility(View.INVISIBLE);
//            i++;
//        }
//        if (Integer.parseInt(mas[pageNumber][6]) != 2 & i < 3){
//            button2.setVisibility(View.INVISIBLE);
//            i++;
//        }
//        if (Integer.parseInt(mas[pageNumber][6]) != 3 & i < 3){
//            button3.setVisibility(View.INVISIBLE);
//            i++;
//        }
//        if (Integer.parseInt(mas[pageNumber][6]) != 4 & i < 3){
//            button4.setVisibility(View.INVISIBLE);
//            i++;
//        }
    }

}