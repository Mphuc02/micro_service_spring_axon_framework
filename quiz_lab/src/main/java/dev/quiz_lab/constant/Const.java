package dev.quiz_lab.constant;

import java.util.UUID;

public class Const {
    public static final class QUIZ_API{
        public static final String URL = "/api/v1/quiz";
        public static String PLAY_URL(UUID id){
            return String.format(URL + "/play/%s", id.toString());
        }
    }

    public static final class EXCEL_INPUT{
        public static final int CONTENT = 0;
        public static final int NUMBER_OF_CHOICES = 1;
        public static final int INDEX_OF_ANSWER = 2;
        public static final int MAX_NUMBER_CHOICES = 7;
    }
}