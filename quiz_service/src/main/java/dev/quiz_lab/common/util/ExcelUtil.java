package dev.quiz_lab.common.util;

import dev.common_service.exception.BadRequestException;
import dev.common_service.exception.ErrorMessages;
import dev.quiz_lab.common.entity.Choice;
import dev.quiz_lab.common.constant.Const.*;
import dev.quiz_lab.common.entity.Question;
import dev.quiz_lab.common.entity.Quiz;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class ExcelUtil {
    public List<Question> getFromFile(byte[] data, Quiz quiz){
        quiz.setId(UUID.randomUUID());
        List<Question> questions = new ArrayList<>();
        try(Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(data))
        ) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();

            if(!rows.hasNext())
                throw new BadRequestException(ErrorMessages.NUMBER_OF_CHOICES_NOT_VALID);

            //Bỏ qua phần header dòng đầu
            rows.next();
            while (rows.hasNext()){
                Question question = getQuestionFromRow(rows.next());
                question.setQuiz(quiz);
                questions.add(question);
            }
        } catch (IOException e) {
            throw new BadRequestException(ErrorMessages.EXCEL_NOT_VALID);
        }

        return questions;
    }

    private Question getQuestionFromRow(Row row){
        Question result = new Question();

        String content = row.getCell(EXCEL_INPUT.CONTENT).toString();
        if(ObjectUtils.isEmpty(content))
            throw new BadRequestException(ErrorMessages.QUESTION_CONTENT_NOT_BLANK);
        result.setContent(content);
        result.setId(UUID.randomUUID());

        int numberOfChoices = 0;
        try {
            numberOfChoices = (int) Float.parseFloat(row.getCell(EXCEL_INPUT.NUMBER_OF_CHOICES)
                                                                    .toString().trim());

            if(numberOfChoices > EXCEL_INPUT.MAX_NUMBER_CHOICES){
                throw new BadRequestException(ErrorMessages.NUMBER_OF_CHOICES_TOO_MUCH);
            }
        } catch (NumberFormatException e){
            log.error("Index of answer is not integer", e);
            throw new BadRequestException(ErrorMessages.NUMBER_OF_CHOICES_NOT_VALID);
        }

        int indexOfAnswer = 0;
        try{
            indexOfAnswer = (int) Float.parseFloat(row.getCell(EXCEL_INPUT.INDEX_OF_ANSWER)
                    .toString().trim());

            if(indexOfAnswer > numberOfChoices || indexOfAnswer <= 0){
                throw new BadRequestException(ErrorMessages.INDEX_OF_ANSWER_NOT_VALID);
            }
        } catch (NumberFormatException e){
            log.error("Index of answer is not integer", e);
            throw new BadRequestException(ErrorMessages.INDEX_OF_ANSWER_NOT_VALID);
        }

        List<Choice> choices = new ArrayList<>();
        for(int index = EXCEL_INPUT.INDEX_OF_ANSWER + 1; index < EXCEL_INPUT.INDEX_OF_ANSWER + numberOfChoices + 1; index++){
            String choiceContent = row.getCell(index).toString();
            if(ObjectUtils.isEmpty(choiceContent))
                throw new BadRequestException(ErrorMessages.CONTENT_CHOICE_NOT_VALID);

            Choice choice = Choice.builder()
                                .id(UUID.randomUUID())
                                .content(choiceContent)
                                .question(result)
                                .build();
            choices.add(choice);
        }

        result.setTrueChoice(choices.get(indexOfAnswer - 1).getId());
        result.setChoices(choices);

        return result;
    }
}